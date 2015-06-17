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
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.GidError;
import com.rakuten.gid.services.rest.client.gidimpl.responsemodel.member.CreateMemberModel;
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

    static void loadRegisterApiDriver(){
        try {
            Class.forName(ArcConstants.REGISTER_API_DRIVER);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public ResponseModel getClientAuth() {
        AuthV1_2 auth = new AuthV1_2();
        auth.setGrant_type("client_credentials");
        ApiClient apiClient = ApiManager.getClient("http://grp01.gidapi-pri.stg.jp.local", auth, null);
        ResponseModel model = (ResponseModel)apiClient.getFirst();
        return model;
    }

    @Override
    public ResponseModel createUser(MemberV1_2 member, String access_token) {
        GidError error = null;
        ApiClient apiClient = ApiManager.getClient("http://grp01.gidapi-pri.stg.jp.local", member, access_token);
        CreateMemberModel createMemberModel = null;
        ResponseModel model = (ResponseModel)apiClient.create();
        if (model.getResponseCode() == 200)
        {
          createMemberModel = (CreateMemberModel)model;
          return createMemberModel;
        }
        error = (GidError)model;
        return error;
    }

}
