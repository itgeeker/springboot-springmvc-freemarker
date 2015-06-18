package com.rakuten.idc.arc.service;

import java.util.List;

import com.rakuten.gid.services.rest.client.ApiClientException;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.CustomProfileV1_2;
import com.rakuten.gid.services.rest.client.gidimpl.basemodel.MemberV1_2;
import com.rakuten.idc.arc.model.User;

public interface UserService {

    public User save(User user);

    public List<User> getList();

    public void addUser(User user);

    public ResponseModel getClientAuthention() throws ApiClientException;

    public ResponseModel createUser(MemberV1_2 member, String access_token) throws ApiClientException;

    public ResponseModel getUserAuthentication(User user) throws ApiClientException;

    public ResponseModel getUserDetails(String authenticationToken)throws ApiClientException;

    public boolean isExistingUser(User user, String authToken) throws ApiClientException;

    public boolean updateUser(MemberV1_2 member, String authToken)throws ApiClientException;

    public boolean deleteUser(MemberV1_2 member, String authToken)throws ApiClientException;

    public ResponseModel addCustomProfile(CustomProfileV1_2 profile,
            String authToken)throws ApiClientException;

}