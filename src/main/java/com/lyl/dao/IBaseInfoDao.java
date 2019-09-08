package com.lyl.dao;

import com.alibaba.fastjson.JSONArray;
import com.lyl.entity.BaseInfo;
import com.lyl.entity.ProvinceCount;

import java.util.List;

public interface IBaseInfoDao {

    public void saveBaseInfo(BaseInfo baseInfo);

    public List<BaseInfo> queryBaseInfo(BaseInfo baseInfo);

    public void deleteBaseInfo(String[] id);

    public void updateBaseInfo(BaseInfo baseInfo);

    public List<ProvinceCount> provinceCount(BaseInfo baseInfo);

    public List<ProvinceCount> birthdayCount(BaseInfo baseInfo);


}
