package com.rakuten.idc.arc.service;

import java.util.Map;

import com.rakuten.idc.arc.model.User;

public interface LoginService {

    public boolean authenticate(User user);

    public Map<String, Object> getUserDetails(String passwordAuthenticationToken);
}
