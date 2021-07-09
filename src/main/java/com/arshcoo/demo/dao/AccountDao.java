package com.arshcoo.demo.dao;

import com.arshcoo.demo.dto.AccountDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountDao {
    //登录
    AccountDto login(AccountDto accountDto);
    //新增账号
    int register(AccountDto accountDto);
    //获取所有账号
    List<AccountDto> getAccounts();
    //删除账号
    int delete(Integer id);
    //根据账号id查询账号
    AccountDto queryAccount(Integer id);
    //修改账号
    int modify(AccountDto accountDto);
    //添加通知消息
    int updateNotices();
    //清除消息通知
    void clearNotices(Integer id);
    //更新任务通知
    int updateProjectsNum(Integer id);
    //清除任务通知
    void clearProjectsNum(Integer id);
}
