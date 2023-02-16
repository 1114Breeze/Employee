package cn.tedu.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类：在原讲解基础上，加上一个序列化：就是为了保存在内存中状态
 *   implements Serializable
 */
public class Duser implements Serializable {
    //定义私有属性
    private Integer pk_id;
    private String name;
    private String pwd;
    private String email;
    private Date d_create;
    private Date d_modified;
    //添加构造方法
    public Duser(){}

    public Duser(String name, String pwd, String email) {
        this.name = name;
        this.pwd = pwd;
        this.email = email;
    }

    public Duser(Integer pk_id, String name, String pwd, String email, Date d_create, Date d_modified) {
        this.pk_id = pk_id;
        this.name = name;
        this.pwd = pwd;
        this.email = email;
        this.d_create = d_create;
        this.d_modified = d_modified;
    }
    //添加set和get方法

    public Integer getPk_id() {
        return pk_id;
    }

    public void setPk_id(Integer pk_id) {
        this.pk_id = pk_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getD_create() {
        return d_create;
    }

    public void setD_create(Date d_create) {
        this.d_create = d_create;
    }

    public Date getD_modified() {
        return d_modified;
    }

    public void setD_modified(Date d_modified) {
        this.d_modified = d_modified;
    }
    //添加一个toString方法

    @Override
    public String toString() {
        return "Duser{" +
                "pk_id=" + pk_id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", d_create=" + d_create +
                ", d_modified=" + d_modified +
                '}';
    }
}
