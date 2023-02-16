package cn.tedu.dao.impl;


import cn.tedu.dao.LeaveDao;
import cn.tedu.pojo.Leave;
import cn.tedu.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class LeaveDaoImpl implements LeaveDao {
    //1:创建QueryRunner类对象并通过静态工厂的方法，获取数据源
    private QueryRunner queryRunner=new QueryRunner(DruidUtil.getDataSource());
    @Override
    public int add(Leave leave) throws SQLException {
        //1定义一个sql语句
        String sql="insert into leave1 (name,sid,cause,time,status) values (?,?,?,?,?)";
        //2创建一个参数数组
        Object[] params={
                leave.getName(),
                leave.getSid(),
                leave.getCause(),
                leave.getTime(),
                leave.getStatus()
        };
        //3在返回的时候执行
        return queryRunner.update(sql,params);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        //定义sql语句
        String sql="delete from leave1 where id=?";
        return queryRunner.update(sql,id);
    }

    @Override
    public int update(Leave leave) throws SQLException {
        //定义一个sql语句
        String sql="update leave1 set name=?,sid=?,cause=?,time=?,status=? where id=?";
        Object[] parms={
                leave.getName(),
                leave.getSid(),
                leave.getCause(),
                leave.getTime(),
                leave.getStatus(),
                leave.getId()
        };
        return queryRunner.update(sql,parms);
    }

    @Override
    public Leave findById(Integer id) throws SQLException {
        //定义一个sql语句
        String sql="select * from leave1 where id=?";
        return queryRunner.query(sql,new BeanHandler<Leave>(Leave.class),id);
    }

    @Override
    public List<Leave> findAll() throws SQLException {
        //定义一个sql语句
        String sql="select * from leave1";
        return queryRunner.query(sql,new BeanListHandler<Leave>(Leave.class));
    }

    @Override
    public Leave findBySid(String sid) throws SQLException {
        String sql="select * from leave1 where sid=?";
        return queryRunner.query(sql,new BeanHandler<Leave>(Leave.class),sid);
    }

    @Override
    public List<Leave> findList(String sid,String status) throws SQLException {
        if (sid.equals("undefined")&&!status.equals("undefined")){
            String sql="select * from leave1 where status="+"'"+status+"'";
            System.out.println(sql+"sid");
            return queryRunner.query(sql,new BeanListHandler<Leave>(Leave.class));
        }
        if (status.equals("undefined")&&!sid.equals("undefined")){
            String sql="select * from leave1 where sid like '%"+sid+"%' or name like '%"+sid+"%'";
            System.out.println(sql+"status");
            return queryRunner.query(sql,new BeanListHandler<Leave>(Leave.class));
        }
        if (!sid.equals("undefined")&&!status.equals("undefined")){
            String sql="select * from leave1 where name like '%"+sid+"%' "+"and status="+"'"+status+"'";
            System.out.println(sql+"and");
            return queryRunner.query(sql,new BeanListHandler<Leave>(Leave.class));
        }if (sid.equals("undefined")&&status.equals("undefined")) {
            String sql="select * from leave1";
            return queryRunner.query(sql,new BeanListHandler<Leave>(Leave.class));
        }else {
            return null;
        }
    }

    @Override
    public int deleteBySid(String sid) throws SQLException {
        //定义sql语句
        String sql="delete from leave1 where sid=?";
        return queryRunner.update(sql,sid);
    }

    @Override
    public int updateStatus(String status,String id) throws SQLException {
        //定义一个sql语句
        String sql="update leave1 set status="+"'"+status+"'"+" where id="+"'"+id+"'";
        System.out.println(sql);
        return queryRunner.update(sql);
    }
}
