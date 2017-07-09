package com.boss.demo.web;

import com.boss.demo.domain.WorkordermanageModel;
import com.boss.demo.web.BaseAction.BaseAction;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * Created by 隔壁老王 on 2017/7/7.
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<WorkordermanageModel> {

    public String save() throws IOException {
        String flg="1";
        try {
            workordermanageService.save(model);

        }catch (Exception e){
            e.printStackTrace();
            flg="0";
        }
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().print(flg);
        return "toList";
    }

    //查询所有的工作单的方法,分页
    //@RequiresPermissions("staff")
    public String findAll(){
        workordermanageService.pageFind(pageBean);
        this.writePageBeanToJson(new String[]{"detachedCriteria"});
        return NONE;
    }
}
