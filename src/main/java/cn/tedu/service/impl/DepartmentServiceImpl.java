package cn.tedu.service.impl;

import cn.tedu.dao.AnnouncementDao;
import cn.tedu.dao.DepartmentDao;
import cn.tedu.dao.impl.AnnouncementDaoImpl;
import cn.tedu.dao.impl.DepartmentDaoImpl;
import cn.tedu.pojo.Announcement;
import cn.tedu.pojo.Department;
import cn.tedu.service.AnnouncementService;
import cn.tedu.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;


public class DepartmentServiceImpl implements DepartmentService {
    //创建dao接口对象并用实现类进行实例化
    DepartmentDao departmentDao=new DepartmentDaoImpl();

    @Override
    public int add(Department department) throws SQLException {
        return departmentDao.add(department);
    }

    @Override
    public int deleteById(Integer id) throws SQLException {
        return departmentDao.deleteById(id);
    }

    @Override
    public int update(Department department) throws SQLException {
        return departmentDao.update(department);
    }

    @Override
    public Department findById(Integer id) throws SQLException {
        return departmentDao.findById(id);
    }

    @Override
    public List<Department> findAll() throws SQLException {
        return departmentDao.findAll();
    }
}
