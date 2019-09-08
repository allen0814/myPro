package com.lyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyl.common.JsonResult;
import com.lyl.entity.Answer;
import com.lyl.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping("/save")
    public JsonResult<Object> saveAnswer(@RequestBody JSONObject jsonObject){

        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        Answer answer = JSON.parseObject(json, Answer.class);

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        answer.setId(uuid);
        answerService.saveAnswer(answer);

        jsonObj.setSuccess(true);
        jsonObj.setData(uuid);

        return jsonObj;
    }

    @RequestMapping("/query")
    public JsonResult<Object> queryAnswer(@RequestBody JSONObject jsonObject){

        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        Answer answer = JSON.parseObject(json, Answer.class);

        List<Answer> list = answerService.queryAnswer(answer);

        jsonObj.setSuccess(true);
        jsonObj.setData(list);

        return jsonObj;
    }
}
