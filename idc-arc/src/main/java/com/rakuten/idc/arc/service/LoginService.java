package com.rakuten.idc.arc.service;

import java.util.Map;

import com.rakuten.gid.services.rest.client.ApiClientException;
import com.rakuten.gid.services.rest.client.ResponseModel;
import com.rakuten.idc.arc.model.User;

public interface LoginService {

    public ResponseModel authenticate(User user)throws ApiClientException;

    public Map<String, Object> getUserDetails(String passwordAuthenticationToken) throws ApiClientException;
}
