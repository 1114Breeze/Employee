package cn.tedu.service.impl;

import cn.tedu.dao.AnnouncementDao;
import cn.tedu.dao.DuserDao;
import cn.tedu.dao.impl.AnnouncementDaoImpl;
import cn.tedu.dao.impl.DuserDaoImpl;
import cn.tedu.pojo.Announcement;
import cn.tedu.service.AnnouncementService;

import java.sql.SQLException;
import java.util.List;

public class AnnouncementServiceImpl implements AnnouncementService {
    //创建dao接口对象并用实现类进行实例化
    AnnouncementDao announcementDao=new AnnouncementDaoImpl();
    @Override
    public int add(Announcement announcement) throws SQLException {
        return announcementDao.add(announcement);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        return announcementDao.deleteById(id);
    }

    @Override
    public int update(Announcement announcement) throws SQLException {
        return announcementDao.update(announcement);
    }

    @Override
    public Announcement findById(Integer id) throws SQLException {
        return announcementDao.findById(id);
    }

    @Override
    public List<Announcement> findAll() throws SQLException {
        return announcementDao.findAll();
    }
}
