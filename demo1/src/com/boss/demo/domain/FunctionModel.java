package com.boss.demo.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体
 */
public class FunctionModel implements Serializable {

    // Fields

    private String id;//编号
    private FunctionModel parentFunctionModel;//当前权限的上级权限
    private String name;//权限名称
    private String code;//关键字
    private String description;//描述
    private String page;//权限对应的访问url地址
    private String generatemenu = "1";//当前权限是否生成到菜单,1表示生成，0表示不生成
    private Integer zindex;//排序，保证菜单顺序
    private Set<FunctionModel> children = new HashSet(0);//当前权限的下级权限
    private Set<RoleModel> roles = new HashSet(0);//当前权限对应的角色

    private String pId;//上级权限的id

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FunctionModel getParentFunctionModel() {
        return parentFunctionModel;
    }

    public void setParentFunctionModel(FunctionModel parentFunctionModel) {
        this.parentFunctionModel = parentFunctionModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getGeneratemenu() {
        return generatemenu;
    }

    public void setGeneratemenu(String generatemenu) {
        this.generatemenu = generatemenu;
    }

    public Integer getZindex() {
        return zindex;
    }

    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }

    public Set getChildren() {
        return children;
    }

    public void setChildren(Set children) {
        this.children = children;
    }

    public Set getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

    public String getpId() {
        if (parentFunctionModel != null) {
            return parentFunctionModel.getId();
        }
        return "0";
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    @Override
    public String toString() {
        return "FunctionModel{" +
                "id='" + id + '\'' +
                ", parentFunctionModel=" + parentFunctionModel +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", page='" + page + '\'' +
                ", generatemenu='" + generatemenu + '\'' +
                ", zindex=" + zindex +
                ", children=" + children +
                ", roles=" + roles +
                ", pId='" + pId + '\'' +
                '}';
    }
}