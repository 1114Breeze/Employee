package cn.tedu.pojo;

import java.io.Serializable;

public class Salary implements Serializable {
    private Integer id;
    private String name;
    private String sid;
    private String time;
    private String base;
    private String late;
    private String leave1;
    private String prize;
    private String tax;
    private String benefits;
    private String total;

    public Salary() {
    }

    public Salary(Integer id, String name, String sid, String time, String base, String late, String leave1, String prize, String tax, String benefits, String total) {
        this.id = id;
        this.name = name;
        this.sid = sid;
        this.time = time;
        this.base = base;
        this.late = late;
        this.leave1 = leave1;
        this.prize = prize;
        this.tax = tax;
        this.benefits = benefits;
        this.total = total;
    }

    public Salary(String name, String sid, String time, String base, String late, String leave1, String prize, String tax, String benefits, String total) {
        this.name = name;
        this.sid = sid;
        this.time = time;
        this.base = base;
        this.late = late;
        this.leave1 = leave1;
        this.prize = prize;
        this.tax = tax;
        this.benefits = benefits;
        this.total = total;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getLate() {
        return late;
    }

    public void setLate(String late) {
        this.late = late;
    }

    public String getLeave1() {
        return leave1;
    }

    public void setLeave1(String leave1) {
        this.leave1 = leave1;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sid='" + sid + '\'' +
                ", time='" + time + '\'' +
                ", base='" + base + '\'' +
                ", late='" + late + '\'' +
                ", leave1='" + leave1 + '\'' +
                ", prize='" + prize + '\'' +
                ", tax='" + tax + '\'' +
                ", benefits='" + benefits + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
