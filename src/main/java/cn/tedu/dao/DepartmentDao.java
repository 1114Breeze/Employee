package cn.tedu.dao;

import cn.tedu.pojo.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao {
    //定义一个接口方法用于完成添加记录
    public int add(Department department) throws SQLException;
    //定义一个方法用于根据id进行删除
    public int deleteById(Integer id) throws SQLException;
    //定义一个方法用于更新记录
    public int update(Department department) throws SQLException;
    //定义一个方法根据id进行查记录
    public Department findById(Integer id) throws SQLException;
    //定义一个方法用于完成所有的记录查询
    public List<Department> findAll() throws SQLException;
}
