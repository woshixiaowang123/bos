package com.boss.demo.web;

import cn.itcast.crm.domain.Customer;
import com.boss.demo.domain.NoticebillModel;
import com.boss.demo.domain.UserModel;
import com.boss.demo.utils.BosGetSession;
import com.boss.demo.web.BaseAction.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * niticebill
 * Created by 隔壁老王 on 2017/7/6.
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<NoticebillModel> {


    //页面提交过来的有一个电话的信息
    private String phone;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //首先是通过电话号码查询用户信息的方法
    public String findByPhone(){
        Customer customer = customerService.findCustomerByPhone(phone);
        //把返回的Customer对象回写到页面
        this.writeObjToJson(customer,new String[]{});

        return NONE;
    }

    //添加业务受理单的方法
    private String decidedzone_id;

    public void setDecidedzone_id(String decidedzone_id) {
        this.decidedzone_id = decidedzone_id;
    }

    public String save(){
        UserModel loginUser = BosGetSession.getLoginUser();
       // ServletActionContext.getRequest().getSession().getAttribute("");
        model.setUser(loginUser);
        noticebillService.save(model,decidedzone_id);
        return "toAdd";
    }
}
