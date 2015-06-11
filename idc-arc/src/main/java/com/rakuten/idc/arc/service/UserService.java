package com.rakuten.idc.arc.service;

import java.util.List;

import com.rakuten.idc.arc.model.User;

public interface UserService {

    public User save(User user);

    public List<User> getList();

    public void addUser(User user);
}