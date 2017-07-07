package com.boss.demo.service;

import com.boss.demo.domain.StaffModel;
import com.boss.demo.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface StaffService {
    void add(StaffModel model);

    void pageFind(PageBean pageBean);

    void delete(String ids);

    StaffModel findById(String id);

    void update(StaffModel staffModel);

    void restar(String ids);

    List<StaffModel> findByDc(DetachedCriteria dc);
}
