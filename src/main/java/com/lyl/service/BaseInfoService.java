package com.lyl.service;

import com.github.pagehelper.PageInfo;
import com.lyl.entity.BaseInfo;
import com.lyl.entity.ProvinceCount;

import java.util.List;

public interface BaseInfoService {

    public void saveBaseInfo(BaseInfo baseInfo);

    public PageInfo<BaseInfo> queryBaseInfo(BaseInfo baseInfo, Integer currentPage, Integer pageSize);

    public List<BaseInfo> exportBaseInfo(BaseInfo baseInfo);

    public void deleteBaseInfo(String[] id);

    public void updateBaseInfo(BaseInfo baseInfo);

    public List<ProvinceCount> provinceCount(BaseInfo baseInfo);

    public List<ProvinceCount> birthdayCount(BaseInfo baseInfo);
}
