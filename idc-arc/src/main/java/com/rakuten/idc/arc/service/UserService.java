package com.rakuten.idc.arc.service;

import java.util.List;

import com.rakuten.idc.arc.model.User;

public interface UserService {
    User save(User user);
    List<User> getList();
}