package co.com.pragma.backend_challenge.user.infrastructure.util.constant;

import co.com.pragma.backend_challenge.user.domain.util.annotation.Generated;

@Generated
public class RestConstants {
    private RestConstants() {
        throw new IllegalStateException("Utility Class");
    }
    // API CODES
    public static final String SWAGGER_CODE_CREATED = "201";

    // HOME
    public static final String SWAGGER_SUMMARY_GET_HOME = "And endpoint to test if app is running";

    // USERS
    public static final String SWAGGER_SUMMARY_CREATE_OWNER = "Create an owner using the given valid info";
    public static final String SWAGGER_DESCRIPTION_CREATED_OWNER = "Owner has been created successfully";

}
