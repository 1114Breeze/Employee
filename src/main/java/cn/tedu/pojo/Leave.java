package cn.tedu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Leave implements Serializable {
    //定义私有属性
    private Integer id;
    private String name;
    private String sid;
    private String cause;
    private String time;
    private String status;

    public Leave() {
    }

    public Leave(String name, String sid, String cause, String status) {
        this.name = name;
        this.sid = sid;
        this.cause = cause;
        this.status = status;
    }

    public Leave(String name, String sid, String cause, String time, String status) {
        this.name = name;
        this.sid = sid;
        this.cause = cause;
        this.time = time;
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

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sid='" + sid + '\'' +
                ", cause='" + cause + '\'' +
                ", time=" + time +
                ", status='" + status + '\'' +
                '}';
    }
}
