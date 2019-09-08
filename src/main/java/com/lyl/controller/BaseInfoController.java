package com.lyl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lyl.common.JsonResult;
import com.lyl.entity.BaseInfo;
import com.lyl.entity.ProvinceCount;
import com.lyl.service.BaseInfoService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@ResponseBody
@RequestMapping("/api/baseinfo")
public class BaseInfoController {

    @Autowired
    private BaseInfoService baseInfoService;

    @RequestMapping("/save")
    public JsonResult<Object> saveBaseInfo(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        BaseInfo baseInfo = JSON.parseObject(json,BaseInfo.class);

        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        baseInfo.setId(uuid);
        baseInfoService.saveBaseInfo(baseInfo);
        jsonObj.setSuccess(true);
        jsonObj.setData(uuid);
        return jsonObj;
    }

    @RequestMapping("import")
    public JsonResult<Object> importBaseInfo(@RequestBody JSONArray jsonArray){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        if (jsonArray.size()>0){
            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String json = JSONObject.toJSONString(jsonObject);
                BaseInfo baseInfo = JSON.parseObject(json,BaseInfo.class);
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                baseInfo.setId(uuid);
                baseInfoService.saveBaseInfo(baseInfo);
            }
        }

        jsonObj.setSuccess(true);

        return jsonObj;
    }

    @RequestMapping("/query")
    public JsonResult<Object> queryBaseInfo(@RequestParam Integer currentPage, @RequestParam Integer pageSize, @RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        BaseInfo baseInfo = JSON.parseObject(json,BaseInfo.class);

        PageInfo<BaseInfo> pageInfo =  baseInfoService.queryBaseInfo(baseInfo,currentPage,pageSize);

        jsonObj.setData(pageInfo);
        jsonObj.setSuccess(true);

        return jsonObj;
    }

    @RequestMapping("/export")
    public JsonResult<Object> exportBaseInfo(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        BaseInfo baseInfo = JSON.parseObject(json,BaseInfo.class);

        List<BaseInfo> list =  baseInfoService.exportBaseInfo(baseInfo);

        jsonObj.setData(list);
        jsonObj.setSuccess(true);

        return jsonObj;
    }


    @RequestMapping("/delete")
    public JsonResult<Object> deleteBaseInfo(@RequestBody JSONArray jsonArray){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String[] id = JSONArray.parseArray(jsonArray.toJSONString(jsonArray)).toArray(new String[0]);
        baseInfoService.deleteBaseInfo(id);
        jsonObj.setSuccess(true);

        return jsonObj;
    }

    @RequestMapping("update")
    public JsonResult<Object> updateBaseInfo(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        BaseInfo baseInfo = JSON.parseObject(json,BaseInfo.class);
        baseInfoService.updateBaseInfo(baseInfo);
        jsonObj.setSuccess(true);

        return jsonObj;
    }

    @RequestMapping("provinceCount")
    public JsonResult<Object> provinceCount(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        BaseInfo baseInfo = JSON.parseObject(json,BaseInfo.class);

        List<ProvinceCount> list = baseInfoService.provinceCount(baseInfo);
        jsonObj.setData(list);
        jsonObj.setSuccess(true);

        return jsonObj;
    }

    @RequestMapping("birthdayCount")
    public JsonResult<Object> birthdayCount(@RequestBody JSONObject jsonObject){
        JsonResult<Object> jsonObj = new JsonResult<Object>();

        String json = JSONObject.toJSONString(jsonObject);
        BaseInfo baseInfo = JSON.parseObject(json,BaseInfo.class);

        List<ProvinceCount> list = baseInfoService.birthdayCount(baseInfo);
        jsonObj.setData(list);
        jsonObj.setSuccess(true);

        return jsonObj;
    }

    @RequestMapping("updateAvatar")
    public JsonResult<Object> updateAvatar(HttpServletRequest request, HttpServletResponse response){
        JsonResult<Object> jsonObj = new JsonResult<Object>();
        // 从请求中获取到文件信息需要将请求转换为MultipartHttpServletRequest类型
        MultipartHttpServletRequest mulRequest = request instanceof MultipartHttpServletRequest ? (MultipartHttpServletRequest) request : null;
        if(mulRequest==null){
            return null;
        }

        CommonsMultipartFile multipartFile = null;
        // 获取用户id
        Map<String,String[]> map = mulRequest.getParameterMap();
        String[] userIds = map.get("userId");
        String userId = userIds[0];
        // 获取上传文件
        Iterator<String> fileNames = mulRequest.getFileNames();
        if (fileNames.hasNext()) { // 遍历请求中的图片信息
            String inputName = fileNames.next(); // 图片对应的参数名

            multipartFile = (CommonsMultipartFile)mulRequest.getFile(inputName);
            String fileName = multipartFile.getOriginalFilename();   //原文件名

            try {
                // 备份的临时File对象
                File file = new File("avatar.jpg");
                // 复制文件
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), file);

                // 获取配置路径
                String path = "/Volumes/Macintosh HD 1/Users/allen/my-project/src/item/upload/";
                //String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String newPath = path + userId + "/";
                File newDir = new File(newPath);
                if (!newDir.exists()) {
                    newDir.mkdirs(); // 目录不存在的情况下，创建目录
                }
                // 根据指定的文件夹复制
                FileUtils.copyFileToDirectory(file, newDir);

                // 删除临时file
                file.delete();

                jsonObj.setData(userId);
                jsonObj.setSuccess(true);// 文件上传成功
            } catch (IOException e) {
                jsonObj.setSuccess(false);;// 文件上传失败
            }
        }

        return jsonObj;
    }
}
