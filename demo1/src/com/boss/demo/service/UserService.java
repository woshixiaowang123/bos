package com.boss.demo.service;

import com.boss.demo.domain.UserModel;
import com.boss.demo.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {
    UserModel login(UserModel model);

    void changePassword(String id, String password);

    void pageFind(PageBean pageBean);

    void save(UserModel model, String[] roleIds);
}
