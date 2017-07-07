package com.boss.demo.service;

import com.boss.demo.domain.DecidedzoneModel;
import com.boss.demo.utils.PageBean;

import java.util.List;

public interface DecidedzoneService {
    void save(DecidedzoneModel model, String[] subareaId);



    void pageFind(PageBean pageBean);
}
