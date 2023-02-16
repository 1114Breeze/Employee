package cn.tedu.service;

import cn.tedu.pojo.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    //定义一个接口方法用于完成添加记录
    public int add(Employee employee) throws SQLException;
    //定义一个方法用于根据id进行删除
    public int deleteById(Integer id) throws SQLException;
    //定义一个方法用于更新记录
    public int update(Employee employee) throws SQLException;
    //定义一个方法用于用户登陆
    public Employee findByUser(String sid, String spwd) throws SQLException;
    //定义一个方法根据id进行查记录
    public Employee findById(Integer id) throws SQLException;
    //定义一个方法用于完成所有的记录查询
    public List<Employee> findAll() throws SQLException;
    public Employee findBySid(String sid) throws SQLException;
    public List<Employee> findList(String sid) throws SQLException;
}
