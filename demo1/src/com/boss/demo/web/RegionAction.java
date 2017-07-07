package com.boss.demo.web;

import com.boss.demo.domain.RegionModel;
import com.boss.demo.utils.PageBean;
import com.boss.demo.utils.PinYin4jUtils;
import com.boss.demo.web.BaseAction.BaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * regionAction类
 * Created by 隔壁老王 on 2017/6/28.
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<RegionModel> {
    //属性驱动,页面上传来的有一个name为myfile的文件,要设置myfile为此类的属性,能拿到此文件
    private File myFile;

    public RegionAction() {
        super();
    }

    public void setMyFile(File myFile) {
        this.myFile = myFile;
    }


    public String upFile() throws IOException {
        System.out.println(myFile);
        String flg = "1";
        try {
            List<RegionModel> list = new ArrayList<RegionModel>();
            HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(myFile));
            HSSFSheet sheetAt = book.getSheetAt(0);

            for (Row cells : sheetAt) {
                if (cells.getRowNum() == 0) {
                    continue;
                } else {

                    //说明取到了除去标题的每一行
                    String id = cells.getCell(0).getStringCellValue();
                    String province = cells.getCell(1).getStringCellValue();
                    String city = cells.getCell(2).getStringCellValue();
                    String district = cells.getCell(3).getStringCellValue();
                    String postcode = cells.getCell(4).getStringCellValue();


                    // 简码----北京市北京市东城区----》BJSBJSDCQ
                    String shortcode = province + city + district;
                    String[] strings = PinYin4jUtils.getHeadByString(shortcode);
                    shortcode = StringUtils.join(strings, "");

                    // 城市编码----北京市-----》beijingshi
                    String[] strings2 = PinYin4jUtils.stringToPinyin(city);
                    String citycode = StringUtils.join(strings2);

                    //String id, String province, String city, String district, String postcode
                    RegionModel regionModel = new RegionModel(id, province, city, district, postcode);
                    regionModel.setShortcode(shortcode);
                    regionModel.setCitycode(citycode);

                    list.add(regionModel);
                    //这是个啥啥啥

                }

            }
            //现在所有的文件内容都已经封装到list集合中了
            System.out.println(list);
            for (RegionModel regionModel : list) {
                System.out.println(regionModel+"+++++++++++++++++++++++++++++");
            }
            regionService.addOrUpdate(list);
        } catch (Exception e) {
            flg = "0";
        }
        ServletActionContext.getResponse().getWriter().print(flg);

        return NONE;
    }

    //分页查询
    public String findPage(){

        System.out.println(pageBean);
        regionService.findPage(pageBean);
        this.writePageBeanToJson(new String[]{"detachedCriteria",
                "pageSize", "currentPage", "subareas"});
        return NONE;

    }

    //分区功能中的查询所有区域的方法
    public String findRegion(){

        List<RegionModel> list=regionService.findAll();

        //需要把查询到的内容回写到页面,所以要排除所有的不需要的属性与关联
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setExcludes(new String[]{"subareas","province","city","district","postcode","shortcode","citycode"});

        JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
        String json=jsonArray.toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        try {
            ServletActionContext.getResponse().getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return NONE;
    }
}
