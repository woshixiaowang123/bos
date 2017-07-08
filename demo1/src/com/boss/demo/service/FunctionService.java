package com.boss.demo.service;

import com.boss.demo.domain.FunctionModel;
import com.boss.demo.utils.PageBean;

import java.util.List;

public interface FunctionService {
    void pageFind(PageBean pageBean);

    List<FunctionModel> findAll();
}
