package cn.tedu.service;

import cn.tedu.pojo.Salary;

import java.sql.SQLException;
import java.util.List;

public interface SalaryService {
    //定义一个接口方法用于完成添加记录
    public int add(Salary salary) throws SQLException;
    //定义一个方法用于根据id进行删除
    public int deleteById(Integer id) throws SQLException;
    //定义一个方法用于更新记录
    public int update(Salary salary) throws SQLException;
    //定义一个方法根据id进行查记录
    public Salary findById(Integer id) throws SQLException;
    //定义一个方法用于完成所有的记录查询
    public List<Salary> findAll() throws SQLException;
    public Salary findBySid(String sid) throws SQLException;
    public List<Salary> findList(String sid,String time) throws SQLException;
    public int deleteBySid(String sid) throws SQLException;
}
