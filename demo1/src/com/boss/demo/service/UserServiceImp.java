package com.boss.demo.service;

import com.boss.demo.dao.UserDao;
import com.boss.demo.domain.UserModel;
import com.boss.demo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户service实现类
 * Created by 隔壁老王 on 2017/6/25.
 */
@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    public UserModel login(UserModel model) {
        //验证时需要对密码进行加密进行验证
        String password=MD5Utils.md5(model.getPassword());
        List<UserModel> queryname = userDao.findByNamedQuery("queryname", model.getUsername(), password);
        if (queryname!=null&&queryname.size()>0){
            //说明登陆成功
            return queryname.get(0);
        }else{
            //说明登陆失败
            return null;

        }
    }
    //根据用户id修改密码
    @Override
    public void changePassword(String id, String password) {
        System.out.println(password);
        password=MD5Utils.md5(password);
        System.out.println(password);
        userDao.executeNamedQuery("editPassword",password,id);
    }
}
