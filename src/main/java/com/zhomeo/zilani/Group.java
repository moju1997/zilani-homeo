package com.zhomeo.zilani;

public enum Group {
  ADMIN("admin"),
  USER("user");

  private String value;

  Group(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static Group parse(String value) {
    Group group = null;
    for (Group item : Group.values()) {
      if (item.getValue().equals(value)) {
        group = item;
        break;
      }
    }
    return group;
  }
}
