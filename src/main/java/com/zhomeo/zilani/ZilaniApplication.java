package com.zhomeo.zilani;


import com.jazasoft.embedded.AbstractApp;
import com.jazasoft.embedded.IConstants;
import com.zhomeo.zilani.entity.User;
import com.zhomeo.zilani.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@ComponentScan(basePackages = {"com.jazasoft.embedded","com.zhomeo.zilani"})
public class ZilaniApplication extends AbstractApp {

    public static void main(String[] args) {
        SpringApplication.run(ZilaniApplication.class, args);
    }

    @RequestMapping("/")
    public String welcome() {
        return "Welcome";
    }

    @Bean
    CommandLineRunner init(UserService userService) {
        return (args) -> {
            if (userService.count() == 0) {
                User user = new User("Md Zahid Raza", "zahid7292", "zahid7292@gmail.com", "Jaza@2008", "890430418");
                user.setRoles(IConstants.ROLE_ADMIN);
                userService.save(user);
            }
        };
    }

}
