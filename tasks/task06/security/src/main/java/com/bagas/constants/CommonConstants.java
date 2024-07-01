package com.bagas.constants;

public class CommonConstants {

    public static final String BEGINNING_AUTH_HEADER = "Bearer";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String LOGIN_SUCCESSFUL_MESSAGE = "User login was successful";

    public static final String REGISTRATION_SUCCESSFUL_MESSAGE = "User registration was successful";

    public static final String UNAUTHORIZED_MESSAGE = "You aren't authorized";

    public static final String TOKEN_GENERATED_MESSAGE = "New token generated";

    public static final long DEFAULT_ACCESS_TOKEN_EXPIRED_TIME = 1000 * 60 * 500;

    public static final long DEFAULT_REFRESH_EXPIRED_TIME = 1000 * 60 * 60 * 24;

   // public static final String SECRET_KEY = System.getenv("SECRET_KEY");

    public static final String SECRET_KEY = "c249d0b92ede74cf785d9bc00d39b9ebf393117c212706af54b5520ab8467a28";

    public static final String IP_CLAIM = "ip";

    public static final String ROLES_CLAIM = "roles";

    public static final String USER_EXISTS_MESSAGE = "User already exist";

    public static final String MAIN_DOMAIN = "/inno-shop";

    public static final String REGISTER_ENDPOINT = MAIN_DOMAIN + "/register";

    public static final String JWT_LOGIN_ENDPOINT = MAIN_DOMAIN + "/jwt-login";

    public static final String REFRESH_TOKEN_ENDPOINT = MAIN_DOMAIN + "refresh-token";

    public static final String SECURED_ENDPOINT = MAIN_DOMAIN + "/secured";

    public static final String HOME_ENDPOINT = MAIN_DOMAIN + "/home";

    public static final String SWAGGER_UI_ENDPOINTS = "/swagger-ui/**";

    public static final String SWAGGER_DOCS_ENDPOINTS = "/v3/api-docs/**";
}
