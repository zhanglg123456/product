package com.fh.po.department;

import java.io.Serializable;

public class Department implements Serializable {
    private static final long serialVersionUID = -7412918999146155133L;

    private Integer id;
    private int parentId;
    private String departmentName;
    private String discraption;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDiscraption() {
        return discraption;
    }

    public void setDiscraption(String discraption) {
        this.discraption = discraption;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", departmentName='" + departmentName + '\'' +
                ", discraption='" + discraption + '\'' +
                '}';
    }
}
