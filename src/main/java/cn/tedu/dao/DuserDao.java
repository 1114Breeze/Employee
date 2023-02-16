package cn.tedu.dao;

import cn.tedu.pojo.Duser;

import java.sql.SQLException;
import java.util.List;

public interface DuserDao {
    //定义一个接口方法用于完成添加记录
    public int add(Duser duser) throws SQLException;
    //定义一个方法用于根据id进行删除
    public int deleteById(Integer id) throws SQLException;
    //定义一个方法用于更新记录
    public int update(Duser duser) throws SQLException;
    //定义一个方法用于用户登陆
    public Duser findByUser(String name,String pwd) throws SQLException;
    //定义一个方法根据id进行查记录
    public Duser findById(Integer id) throws SQLException;
    //定义一个方法用于完成所有的记录查询
    public List<Duser> findAll() throws SQLException;
}
