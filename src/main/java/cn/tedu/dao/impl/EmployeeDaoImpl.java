package cn.tedu.dao.impl;

import cn.tedu.dao.EmployeeDao;
import cn.tedu.pojo.Employee;
import cn.tedu.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    String a="员工";
    //1:创建QueryRunner类对象并通过静态工厂的方法，获取数据源
    private QueryRunner queryRunner=new QueryRunner(DruidUtil.getDataSource());
    @Override
    public int add(Employee employee) throws SQLException {
        //1定义一个sql语句
        String sql="insert into employee (sid,name,spwd,age,sex,tel,position,department,salary) values (?,?,?,?,?,?,?,?,?)";
        //2创建一个参数数组
        Object[] params={
                employee.getSid(),
                employee.getName(),
                employee.getSpwd(),
                employee.getAge(),
                employee.getSex(),
                employee.getTel(),
                employee.getPosition(),
                employee.getDepartment(),
                employee.getSalary()
        };
        //3在返回的时候执行
        return queryRunner.update(sql,params);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        //定义sql语句
        String sql="delete from employee where u_id=?";
        return queryRunner.update(sql,id);
    }

    @Override
    public int update(Employee employee) throws SQLException {
        //定义一个sql语句
        String sql="update employee set name=?,age=?,sex=?,tel=?,sid=?,spwd=?,position=?,department=?,salary=?,s_modified=now() where u_id=?";
        //创建一个数组
        Object[] parms={
                employee.getName(),
                employee.getAge(),
                employee.getSex(),
                employee.getTel(),
                employee.getSid(),
                employee.getSpwd(),
                employee.getPosition(),
                employee.getDepartment(),
                employee.getSalary(),
                employee.getU_id()
        };
        return queryRunner.update(sql,parms);
    }

    @Override
    public Employee findByUser(String sid, String spwd) throws SQLException {
        //定义sql语句
        String a="待审核";
        String sql="select * from employee where sid=? and spwd=?";
        return queryRunner.query(sql,new BeanHandler<Employee>(Employee.class),sid,spwd);
    }

    @Override
    public Employee findById(Integer id) throws SQLException {
        //定义一个sql语句
        String sql="select * from employee where u_id=?";
        return queryRunner.query(sql,new BeanHandler<Employee>(Employee.class),id);
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        //定义一个sql语句
        String sql="select * from employee";
        return queryRunner.query(sql,new BeanListHandler<Employee>(Employee.class));
    }
    @Override
    public Employee findBySid(String sid) throws SQLException {
        //定义一个sql语句
//        String sql="select * from employee where sid like '%"+sid+"%'";
        String sql="select * from employee where sid=?";
        return queryRunner.query(sql,new BeanHandler<Employee>(Employee.class),sid);
    }

    @Override
    public List<Employee> findList(String sid) throws SQLException {
        String sql="select * from employee where sid like '%"+sid+"%' or name like '%"+sid+"%' or department like '%"+sid+"%'";
//        System.out.println(sql);
        return queryRunner.query(sql,new BeanListHandler<Employee>(Employee.class));
    }
}
