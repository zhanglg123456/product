package com.fh.controller;

import com.fh.biz.department.IDepartmentService;
import com.fh.biz.emptoyee.IEmptoyeeService;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.common.SystemConst;
import com.fh.po.admin.User;
import com.fh.po.department.Department;
import com.fh.po.emptoyee.Emptoyee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Resource(name = "iDepartmentService")
    private IDepartmentService iDepartmentService;

    @Resource(name = "iEmptoyeeService")
    private IEmptoyeeService iEmptoyeeService;




    @RequestMapping(value = "/emp/updateEmpDept" ,method = RequestMethod.POST)
    @ResponseBody
    public  ServerResponse updateEmpDept(@RequestParam("ids[]") Integer [] ids ,@RequestParam("deptId") Integer deptId){



            iEmptoyeeService.updateEmpDept(ids,deptId);

        return ServerResponse.success();


    }

    @RequestMapping("/emp/deleteEmp")
    @ResponseBody
    public  ServerResponse deleteEmp(String ids){


            iEmptoyeeService.deleteEmp(ids);

        return ServerResponse.success();


    }

    @RequestMapping("/emp/updateEmp")
    @ResponseBody
    public ServerResponse updateEmp(Emptoyee emptoyee){

            iEmptoyeeService.updateEmp(emptoyee);


        return ServerResponse.success();


    }

    @RequestMapping("emp/showbackEmp")
    @ResponseBody
    public  ServerResponse showbackEmp(Integer id){

            Emptoyee emptoyee = iEmptoyeeService.showbackEmp(id);

        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");

            Map map1 = new HashMap();
            map1.put("id",emptoyee.getId());
            map1.put("deptId",emptoyee.getDeptId());
            map1.put("empName",emptoyee.getEmptoyeeName());
            map1.put("deptName",emptoyee.getDeptName());
            map1.put("sex",emptoyee.getSex());
            map1.put("salary",emptoyee.getSalary());
            map1.put("birthday",sim.format(emptoyee.getBirthday()));


        return  ServerResponse.success(map1);

    }


    @RequestMapping("/emp/addEmp")
    @ResponseBody
    public ServerResponse addEmp(Emptoyee emptoyee){


            iEmptoyeeService.addEmp(emptoyee);

        return ServerResponse.success();

    }

    @RequestMapping("/emp/findEmpList")
    @ResponseBody
    public  ServerResponse findEmp(Emptoyee emptoyee){
        if(emptoyee.getDepts()!=null && !emptoyee.getDepts().equals("")){
            String[] split = emptoyee.getDepts().split(",");
            List<Integer> list =new ArrayList<>();
            for (String s : split) {
                list.add(Integer.parseInt(s));
            }
            emptoyee.setDeptIdsList(list);

        }
        Map map = new HashMap();
        map.put("draw",emptoyee.getDraw());
        int count = iEmptoyeeService.queryEmpCount(emptoyee);
        //总条数
        map.put("recordsTotal",count);
        map.put("recordsFiltered",count);

        List<Emptoyee> emptoyeeList = iEmptoyeeService.findEmpListByPage(emptoyee);
        map.put("data",emptoyeeList);


        return ServerResponse.success(map);

    }


    @RequestMapping("/department/delete")
    @ResponseBody
    public ServerResponse deleteDept(@RequestParam("ids[]") Integer [] ids){


            iDepartmentService.deleteDept(ids);

        return ServerResponse.success();

    }

    @RequestMapping("/department/update")
    @ResponseBody
    public  ServerResponse updateDept(Department dept){


            iDepartmentService.updateDept(dept);

        return ServerResponse.success();

    }

    @RequestMapping("/department/addDept")
    @ResponseBody public  ServerResponse addDept(Department dept){


            iDepartmentService.addDept(dept);


        return ServerResponse.success(dept.getId());

    }

    @RequestMapping("/department/findList")
    @ResponseBody
    public ServerResponse findList(){
        List<Department>  departments = iDepartmentService.findList();
        List<Map> list =new ArrayList<>();
        //后台处理ztree需要的字段
        for (Department department : departments) {
            Map map = new HashMap();
            map.put("id",department.getId());
            map.put("pId",department.getParentId());
            map.put("name",department.getDepartmentName());
            map.put("discraption",department.getDiscraption());
            list.add(map);
        }
        return ServerResponse.success(list);

    }

    @RequestMapping("/index")
    public String index(){

        return "index";

    }


}
