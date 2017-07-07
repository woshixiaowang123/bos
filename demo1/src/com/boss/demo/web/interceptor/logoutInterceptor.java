package com.boss.demo.web.interceptor;

import com.boss.demo.domain.UserModel;
import com.boss.demo.utils.BosGetSession;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 登陆过滤器
 * Created by 隔壁老王 on 2017/6/25.
 */
public class logoutInterceptor extends MethodFilterInterceptor {


    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        UserModel loginUser = BosGetSession.getLoginUser();
        if (loginUser==null){
            //说明未登录
            return "toLogin";
        }else{
            //说明已经登陆
            actionInvocation.invoke();
        }
        return null;
    }
}

