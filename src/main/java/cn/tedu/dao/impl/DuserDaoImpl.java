package cn.tedu.dao.impl;

import cn.tedu.dao.DuserDao;
import cn.tedu.pojo.Duser;
import cn.tedu.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * QueryRunner类（org.apache.commons.dbutils.QueryRunner)是Dbutils的核心类之一
 * 它简化了sql查询
 */
public class DuserDaoImpl implements DuserDao {
    //1:创建QueryRunner类对象并通过静态工厂的方法，获取数据源
    private QueryRunner queryRunner=new QueryRunner(DruidUtil.getDataSource());
    @Override
    public int add(Duser duser) throws SQLException {
        //1定义一个sql语句
        String sql="insert into duser (name,pwd,email) values (?,?,?)";
        //2创建一个参数数组
        Object[] params={
                duser.getName(),
                duser.getPwd(),
                duser.getEmail()
        };
        //3在返回的时候执行
        return queryRunner.update(sql,params);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        //定义sql语句
        String sql="delete from duser where pk_id=?";
        return queryRunner.update(sql,id);
    }

    @Override
    public int update(Duser duser) throws SQLException {
        //定义一个sql语句
        String sql="update duser set name=?,pwd=?,email=?,d_modified=now() where pk_id=?";
        //创建一个数组
        Object[] parms={
                duser.getName(),
                duser.getPwd(),
                duser.getEmail(),
                duser.getPk_id()
        };
        return queryRunner.update(sql,parms);
    }

    @Override
    public Duser findByUser(String name, String pwd) throws SQLException {
        //定义sql语句
        String sql="select * from duser where name=? and pwd=?";
        return queryRunner.query(sql,new BeanHandler<Duser>(Duser.class),name,pwd);
    }

    @Override
    public Duser findById(Integer id) throws SQLException {
        //定义一个sql语句
        String sql="select * from duser where pk_id=?";
        return queryRunner.query(sql,new BeanHandler<Duser>(Duser.class),id);
    }

    @Override
    public List<Duser> findAll() throws SQLException {
        //定义一个sql语句
        String sql="select * from duser";
        return queryRunner.query(sql,new BeanListHandler<Duser>(Duser.class));
    }

}
