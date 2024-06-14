package com.bagas.constants;

public class CommonConstants {

    public static final String ID_PATH_VARIABLE = "id";

    public static final String MAIN_DOMAIN = "/inno-shop";

    public static final String PRODUCTS_ENDPOINT = "/products";

    public static final String CATEGORY_ENDPOINT = "/categories";

    public static final String SUBCATEGORY_ENDPOINT = "/subcategories";

    public static final String GROUP_ENDPOINT = "/groups";

    public static final String CREATE_ENDPOINT = "/create";

    public static final String DELETE_ENDPOINT = "/delete";

    public static final String ID_ENDPOINT = "/{id}";

    public static final String UPDATE_ENDPOINT = "/update";

    public static final String WHOLE_CREATE_ENDPOINT = MAIN_DOMAIN + CREATE_ENDPOINT + "/**";

    public static final String WHOLE_UPDATE_ENDPOINT = MAIN_DOMAIN + UPDATE_ENDPOINT + "/**";

    public static final String WHOLE_DELETE_ENDPOINT = MAIN_DOMAIN + DELETE_ENDPOINT + "/**";

    public static final String WHOLE_GROUP_ENDPOINT = MAIN_DOMAIN + GROUP_ENDPOINT + "/**";

    public static final String WHOLE_PRODUCT_ENDPOINT = MAIN_DOMAIN + PRODUCTS_ENDPOINT + "/**";

    public static final String WHOLE_SUBCATEGORY_ENDPOINT = MAIN_DOMAIN + SUBCATEGORY_ENDPOINT + "/**";

    public static final String WHOLE_CATEGORY_ENDPOINT = MAIN_DOMAIN + CATEGORY_ENDPOINT + "/**";

    public static final String GET_PRODUCT_BY_ID = PRODUCTS_ENDPOINT + ID_ENDPOINT;

    public static final String CREATE_GROUP_ENDPOINT = CREATE_ENDPOINT + GROUP_ENDPOINT;

    public static final String CREATE_CATEGORY_ENDPOINT = CREATE_ENDPOINT + CATEGORY_ENDPOINT;

    public static final String CREATE_SUBCATEGORY_ENDPOINT = CREATE_ENDPOINT + SUBCATEGORY_ENDPOINT;

    public static final String CREATE_PRODUCT_ENDPOINT = CREATE_ENDPOINT + PRODUCTS_ENDPOINT;

    public static final String UPDATE_GROUP_ENDPOINT = UPDATE_ENDPOINT + GROUP_ENDPOINT + ID_ENDPOINT;

    public static final String UPDATE_CATEGORY_ENDPOINT = UPDATE_ENDPOINT + CATEGORY_ENDPOINT + ID_ENDPOINT;

    public static final String UPDATE_SUBCATEGORY_ENDPOINT = UPDATE_ENDPOINT + SUBCATEGORY_ENDPOINT + ID_ENDPOINT;

    public static final String UPDATE_PRODUCT_ENDPOINT = UPDATE_ENDPOINT + PRODUCTS_ENDPOINT + ID_ENDPOINT;

    public static final String DELETE_GROUP_ENDPOINT = DELETE_ENDPOINT + GROUP_ENDPOINT + ID_ENDPOINT;

    public static final String DELETE_CATEGORY_ENDPOINT = DELETE_ENDPOINT + CATEGORY_ENDPOINT + ID_ENDPOINT;

    public static final String DELETE_SUBCATEGORY_ENDPOINT = DELETE_ENDPOINT + SUBCATEGORY_ENDPOINT + ID_ENDPOINT;

    public static final String DELETE_PRODUCT_ENDPOINT = DELETE_ENDPOINT + PRODUCTS_ENDPOINT + ID_ENDPOINT;

    public static final String PRODUCTS_BY_SUBCATEGORY_ENDPOINT = "/products-by-subcategory";

    public static final String WHOLE_PRODUCTS_BY_SUBCATEGORY_ENDPOINT = MAIN_DOMAIN + "/products-by-subcategory/**";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEGINNING_AUTH_HEADER = "Bearer";

    public static final String SECRET_KEY = "c249d0b92ede74cf785d9bc00d39b9ebf393117c212706af54b5520ab8467a28";

    public static final String IP_CLAIM = "ip";

    public static final String ROLES_CLAIM = "roles";

    public static final String SWAGGER_UI_ENDPOINTS = "/swagger-ui/**";

    public static final String SWAGGER_DOCS_ENDPOINTS = "/v3/api-docs/**";
}
