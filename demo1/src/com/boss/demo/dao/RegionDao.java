package com.boss.demo.dao;

import com.boss.demo.dao.base.BaseDao;
import com.boss.demo.domain.RegionModel;

public interface RegionDao extends BaseDao<RegionModel> {
    void saveOrUpdate(RegionModel regionModel);
}
