package com.boss.demo.dao;

import com.boss.demo.dao.StaffDao;
import com.boss.demo.dao.base.BaseDaoImp;
import com.boss.demo.domain.StaffModel;
import org.springframework.stereotype.Repository;

/**
 * dao
 * Created by 隔壁老王 on 2017/6/28.
 */
@Repository
public class StaffDaoImp extends BaseDaoImp<StaffModel> implements StaffDao {
}
