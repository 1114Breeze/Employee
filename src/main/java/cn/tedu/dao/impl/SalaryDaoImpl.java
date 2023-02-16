package cn.tedu.dao.impl;

import cn.tedu.dao.SalaryDao;
import cn.tedu.pojo.Leave;
import cn.tedu.pojo.Salary;
import cn.tedu.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class SalaryDaoImpl implements SalaryDao {
    //1:创建QueryRunner类对象并通过静态工厂的方法，获取数据源
    private QueryRunner queryRunner=new QueryRunner(DruidUtil.getDataSource());
    @Override
    public int add(Salary salary) throws SQLException {
        //1定义一个sql语句
        String sql="insert into salary (name,sid,time,base,late,leave1,prize,tax,benefits,total) values (?,?,?,?,?,?,?,?,?,?)";
        //2创建一个参数数组
        Object[] params={
                salary.getName(),
                salary.getSid(),
                salary.getTime(),
                salary.getBase(),
                salary.getLate(),
                salary.getLeave1(),
                salary.getPrize(),
                salary.getTax(),
                salary.getBenefits(),
                salary.getTotal()
        };
        //3在返回的时候执行
        return queryRunner.update(sql,params);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        //定义sql语句
        String sql="delete from salary where id=?";
        return queryRunner.update(sql,id);
    }

    @Override
    public int update(Salary salary) throws SQLException {
        //定义一个sql语句
        String sql="update salary set name=?,sid=?,time=?,base=?,late=?,leave1=?,prize=?,tax=?,benefits=?,total=? where id=?";
        Object[] parms={
                salary.getName(),
                salary.getSid(),
                salary.getTime(),
                salary.getBase(),
                salary.getLate(),
                salary.getLeave1(),
                salary.getPrize(),
                salary.getTax(),
                salary.getBenefits(),
                salary.getTotal(),
                salary.getId()
        };
        return queryRunner.update(sql,parms);
    }

    @Override
    public Salary findById(Integer id) throws SQLException {
        //定义一个sql语句
        String sql="select * from salary where id=?";
        return queryRunner.query(sql,new BeanHandler<Salary>(Salary.class),id);
    }

    @Override
    public List<Salary> findAll() throws SQLException {
        //定义一个sql语句
        String sql="select * from salary";
        return queryRunner.query(sql,new BeanListHandler<Salary>(Salary.class));
    }

    @Override
    public Salary findBySid(String sid) throws SQLException {
        String sql="select * from salary where sid=?";
        return queryRunner.query(sql,new BeanHandler<Salary>(Salary.class),sid);
    }

    @Override
    public List<Salary> findList(String sid, String time) throws SQLException {
        if (sid.equals("undefined")&&!time.equals("undefined")){
            String sql="select * from salary where time="+"'"+time+"'";
            System.out.println(sql+"sid");
            return queryRunner.query(sql,new BeanListHandler<Salary>(Salary.class));
        }
        if (time.equals("undefined")&&!sid.equals("undefined")){
            String sql="select * from salary where sid like '%"+sid+"%' or name like '%"+sid+"%'";
            System.out.println(sql+"time");
            return queryRunner.query(sql,new BeanListHandler<Salary>(Salary.class));
        }
        if (!sid.equals("undefined")&&!time.equals("undefined")){
            String sql="select * from salary where name like '%"+sid+"%' "+"and time="+"'"+time+"'";
            System.out.println(sql+"and");
            return queryRunner.query(sql,new BeanListHandler<Salary>(Salary.class));
        }if (sid.equals("undefined")&&time.equals("undefined")) {
            String sql="select * from salary";
            return queryRunner.query(sql,new BeanListHandler<Salary>(Salary.class));
        }else {
            return null;
        }
    }

    @Override
    public int deleteBySid(String sid) throws SQLException {
        //定义sql语句
        String sql="delete from salary where sid=?";
        return queryRunner.update(sql,sid);
    }

}
