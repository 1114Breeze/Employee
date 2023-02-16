package cn.tedu.service.impl;

import cn.tedu.dao.AttendanceDao;
import cn.tedu.dao.impl.AttendanceDaoImpl;
import cn.tedu.pojo.Attendance;
import cn.tedu.service.AttendanceService;

import java.sql.SQLException;
import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {
    AttendanceDao attendanceDao= new AttendanceDaoImpl();
    @Override
    public int add(Attendance attendance) throws SQLException {
        return attendanceDao.add(attendance);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        return attendanceDao.deleteById(id);
    }

    @Override
    public int update(Attendance attendance) throws SQLException {
        return attendanceDao.update(attendance);
    }

    @Override
    public Attendance findById(Integer id) throws SQLException {
        return attendanceDao.findById(id);
    }

    @Override
    public List<Attendance> findAll() throws SQLException {
        return attendanceDao.findAll();
    }

    @Override
    public Attendance findBySid(String sid) throws SQLException {
        return attendanceDao.findBySid(sid);
    }

    @Override
    public List<Attendance> findList(String sid, String id) throws SQLException {
        return attendanceDao.findList(sid, id);
    }

    @Override
    public int deleteBySid(String sid) throws SQLException {
        return attendanceDao.deleteBySid(sid);
    }

    @Override
    public int updateStatus(String status, String id) throws SQLException {
        return attendanceDao.updateStatus(status, id);
    }
}
