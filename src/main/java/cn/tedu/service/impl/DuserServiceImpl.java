package cn.tedu.service.impl;

import cn.tedu.dao.DuserDao;
import cn.tedu.dao.impl.DuserDaoImpl;
import cn.tedu.pojo.Duser;
import cn.tedu.service.DuserService;

import java.sql.SQLException;
import java.util.List;

public class DuserServiceImpl implements DuserService {
    //创建dao接口对象并用实现类进行实例化
    DuserDao duserDao=new DuserDaoImpl();
    @Override
    public int add(Duser duser) throws SQLException {
        return duserDao.add(duser);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        return duserDao.deleteById(id);
    }

    @Override
    public int update(Duser duser) throws SQLException {
        return duserDao.update(duser);
    }

    @Override
    public Duser findByUser(String name, String pwd) throws SQLException {
        return duserDao.findByUser(name,pwd);
    }

    @Override
    public Duser findById(Integer id) throws SQLException {
        return duserDao.findById(id);
    }

    @Override
    public List<Duser> findAll() throws SQLException {
        return duserDao.findAll();
    }
}

