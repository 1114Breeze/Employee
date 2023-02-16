package cn.tedu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Attendance implements Serializable {
    //定义私有属性
    private Integer id;
    private String name;
    private String sid;
    private String status;
    private String time;

    public Attendance() {
    }

    public Attendance(String name, String sid, String status, String time) {
        this.name = name;
        this.sid = sid;
        this.status = status;
        this.time = time;
    }

    public Attendance(Integer id, String name, String sid, String status, String time) {
        this.id = id;
        this.name = name;
        this.sid = sid;
        this.status = status;
        this.time = time;
    }

    public Attendance(String name, String sid, String status) {
        this.name = name;
        this.sid = sid;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sid='" + sid + '\'' +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
