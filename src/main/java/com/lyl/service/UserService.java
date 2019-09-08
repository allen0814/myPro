package com.lyl.service;


import com.lyl.entity.User;

public interface UserService{

    public User findByUserAndPass(User user);

    public void saveUser(User user);

    public void updateUserinfo(User user);

    public void updatePassword(User user);

}
