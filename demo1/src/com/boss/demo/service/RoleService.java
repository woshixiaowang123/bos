package com.boss.demo.service;

import com.boss.demo.domain.RoleModel;
import com.boss.demo.utils.PageBean;

import java.util.List;

public interface RoleService {
    void findPage(PageBean pageBean);

    void save(RoleModel model, String functionIds);

    List<RoleModel> findAll();
}
