package com.lyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lyl.common.JsonResult;
import com.lyl.entity.Question;
import com.lyl.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/save")
    public JsonResult<Object> saveQuestion(@RequestBody JSONObject jsonObject){

        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        Question question = JSON.parseObject(json, Question.class);

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        question.setId(uuid);
        questionService.saveQuestion(question);

        jsonObj.setSuccess(true);
        jsonObj.setData(uuid);

        return jsonObj;
    }

    @RequestMapping("/query")
    public JsonResult<Object> queryQuestion(@RequestParam Integer currentPage, @RequestParam Integer pageSize, @RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        Question question = JSON.parseObject(json,Question.class);

        PageInfo<Question> pageInfo =  questionService.queryQuestion(question,currentPage,pageSize);

        jsonObj.setData(pageInfo);
        jsonObj.setSuccess(true);

        return jsonObj;
    }

    @RequestMapping("/single")
    public JsonResult<Object> singleQuestion(@RequestBody JSONObject jsonObject){

        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        Question question = JSON.parseObject(json,Question.class);

        question = questionService.singleQuestion(question);

        jsonObj.setData(question);
        jsonObj.setSuccess(true);

        return jsonObj;
    }

    @RequestMapping("/update")
    public JsonResult<Object> updateQuestion(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        Question question = JSON.parseObject(json,Question.class);

        questionService.updateQuestion(question);

        jsonObj.setSuccess(true);

        return jsonObj;
    }

}
