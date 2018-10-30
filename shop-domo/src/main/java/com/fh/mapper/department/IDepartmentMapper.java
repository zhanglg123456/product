package com.fh.mapper.department;

import com.fh.po.department.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDepartmentMapper {


    List<Department> findList();

    void addDept(Department dept);

    void updateDept(Department dept);

    void deleteDept(@Param("ids") Integer[] ids);
}
