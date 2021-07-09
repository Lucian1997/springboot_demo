package com.arshcoo.demo.dao;

import com.arshcoo.demo.dto.NoticeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NoticeDao {
    //获取所有通知
    List<NoticeDto> getNotices();
    //根据通知id查看
    NoticeDto queryNotice(Integer id);
    //删除通知
    int delete(Integer id);
    //发布通知
    int addNotice(NoticeDto noticeDto);
}
