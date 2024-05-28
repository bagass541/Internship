package com.bagas.constants;

public class CommonConstants {

    public static final String BEGINNING_AUTH_HEADER = "Bearer";

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String LOGIN_SUCCESSFUL_MESSAGE = "User login was successful";

    public static final String REGISTRATION_SUCCESSFUL_MESSAGE = "User registration was successful";

    public static final String UNAUTHORIZED_MESSAGE = "You aren't authorized";

    public static final String TOKEN_GENERATED_MESSAGE = "New token generated";

    public static final long DEFAULT_ACCESS_TOKEN_EXPIRED_TIME = 1000 * 60 * 20;

    public static final long DEFAULT_REFRESH_EXPIRED_TIME = 1000 * 60 * 60 * 24;

    public static final String SECRET_KEY = System.getenv("SECRET_KEY");

    public static final String IP_CLAIM = "ip";

    public static final String USER_EXISTS_MESSAGE = "User already exist";

    public static final String MAIN_DOMAIN = "/inno-shop";

    public static final String REGISTER_ENDPOINT = "/register";

    public static final String LOGIN_ENDPOINT = "/login";

    public static final String JWT_LOGIN_ENDPOINT = "/jwt-login";

    public static final String SECURED_ENDPOINT = "/secured";

    public static final String HOME_ENDPOINT = "/home";
}
