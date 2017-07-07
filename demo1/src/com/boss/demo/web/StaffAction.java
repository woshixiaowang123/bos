package com.boss.demo.web;

import com.boss.demo.domain.StaffModel;
import com.boss.demo.utils.PageBean;
import com.boss.demo.web.BaseAction.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import ognl.IteratorElementsAccessor;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * 取派员action
 * Created by 隔壁老王 on 2017/6/26.
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<StaffModel> {


    //添加取派员的操作
    public String add(){
        staffService.add(model);

        return "toList";
    }

    //分页查询页面向后台传送两个数据,一个page一个rows,要想在后台接收到此参数,需要把这两个变成本类的属性


    //分页查询
    public String pageFind() throws IOException {

        staffService.pageFind(pageBean);
        //此时pageBean的的数据是全的,而我们只需要两组数据即可,即total与list结合,而且还要输出字符串的形式输出到页面
        this.writePageBeanToJson(new String[]{"currentPage","pageSize","detachedCriteria","decidedzones"});
        return NONE;
    }

    private String ids;

    public void setIds(String ids) {
        this.ids = ids;
    }
    //作废取派员的方法,其中有一个参数名称是ids所以可以在本类中添加ids属性类获取此参数
    public String delete(){
        staffService.delete(ids);
        return "toList";
    }

    //修改取派员信息的方法
    public String update(){
        //这种方式的修改可以利用覆盖的方式,即利用快照区修改
        StaffModel staffModel=staffService.findById(model.getId());
        System.out.println(staffModel+"staffModel");
        System.out.println(model);
        if (model.getHaspda()==null){
            staffModel.setHaspda("0");
        }else {
            staffModel.setHaspda(model.getHaspda());
        }
        staffModel.setName(model.getName());
        staffModel.setTelephone(model.getTelephone());
        staffModel.setStation(model.getStation());
        staffModel.setStandard(model.getStandard());
        System.out.println(staffModel);
        staffService.update(staffModel);
        return "toList";
    }

    //还原取派员的方法
    public String restar(){
       staffService.restar(ids);

        return "toList";
    }

    //定区的添加选项中需要用到查询的取派员的信息
    public String findAjax(){
        //这里查询的是在职的,而且没有分配定区的员工
        DetachedCriteria dc=DetachedCriteria.forClass(StaffModel.class);
        dc.add(Restrictions.eq("deltag","1"));
        dc.add(Restrictions.isEmpty("Decidedzones"));
        List<StaffModel> list= staffService.findByDc(dc);

        //这里就得到了一个存储staffModel的集合
        this.writeListToJson(list,new String[]{"Decidedzones","standard","station","deltag","haspda","telephone"});

        return NONE;
    }
}
