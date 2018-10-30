package com.fh.po.emptoyee;

import com.fh.util.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Emptoyee extends Page implements Serializable {

    private static final long serialVersionUID = 7142482159737678908L;
    private Integer id;
    private  String emptoyeeName;
    private int sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private  int salary;
    private int deptId;

    private Integer minSalary;
    private Integer maxSalary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date minbirthday;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date maxbirthday;

    private  String deptName;

    private String depts;
    private List<Integer> deptIdsList;


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getDepts() {
        return depts;
    }

    public void setDepts(String depts) {
        this.depts = depts;
    }

    public List<Integer> getDeptIdsList() {
        return deptIdsList;
    }

    public void setDeptIdsList(List<Integer> deptIdsList) {
        this.deptIdsList = deptIdsList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmptoyeeName() {
        return emptoyeeName;
    }

    public void setEmptoyeeName(String emptoyeeName) {
        this.emptoyeeName = emptoyeeName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public Date getMinbirthday() {
        return minbirthday;
    }

    public void setMinbirthday(Date minbirthday) {
        this.minbirthday = minbirthday;
    }

    public Date getMaxbirthday() {
        return maxbirthday;
    }

    public void setMaxbirthday(Date maxbirthday) {
        this.maxbirthday = maxbirthday;
    }

    @Override
    public String toString() {
        return "Emptoyee{" +
                "id=" + id +
                ", emptoyeeName='" + emptoyeeName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", salary=" + salary +
                ", deptId=" + deptId +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", minbirthday=" + minbirthday +
                ", maxbirthday=" + maxbirthday +
                ", deptName='" + deptName + '\'' +
                ", depts='" + depts + '\'' +
                ", deptIdsList=" + deptIdsList +
                '}';
    }
}
