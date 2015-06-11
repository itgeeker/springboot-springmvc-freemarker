package com.rakuten.idc.arc.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.rakuten.gid.services.rest.client.ApiClient;
import com.rakuten.gid.services.rest.client.ApiManager;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.AuthV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GetAuthModel;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidError;
import com.rakuten.idc.arc.constants.ArcConstants;
import com.rakuten.idc.arc.model.User;
import com.rakuten.idc.arc.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

    UserServiceImpl userServices = new UserServiceImpl();

    @Override
    public boolean authenticate(User user) {
        authenticateV1_2();
        for (User u : userServices.getList()) {
            if (u.getUserName().equals(user.getUserName())
                    && u.getPassword().equals(user.getPassword()))
                return true;
        }
        return false;
    }

    public void authenticateV1_2() {
        String grantType = "password";
        String username = "testcustomprofile2";
        String password = "test_123";
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
            System.out.println("password token : " + authenticationToken);
        } else {
            error = (GidError) model;
            System.out.println(ArcConstants.AUTHENTICATION_ERROR
                    + error.toString());
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

}
