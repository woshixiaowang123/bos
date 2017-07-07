package com.boss.demo.service;

import com.boss.demo.domain.SubareaModel;
import com.boss.demo.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface SubareaService {
    void add(SubareaModel model);

    void update(SubareaModel model);

    List<SubareaModel> findAll();

    void pageFind(PageBean pageBean);

    List<SubareaModel> findByDc(DetachedCriteria dc);
}
