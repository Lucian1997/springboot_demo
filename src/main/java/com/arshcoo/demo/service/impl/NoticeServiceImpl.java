package com.arshcoo.demo.service.impl;

import com.arshcoo.demo.dao.NoticeDao;
import com.arshcoo.demo.dto.NoticeDto;
import com.arshcoo.demo.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeDao noticeDao;

    @Override
    public List<NoticeDto> getNotices() {
        return noticeDao.getNotices();
    }

    @Override
    public int addNotice(NoticeDto noticeDto) {
        return noticeDao.addNotice(noticeDto);
    }

    @Override
    public NoticeDto queryNotice(Integer id) {
        return noticeDao.queryNotice(id);
    }

    @Override
    public int delete(Integer id) {
        return noticeDao.delete(id);
    }
}
