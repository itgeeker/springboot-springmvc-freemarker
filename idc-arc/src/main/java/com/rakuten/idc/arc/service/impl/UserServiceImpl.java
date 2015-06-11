package com.rakuten.idc.arc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

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

}
