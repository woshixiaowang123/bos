package com.boss.demo.service;

import cn.itcast.crm.service.CustomerService;
import com.boss.demo.dao.DecidedzoneDao;
import com.boss.demo.dao.NoticebillDao;
import com.boss.demo.dao.WorkbillDao;
import com.boss.demo.domain.DecidedzoneModel;
import com.boss.demo.domain.NoticebillModel;
import com.boss.demo.domain.StaffModel;
import com.boss.demo.domain.WorkbillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * Created by 隔壁老王 on 2017/7/7.
 */
@Service
@Transactional
public class NoticebissServiceImp implements NoticebillService{
    //注入dao
    @Autowired
    private NoticebillDao noticebillDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    @Autowired
    private WorkbillDao workbillDao;
    @Override
    //保存一个业务通知单，并尝试自动分单
    public void save(NoticebillModel model, String decidedzone_id) {
        noticebillDao.save(model);//持久化对象
        //接下来尝试自动分单
        String pickaddress = model.getPickaddress();//获得客户的地址
        //通过用户的地址查询该定区的派单员
        //String decidedzoneidByAddress = customerService.findDecidedzoneidByAddress(pickaddress);
        DecidedzoneModel byId = decidedzoneDao.findById(decidedzone_id);
        if (byId!=null){
            //可以自动分单
            //DecidedzoneModel byId = decidedzoneDao.findById(decidedzoneidByAddress);
            StaffModel staff = byId.getStaff();//获得对应的stafff
            model.setStaff(staff);//设置对应的staff
            //设置分单类型为自动
            model.setOrdertype("自动");
            //为当前取派员创建一个工单
            WorkbillModel workbill=new WorkbillModel();
            //设置工单的属性
            workbill.setAttachbilltimes(0);//追单次数
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//工单生成时间，系统时间
            workbill.setNoticebill(model);//工单关联业务通知单
            workbill.setPickstate("未取件");//取件状态：未取件、取件中、已取件
            workbill.setRemark(model.getRemark());//备注信息
            workbill.setStaff(staff);//工单关联取派员
            workbill.setType("新");//工单类型：新、追、改、销
            workbillDao.save(workbill);//保存工单
        }else {
            //设置分单类型为手动
            model.setOrdertype("手动");
        }


    }
}
