package cn.tedu.service.impl;

import cn.tedu.dao.EmployeeDao;
import cn.tedu.dao.impl.EmployeeDaoImpl;
import cn.tedu.pojo.Employee;
import cn.tedu.service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDao employeeDao= new EmployeeDaoImpl();
    @Override
    public int add(Employee employee) throws SQLException {
        return employeeDao.add(employee);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        return employeeDao.deleteById(id);
    }

    @Override
    public int update(Employee employee) throws SQLException {
        return employeeDao.update(employee);
    }

    @Override
    public Employee findByUser(String sid, String spwd) throws SQLException {
        return employeeDao.findByUser(sid,spwd);
    }

    @Override
    public Employee findById(Integer id) throws SQLException {
        return employeeDao.findById(id);
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        return employeeDao.findAll();
    }

    @Override
    public Employee findBySid(String sid) throws SQLException {
        return employeeDao.findBySid(sid);
    }

    @Override
    public List<Employee> findList(String sid) throws SQLException {
        return employeeDao.findList(sid);
    }
}
