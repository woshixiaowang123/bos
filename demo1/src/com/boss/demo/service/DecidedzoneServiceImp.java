package com.boss.demo.service;

import com.boss.demo.dao.DecidedzoneDao;
import com.boss.demo.dao.SubareaDao;
import com.boss.demo.domain.DecidedzoneModel;
import com.boss.demo.domain.SubareaModel;
import com.boss.demo.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service
 * Created by 隔壁老王 on 2017/6/30.
 */
@Service
@Transactional
public class DecidedzoneServiceImp implements DecidedzoneService {
    @Autowired
    private DecidedzoneDao decidedzoneDao;
    @Autowired
    private SubareaDao subareaDao;
    @Override
    public void save(DecidedzoneModel model, String[] subareaId) {
        decidedzoneDao.save(model);

        //其次要修改关联的分区的外键
        //遍历传过来的数组,然后修改关联的外键
        for (String s : subareaId) {
            SubareaModel byId = subareaDao.findById(s);
            byId.setDecidedzone(model);
        }
    }

    @Override
    public void pageFind(PageBean pageBean) {
        decidedzoneDao.pageFind(pageBean);
    }

}
