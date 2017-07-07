package com.boss.demo.service;

import com.boss.demo.dao.SubareaDao;
import com.boss.demo.domain.DecidedzoneModel;
import com.boss.demo.domain.RegionModel;
import com.boss.demo.domain.SubareaModel;
import com.boss.demo.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * subareaservice实现类
 * Created by 隔壁老王 on 2017/6/30.
 */
@Service
@Transactional
public class SubareaServiceImp implements SubareaService {
    @Autowired
    private SubareaDao subareaDao;
    @Override
    public void add(SubareaModel model) {

        subareaDao.save(model);
    }

    @Override
    public void update(SubareaModel model) {
        subareaDao.update(model);
    }

    @Override
    public List<SubareaModel> findAll() {
        return subareaDao.findAll();
    }

    @Override
    public void pageFind(PageBean pageBean) {

        subareaDao.pageFind(pageBean);

    }

    @Override
    public List<SubareaModel> findByDc(DetachedCriteria dc) {
        return subareaDao.findByCriteria(dc);
    }
}
