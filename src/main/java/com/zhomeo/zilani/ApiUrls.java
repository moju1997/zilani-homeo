package com.zhomeo.zilani;

public interface ApiUrls {

    String VERSION_1 = "/v1";

    public static final String ROOT_URL_USERS = "/api/users";
    public static final String URL_USERS_USER = "/{userId}";
    public static final String URL_USERS_USER_PROFILE = "/profile";
    public static final String URL_USERS_USER_CHANGE_PASSWORD = "/changePassword";
    public static final String URL_USERS_USER_FORGOT_PASSWORD = "/forgotPassword";

    String ROOT_URL_BUYERS = "/api/buyers";
    String URL_BUYERS_BUYER= "/{buyerId}";
}
