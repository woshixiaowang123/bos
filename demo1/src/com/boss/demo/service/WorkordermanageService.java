package com.boss.demo.service;

import com.boss.demo.domain.WorkordermanageModel;
import com.boss.demo.utils.PageBean;

public interface WorkordermanageService {
    void save(WorkordermanageModel model);

    void pageFind(PageBean pageBean);
}
