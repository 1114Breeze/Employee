package cn.tedu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
    //定义私有属性
    private Integer u_id;
    private String name;
    private String spwd;
    private Integer age;
    private String sex;
    private String sid;
    private String tel;
    private String position;
    private String department;
    private String salary;
    private Date s_create;
    private Date s_modified;

    public Employee() {
    }

    public Employee(String spwd, String sid) {
        this.spwd = spwd;
        this.sid = sid;
    }

    public Employee(String sid, String name, String spwd, Integer age, String sex, String tel, String position,String department, String salary) {
        this.name = name;
        this.spwd = spwd;
        this.age = age;
        this.sex = sex;
        this.sid = sid;
        this.tel = tel;
        this.position = position;
        this.department = department;
        this.salary = salary;
    }
    public Employee(String sid, String name, String spwd, Integer age, String sex, String tel, String position,String department) {
        this.name = name;
        this.spwd = spwd;
        this.age = age;
        this.sex = sex;
        this.sid = sid;
        this.tel = tel;
        this.position = position;
        this.department = department;
    }

    public Employee(String sid, String name, String spwd, Integer age, String sex, String tel, String position, String salary, Date s_create, Date s_modified) {
        this.name = name;
        this.spwd = spwd;
        this.age = age;
        this.sex = sex;
        this.sid = sid;
        this.tel = tel;
        this.position = position;
        this.salary = salary;
        this.s_create = s_create;
        this.s_modified = s_modified;
    }

    public Employee(String sid, String name, String spwd, Integer age, String sex, String tel) {
        this.name = name;
        this.spwd = spwd;
        this.age = age;
        this.sex = sex;
        this.sid = sid;
        this.tel = tel;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpwd() {
        return spwd;
    }

    public void setSpwd(String spwd) {
        this.spwd = spwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getS_create() {
        return s_create;
    }

    public void setS_create(Date s_create) {
        this.s_create = s_create;
    }

    public Date getS_modified() {
        return s_modified;
    }

    public void setS_modified(Date s_modified) {
        this.s_modified = s_modified;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "u_id=" + u_id +
                ", name='" + name + '\'' +
                ", spwd='" + spwd + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", sid='" + sid + '\'' +
                ", tel='" + tel + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", s_create=" + s_create +
                ", s_modified=" + s_modified +
                '}';
    }
}
