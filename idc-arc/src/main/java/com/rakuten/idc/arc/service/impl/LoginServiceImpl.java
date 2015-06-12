package com.rakuten.idc.arc.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.rakuten.gid.services.rest.client.ApiClient;
import com.rakuten.gid.services.rest.client.ApiManager;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.AuthV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.MemberV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GetAuthModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidError;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.member.MemberModel;
import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.model.User;
import com.rakuten.idc.arc.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    UserServiceImpl userServices = new UserServiceImpl();

    @Override
    public boolean authenticate(User user) {
        return authenticateV1_2(user);
        /*
         * for (User u : userServices.getList()) { if
         * (u.getUserName().equals(user.getUserName()) &&
         * u.getPassword().equals(user.getPassword())) return true; } return
         * false;
         */
    }

    public boolean authenticateV1_2(User user) {
        String grantType = "password";
        String username = user.getUserName();// "testcustomprofile2";
        String password = user.getPassword();// "test_123";
        String authenticationToken = null;
        GidError error = null;
        GetAuthModel authModelPassword = null;
        AuthV1_2 auth = new AuthV1_2();

        try {
            Class.forName(ArcConstants.REGISTER_API_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        auth.setGrant_type(grantType);
        auth.setUsername(username);
        auth.setPassword(password);
        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, auth,
                authenticationToken);
        ResponseModel model = (ResponseModel) apiClient.getFirst();

        if (model.getResponseCode() == 200) {
            authModelPassword = (GetAuthModel) model;
            authenticationToken = authModelPassword.getAccess_token();
            user.setPasswordAuthenticationToken(authenticationToken);
            System.out.println("password token : " + authenticationToken);
            return true;
        } else {
            error = (GidError) model;
            System.out.println(ArcConstants.AUTHENTICATION_ERROR
                    + error.toString());
            return false;
        }

    }

    /**
     * This is just a method written for reference to know how to read from
     * property files.
     */
    @SuppressWarnings("unused")
    private void readingFromPropertyFile() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(
                "/application.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(properties.get(ArcConstants.VIEW_PREFIX));
        System.out.println(properties.get(ArcConstants.VIEW_SUFFIX));
        System.out.println(properties.get(ArcConstants.TEMPLATE_LOADER_PATH));
    }

    @Override
    public Map<String, Object> getUserDetails(String passwordAuthenticationToken) {
        System.out.println("Entering the getUserDetails");
        Map<String, Object> userDetails = new HashMap<String, Object>();
        GidError error = null;
        MemberModel memberModel = null;

        MemberV1_2 member = new MemberV1_2();
        ApiClient apiClient = ApiManager.getClient(ArcConstants.URL, member,
                passwordAuthenticationToken);
        ResponseModel model = (ResponseModel) apiClient.getFirst();
        if (model.getResponseCode() == 200) {
            memberModel = (MemberModel) model;
            userDetails.put("PROFILE_MODEL", memberModel.getProfile()
                    .toString());
            userDetails.put("CARD_MODEL", memberModel.getCard().toString());
            userDetails.put("ADDRESS_MODEL", memberModel.getAddress()
                    .toString());
            userDetails.put("MEMBER_MODEL", memberModel.toString());

            System.out.println("User Details : " + userDetails);
        } else {
            error = (GidError) model;
            userDetails.put("ERROR", error);
            System.out.println("Error happened: " + error.toString());
        }
        System.out.println("Exiting the getUserDetails");
        return userDetails;
    }

}
