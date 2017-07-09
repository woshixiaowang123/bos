package com.boss.demo.web;

import com.boss.demo.domain.FunctionModel;
import com.boss.demo.utils.BosGetSession;
import com.boss.demo.web.BaseAction.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by 隔壁老王 on 2017/7/7.
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<FunctionModel> {

    public String findAll(){
        System.out.println("执行吧");
       functionService.pageFind(pageBean);
        //List<FunctionModel> list= functionService.findAll();

        this.writePageBeanToJson(new String[]{"detachedCriteria","parentFunction","children","roles"});
        //this.writeListToJson(list,new String[]{"parentFunction","children","roles"});
        return NONE;
    }

    public  String list(){

        List<FunctionModel> all = functionService.findAll();
        this.writeListToJson(all,new String[]{"parentFunction","children","roles"});

        return NONE;
    }

    //查询当前登录用户的权限
    public String findMenu(){

        List<FunctionModel> list=functionService.finMenu(BosGetSession.getLoginUser());
        this.writeListToJson(list,new String[]{"parentFunctionModel","children","roles"});

        return NONE;
    }
}
