package com.arshcoo.demo.service.impl;

import com.arshcoo.demo.dao.EmployeeDao;
import com.arshcoo.demo.dto.EmployeeDto;
import com.arshcoo.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public List<EmployeeDto> getEmployee() {
        return employeeDao.getEmployee();
    }

    @Override
    public int addEmployee(EmployeeDto employeeDto) {
        return employeeDao.addEmployee(employeeDto);
    }

    @Override
    public int updateEmployee(EmployeeDto employeeDto) {
        return employeeDao.updateEmployee(employeeDto);
    }

    @Override
    public int deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }

    @Override
    public EmployeeDto queryEmployee(int id) {
        return employeeDao.queryEmployee(id);
    }

    @Override
    public EmployeeDto queryEmployeeByAccountId(Integer accountId) {
        return employeeDao.queryEmployeeByAccountId(accountId);
    }
    @Override
    public List<EmployeeDto> queryEmployeeByAreaPosition(String area,String work) {
        return employeeDao.queryEmployeeByAreaPosition(area,work);
    }

    @Override
    public List<String> getArea() {
        return employeeDao.getArea();
    }

    @Override
    public List<String> getWork() {
        return employeeDao.getWork();
    }

    @Override
    public List<EmployeeDto> queryEmployeeByArea(String area) {
        return employeeDao.queryEmployeeByArea(area);
    }

    @Override
    public List<EmployeeDto> queryEmployeeByPosition(String position) {
        return employeeDao.queryEmployeeByPosition(position);
    }

}
