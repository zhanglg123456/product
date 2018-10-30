package com.fh.biz.emptoyee;

import com.fh.common.ServerResponse;
import com.fh.po.admin.User;
import com.fh.po.emptoyee.Emptoyee;

import java.util.List;

public interface IEmptoyeeService {
    int queryEmpCount(Emptoyee emptoyee);

    List<Emptoyee> findEmpListByPage(Emptoyee emptoyee);

    void addEmp(Emptoyee emptoyee);

    Emptoyee showbackEmp(Integer id);

    void updateEmp(Emptoyee emptoyee);

    void deleteEmp(String ids);

    void deleteEmpByDeptId(Integer[] ids);

    void updateEmpDept(Integer[] ids, Integer deptId);



}
