package com.fh.biz.department;

import com.fh.po.department.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> findList();

    void addDept(Department dept);

    void updateDept(Department dept);

    void deleteDept(Integer[] ids);
}
