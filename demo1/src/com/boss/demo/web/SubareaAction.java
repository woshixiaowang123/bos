package com.boss.demo.web;

import com.boss.demo.domain.RegionModel;
import com.boss.demo.domain.SubareaModel;
import com.boss.demo.utils.FileUtils;
import com.boss.demo.web.BaseAction.BaseAction;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * SubareaAction分区管理
 * Created by 隔壁老王 on 2017/6/30.
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<SubareaModel> {


    public String add(){
        //传过来的数据已经封装到model中
        String flg="1";
        try {
            subareaService.add(model);
        }catch (Exception e){
            flg="0";
        }
        try {
            ServletActionContext.getResponse().getWriter().print(flg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    //修改分区
    public String update(){
        //传过来的数据已经封装到model中
        String flg="1";
        try {
            subareaService.update(model);
        }catch (Exception e){
            flg="0";
        }
        try {
            ServletActionContext.getResponse().getWriter().print(flg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }


    //跳转到列表的方法
    public String toList(){
        return "toList";
    }

    //查询所有记录然后回显到页面的方法
    public String findAll(){

        //现在编写的是通过条件查询
        //查询的表单中提交过来了四组数据 ,分别是省,市,区,关键字,首先需要先拿到这几组数据
        //因为查询使用的是异步请求
        System.out.println(model+"------------");
        RegionModel region = model.getRegion();
        String addresskey = model.getAddresskey();
        DetachedCriteria dc=pageBean.getDetachedCriteria();
        if(StringUtils.isNotBlank(addresskey)){
            //说明关键字是存在的,需要设置模糊查询
            dc.add(Restrictions.like("addresskey", "%" + addresskey
                    + "%"));
        }
        if(region!=null){
            //只有region不等于null,才可以添加关于省市县的查询条件
            /*String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            DetachedCriteria detachedCriteria = dc.createCriteria("region");
            if (StringUtils.isBlank(province)){
                detachedCriteria.add(Restrictions.like("province","%"+province+"%"));
            }
            if (StringUtils.isBlank(city)){
                detachedCriteria.add(Restrictions.like("city","%"+city+"%"));
            }
            if (StringUtils.isBlank(district)){
                detachedCriteria.add(Restrictions.like("district","%"+district+"%"));
            }*/
            String province = region.getProvince();// 省
            String city = region.getCity();// 市
            String district = region.getDistrict();// 区
            DetachedCriteria regionDC = dc.createCriteria("Region");


            if (StringUtils.isNotBlank(province)) {
                // 按照省进行模糊查询
                regionDC.add(Restrictions
                        .like("province", "%" + province + "%"));
            }
            if (StringUtils.isNotBlank(city)) {
                // 按照市进行模糊查询
                regionDC.add(Restrictions.like("city", "%" + city + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                // 按照区进行模糊查询
                regionDC.add(Restrictions
                        .like("district", "%" + district + "%"));
            }
        }

        subareaService.pageFind(pageBean);
        writePageBeanToJson(new String[]{"subareas","decidedzone", "detachedCriteria", "pageSize", "currentPage"});

       return NONE;
    }

    //导出功能
    public String dowLoad() throws IOException {
        //首先查询所有的数据
        List<SubareaModel> all = subareaService.findAll();

        //然后创建一个excle文件
        HSSFWorkbook book=new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = book.createSheet();
        //创建表头
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("分区编号");
        row.createCell(1).setCellValue("区域编码");
        row.createCell(2).setCellValue("关键字");
        row.createCell(3).setCellValue("起始号");
        row.createCell(4).setCellValue("结束号");
        row.createCell(5).setCellValue("单双号");
        row.createCell(6).setCellValue("位置信息");

        //循环插入数据
        for (SubareaModel subareaModel : all) {
            HSSFRow row1 = sheet.createRow(sheet.getLastRowNum() + 1);
            row1.createCell(0).setCellValue(subareaModel.getId());
            row1.createCell(1).setCellValue(subareaModel.getRegion().getId());
            row1.createCell(2).setCellValue(subareaModel.getAddresskey());
            row1.createCell(3).setCellValue(subareaModel.getStartnum());
            row1.createCell(4).setCellValue(subareaModel.getEndnum());
            row1.createCell(5).setCellValue(subareaModel.getSingle());
            row1.createCell(6).setCellValue(subareaModel.getPosition());
        }

        //设置一个流,两个头
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();

        String filename="分区文件.xls";
        filename = FileUtils.encodeDownloadFilename(filename,
                ServletActionContext.getRequest().getHeader("user-agent"));
        ServletActionContext.getResponse().setContentType(
                ServletActionContext.getServletContext().getMimeType(filename));

        ServletActionContext.getResponse().setHeader("content-disposition",
                "attachment;filename=" + filename);
        book.write(outputStream);
        return NONE;
    }

    //定区添加的方法中需要查询分区
    public String findByAjax(){
        //这里要查询的是还没有定区的分区
        DetachedCriteria dc=DetachedCriteria.forClass(SubareaModel.class);
        dc.add(Restrictions.isNull("Decidedzone"));
        List<SubareaModel> list = subareaService.findByDc(dc);


        this.writeListToJson(list,new String[]{"Decidedzone","region","startnum","endnum","single"});


        return NONE;
    }
}
