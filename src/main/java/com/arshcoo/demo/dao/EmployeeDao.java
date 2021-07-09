package com.arshcoo.demo.dao;

import com.arshcoo.demo.dto.EmployeeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmployeeDao {
    //查找员工个人信息
    List<EmployeeDto> getEmployee();
    //添加信息
    int addEmployee(EmployeeDto employeeDto);
    //修改员工个人信息
    int updateEmployee(EmployeeDto employeeDto);
    //删除员工信息
    int deleteEmployee(int id);
    //通过员工ID获取信息
    EmployeeDto queryEmployee(int id);
    //通过账号id获取账员工信息
    EmployeeDto queryEmployeeByAccountId(Integer accountId);
    //通过地区和职位查找员工信息
    List<EmployeeDto> queryEmployeeByAreaPosition(String area,String work);
    //获取地区
    List<String> getArea();
    //获取职位
    List<String> getWork();
    //单独通过地区搜索员工信息
    List<EmployeeDto> queryEmployeeByArea(String area);
    //单独通过职位搜索员工信息
    List<EmployeeDto> queryEmployeeByPosition(String position);
}
