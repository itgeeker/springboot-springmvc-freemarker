package com.rakuten.idc.arc.service;

import java.util.List;

import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.MemberV1_2;
import com.rakuten.idc.arc.model.User;

public interface UserService {

    public User save(User user);

    public List<User> getList();

    public void addUser(User user);

    public ResponseModel getClientAuth();

    public ResponseModel createUser(MemberV1_2 member, String access_token);
}