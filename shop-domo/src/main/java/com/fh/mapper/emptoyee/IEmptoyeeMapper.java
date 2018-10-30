package com.fh.mapper.emptoyee;

import com.fh.common.ServerResponse;
import com.fh.po.admin.User;
import com.fh.po.emptoyee.Emptoyee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEmptoyeeMapper {
    int queryEmpCount(Emptoyee emptoyee);

    List<Emptoyee> findEmpListByPage(Emptoyee emptoyee);

    void addEmp(Emptoyee emptoyee);

    Emptoyee showbackEmp(Integer id);

    void updateEmp(Emptoyee emptoyee);

    void deleteEmp(List<Integer> list);

    void deleteEmpByDeptId(@Param("ids") Integer[] ids);

    void updateEmpDept(@Param("ids") Integer[] ids,@Param("deptId") Integer deptId);

    User loginUser(String username);


}
