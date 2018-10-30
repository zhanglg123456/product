package com.fh.biz.emptoyee;

import com.fh.common.ServerResponse;
import com.fh.mapper.emptoyee.IEmptoyeeMapper;
import com.fh.po.admin.User;
import com.fh.po.emptoyee.Emptoyee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "iEmptoyeeService")
public class IEmptoyeeServiceImpl implements IEmptoyeeService {

    @Autowired
    private IEmptoyeeMapper iEmptoyeeMapper;

    @Override
    public int queryEmpCount(Emptoyee emptoyee) {
        return iEmptoyeeMapper.queryEmpCount(emptoyee);
    }

    @Override
    public List<Emptoyee> findEmpListByPage(Emptoyee emptoyee) {
        return iEmptoyeeMapper.findEmpListByPage(emptoyee);
    }

    @Override
    public void addEmp(Emptoyee emptoyee) {
        iEmptoyeeMapper.addEmp(emptoyee);
    }

    @Override
    public Emptoyee showbackEmp(Integer id) {
        return iEmptoyeeMapper.showbackEmp(id);
    }

    @Override
    public void updateEmp(Emptoyee emptoyee) {
        iEmptoyeeMapper.updateEmp(emptoyee);
    }

    @Override
    public void deleteEmp(String ids) {

        if(null!=ids && !ids.equals("")){
            String[] split = ids.split(",");

            List<Integer> list = new ArrayList<>();

            for (String s : split) {
                list.add(Integer.parseInt(s));

            }

            iEmptoyeeMapper.deleteEmp(list);
        }
    }

    @Override
    public void deleteEmpByDeptId(Integer[] ids) {
        iEmptoyeeMapper.deleteEmpByDeptId(ids);
    }

    @Override
    public void updateEmpDept(Integer[] ids, Integer deptId) {
        iEmptoyeeMapper.updateEmpDept(ids,deptId);
    }



}
