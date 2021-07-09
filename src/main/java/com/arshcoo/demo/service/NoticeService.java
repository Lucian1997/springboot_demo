package com.arshcoo.demo.service;

import com.arshcoo.demo.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    //获取所有通知
    List<NoticeDto> getNotices();
    //根据通知id查看
    NoticeDto queryNotice(Integer id);
    //删除通知
    int delete(Integer id);
    //发布通知
    int addNotice(NoticeDto noticeDto);
}
