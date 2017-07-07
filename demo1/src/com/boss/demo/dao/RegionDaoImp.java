package com.boss.demo.dao;

import com.boss.demo.dao.base.BaseDaoImp;
import com.boss.demo.domain.RegionModel;
import org.springframework.stereotype.Repository;

/**
 * dao
 * Created by 隔壁老王 on 2017/6/28.
 */
@Repository
public class RegionDaoImp extends BaseDaoImp<RegionModel> implements RegionDao {
    @Override
    public void saveOrUpdate(RegionModel regionModel) {
        this.getHibernateTemplate().saveOrUpdate(regionModel);

    }
}
