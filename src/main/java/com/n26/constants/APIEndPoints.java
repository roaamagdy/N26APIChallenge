package com.n26.constants;

/**
 * @author Roaa
 * This class will contain list of end points for all services
 */
public class APIEndPoints {

    //Pet endpoints
    public static final String ADD_PET = "/pet";
    public static final String PUT_PET = "/pet";
    public static final String GET_PET_BY_STATUS = "/pet/findByStatus";
    public static final String GET_PET_BY_TAGS = "/pet/findByTags";
    public static final String GET_PET_BY_ID = "/pet/{id}";
    public static final String UPDATE_PET_BY_ID = "/pet/{id}";
    public static final String DELETE_PET = "/pet/{id}";
    public static final String UPLOAD_PET_IMAGE = "/pet/{id}/uploadImage";

    //Store endpoints
    public static final String CREATE_ORDER = "/store/order";
    public static final String GET_ORDER_BY_ID = "/store/order/{id}";
    public static final String GET_INVENTORY = "/store/inventory";
    public static final String DELETE_ORDER = "/store/order/{id}";

    //User endpoints
    public static final String CREATE_USER = "/user";
    public static final String CREATE_LIST_OF_USERS = "/user/createWithList";
    public static final String USER_LOGIN = "/user/login";
    public static final String USER_LOGOUT = "/user/logout";
    public static final String GET_USER_BY_USERNAME = "/user/{username}";
    public static final String UPDATE_USER = "/user/{username}";
    public static final String DELETE_USER = "/user/{username}";

}
