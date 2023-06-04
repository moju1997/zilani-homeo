package com.zhomeo.zilani.entity;

import com.jazasoft.embedded.entity.AbstractUser;
import com.jazasoft.validation.FixedEnumValue;
import com.zhomeo.zilani.Group;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User extends AbstractUser {

  @Transient
  @FixedEnumValue(enumClass = Group.class)
  private String groups;

  public User() {
  }

  public User(String fullName, String username, String email, String password, String mobile) {
    super(fullName, username, email, password, mobile);
  }

  public String getGroups() {
    return groups;
  }

  public void setGroups(String groups) {
    this.groups = groups;
  }
}
