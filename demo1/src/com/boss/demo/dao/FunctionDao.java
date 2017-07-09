package com.boss.demo.dao;

import com.boss.demo.dao.base.BaseDao;
import com.boss.demo.domain.FunctionModel;

import java.util.List;

public interface FunctionDao extends BaseDao<FunctionModel> {
    List<FunctionModel> findByUserId(String id);
}
