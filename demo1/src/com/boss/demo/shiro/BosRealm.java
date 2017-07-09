package com.boss.demo.shiro;

import java.util.List;

import javax.annotation.Resource;

import com.boss.demo.dao.FunctionDao;
import com.boss.demo.dao.UserDao;
import com.boss.demo.domain.FunctionModel;
import com.boss.demo.domain.UserModel;
import com.boss.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * realm
 * Created by 隔壁老王 on 2017/7/4.
 */
public class BosRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDao userDao;

    @Autowired
    private FunctionDao functionDao;


    private PrincipalCollection principals;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        char[] password = token.getPassword();
        // 根据用户名查询数据库中的密码，将密码交给安全管理器，由安全管理器对象负责比较数据库中的密码和页面传递的密码是否一致
        UserModel userModel=userDao.findUserByUsername(username);
        if (userModel==null){
            return null;
        }
        // 参数一：签名对象，认证通过后，可以在程序的任意位置获取当前放入的对象
        // 参数二：数据库中查询出的密码
        // 参数三：当前realm的类名
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(userModel,
                userModel.getPassword(), this.getClass().getName());

        return info;
    }

    @Override
    //在自定义的realm方法中进行授权
   /* protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获得授权对象
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo();
        info.addStringPermission("staff");//为用户授权
        return  info;
    }*/
    // 授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //授权信息对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //首先获取登录人信息
       UserModel userModel= (UserModel) principals.getPrimaryPrincipal();
       //判断登录人是否是admin超级管理员,如果是就给予最大权限
        if ("admin".equals(userModel.getUsername())){
            List<FunctionModel> all = functionDao.findAll();
            for (FunctionModel functionModel : all) {
                info.addStringPermission(functionModel.getCode());
            }
        }else {
            //普通用户,动态的赋予权限
            List<FunctionModel> list=functionDao.findByUserId(userModel.getId());
            for (FunctionModel functionModel : list) {
                System.out.println(functionModel.getCode()+"-----------");
                info.addStringPermission(functionModel.getCode());

            }
        }
        //info.addStringPermission("staff");
        return info;
    }
}
