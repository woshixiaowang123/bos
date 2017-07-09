package com.boss.demo.web;


import com.boss.demo.domain.UserModel;
import com.boss.demo.utils.BosGetSession;
import com.boss.demo.utils.MD5Utils;
import com.boss.demo.web.BaseAction.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.IOException;

/**
 * 用户类action
 * Created by 隔壁老王 on 2017/6/25.
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<UserModel> {

    //为了验证验证码,需要拿到输入的验证码,与存在于session域中的验证码比较,所以要定义一个属性,属性名与写入验证码的框的name相同
    private String checkcode;

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    //用户登录的方法
    public String login() {
        String values = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        if (StringUtils.isBlank(checkcode) || !checkcode.equals(values)) {
            //判断输入的验证码是否为空或者输入的值与生成的值不一致
            //验证码输入有误,跳转到登陆页面
            this.addActionError("验证码输入有误!");
            return "toLogin";
        } else {

            //修改登录认证,使用shiro 认证
            Subject subject = SecurityUtils.getSubject();
            String username = model.getUsername();
            String password = model.getPassword();
            password = MD5Utils.md5(password);
            AuthenticationToken token = new UsernamePasswordToken(username, password);
            try {
                subject.login(token);//调用安全管理器,安全管理器调用realm
                UserModel userModel = (UserModel) subject.getPrincipal();
                //登陆成功,把Usermodel放入session
                ServletActionContext.getRequest().getSession().setAttribute("loginUser", userModel);
            } catch (UnknownAccountException e) {
                //e.printStackTrace();
                //用户名不存在，跳转到登录页面
                this.addActionError("用户名不存在！");
                return "toLogin";
            } catch (IncorrectCredentialsException e) {
                // 密码错误，跳转到登录页面
                this.addActionError("密码错误！");
               // e.printStackTrace();
                return "toLogin";
            }
            return "home";
        }
    }

    //退出登录的方法
    public String logout() {
        ServletActionContext.getRequest().getSession().invalidate();
        return "toLogin";

    }

    //修改密码
    public String changePassword() throws IOException {
        String password = model.getPassword();
        String id = BosGetSession.getLoginUser().getId();
        String flg = "1";
        try {
            userService.changePassword(id, password);
        } catch (Exception e) {
            flg = "0";
        }
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(flg);


        return NONE;
    }

    //查询所有用户的方法
    public String list(){
        userService.pageFind(pageBean);
        this.writePageBeanToJson(new String[]{"password","noticebills","roles","detachedCriteria","birthday"});
        return NONE;
    }

    private String[] roleIds;

    public void setRoleIds(String[] roleIds) {
        this.roleIds = roleIds;
    }

    //添加用户
    public String save(){
        userService.save(model,roleIds);
        return "toList";
    }
}
