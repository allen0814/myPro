package com.lyl.dao;

import com.lyl.entity.User;

public interface IUserDao {

    public User findUser(User user);

    public void saveUser(User user);

    public void updateUserinfo(User user);

    public void updatePassword(User user);


}
