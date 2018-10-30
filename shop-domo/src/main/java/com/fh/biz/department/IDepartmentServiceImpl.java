package com.fh.biz.department;

import com.fh.biz.emptoyee.IEmptoyeeService;
import com.fh.mapper.department.IDepartmentMapper;
import com.fh.po.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "iDepartmentService")
public class IDepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentMapper iDepartmentMapper;

    @Resource(name = "iEmptoyeeService")
    private IEmptoyeeService iEmptoyeeService;

    @Override
    public List<Department> findList() {
        return iDepartmentMapper.findList();
    }

    @Override
    public void addDept(Department dept) {
        iDepartmentMapper.addDept(dept);
    }

    @Override
    public void updateDept(Department dept) {
        iDepartmentMapper.updateDept(dept);
    }

    @Override
    public void deleteDept(Integer[] ids) {
        iDepartmentMapper.deleteDept(ids);

        //根据部门ID删除部门下的员工
        iEmptoyeeService.deleteEmpByDeptId(ids);
    }
}
