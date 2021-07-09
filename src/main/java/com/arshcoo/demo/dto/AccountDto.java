package com.arshcoo.demo.dto;

import javax.validation.constraints.NotBlank;

public class AccountDto {
    private Integer id;
    @NotBlank(message = "员工姓名不能为空")
    private String name;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String power;
    private Integer projectNum;
    private Integer noticeNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }

    public Integer getNoticeNum() {
        return noticeNum;
    }

    public void setNoticeNum(Integer noticeNum) {
        this.noticeNum = noticeNum;
    }
}
