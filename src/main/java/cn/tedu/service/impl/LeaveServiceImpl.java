package cn.tedu.service.impl;

import cn.tedu.dao.EmployeeDao;
import cn.tedu.dao.LeaveDao;
import cn.tedu.dao.impl.EmployeeDaoImpl;
import cn.tedu.dao.impl.LeaveDaoImpl;
import cn.tedu.pojo.Leave;
import cn.tedu.service.LeaveService;

import java.sql.SQLException;
import java.util.List;

public class LeaveServiceImpl implements LeaveService {
    LeaveDao leaveDao= new LeaveDaoImpl();
    @Override
    public int add(Leave leave) throws SQLException {
        return leaveDao.add(leave);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        return leaveDao.deleteById(id);
    }

    @Override
    public int update(Leave leave) throws SQLException {
        return leaveDao.update(leave);
    }

    @Override
    public Leave findById(Integer id) throws SQLException {
        return leaveDao.findById(id);
    }

    @Override
    public List<Leave> findAll() throws SQLException {
        return leaveDao.findAll();
    }

    @Override
    public Leave findBySid(String sid) throws SQLException {
        return leaveDao.findBySid(sid);
    }

    @Override
    public List<Leave> findList(String sid,String status) throws SQLException {
        return leaveDao.findList(sid,status);
    }

    @Override
    public int deleteBySid(String sid) throws SQLException {
        return leaveDao.deleteBySid(sid);
    }

    @Override
    public int updateStatus(String status,String id) throws SQLException {
        return leaveDao.updateStatus(status,id);
    }
}
