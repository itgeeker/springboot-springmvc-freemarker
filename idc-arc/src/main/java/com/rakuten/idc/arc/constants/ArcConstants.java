package com.rakuten.idc.arc.constants;

public class ArcConstants {

    /**
     * Making constructor private to make sure class not to instantiate this
     * class.
     */
    private ArcConstants() {
    }

    /**
     * Default Constants
     */
    public static final String DEFAULT_SITE_NAME = "default";
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";
    public static final String ROOT = "/";

    /**
     * View names (Used in ModelAndView classes)
     */
    public static final String HOME_VIEW = "home/home";
    public static final String LOGIN_VIEW = "user/login";
    public static final String LOGIN_SUCCESS = "user/loginsuccess";
    public static final String RESULT_VIEW = "user/result";
    public static final String PROFILE_VIEW = "user/profile";
    public static final String USERS_VIEW = "user/users";
    public static final String USER_VIEW = "user/user";
    public static final String SIGNUP_VIEW = "user/signup";
    public static final String SUCCESS_VIEW = "user/success";

    /**
     * View objects/parameters (Objects which are sending to view)
     */
    public static final String USERS = "users";
    public static final String USER = "user";
    public static final String MESSAGE = "message";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String RE_ENTERED_PASSWORD = "reEnteredPassword";
    public static final String RESULT = "result";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String EMAIL = "email";
    public static final String SITE_NAME = "sitename";
    public static final String USER_DETAILS = "userDetails";
    public static final String ERROR ="error";
    public static final String CREATE_MEMBER_MODEL = "createMemberModel";
    public static final String PROFILE_MODEL = "ProfileModel";
    public static final String CARD_MODEL = "CardModel";
    public static final String GLOBAL_ID_MODEL = "GlobalIdModel";
    public static final String ADDRESS_MODEL = "AddressModel";
    public static final String MEMBER_MODEL ="MemberModel";
    
    /**
     * Session Attributes
     */
    public static final String AUTHMODEL = "authModel";
    
    
    
    /**
     * Request constants
     */
    public static final String SITE = "site";
    public static final String MY_SITE = "mysite";

    /**
     * Request mapping constants.
     */
    public static final String REQUEST_MAPPING_LOGIN = "/login";
    public static final String REQUEST_MAPPING_AUTHENTICATE = "/authenticate";
    public static final String REQUEST_MAPPING_USERS = "/users";
    public static final String REQUEST_MAPPING_REGISTER = "/register";
    public static final String REQUEST_MAPPING_ADD_USER = "/addUser";
    public static final String REQUEST_MAPPING_SIGNUP = "/signup";
    public static final String REQUEST_MAPPING_DO_SIGNUP = "/dosignup";
    public static final String REQUEST_MAPPING_PROFILE = "/profile";

    /**
     * Spring Related Constants
     */

    public static final String VIEW_PREFIX = "${spring.view.prefix}";
    public static final String VIEW_SUFFIX = "${spring.view.suffix}";
    public static final String TEMPLATE_LOADER_PATH = "${spring.freemarker.templateLoaderPath}";

    /**
     * String Messages Constant
     */
    public static final String WELCOME_MESSAGE = "Welcome to Accounts.Rakuten.com";
    public static final String AUTHENTICATION_SUCCESS = "Sucessfully Authenticated !";
    public static final String AUTHENTICATION_FAILURE = "Authentication Failed !";
    public static final String AUTHENTICATION_ERROR = "Error while authenticating...";
    public static final String PASSWORD_MATCH_FAILED = "Password Does not match !! ";
    public static final String ADD_USER_SUCCESSFUL = "Sucessfully added the user !";
    public static final String SINGUP_MESSAGE = "Signup to Awesome Userprofile project!";

    /**
     * Java Classes.
     */
    public static final String REGISTER_API_DRIVER = "com.rakuten.gid.services.rest.client.gidimpl.RegisterApiDriver";

    /**
     * URLs
     */
    public static final String URL = "http://grp01.gidapi-pri.stg.jp.local";

    /**
     * Grant Type Constants
     */
    public static final String GRANT_TYPE_PASSWORD = "password";
    public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
}

