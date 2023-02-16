package cn.tedu.service.impl;

import cn.tedu.dao.SalaryDao;
import cn.tedu.dao.impl.SalaryDaoImpl;
import cn.tedu.pojo.Salary;
import cn.tedu.service.SalaryService;

import java.sql.SQLException;
import java.util.List;

public class SalaryServiceImpl implements SalaryService {
    SalaryDao salaryDao=new SalaryDaoImpl();
    @Override
    public int add(Salary salary) throws SQLException {
        return salaryDao.add(salary);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        return salaryDao.deleteById(id);
    }

    @Override
    public int update(Salary salary) throws SQLException {
        return salaryDao.update(salary);
    }

    @Override
    public Salary findById(Integer id) throws SQLException {
        return salaryDao.findById(id);
    }

    @Override
    public List<Salary> findAll() throws SQLException {
        return salaryDao.findAll();
    }

    @Override
    public Salary findBySid(String sid) throws SQLException {
        return salaryDao.findBySid(sid);
    }

    @Override
    public List<Salary> findList(String sid, String time) throws SQLException {
        return salaryDao.findList(sid, time);
    }

    @Override
    public int deleteBySid(String sid) throws SQLException {
        return salaryDao.deleteBySid(sid);
    }
}
