package com.lyl.service.impl;

import com.lyl.dao.IUserDao;
import com.lyl.entity.User;
import com.lyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User findByUserAndPass(User user) {
        return userDao.findUser(user);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUserinfo(User user) {
        userDao.updateUserinfo(user);
    }

    @Override
    public void updatePassword(User user) {
        userDao.updatePassword(user);
    }
}
