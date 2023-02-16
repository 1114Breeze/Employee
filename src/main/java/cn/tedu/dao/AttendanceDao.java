package cn.tedu.dao;

import cn.tedu.pojo.Attendance;
import cn.tedu.pojo.Leave;

import java.sql.SQLException;
import java.util.List;

public interface AttendanceDao {
    //定义一个接口方法用于完成添加记录
    public int add(Attendance attendance) throws SQLException;
    //定义一个方法用于根据id进行删除
    public int deleteById(Integer id) throws SQLException;
    //定义一个方法用于更新记录
    public int update(Attendance attendance) throws SQLException;
    //定义一个方法根据id进行查记录
    public Attendance findById(Integer id) throws SQLException;
    //定义一个方法用于完成所有的记录查询
    public List<Attendance> findAll() throws SQLException;
    public Attendance findBySid(String sid) throws SQLException;
    public List<Attendance> findList(String sid,String id) throws SQLException;
    public int deleteBySid(String sid) throws SQLException;
    //定义一个方法用于更新记录
    public int updateStatus(String status,String id) throws SQLException;
}
