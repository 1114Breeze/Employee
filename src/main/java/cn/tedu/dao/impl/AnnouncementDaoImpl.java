package cn.tedu.dao.impl;

import cn.tedu.dao.AnnouncementDao;
import cn.tedu.pojo.Announcement;
import cn.tedu.pojo.Duser;
import cn.tedu.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AnnouncementDaoImpl implements AnnouncementDao {
    //1:创建QueryRunner类对象并通过静态工厂的方法，获取数据源
    private QueryRunner queryRunner=new QueryRunner(DruidUtil.getDataSource());
    @Override
    public int add(Announcement announcement) throws SQLException {
        //1定义一个sql语句
        String sql="insert into announcement (title,content) values (?,?)";
        //2创建一个参数数组
        Object[] params={
                announcement.getTitle(),
                announcement.getContent()
        };
        //3在返回的时候执行
        return queryRunner.update(sql,params);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        //定义sql语句
        String sql="delete from announcement where id=?";
        return queryRunner.update(sql,id);
    }

    @Override
    public int update(Announcement announcement) throws SQLException {
        //定义一个sql语句
        String sql="update announcement set title=?,content=?,a_modified=now() where id=?";
        //创建一个数组
        Object[] parms={
                announcement.getTitle(),
                announcement.getContent(),
                announcement.getId()
        };
        return queryRunner.update(sql,parms);
    }

    @Override
    public Announcement findById(Integer id) throws SQLException {
        //定义一个sql语句
        String sql="select * from announcement where id=?";
        return queryRunner.query(sql,new BeanHandler<Announcement>(Announcement.class),id);
    }

    @Override
    public List<Announcement> findAll() throws SQLException {
        //定义一个sql语句
        String sql="select * from announcement";
        return queryRunner.query(sql,new BeanListHandler<Announcement>(Announcement.class));
    }
}
