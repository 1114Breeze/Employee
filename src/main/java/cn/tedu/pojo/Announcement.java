package cn.tedu.pojo;

import java.io.Serializable;
import java.util.Date;

public class Announcement implements Serializable {
    //定义私有属性
    private Integer id;
    private String title;
    private String content;
    private Date a_create;
    private Date a_modified;

    public Announcement() {
    }

    public Announcement(String title, String content, Date a_create, Date a_modified) {
        this.title = title;
        this.content = content;
        this.a_create = a_create;
        this.a_modified = a_modified;
    }

    public Announcement(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getA_create() {
        return a_create;
    }

    public void setA_create(Date a_create) {
        this.a_create = a_create;
    }

    public Date getA_modified() {
        return a_modified;
    }

    public void setA_modified(Date a_modified) {
        this.a_modified = a_modified;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", a_create=" + a_create +
                ", a_modified=" + a_modified +
                '}';
    }
}
