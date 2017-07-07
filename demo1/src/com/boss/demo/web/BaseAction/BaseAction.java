package com.boss.demo.web.BaseAction;

import cn.itcast.crm.service.CustomerService;
import com.boss.demo.service.*;
import com.boss.demo.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 抽取的action类,所有的action类继承此类即刻
 * Created by 隔壁老王 on 2017/6/25.
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected PageBean pageBean=new PageBean();

    public void setRows(int rows) {
        pageBean.setPageSize(rows);
    }

    public void setPage(int page) {
        pageBean.setCurrentPage(page);
    }

    //设置模型类
    protected  T model;
    @Override
    public T getModel() {
        return model;
    }

    @Autowired
    protected StaffService staffService;

    @Autowired
    protected RegionService regionService;

    protected DetachedCriteria detachedCriteria;

    @Autowired
    protected SubareaService subareaService;

    @Autowired
    protected DecidedzoneService decidedzoneService;

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected  NoticebillService noticebillService;

    @Autowired
    protected  WorkordermanageService workordermanageService;

    @Autowired
    protected  UserService userService;
    //在这里必须初始化该模型类,就要通过dao层相似的方法获得泛型的类型,再初始化
    public BaseAction(){
        ParameterizedType genericSuperclass=null;
        Type genericSuperclass2 = this.getClass().getGenericSuperclass();
        if(genericSuperclass2 instanceof ParameterizedType){
            genericSuperclass = (ParameterizedType) genericSuperclass2;
        }else{
            genericSuperclass = (ParameterizedType) this.getClass().getSuperclass().getGenericSuperclass();
        }
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        Class<T> clazz= (Class<T>) actualTypeArguments[0];
        detachedCriteria=DetachedCriteria.forClass(clazz);
        pageBean.setDetachedCriteria(detachedCriteria);
        try {
            model=clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    //分页查询抽取
   /* protected  int rows;
    protected int page;*/
   public void writePageBeanToJson(String[] arr){
       JsonConfig jsonConfig=new JsonConfig();
       jsonConfig.setExcludes(arr);
       JSONObject jsonObject = JSONObject.fromObject(pageBean, jsonConfig);
       String json=jsonObject.toString();

       //以json字符串的形式往页面输出
       ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
       try {
           ServletActionContext.getResponse().getWriter().print(json);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   //由于业务需求,需要一个吧list集合转换成json的方法
    public void writeListToJson(List list,String[] arr){
       JsonConfig jsonConfig=new JsonConfig();
       jsonConfig.setExcludes(arr);
        JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
        String json=jsonArray.toString();

        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //抽取一个把对象回写的方法
    public void writeObjToJson(Object object,String[] arr){
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(arr);
        JSONObject obj=JSONObject.fromObject(object,jsonConfig);
        String json=obj.toString();

        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
