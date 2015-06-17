package com.rakuten.idc.arc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.rakuten.gid.services.rest.client.ApiClient;
import com.rakuten.gid.services.rest.client.ApiManager;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.AuthV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.MemberV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.helper.CreateProfileModel;
import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.model.User;
import com.rakuten.idc.arc.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private static List<User> userList = new ArrayList<User>();
    private static Map<String, User> userMap = new ConcurrentHashMap<String, User>();

    static {
        User user = new User();
        user.setEmail("singh.niraj@aranin.com");
        user.setUserName("Niraj Singh");
        user.setPassword("qwerty");
        userList.add(user);
        userMap.put(user.getEmail(), user);

        user = new User();
        user.setEmail("niraj.aar@gmail.com");
        user.setUserName("Niraj AAR");
        user.setPassword("qwerty");
        userList.add(user);
        userMap.put(user.getEmail(), user);

        user = new User();
        user.setEmail("john@doe.com");
        user.setUserName("John Doe");
        user.setPassword("qwerty");
        userList.add(user);
        userMap.put(user.getEmail(), user);

        user = new User();
        user.setEmail("aarav@aarav.com");
        user.setUserName("Aarav Doe");
        user.setPassword("qwerty");
        userList.add(user);
        userMap.put(user.getEmail(), user);
        loadRegisterApiDriver();
    }

    static void loadRegisterApiDriver() {
        try {
            Class.forName(ArcConstants.REGISTER_API_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User save(User user) {
        // save the user to map
        userMap.put(user.getEmail(), user);
        // save the user to list
        userList.add(user);
        return user;
    }

    @Override
    public List<User> getList() {
        // return the userList
        return userList;
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
        userMap.put(user.getEmail(), user);
    }

    /**
     * This method is used to get the client authentication token.
     * which will be used by client to do further transactions.
     */
    @Override
    public ResponseModel getClientAuthention() {
        AuthV1_2 auth = new AuthV1_2();
        auth.setGrant_type(ArcConstants.GRANT_TYPE_CLIENT_CREDENTIALS);
        ApiClient apiClient = ApiManager
                .getClient(ArcConstants.URL, auth, null);
        ResponseModel model = (ResponseModel) apiClient.getFirst();
        return model;
    }

    /**
     * This method is used to authenticate the user, by username and password.
     * Once the user will be authenticated , we get the memberModel.
     */
    @Override
    public ResponseModel getUserAuthentication(User user) {
        AuthV1_2 auth = new AuthV1_2();
        auth.setGrant_type(ArcConstants.GRANT_TYPE_PASSWORD);
        auth.setUsername(user.getUserName());
        auth.setPassword(user.getPassword());
        ApiClient apiClient = ApiManager
                .getClient(ArcConstants.URL, auth, null);
        ResponseModel model = (ResponseModel) apiClient.getFirst();
        return model;
    }

    /**
     * This method is used to create the user(Member) using the authentication token and the provided member.
     */
    @Override
    public ResponseModel createUser(MemberV1_2 member, String accessToken) {
        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, member,
                accessToken);
        ResponseModel model = (ResponseModel) apiClient.create();
        return model;
    }

    /**
     * To check the user is already existing using the authentication token and the user information.
     */
    @Override
    public boolean isExistingUser(User user, String authToken) {

        MemberV1_2 member = new MemberV1_2();
        member.setUsername(user.getUserName());
        CreateProfileModel profile = new CreateProfileModel();
        profile.setPr_email(user.getEmail());
        member.setProfile(profile);

        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, member,
                authToken);
        boolean exists = apiClient.exists();
        System.out.println("Is User Exist ? : " + exists);
        return exists;
    }

    /**
     * To update the member using the authenticationToken.
     */
    @Override
    public boolean updateUser(MemberV1_2 member, String authToken) {
        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, member,
                authToken);
        boolean result = apiClient.update();
        System.out.println("updated ? : " + result);
        return result;
    }

    /**
     * To delete the user .
     */
    @Override
    public boolean deleteUser(MemberV1_2 member, String authToken) {
        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, member,
                authToken);
        boolean result = apiClient.delete();
        System.out.println("Deleted ? : " + result);
        return result;
    }

    /**
     * To get the user details.
     */
    @Override
    public ResponseModel getUserDetails(String authenticationToken) {
        MemberV1_2 member = new MemberV1_2();
        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, member,
                authenticationToken);
        ResponseModel model = (ResponseModel) apiClient.getFirst();
        System.out.println("Response Returned: " + model.toString());
        return model;
    }
}
