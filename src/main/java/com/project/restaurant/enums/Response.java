package com.project.restaurant.enums;

import lombok.Getter;

/**
 * Defining the enum
 *
 * @author xuanmai
 * @since 2023-11-22
 */
public class Response {

    //====== ADD 2023/11/22 xuanmai START ======//
    @Getter
    public static enum Key {
        STATUS;
    }

    @Getter
    public static enum Value {
        FAILURE,
        SUCCESSFULLY,
        DUPLICATED,
        NOT_FOUND,
        INVALID_IMAGES_FILE,
        INVALID_OTP,
        INVALID_VALUE,
        NOT_ACCEPTABLE;
    }
    //====== ADD 2023/11/22 xuanmai END ======//
}