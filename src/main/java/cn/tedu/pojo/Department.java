package cn.tedu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Department implements Serializable {
    //定义私有属性
    private Integer id;
    private String department;

    public Department() {
    }

    public Department(Integer id, String department) {
        this.id = id;
        this.department = department;
    }

    public Department(String department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", department='" + department + '\'' +
                '}';
    }
}
