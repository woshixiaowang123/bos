package com.boss.demo.utils;

import com.boss.demo.domain.UserModel;
import org.apache.struts2.ServletActionContext;

/**
 * 获得session域
 * Created by 隔壁老王 on 2017/6/25.
 */
public class BosGetSession {

    public static UserModel getLoginUser(){
        UserModel user = (UserModel) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
        return user;

    }
}
