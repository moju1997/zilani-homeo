package com.zhomeo.zilani.repository;


import com.zhomeo.zilani.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  User findOneByUsername(String username);

  User findOneByUsernameOrEmail(String username, String email);

  User findOneByEmail(String email);
}
