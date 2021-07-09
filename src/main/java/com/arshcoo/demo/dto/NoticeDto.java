package com.arshcoo.demo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class NoticeDto {
    private Integer id;
    @NotBlank(message = "通知标题不能为空")
    private String title;
    @NotBlank(message = "通知内容不能为空")
    private String context;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
