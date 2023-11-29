package com.project.restaurant.utils;

/**
 * Declaring all constants
 *
 * @author xuanmai
 * @since 2023-11-22
 */
public class Constants {

    //====== ADD 2023/11/22 xuanmai START ======//
    public static final String DATA_ID_NOT_FOUND = "data.id.notFound";
    public static final String DATA_NOT_FOUND = "data.notFound";
    public static final String DATA_SAVE_SUCCESSFULLY = "data.save.successfully";
    public static final String DATA_SAVE_FAILED = "data.save.failed";
    public static final String DATA_DELETE_SUCCESSFULLY = "data.delete.successfully";
    public static final String DATA_DELETE_FAILED = "data.delete.failed";
    public static final String SERVER_NAME = "server.name";

    /**
     * Expire duration for JWT
     */
    public static final int EXPIRE_DURATION = 36000;
    public static final String ADMIN = "admin";
    public static final String MOD = "moderator";
    public static final String USER = "user";
    public static final String USERNAME_NOT_FOUND = "username.notFound";
    public static final String AUTHORIZATION_TYPE = "Bearer";
    public static final String USERNAME_EXIST = "username.exist";
    public static final String EMAIL_EXIST = "email.exist";
    public static final String ROLE_NOT_FOUND = "role.notFound";
    public static final String EMAIL_MESSAGE = "email.message";
    public static final String EMAIL_SUBJECT = "email.subject";
    public static final String EMAIL_NOT_FOUND = "email.notFound";
    public static final String RESTAURANT = "restaurant";

    /**
     * Validation of Promotion
     */
    public static final String MAXIMUM_PRICE_DISCOUNT = "The highest price for the percentage discount promotion is invalid";
    public static final String PERCENTAGE_PROMOTION = "The reduced price is invalid";
    public static final String MINIMUM_ORDER_VALUE_PROMOTION = "Minimum order value is invalid";
    public static final String PROMOTION_CODE_MAX_MIN_CHARACTER = "promotion.validation.max-26-min-1-characters";
    public static final String PROMOTION_MAX_MIN_CHARACTERS = "promotion.validation.max-2000-min-1-characters";

    /**
     * Validation of Product
     */
    public static final String NOT_REQUIRE = "not-require";
    public static final String PRODUCT_MAX_MIN_CHARACTERS = "product.validation.min-1-max-1000-characters";
    public static final String PRODUCT_NAME_MAX_CHARACTERS = "product.name.validation.max-255-characters";
    public static final String PRODUCT_PRICE_MIN_VALUE = "product.validation.greater-than-or-equal-to-0";
    public static final String PRODUCT_PRICE_MAX_VALUE = "product.validation.cannot-exceed-999999.99";
    public static final String PRODUCT_QUANTITY_MIN_VALUE = "product.price.validation.min-0";
    public static final String PRODUCT_QUANTITY_MAX_VALUE = "product.price.validation.max-999999";
    public static final String PRODUCT_CONDITION_MAX_MIN_CHARACTERS = "product.condition.validation.min-1-max-20-characters";

    //====== 2023/11/22 xuanmai END ======//

}
