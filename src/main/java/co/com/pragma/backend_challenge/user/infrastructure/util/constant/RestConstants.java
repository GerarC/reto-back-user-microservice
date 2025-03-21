package co.com.pragma.backend_challenge.user.infrastructure.util.constant;

import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;

@Generated
public class RestConstants {

    private RestConstants() {
        throw new IllegalStateException("Utility Class");
    }
    // API CODES
    public static final String SWAGGER_CODE_CREATED = "201";
    public static final String SWAGGER_CODE_OK = "200";
    public static final String SWAGGER_CODE_ACCEPTED = "202";
    public static final String SWAGGER_CODE_BAD_REQUEST = "400";
    public static final String SWAGGER_CODE_NOT_FOUND = "404";
    public static final String SWAGGER_CODE_CONFLICT = "409";

    // Validations
    public static final String SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS = "Validations don't pass";

    // HOME
    public static final String SWAGGER_SUMMARY_GET_HOME = "And endpoint to test if app is running";

    // USERS
    public static final String SWAGGER_SUMMARY_CREATE_OWNER = "Create an owner using the given valid info";
    public static final String SWAGGER_DESCRIPTION_CREATED_OWNER = "Owner has been created successfully";
    public static final String SWAGGER_ERROR_USER_WITH_EMAIL_ALREADY_EXISTS = "An user with that email already exists";
    public static final String SWAGGER_ERROR_USER_WITH_ID_ALREADY_EXISTS = "An user with that identity document already exists";
    public static final String SWAGGER_ERROR_USER_UNDER_AGED = "Given user is under aged";
    public static final String SWAGGER_SUMMARY_GET_IS_OWNER = "Get if a user has role of Owner or not";
    public static final String SWAGGER_DESCRIPTION_EXIST_USER = "If user exist return true or false";
    public static final String SWAGGER_ERROR_USER_DOES_NOT_EXIST = "Requested user doesn't exist";
    public static final String SWAGGER_SUMMARY_CREATE_EMPLOYEE = "Create an employee using the given valid info";
    public static final String SWAGGER_DESCRIPTION_CREATED_EMPLOYEE = "Employee has been created successfully";
    public static final String SWAGGER_SUMMARY_CREATE_CLIENT = "Create an client using the given valid info";
    public static final String SWAGGER_DESCRIPTION_CREATED_CLIENT = "Client has been created successfully";

    //Authentication
    public static final String SWAGGER_SUMMARY_LOGIN = "Creates a token if credentials are right";
    public static final String SWAGGER_DESCRIPTION_LOGGED_IN = "Credentials were right and it retrieves some basic info from the user";
    public static final String SWAGGER_ERROR_WRONG_CREDENTIALS = "Email or password are wrong";
    public static final String SWAGGER_SUMMARY_AUTHORIZE = "Verify if given token is authorized";
    public static final String SWAGGER_DESCRIPTION_AUTHORIZED = "Token is authorized, response some user data";
    public static final String SWAGGER_ERROR_TOKEN_UNAUTHORIZED = "Given token is not authorized";

}
