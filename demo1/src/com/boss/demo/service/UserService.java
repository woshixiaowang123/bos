package com.boss.demo.service;

import com.boss.demo.domain.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {
    UserModel login(UserModel model);

    void changePassword(String id, String password);
}
