package com.zhomeo.zilani.service;

import com.jazasoft.embedded.service.EmailService;
import com.jazasoft.embedded.service.IUserService;
import com.jazasoft.embedded.service.SmsService;
import com.jazasoft.util.Assert;
import com.jazasoft.util.CommonUtils;
import com.jazasoft.util.DateUtils;
import com.jazasoft.util.Utils;
import com.zhomeo.zilani.entity.User;
import com.zhomeo.zilani.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
  private final Logger logger = LoggerFactory.getLogger(UserService.class);


  private final UserRepository userRepository;
  private final EmailService emailService;
  private final SmsService smsService;

  public UserService(UserRepository userRepository, EmailService emailService, SmsService smsService) {
    this.userRepository = userRepository;
    this.emailService = emailService;
    this.smsService = smsService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.trace("Looking for user for {}", username);
    try {
      User user = userRepository.findOneByUsername(username);
      if (user == null) {
        user = userRepository.findOneByEmail(username);
        if (user == null) {
          logger.info("User not found for {}", username);
          throw new UsernameNotFoundException("user not found");
        }
      }
      logger.trace("Found user for {}", username);
      return user;
    } catch (Exception e) {
      logger.error("Error loading user {}", username, e);
    }
    return null;
  }

  @Override
  public User findByUsername(String username) {
    logger.debug("findByUsername(): username = {}", username);
    User user = userRepository.findOneByUsername(username);
    return user;
  }

  @Override
  public User findByEmail(String email) {
    logger.debug("findByEmail(): email = {}", email);
    return userRepository.findOneByEmail(email);
  }

  public User findOne(Long id) {
    logger.debug("findOne(): id = {}", id);
    User mUser = userRepository.findById(id).orElse(null);
    return mUser;

  }

  public List<User> findAll() {
    logger.debug("findAll()");
    return userRepository.findAll();
  }

  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  public Page<User> findAll(Specification<User> spec, Pageable pageable) {
    return userRepository.findAll(spec, pageable);
  }


  public Boolean exists(Long id) {
    logger.debug("exists(): id = {}", id);
    return userRepository.existsById(id);
  }

  public boolean exists(String username) {
    return userRepository.findOneByUsernameOrEmail(username, username) != null;
  }

  public Long count() {
    logger.debug("count()");
    return userRepository.count();
  }

  @Transactional
  public User save(User user) {
    logger.debug("save()");
    user.setRoles("ADMIN");
    if (user.getPassword() == null){
      user.setPassword(user.getMobile());
    }
    user.setAccountExpired(false);
    user.setAccountLocked(false);
    user.setEnabled(true);
    user.setCredentialExpired(false);

    return userRepository.save(user);
  }

  @Transactional
  public User update(User user) {
    logger.debug("update()");
    User mUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));

    mUser.setFullName(user.getFullName());
    mUser.setUsername(user.getUsername());
    mUser.setEmail(user.getEmail());
    mUser.setMobile(user.getMobile());

    return mUser;
  }


  @Transactional
  public void delete(Long id) {
    logger.debug("delete(): id = {}", id);
    userRepository.deleteById(id);
  }

  @Transactional
  public boolean changePassword(String username, String oldPassword, String newPassword) {
    User user = userRepository.findOneByUsername(username);
    if (user == null) {
      user = userRepository.findOneByEmail(username);
      if (user == null) return false;
    }

    String idForEncode = "bcrypt";
    Map encoders = new HashMap();
    encoders.put(idForEncode, new BCryptPasswordEncoder());
    PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(idForEncode, encoders);
    if (passwordEncoder.matches(oldPassword, user.getPassword())) {
      user.setPassword(newPassword);
      return true;
    }
    return false;
  }

  @Transactional
  public void resetPassword(String username) {
    User user = userRepository.findOneByUsernameOrEmail(username, username);
    String resetPassword = CommonUtils.randomAlphaNumericString(10);
    String email = user.getEmail();
    Assert.notNull(email, "Email is required for Password reset.");
    String subject = "Password Reset";
    String message = "Hello, " + user.getFullName() + "\n\n" +
        "New Password: " + resetPassword + "\n\n" +
        "Please, Do change your password." + "\n\n\n" +
        "Jaza Software (OPC) Private Limited.";
    boolean result = emailService.sendSimpleEmail(new String[]{email}, subject, message);
    if (result) {
      user.setPassword(resetPassword);
    }
  }

  @Transactional
  public Map<String, String> forgotPassword(String action, String username, String userOtp, String newPassword) {
    User user = userRepository.findOneByUsername(username);
    Assert.isTrue(user.getMobile() != null || user.getEmail() != null, "Either mobile or email is required for resetting password.");

    Map<String, String> result = new HashMap<>();

    if (action.equalsIgnoreCase("send_otp") || action.equalsIgnoreCase("resend_otp")) {
      String otp;
      if (action.equalsIgnoreCase("send_otp")) {
        otp = Utils.getRandomNumber(6);
        user.setOtp(otp);
        user.setOtpGeneratedAt(new Date());
      } else {
        otp = user.getOtp();
      }

      String message = "OTP to change password for user - " + username + " is: " + otp;
      if (user.getMobile() != null) {
        smsService.sendSMS(user.getMobile(), message);
      }
      if (user.getEmail() != null) {
        emailService.sendSimpleEmail(new String[]{user.getEmail()}, "OTP for password change", message);
      }
      result.put("status", "SUCCESS");
      result.put("message", "OTP has been sent registered mobile and email");
    } else if (action.equalsIgnoreCase("confirm_otp")) {
      if (user.getOtp() != null && user.getOtp().equals(userOtp)) {
        result.put("status", "SUCCESS");
        result.put("message", "OTP matched");
      } else {
        result.put("status", "FAILED");
        result.put("message", "Invalid OTP.");
      }
    } else if (action.equalsIgnoreCase("change_password")) {
      Assert.notNull(newPassword, "New Password not provided");
      if (user.getOtp() != null && user.getOtp().equals(userOtp)) {

        if (LocalDateTime.now().isBefore(DateUtils.toLocalDateTime(user.getOtpGeneratedAt()).plusMinutes(5))) {
          user.setPassword(newPassword);

          result.put("status", "SUCCESS");
          result.put("message", "Password has been change successfully.");
        } else {
          result.put("status", "FAILED");
          result.put("message", "OTP expired");
        }
        user.setOtp(null);
        user.setOtpGeneratedAt(null);
      } else {
        result.put("status", "FAILED");
        result.put("message", "OTP did not match.");
      }
    }
    return result;
  }
}
