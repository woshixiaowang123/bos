package com.boss.demo.web;

import cn.itcast.crm.domain.Customer;
import com.boss.demo.domain.DecidedzoneModel;
import cn.itcast.crm.service.CustomerService;
import com.boss.demo.web.BaseAction.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * action
 * Created by 隔壁老王 on 2017/6/30.
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<DecidedzoneModel> {



    //提供subareaId属性,来获得提交的内容
    private String[] subareaId;

    public void setSubareaId(String[] subareaId) {
        this.subareaId = subareaId;
    }

    //首先是添加定区的方法
    public String save(){
        decidedzoneService.save(model,subareaId);

        return NONE;
    }

    //分页查询
    //查询所有的定区,并回显到页面上
    public String findAll(){
        decidedzoneService.pageFind(pageBean);
        //此时pagebean的属性已经被赋值,要回显到页面上的话需要排除一些属性
        this.writePageBeanToJson(new String[]{"currentPage","pageSize","detachedCriteria", "decidedzones","subareas"});
        return NONE;
    }

    //要查询crm的信息,要注入CustomerService信息
    @Resource
    private CustomerService customerService;

    //查询还没有关联定区的客户信息
    public String findCustomersAssociation(){

        List<Customer> customers = customerService.findnoassociationCustomers();
        //通过已经提取的集合转json的方法
        this.writeListToJson(customers,new String[]{"station","telephone","address","decidedzone_id"});

        return NONE;
    }

    //获得已经关联的客户信息
    public String findCustomersAssociations(){

        List<Customer> customers = customerService.findhasassociationCustomers(model.getId());
        this.writeListToJson(customers,new String[]{"station","telephone","address","decidedzone_id"});
        return NONE;
    }

    private Integer[] customerIds;

    public void setCustomerIds(Integer[] customerIds) {
        this.customerIds = customerIds;
    }

    //关联客户的方法
    public String assigncustomerstodecidedzone(){

        //此时传过来了有id以及客户的id
        customerService.assignCustomersToDecidedZone(customerIds,model.getId());

        return "toList";
    }

}
