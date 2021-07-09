package com.arshcoo.demo.service;

import com.arshcoo.demo.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto login(AccountDto accountDto);

    int register(AccountDto accountDto);

    List<AccountDto> getAccounts();

    int delete(Integer id);

    AccountDto queryAccount(Integer id);

    int modify(AccountDto accountDto);

    int updateNotices();

    void clearNotices(Integer id);

    int updateProjectsNum(Integer id);

    void clearProjectsNum(Integer id);
}
