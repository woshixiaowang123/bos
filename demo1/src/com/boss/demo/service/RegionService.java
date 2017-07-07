package com.boss.demo.service;

import com.boss.demo.domain.RegionModel;
import com.boss.demo.utils.PageBean;

import java.util.List;

public interface RegionService {
    void addOrUpdate(List<RegionModel> list);

    void findPage(PageBean pageBean);

    List<RegionModel> findAll();
}
