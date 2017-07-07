package com.boss.demo.web;

import com.boss.demo.domain.WorkordermanageModel;
import com.boss.demo.web.BaseAction.BaseAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by 隔壁老王 on 2017/7/7.
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<WorkordermanageModel> {

    public String save(){


        return NONE;
    }
}
