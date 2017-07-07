package com.boss.demo.service;

import com.boss.demo.dao.RegionDao;
import com.boss.demo.dao.StaffDao;
import com.boss.demo.domain.RegionModel;
import com.boss.demo.domain.StaffModel;
import com.boss.demo.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 取派员service实现类
 * Created by 隔壁老王 on 2017/6/26.
 */
@Service
@Transactional
public class StaffServiceImp implements StaffService {
    @Autowired
    private StaffDao staffDao;

    @Override
    public void add(StaffModel model) {


        //由于添加取派员时取派员入职,所有其在职状态应该是在职
        model.setDeltag("1");
        String haspda = model.getHaspda();
        if (haspda==null){
            model.setHaspda("0");
        }
        staffDao.save(model);
    }

    //分页查询
    @Override
    public void pageFind(PageBean pageBean) {
        staffDao.pageFind(pageBean);

    }

    @Override
    public void delete(String ids) {

        //首先应该切割此字符串,
        String[] split = ids.split(",");
        //然后遍历数组,因为仅仅修改了一个属性,可以利用快照区来修改
        for (String s : split) {
            StaffModel byId = staffDao.findById(s);
            byId.setDeltag("0");
        }

    }

    @Override
    public StaffModel findById(String id) {
        return staffDao.findById(id);
    }

    //修改信息
    @Override
    public void update(StaffModel staffModel) {
        staffDao.update(staffModel);
    }

    //还原取派员的方法
    @Override
    public void restar(String ids) {
        String[] split = ids.split(",");
        for (String s : split) {
            StaffModel byId = staffDao.findById(s);
            byId.setDeltag("1");
        }

    }

    @Override
    public List<StaffModel> findByDc(DetachedCriteria dc) {

        return staffDao.findByCriteria(dc);
    }
}
