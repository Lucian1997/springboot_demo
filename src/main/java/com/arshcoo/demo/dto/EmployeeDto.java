package com.arshcoo.demo.dto;

import org.apache.ibatis.annotations.Mapper;

import javax.validation.constraints.NotBlank;

public class EmployeeDto {
    private Integer id;
    private Integer accountId;
    private String name;
    @NotBlank(message = "职位不能为空")
    private String work;
    @NotBlank(message = "部门不能为空")
    private String position;
    @NotBlank(message = "地区不能为空")
    private String area;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
