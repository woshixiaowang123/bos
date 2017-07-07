package com.boss.demo.service;

import com.boss.demo.dao.RegionDao;
import com.boss.demo.dao.StaffDao;
import com.boss.demo.domain.RegionModel;
import com.boss.demo.domain.StaffModel;
import com.boss.demo.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * regionservice
 * Created by 隔壁老王 on 2017/6/28.
 */
@Service
@Transactional
public class RegionServiceImp implements RegionService {

    @Resource
    private RegionDao regionDao;

    @Override
    public void addOrUpdate(List<RegionModel> list) {
        for (RegionModel regionModel : list) {
            regionDao.saveOrUpdate(regionModel);
        }



        /*for (RegionModel regionModel : list) {
            System.out.println(regionModel+"*********************");
            regionDao.saveOrUpdate(regionModel);
        }*/

    }

    @Override
    public void findPage(PageBean pageBean) {
        regionDao.pageFind(pageBean);
    }

    @Override
    public List<RegionModel> findAll() {
        return regionDao.findAll();
    }
}
