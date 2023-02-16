package cn.tedu.dao.impl;

import cn.tedu.dao.AttendanceDao;
import cn.tedu.pojo.Attendance;
import cn.tedu.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AttendanceDaoImpl implements AttendanceDao {
    //1:创建QueryRunner类对象并通过静态工厂的方法，获取数据源
    private QueryRunner queryRunner=new QueryRunner(DruidUtil.getDataSource());
    @Override
    public int add(Attendance attendance) throws SQLException {
        //1定义一个sql语句
        String sql="insert into attendance (name,sid,status,time) values (?,?,?,?)";
        //2创建一个参数数组
        Object[] params={
                attendance.getName(),
                attendance.getSid(),
                attendance.getStatus(),
                attendance.getTime(),
        };
        //3在返回的时候执行
        return queryRunner.update(sql,params);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        //定义sql语句
        String sql="delete from attendance where id=?";
        return queryRunner.update(sql,id);
    }

    @Override
    public int update(Attendance attendance) throws SQLException {
        //定义一个sql语句
        String sql="update attendance set name=?,sid=?,status=?,time=? where id=?";
        Object[] parms={
                attendance.getName(),
                attendance.getSid(),
                attendance.getStatus(),
                attendance.getTime(),
                attendance.getId()
        };
        return queryRunner.update(sql,parms);
    }

    @Override
    public Attendance findById(Integer id) throws SQLException {
        //定义一个sql语句
        String sql="select * from attendance where id=?";
        return queryRunner.query(sql,new BeanHandler<Attendance>(Attendance.class),id);
    }

    @Override
    public List<Attendance> findAll() throws SQLException {
        //定义一个sql语句
        String sql="select * from attendance";
        return queryRunner.query(sql,new BeanListHandler<Attendance>(Attendance.class));
    }

    @Override
    public Attendance findBySid(String sid) throws SQLException {
        String sql="select * from attendance where sid=?";
        return queryRunner.query(sql,new BeanHandler<Attendance>(Attendance.class),sid);
    }

    @Override
    public List<Attendance> findList(String sid, String status) throws SQLException {
        if (sid.equals("undefined")&&!status.equals("undefined")){
            String sql="select * from attendance where status="+"'"+status+"'";
            System.out.println(sql+"sid");
            return queryRunner.query(sql,new BeanListHandler<Attendance>(Attendance.class));
        }
        if (status.equals("undefined")&&!sid.equals("undefined")){
            String sql="select * from attendance where sid like '%"+sid+"%' or name like '%"+sid+"%'";
            System.out.println(sql+"status");
            return queryRunner.query(sql,new BeanListHandler<Attendance>(Attendance.class));
        }
        if (!sid.equals("undefined")&&!status.equals("undefined")){
            String sql="select * from attendance where name like '%"+sid+"%' "+"and status="+"'"+status+"'";
            System.out.println(sql+"and");
            return queryRunner.query(sql,new BeanListHandler<Attendance>(Attendance.class));
        }if (sid.equals("undefined")&&status.equals("undefined")) {
            String sql="select * from attendance";
            return queryRunner.query(sql,new BeanListHandler<Attendance>(Attendance.class));
        }else {
            return null;
        }
    }

    @Override
    public int deleteBySid(String sid) throws SQLException {
        //定义sql语句
        String sql="delete from attendance where sid=?";
        return queryRunner.update(sql,sid);
    }

    @Override
    public int updateStatus(String status, String id) throws SQLException {
        //定义一个sql语句
        String sql="update attendance set status="+"'"+status+"'"+" where sid="+"'"+id+"'";
        System.out.println(sql);
        return queryRunner.update(sql);
    }
}
