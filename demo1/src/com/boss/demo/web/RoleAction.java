package com.boss.demo.web;

import com.boss.demo.domain.RoleModel;
import com.boss.demo.web.BaseAction.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * role
 * Created by 隔壁老王 on 2017/7/7.
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<RoleModel> {
    //提交的数据有ids参数
    private String functionIds;

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }
    //分页查询所有的角色
    public String  findAll(){
        roleService.findPage(pageBean);

        this.writePageBeanToJson(new  String[]{"detachedCriteria","functions","users"});
        return NONE;
    }

    public String save(){
        roleService.save(model,functionIds);
        return "toList";
    }

    //查询所有角色的信息
    public String list(){
        List<RoleModel> list= roleService.findAll();
        this.writeListToJson(list,new String[]{"functions","users"});
        return NONE;
    }
}
