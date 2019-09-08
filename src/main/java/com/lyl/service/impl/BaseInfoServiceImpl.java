package com.lyl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyl.dao.IBaseInfoDao;
import com.lyl.entity.BaseInfo;
import com.lyl.entity.ProvinceCount;
import com.lyl.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("baseInfoService")
public class BaseInfoServiceImpl implements BaseInfoService {

    @Autowired
    private IBaseInfoDao baseInfoDao;

    @Override
    public void saveBaseInfo(BaseInfo baseInfo) {
        baseInfoDao.saveBaseInfo(baseInfo);
    }

    @Override
    public PageInfo<BaseInfo> queryBaseInfo(BaseInfo baseInfo, Integer currentPage, Integer pageSize) {

        PageHelper.startPage(currentPage,pageSize);

        List<BaseInfo> list = baseInfoDao.queryBaseInfo(baseInfo);

        PageInfo<BaseInfo> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    @Override
    public List<BaseInfo> exportBaseInfo(BaseInfo baseInfo) {
        return baseInfoDao.queryBaseInfo(baseInfo);
    }

    @Override
    public void deleteBaseInfo(String[] id) {
        baseInfoDao.deleteBaseInfo(id);
    }

    @Override
    public void updateBaseInfo(BaseInfo baseInfo) {
        baseInfoDao.updateBaseInfo(baseInfo);
    }

    @Override
    public List<ProvinceCount> provinceCount(BaseInfo baseInfo) {
        return baseInfoDao.provinceCount(baseInfo);
    }

    @Override
    public List<ProvinceCount> birthdayCount(BaseInfo baseInfo) {
        return baseInfoDao.birthdayCount(baseInfo);
    }
}
