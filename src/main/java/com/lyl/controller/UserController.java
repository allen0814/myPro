package com.lyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyl.common.JsonResult;
import com.lyl.entity.User;
import com.lyl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public JsonResult<Object> login(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();
        String json = JSONObject.toJSONString(jsonObject);
        User user = JSON.parseObject(json,User.class);

        user= userService.findByUserAndPass(user);

        if (user!=null){
            jsonObj.setSuccess(true);
            jsonObj.setData(user);
        }else{
            jsonObj.setSuccess(false);
        }

        return jsonObj;
    }



    @RequestMapping("/register")
    public JsonResult<Object> register(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();
        String json = JSONObject.toJSONString(jsonObject);
        User user = JSON.parseObject(json,User.class);
        User user1 = JSON.parseObject(json,User.class);

        user.setPassWord("");

        user= userService.findByUserAndPass(user);
        if (user != null){
            // 用户名已存在
            jsonObj.setSuccess(false);
        }else{
            userService.saveUser(user1);
            jsonObj.setSuccess(true);
        }
        return jsonObj;
    }

    // @CrossOrigin
    @RequestMapping("/updateUserinfo")
    public JsonResult<Object> updateUserinfo(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        User user = JSON.parseObject(json,User.class);
        userService.updateUserinfo(user);

        jsonObj.setSuccess(true);
        return jsonObj;
    }

    // @CrossOrigin
    @RequestMapping("/updatePassword")
    public JsonResult<Object> updatePassword(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        User user = JSON.parseObject(json,User.class);
        userService.updatePassword(user);

        jsonObj.setSuccess(true);
        return jsonObj;
    }

    @RequestMapping("/findUser")
    public JsonResult<Object> findUser(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        User user = JSON.parseObject(json,User.class);
        user= userService.findByUserAndPass(user);

        jsonObj.setSuccess(true);
        jsonObj.setData(user);

        return jsonObj;
    }


}
