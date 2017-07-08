package com.boss.demo.service;

import com.boss.demo.domain.RoleModel;
import com.boss.demo.utils.PageBean;

public interface RoleService {
    void findPage(PageBean pageBean);

    void save(RoleModel model, String functionIds);
}
