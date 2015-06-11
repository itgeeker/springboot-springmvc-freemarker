package com.rakuten.idc.arc.service;

import com.rakuten.idc.arc.model.User;

public interface LoginService {

    public boolean authenticate(User user);
}
