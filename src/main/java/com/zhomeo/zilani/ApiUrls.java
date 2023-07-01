package com.zhomeo.zilani;

public interface ApiUrls {

    public static final String ROOT_URL_USERS = "/api/users";
    public static final String URL_USERS_USER = "/{userId}";
    public static final String URL_USERS_USER_PROFILE = "/profile";
    public static final String URL_USERS_USER_CHANGE_PASSWORD = "/changePassword";
    public static final String URL_USERS_USER_FORGOT_PASSWORD = "/forgotPassword";

    String ROOT_URL_VENDORS = "/api/vendors";
    String URL_VENDORS_VENDOR= "/{vendorId}";

    String ROOT_URL_MEDICINE_CATEGORIES = "/api/medicineCategories";
    String URL_MEDICINE_CATEGORIES_MEDICINE_CATEGORY = "/{medicineCategoryId}";

    String ROOT_URL_MEDICINE_POWERS = "/api/medicinePowers";
    String URL_MEDICINE_POWERS_MEDICINE_POWER= "/{medicinePowerId}";

    String ROOT_URL_MEDICINE_BRANDS = "/api/medicineBrands";
    String URL_MEDICINE_BRANDS_MEDICINE_BRAND= "/{medicineBrandId}";
}
