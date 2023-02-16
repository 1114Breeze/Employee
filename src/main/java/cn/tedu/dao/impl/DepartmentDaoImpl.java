package cn.tedu.dao.impl;

import cn.tedu.dao.DepartmentDao;
import cn.tedu.pojo.Announcement;
import cn.tedu.pojo.Department;
import cn.tedu.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    //1:创建QueryRunner类对象并通过静态工厂的方法，获取数据源
    private QueryRunner queryRunner=new QueryRunner(DruidUtil.getDataSource());
    @Override
    public int add(Department department) throws SQLException {
        //1定义一个sql语句
        String sql="insert into department (department) values (?)";
        //2创建一个参数数组
        Object[] params={
                department.getDepartment()
        };
        //3在返回的时候执行
        return queryRunner.update(sql,params);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        //定义sql语句
        String sql="delete from department where id=?";
        return queryRunner.update(sql,id);
    }

    @Override
    public int update(Department department) throws SQLException {
        //定义一个sql语句
        String sql="update department set department=? where id=?";
        //创建一个数组
        Object[] parms={
                department.getDepartment(),
                department.getId()
        };
        return queryRunner.update(sql,parms);
    }

    @Override
    public Department findById(Integer id) throws SQLException {
        //定义一个sql语句
        String sql="select * from department where id=?";
        return queryRunner.query(sql,new BeanHandler<Department>(Department.class),id);
    }

    @Override
    public List<Department> findAll() throws SQLException {
        //定义一个sql语句
        String sql="select * from department";
        return queryRunner.query(sql,new BeanListHandler<Department>(Department.class));
    }
}
