package com.arshcoo.demo.service.impl;

import com.arshcoo.demo.dao.AccountDao;
import com.arshcoo.demo.dto.AccountDto;
import com.arshcoo.demo.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public AccountDto login(AccountDto accountDto) {
        return accountDao.login(accountDto);
    }

    @Override
    public int register(AccountDto accountDto) {
        return accountDao.register(accountDto);
    }

    @Override
    public List<AccountDto> getAccounts() {
        return accountDao.getAccounts();
    }

    @Override
    public int delete(Integer id) {
        return accountDao.delete(id);
    }

    @Override
    public AccountDto queryAccount(Integer id) {
        return accountDao.queryAccount(id);
    }

    @Override
    public int modify(AccountDto accountDto) {
        return accountDao.modify(accountDto);
    }

    @Override
    public int updateNotices() {
        return accountDao.updateNotices();
    }

    @Override
    public void clearNotices(Integer id) {
        accountDao.clearNotices(id);
    }

    @Override
    public int updateProjectsNum(Integer id) {
        return accountDao.updateProjectsNum(id);
    }

    @Override
    public void clearProjectsNum(Integer id) {
        accountDao.clearProjectsNum(id);
    }
}
