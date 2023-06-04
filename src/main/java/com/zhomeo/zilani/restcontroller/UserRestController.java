package com.zhomeo.zilani.restcontroller;

import com.jazasoft.embedded.dto.RestError;
import com.jazasoft.embedded.specification.CustomRsqlVisitor;
import com.zhomeo.zilani.ApiUrls;
import com.zhomeo.zilani.entity.User;
import com.zhomeo.zilani.service.UserService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping(ApiUrls.VERSION_1 + ApiUrls.ROOT_URL_USERS)
public class UserRestController {
    private final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> findAllUsers(@RequestParam(value = "search", defaultValue = "") String search, Pageable pageable, HttpServletRequest request) {
        logger.debug("findAllUsers()");
        Page<User> users;
        if (search.trim().isEmpty()) {
            users = userService.findAll(pageable);
        } else {
            Node rootNode = new RSQLParser().parse(search);
            Specification<User> spec = rootNode.accept(new CustomRsqlVisitor<>());
            users = userService.findAll(spec, pageable);
        }
        return ResponseEntity.ok(users);
    }


    @GetMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<?> findOneUser(@PathVariable("userId") long id) {
        logger.debug("findOneUser(): id = {}", id);
        User user = userService.findOne(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, HttpServletRequest request) {
        user = userService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }


    @SuppressWarnings("unchecked")
    @PutMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<?> updateUser(@PathVariable("userId") long id, @Valid @RequestBody User user) {
        logger.debug("updateUser(): id = {}", id);
        if (!userService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        return ResponseEntity.ok(userService.update(user));
    }

    /**
     * User cannot be deleted but can be: deactivated
     *
     * @param id
     * @return
     */
    @DeleteMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") long id) {
        logger.debug("deleteUser(): id = {}", id);
        if (!userService.exists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(ApiUrls.URL_USERS_USER_PROFILE)
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String username = (String) request.getAttribute("user_name");
        logger.debug("get Profile(): username = {}", username);
        User user = userService.findByUsername(username);
        if (user == null) {
            user = userService.findByEmail(username);
        }
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping(ApiUrls.URL_USERS_USER_CHANGE_PASSWORD)
    public ResponseEntity<?> changePassword(@RequestParam("username") String username,
                                            @RequestParam("oldPassword") String oldPassword,
                                            @RequestParam("newPassword") String newPassword) {
        logger.debug("changePassword: username = {}", username);
        if (!userService.exists(username)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean result = userService.changePassword(username, oldPassword, newPassword);
        Map<String, String> resp = new HashMap<>();
        if (result) {
            resp.put("status", "SUCCESS");
            resp.put("message", "Password Changed Successfully.");
            return ResponseEntity.ok(resp);
        } else {
            resp.put("status", "FAIL");
            resp.put("message", "Incorrect Credential. Password change failed.");
            return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping(ApiUrls.URL_USERS_USER_FORGOT_PASSWORD)
    public ResponseEntity<?> forgotPassword(@RequestParam(value = "action") String action,
                                            @RequestParam("username") String username,
                                            @RequestParam(value = "otp", defaultValue = "") String otp,
                                            @RequestParam(value = "newPassword", defaultValue = "") String newPassword) {
        Pattern pattern = Pattern.compile("send_otp|resend_otp|confirm_otp|change_password");
        if (!pattern.matcher(action).matches()) {
            RestError error = new RestError(400, 40003, "Unsupported action. Action can only be " + pattern.pattern());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        if (!userService.exists(username)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Map<String, String> resp = userService.forgotPassword(action, username, otp, newPassword);
        return ResponseEntity.ok(resp);
    }
}
