package com.bagas.constants;

import static com.bagas.constants.CommonConstants.CATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.CREATE_CATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.CREATE_GROUP_ENDPOINT;
import static com.bagas.constants.CommonConstants.CREATE_PRODUCT_ENDPOINT;
import static com.bagas.constants.CommonConstants.CREATE_SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.DELETE_ENDPOINT;
import static com.bagas.constants.CommonConstants.GROUP_ENDPOINT;
import static com.bagas.constants.CommonConstants.ID_ENDPOINT;
import static com.bagas.constants.CommonConstants.MAIN_DOMAIN;
import static com.bagas.constants.CommonConstants.PRODUCTS_ENDPOINT;
import static com.bagas.constants.CommonConstants.SUBCATEGORY_ENDPOINT;
import static com.bagas.constants.CommonConstants.UPDATE_ENDPOINT;
import static com.bagas.constants.CommonConstants.UPDATE_PRODUCT_ENDPOINT;

public class CommonTestConstants {

    public static String TEST_CREATE_PRODUCT_ENDPOINT = MAIN_DOMAIN + CREATE_PRODUCT_ENDPOINT;

    public static String TEST_CREATE_SUBCATEGORY_ENDPOINT = MAIN_DOMAIN + CREATE_SUBCATEGORY_ENDPOINT;

    public static String TEST_CREATE_CATEGORY_ENDPOINT = MAIN_DOMAIN + CREATE_CATEGORY_ENDPOINT;

    public static String TEST_CREATE_GROUP_ENDPOINT = MAIN_DOMAIN + CREATE_GROUP_ENDPOINT;

    public static String TEST_UPDATE_PRODUCT_ENDPOINT = MAIN_DOMAIN + "/update" + PRODUCTS_ENDPOINT + "/%s";

    public static String TEST_UPDATE_SUBCATEGORY_ENDPOINT = MAIN_DOMAIN + "/update" + SUBCATEGORY_ENDPOINT + "/%s";

    public static String TEST_UPDATE_CATEGORY_ENDPOINT = MAIN_DOMAIN + "/update" + CATEGORY_ENDPOINT + "/%s";

    public static String TEST_UPDATE_GROUP_ENDPOINT = MAIN_DOMAIN + "/update" + GROUP_ENDPOINT + "/%s";

    public static String TEST_DELETE_PRODUCT_ENDPOINT = MAIN_DOMAIN + DELETE_ENDPOINT + PRODUCTS_ENDPOINT + "/%d";

    public static String TEST_DELETE_SUBCATEGORY_ENDPOINT = MAIN_DOMAIN + DELETE_ENDPOINT + SUBCATEGORY_ENDPOINT + "/%d";

    public static String TEST_DELETE_CATEGORY_ENDPOINT = MAIN_DOMAIN + DELETE_ENDPOINT + CATEGORY_ENDPOINT + "/%d";

    public static String TEST_DELETE_GROUP_ENDPOINT = MAIN_DOMAIN + DELETE_ENDPOINT + GROUP_ENDPOINT + "/%d";

    public static String TEST_PRODUCTS_BY_SUBCATEGORY_ENDPOINT = MAIN_DOMAIN + "/products-by-subcategory/%d";
}
