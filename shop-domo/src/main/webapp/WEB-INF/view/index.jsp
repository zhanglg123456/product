<%--
  Created by IntelliJ IDEA.
  User: 张鹿阁
  Date: 2018/8/17
  Time: 0:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ page   isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Title</title>
    <link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/js/datatable/DataTables-1.10.18/css/dataTables.bootstrap.min.css" type="text/css">
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <b style="margin-left:570px;">欢迎您，<span style="color: red;">${loginuser.relname}</span>，您上次登录时间是
                <fmt:formatDate value="${loginuser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss" /> ,您今天是第${loginuser.todayLoginCount}次登录！</b>
            <a href="javascript:;" onclick="loginOut()">安全退出</a>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row" style="height: 700px;" >
        <div class="col-lg-2" style="border:solid slateblue 1px; min-height: 806px;border-radius: 5px;">

            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-12">

                    <button type="button" class="btn btn-primary" onclick="showAddDlg();"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增</button>
                    <button type="button" class="btn btn-info" onclick="showUpdateDlg()"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button>
                    <button type="button" class="btn btn-danger" onclick="deleteDpt()"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button>


                </div>
            </div>
            <div class="row" style="margin-top: 10px;">
                <div class="col-lg-12">

                    <ul id="deptTree" class="ztree"></ul>
                </div>
            </div>
        </div>
        <div class="col-lg-10" style="border:solid slateblue 1px; min-height:800px; border-radius: 5px; padding: 10px;">

            <div class="row" style="margin-bottom:-20px;" >
                <div class="col-md-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">员工查询

                        </div>
                        <div class="panel-body">

                            <form class="form-horizontal" >

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">姓名</label>

                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="emptoyeeName" placeholder="员工姓名">

                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">薪资范围</label>

                                    <div class="col-sm-6">
                                        <div class="input-group">
                                            <input type="text" class="form-control" id="minSalary" placeholder="最低薪资">
                                            <span class="input-group-addon">$</span>
                                            <input type="text" class="form-control" id="maxSalary" placeholder="最高薪资">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">性别</label>

                                    <div class="col-sm-6">
                                        <input type="radio" name="find_sex" value="0"/>男
                                        <input type="radio" name="find_sex" value="1"/>女
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label  class="col-sm-2 control-label">生日</label>
                                    <div class="col-sm-6">
                                        <div class="input-group">
                                            <input type="date" class="form-control" name="minBirthday" id="minBirthday" placeholder="最早日期">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                            <input type="date" class="form-control" name="maxBirthday" id="maxBirthday" placeholder="最晚日期">
                                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">

                                    <button type="button" class="btn btn-primary" onclick="serach()" style="margin-left:445px;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询</button>
                                    <button type="reset" class="btn btn-info"  ><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>重置</button>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row" style="margin-bottom:-20px;" >
                <div class="col-md-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">员工列表


                            <button type="button" class="btn btn-primary" onclick="showAddEmpDlg()" style="margin-left:260px;"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增</button>

                            <button type="button" class="btn btn-danger" onclick="deleteEmpDlg()" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button>
                            <button type="button" class="btn btn-info" onclick="showUpdateEmpDlg()"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button>
                            <button type="button" class="btn btn-success" onclick="updateEmpDeptDlg()" ><span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>批量更换部门</button>
                            <button type="button" class="btn btn-warning" onclick="downExcl()" ><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>批量导出EXCEL</button>
                            <button type="button" class="btn btn-warning" onclick="downExclByDept()" ><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span>按部门批量导出EXCEL</button>
                            <button type="button" class="btn btn-info" onclick="queryDeptEmpPie()" ><span class="glyphicon glyphicon-user" aria-hidden="true"></span>查看各部门人数</button>
                        </div>
                        <div class="panel-body">

                            <table id="empTableList" class="table table-striped table-bordered" style="width:100%">
                                <thead>
                                <tr>
                                    <th>选择</th>
                                    <th>序号</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>生日</th>
                                    <th>薪资</th>
                                    <th>所属部门</th>

                                </tr>
                                </thead>
                                <tbody id="tbdObj">


                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>选择</th>
                                    <th>序号</th>
                                    <th>姓名</th>
                                    <th>性别</th>
                                    <th>生日</th>
                                    <th>薪资</th>
                                    <th>所属部门</th>


                                </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>


</div>

<!--新增的div-->
<div id="dptForm" style="display:none;">
    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">上级部门:</label>
            <div class="col-sm-6" id="deptName" >
                <input type="text"  class="form-control"  id="parentName" disabled />
                <input type="hidden"  class="form-control"  id="parentId"/>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">部门名</label>

            <div class="col-sm-6">
                <input type="text" class="form-control" id="brandName" placeholder="部门名">
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">部门描述</label>

            <div class="col-sm-6">
                <textarea class="form-control" id="discraption" rows="3"></textarea>
            </div>
        </div>
    </form>
</div>

<!--修改的div-->
<div id="dptEdetForm" style="display:none;">
    <form class="form-horizontal" >

        <div class="form-group">
            <label  class="col-sm-2 control-label">部门名</label>

            <div class="col-sm-6">
                <input type="text" class="form-control" id="edetBrandName" placeholder="部门名">
                <input type="hidden"  class="form-control"  id="edetId"/>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">部门描述</label>

            <div class="col-sm-6">
                <textarea class="form-control" id="edetDiscraption" rows="3"></textarea>
            </div>
        </div>
    </form>
</div>

<!--新增员工的div-->
<div id="empAddForm" style="display:none;">
    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">员工部门：</label>

            <div class="col-sm-6">
                <div class="input-group">

                    <input type="text" class="form-control" onclick="selectedDeptId()" placeholder="请选择部门" id="add_EmpDept" readonly value="" >
                    <span class="input-group-btn">

                    <button type="button" class="btn btn-info" id="menuBtn" onclick="selectedDeptId()" ><span class="glyphicon glyphicon-search" aria-hidden="true"></span>请选择</button>
                 </span>
                </div>
                <input type="hidden" class="form-control" id="add_deptId" >
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">员工名称:</label>
            <div class="col-sm-6"  >
                <input type="text"  class="form-control" placeholder="姓名"  id="add_empName" />

            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">性别：</label>

            <div class="col-sm-6">
                <input type="radio" name="add_sex" value="0" checked />男
                <input type="radio" name="add_sex" value="1"/>女
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">生日</label>
            <div class="col-sm-6">
                <div class="input-group">
                    <input type="date" class="form-control" name="add_birthday" id="add_birthday" placeholder="">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>

                </div>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">薪资：</label>

            <div class="col-sm-6">
                <input type="text"  class="form-control" placeholder="薪资"  id="add_salary" />
            </div>
        </div>
    </form>
</div>

<!--修改员工的div-->

<div id="empUpdateForm" style="display:none;">
    <form class="form-horizontal" >
        <div class="form-group">
            <label  class="col-sm-2 control-label">员工部门：</label>

            <div class="col-sm-6">
                <div class="input-group">

                    <input type="text" class="form-control" onclick="selectedDeptId()" placeholder="请选择部门" id="edit_EmpDept" readonly value="" >
                    <span class="input-group-btn">

                    <button type="button" class="btn btn-info" id="menuBtn1" onclick="selectedDeptId()" ><span class="glyphicon glyphicon-search" aria-hidden="true"></span>请选择</button>
                 </span>
                </div>
                <input type="hidden" class="form-control" id="edit_deptId" >
                <input type="hidden" class="form-control" id="edit_id" >
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">员工名称:</label>
            <div class="col-sm-6"  >
                <input type="text"  class="form-control" placeholder="姓名"  id="edit_empName" />

            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">性别：</label>

            <div class="col-sm-6">
                <input type="radio" name="edit_sex" value="0"  />男
                <input type="radio" name="edit_sex" value="1"/>女
            </div>
        </div>

        <div class="form-group">
            <label  class="col-sm-2 control-label">生日</label>
            <div class="col-sm-6">
                <div class="input-group">
                    <input type="date" class="form-control" name="edit_birthday" id="edit_birthday" placeholder="">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>

                </div>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">薪资：</label>

            <div class="col-sm-6">
                <input type="text"  class="form-control" placeholder="薪资"  id="edit_salary" />
            </div>
        </div>
    </form>
</div>



<div id="menuContent"  class="menuContent" style="display:none; position: absolute;">
    <ul id="deptTreeEmp" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>







<script src="<%=request.getContextPath()%>/js/Jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/ztree/js/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>


<script>

    $(function () {
        getDepartmentTree();
        //datatable
        getEmpList();
        
        initTrClick();




    });


    //安全退出登录
    function loginOut() {

        //发送ajax退出
        $.ajax({
            type:"post",
            url:"<%=request.getContextPath()%>/loginOut",
            success:function (result) {
                if(result.code==200){

                    window.location.href="/";

                }

            }


        })

    }

    var addDialog;

    var updateDialog;

    var flog=0;
    var chekendTrBase = [];



    function updateEmpDeptDlg() {


        if(chekendTrBase.length>0){
            flog = 2;

            selectedDeptId();

        }else {

            bootbox.alert({
                message: "<b style='color:red;'>" +
                "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                "</b><span style='color: #df8505;margin-left:5px;'>请选择要删除的部门！</span>",
                size: 'small',
                title: "提示信息"
            });

        }

    }


    function deleteEmpDlg() {
        var checkeds =   $('#empTableList input[type="checkbox"]:checked');

        if(checkeds.length>0){
            bootbox.confirm({
                message: "你确定要删除"+checkeds.length+"条数据?",
                size: 'small',
                title: "提示信息",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确定',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    if (result == true) {
                        var idss = "";
                        var empids = "";
                        for(var i =0 ; i<chekendTrBase.length;i++){
                            idss+=chekendTrBase[i]+",";
                        }
                        if(idss.length>0){
                            empids = idss.substr(0, idss.length - 1);
                        }
                        var ids = empids;
                        //发送ajax删除
                        $.ajax({
                            type: "post",
                            url: "<%=request.getContextPath()%>/emp/deleteEmp",
                            data: {"ids": ids},
                            success: function (result) {
                                if (result.code == 200) {
                                    //重置成功删除后的数组为空
                                    chekendTrBase = [];

                                    serach();

                                    bootbox.alert({
                                        message: "<b style='color:green;'>" +
                                        "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                        "</b><span style='color: #df8505;margin-left:5px;'>删除成功！</span>",
                                        size: 'small',
                                        title: "提示信息"
                                    });
                                }else {

                                    bootbox.alert({
                                        message: "<b style='color:red;'>" +
                                        "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                        "</b><span style='color: #df8505;margin-left:5px;'>删除失败！</span>",
                                        size: 'small',
                                        title: "提示信息"
                                    });

                                }
                            }
                        })
                    }
                }
            })
        }else {
            bootbox.alert({
                message: "<b style='color:red;'>" +
                "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                "</b><span style='color: #df8505;margin-left:5px;'>请选中要删除的员工！</span>",
                size: 'small',
                title: "提示信息"
            });


        }


    }
    
    function initTrClick() {
        $("#empTableList tbody").on('click','tr', function () {


            //获得复选input框对象
            var checkboxs = $(this).find("input[type='checkbox']");
            if($(checkboxs).prop("checked")==false){
                $(checkboxs).prop("checked",true);
                this.style.backgroundColor="red";
                //选中之后把id存入数组
                chekendTrBase.push(checkboxs.val());

            }else {
                $(checkboxs).prop("checked", false);
                this.style.backgroundColor = "";
                for (var i = 0; i < chekendTrBase.length; i++) {
                    if (chekendTrBase[i] == checkboxs.val() ) {
                        //移除数组当前元素
                        chekendTrBase.splice(i,1);
                    }
                }
            }
        })
    }



    function showUpdateEmpDlg() {


        flog = 1;


        var checkeds = $('#empTableList input[type="checkbox"]:checked');

        if(checkeds.length==1){

            var id = checkeds.val();

            $.ajax({
                type:"post",
                url:"<%=request.getContextPath()%>emp/showbackEmp",
                data:{"id":id},
                success:function (result) {

                    if(result.code==200){
                        $("#edit_EmpDept",updateDialog).val(result.data.deptName);
                        $("#edit_id",updateDialog).val(result.data.id);
                        $("#edit_deptId",updateDialog).val(result.data.deptId);
                        $("#edit_empName",updateDialog).val(result.data.empName);
                        $("#edit_salary",updateDialog).val(result.data.salary);
                        $("#edit_birthday",updateDialog).val(result.data.birthday);
                        $('[name="edit_sex"]',updateDialog).each(function () {

                            if(this.value==result.data.sex){
                                this.checked=true;
                            }

                        })
                    }else {
                        bootbox.alert({
                            message: "<b style='color:red;'>" +
                            "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                            "</b><span style='color: #df8505;margin-left:5px;'>系统出现异常！</span>",
                            size: 'small',
                            title: "提示信息"
                        });
                    }
                }
            })

            var info = $("#empUpdateForm").html();

            // bootbox
            updateDialog = bootbox.dialog({
                title: '修改员工',
                message: info,
                size:"large",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确认',
                        className: 'btn-primary',
                        callback: function(){
                            var v_id = $("#edit_id",updateDialog).val();
                            var v_deptId = $("#edit_deptId",updateDialog).val();
                            var v_empName = $("#edit_empName",updateDialog).val();
                            var v_salary = $("#edit_salary",updateDialog).val();
                            var v_sex = $('[name="edit_sex"]:checked',updateDialog).val();

                            var param = {};
                            param.id = v_id;
                            param.emptoyeeName = v_empName;
                            param.sex = v_sex;
                            param.salary = v_salary;
                            param.deptId = v_deptId;



                            $.ajax({
                                type:'post',
                                url:"<%=request.getContextPath()%>/emp/updateEmp",
                                data:param,
                                success:function (result) {
                                    //判断后台返回的map是否为ok
                                    if(result.code==200){
                                        chekendTrBase = [];
                                        serach();

                                        bootbox.alert({
                                            message: "<b style='color:green;'>" +
                                            "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                            "</b><span style='color: #df8505;margin-left:5px;'>修改成功！</span>",
                                            size: 'small',
                                            title: "提示信息"
                                        });
                                    }else{

                                        bootbox.alert({

                                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>修改失败，系统异常！！！",
                                            size: 'small',
                                            title:"提示信息"

                                        });
                                    }
                                }
                            })
                        }
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                }
            });

        }else{

            bootbox.alert({
                message: "<b style='color:red;'>" +
                "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                "</b><span style='color: #df8505;margin-left:5px;'>请选中具体要修改的员工！</span>",
                size: 'small',
                title: "提示信息"
            });


        }





    }
    
    
    function selectedDeptId() {

        //首先初始化ztree选项表
        $.ajax({
            type: "post",
            url: "<%=request.getContextPath()%>/department/findList",
            success: function (data) {
                var settings = {
                    data: {
                        //这是前台处理方式来解决属性名不匹配的问题，很重要！！！
                        // key: {
                        //     name: "departmentName"
                        // },
                        // 关键点 开启简单模式
                        simpleData: {
                            enable: true
                            // pIdKey: "fatherId"
                        }
                    }
                };
                var treeObj = $.fn.zTree.init($("#deptTreeEmp"), settings, data.data);
                treeObj.expandAll(true);
            }
        })


        bootbox.dialog({
            title: '部门选择',
            closeButton:false,
            message: $("#deptTreeEmp"),
            size: "small",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>确认',
                    className: 'btn-primary',
                    callback: function () {
                        //重新给加载ul
                        $("#menuContent").html('<ul id="deptTreeEmp" class="ztree" style="margin-top:0; width:160px;"></ul>');
                        //获得ztree树对象
                        var treeEmpObj = $.fn.zTree.getZTreeObj("deptTreeEmp");
                        //获得选中的节点集合
                        var nodes = treeEmpObj.getSelectedNodes();
                        if(nodes.length==1){
                            var nodeAndChildens = treeEmpObj.transformToArray(nodes[0]);
                            if(nodeAndChildens.length==1){
                                if(flog==1){
                                    $("#edit_EmpDept",updateDialog).val(nodeAndChildens[0].name);
                                    $("#edit_deptId",updateDialog).val(nodeAndChildens[0].id);
                                }else if(flog==0){
                                    $("#add_EmpDept",addDialog).val(nodeAndChildens[0].name);
                                    $("#add_deptId",addDialog).val(nodeAndChildens[0].id);

                                }else if(flog==2){

                                    //批量换部门

                                    //获得选中部门的ID
                                    var deptId = nodeAndChildens[0].id;

                                    //获得选中员工的ID集合

                                    var ids = [];

                                    $("#empTableList tbody input[type = 'checkbox']:checked").each(function () {
                                        ids.push(this.value);
                                    })

                                    //ajax

                                    $.ajax({
                                        type:"post",
                                        url:"<%=request.getContextPath()%>/emp/updateEmpDept",
                                        data:{"ids":ids,"deptId":deptId},
                                        success:function (result) {

                                            if(result.code==200){

                                                //清空员工ID数组
                                                chekendTrBase = [];
                                                serach();
                                                bootbox.alert({
                                                    message: "<b style='color:green;'>" +
                                                    "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                                    "</b><span style='color: #df8505;margin-left:5px;'>更换部门成功！</span>",
                                                    size: 'small',
                                                    title: "提示信息"
                                                });

                                            }else {
                                                bootbox.alert({
                                                    message: "<b style='color:red;'>" +
                                                    "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                                    "</b><span style='color: #df8505;margin-left:5px;'>更换部门失败！</span>",
                                                    size: 'small',
                                                    title: "提示信息"
                                                });


                                            }
                                        }



                                    })





                                }
                            }else {
                                bootbox.alert({
                                    message: "<b style='color:red;'>" +
                                    "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                    "</b><span style='color: #df8505;margin-left:5px;'>请选中具体的部门！</span>",
                                    size: 'small',
                                    title: "提示信息"
                                });
                            }
                        }
                    }
                },
                cancel: {
                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                    className: 'btn-danger',
                    callback: function () {
                        //重新给加载ul
                        $("#menuContent").html('<ul id="deptTreeEmp" class="ztree" style="margin-top:0; width:160px;"></ul>');
                    }
                }
            }
        });
        
    }

    function showAddEmpDlg() {

        flog = 0;

        var info = $("#empAddForm").html();
        // bootbox
        addDialog = bootbox.dialog({
            title: '新增员工',
            message: info,
            size:"large",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>确认',
                    className: 'btn-primary',
                    callback: function(){
                        //获得指定表单的值进行ajax新增

                        var v_deptId = $("#add_deptId",addDialog).val();
                        var v_empName = $("#add_empName",addDialog).val();
                        var v_sex = $('[name="add_sex"]:checked',addDialog).val();
                        var v_salary = $("#add_salary",addDialog).val();

                        var param = {};
                        param.emptoyeeName = v_empName;
                        param.sex = v_sex;
                        param.salary = v_salary;
                        param.deptId = v_deptId;

                        $.ajax({
                            type:'post',
                            url:"<%=request.getContextPath()%>/emp/addEmp",
                            data:param,
                            success:function (result) {
                                //判断后台返回的map是否为ok
                                if(result.code==200){

                                    serach();

                                    bootbox.alert({
                                        message: "<b style='color:green;'>" +
                                        "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                        "</b><span style='color: #df8505;margin-left:5px;'>新增成功！</span>",
                                        size: 'small',
                                        title: "提示信息"
                                    });
                                }else{

                                    bootbox.alert({

                                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>新增失败，系统异常！！！",
                                        size: 'small',
                                        title:"提示信息"

                                    });

                                }
                            }

                        })

                    }
                },
                cancel: {
                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                    className: 'btn-danger'
                }
            }
        });



    }
    //获得table对象，条件查询带上条件重新加载
    var emptable;
    //条件查询+ztree
    function serach() {

      var v_minSalary = $("#minSalary").val();
      var v_maxSalary = $("#maxSalary").val();
      var v_emptoyeeName = $("#emptoyeeName").val();
      var v_minBirthday = $("#minBirthday").val();
      var v_maxBirthday = $("#maxBirthday").val();
      var v_sex = $('[name="find_sex"]:checked').val();



        //获得ztree树对象
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        //获得选中的节点集合
        var nodes = treeObj.getSelectedNodes();
        var ids="";
        if(nodes.length==1){
            //调用ztree官网的方法获得当前标签和下面所有的子标签集合
            var nodeAndChildens = treeObj.transformToArray(nodes[0]);
            for(var i =0 ;i<nodeAndChildens.length;i++){
                ids+=nodeAndChildens[i].id+",";
            }
            if (ids.length > 0) {
                ids.substr(0, ids.length - 1);
            }
        }
        var v_depts = ids;

        var v_param ={};
        v_param.emptoyeeName = v_emptoyeeName;
        v_param.minSalary = v_minSalary;
        v_param.maxSalary = v_maxSalary;
        v_param.minbirthday = v_minBirthday;
        v_param.maxbirthday = v_maxBirthday;
        v_param.sex = v_sex;

        v_param.depts = v_depts;
        //给请求对象重新赋值，添加请求参数
        emptable.settings()[0].ajax.data = v_param;
        //刷新页面，重新请求
        emptable.ajax.reload();


        
    }


    function getEmpList() {
        emptable = $('#empTableList').DataTable({
            //draw画 ，这个应该是重绘的回调函数
            "fnDrawCallback": function (oSettings) {

                //获得所有复选框的值
               var checkboxs = $("#tbdObj input[type='checkbox']");

               for(var i=0; i<checkboxs.length;i++){

                for (var j=0;j<chekendTrBase.length;j++  ){

                    if (checkboxs[i].value==chekendTrBase[j]){
                        //给TR附上背景色
                        $(checkboxs[i]).closest("tr").css("background-color","red");
                        //复选框选中
                        checkboxs[i].checked=true;

                    }

                }

               }
            },
            "lengthMenu": [[5, 20, 30, -1], [5, 20, 30, "全部"]],
            //.显示数字的翻页样式：
            "sPaginationType": "full_numbers",
            //状态保存，使用了翻页或者改变了每页显示数据数量，
            // 会保存在cookie中，下回访问时会显示上一次关闭页面时的内容：
            /*"bStateSave": true,*/
            //中文汉化，还可以自定义配置
            "language": {
                "url": "<%=request.getContextPath()%>/js/datatable/Chinese.json"
            },
            "processing": true,
            "serverSide": true,
            "ajax": {
                url:"<%=request.getContextPath()%>/emp/findEmpList",
                type:"post",
                //解决后台数据格式不匹配问题
                dataSrc: function (json) {
                    json.draw = json.data.draw;
                    json.recordsTotal = json.data.recordsTotal;
                    json.recordsFiltered = json.data.recordsFiltered;
                    return json.data.data;
                },

            },
            //datatable会自动把数据查询到的数据放到data里面

            "columns": [
                {data: 'id',

                    render:function (data, type, row,meta) {
                        return '<input type ="checkbox" name="empId" value="'+data+'"/>';
                    }


                },
                {data: 'id'},
                {data: 'emptoyeeName'},
                {
                    data: 'sex',
                    render: function (data, type, row,meta) {
                        if (data == 1) {
                            return "女";
                        }
                        if (data == 0) {
                            return "男";
                        }
                    }
                },
                {data: 'birthday',
                    render: function (data, type, row,meta) {
                        //转换成自己需要的格式，需要从写一下toLocalString()方法;
                        Date.prototype.toLocaleString = function() {
                            return this.getFullYear() + "/" + (this.getMonth() + 1) + "/" + this.getDate();

                        };
                      return  new Date(data).toLocaleString();

                    }


                },
                {
                    data: 'salary',
                    render: function (data, type, row,meta) {
                        return '$' + data;
                    }
                },
                {data: 'deptName'}
            ]
        });
    }

    function deleteDpt() {

        //获得ztree树对象
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        //获得选中的节点集合
        var nodes = treeObj.getSelectedNodes();
        //当选中的节点为一个时才调用方法
        if (nodes.length == 1) {

            bootbox.confirm({
                message: "你确定删除吗,这样会把部门下的员工也删除?",
                size: 'small',
                title:"提示信息",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确定',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {

                    if(result==true){

                        var selectedKey = nodes[0];

                        //调用ztree官网的方法获得当前标签和下面所有的子标签集合
                        var nodeAndChildens = treeObj.transformToArray(selectedKey);
                        //循环集合取出所有的ID
                        var ids = [];
                        for(var i=0 ; i<nodeAndChildens.length ; i++){
                            ids.push(nodeAndChildens[i].id);
                        }

                        //发送ajax删除
                        $.ajax({
                            type:"post",
                            url:"<%=request.getContextPath()%>/department/delete",
                            data:{"ids":ids},
                            success:function (result) {

                                if(result.code==200){
                                    //调用ztree官网的删除方法，把前台的树也进行删除，切记，循环要倒着删除

                                    for(var i = nodeAndChildens.length-1 ; i>=0; i-- ){

                                        treeObj.removeNode(nodeAndChildens[0]);
                                    }
                                    //清空员工ID数组
                                    chekendTrBase = [];
                                    serach();
                                    bootbox.alert({
                                        message: "<b style='color:green;'>" +
                                        "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                        "</b><span style='color: #df8505;margin-left:5px;'>删除部门成功！</span>",
                                        size: 'small',
                                        title: "提示信息"
                                    });


                                }else if(result.code==500) {

                                    bootbox.alert({

                                        message: "<span class='glyphicon glyphicon-exclamation-sign'></span>删除失败，系统异常！！！",
                                        size: 'small',
                                        title:"提示信息"

                                    });

                                }
                            }
                        })
                    }
                }
            })

        }else{

            bootbox.alert({
                message: "<b style='color:red;'>" +
                "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                "</b><span style='color: #df8505;margin-left:5px;'>请选中要删除的部门！</span>",
                size: 'small',
                title:"提示信息"});
        }


    }

    function showUpdateDlg() {
        //获得ztree树对象
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        //获得选中的节点集合
        var nodes = treeObj.getSelectedNodes();
        //当选中的节点为一个时才调用方法
        if (nodes.length == 1) {

            //获得当前选中的节点对象
            var selectedKey = nodes[0];

            $("#edetBrandName").attr("value",selectedKey.name);
            $("#edetId").attr("value",selectedKey.id);
            $("#edetDiscraption").html(selectedKey.discraption);

            var info = $("#dptEdetForm").html();

            // bootbox
            var idetdialog = bootbox.dialog({
                title: '修改部门节点',
                message: info,
                size:"large",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确认',
                        className: 'btn-primary',
                        callback: function(){
                            //获得指定表单的值进行ajax新增

                            var v_id = $("#edetId",idetdialog).val();
                            var v_deptName = $("#edetBrandName",idetdialog).val();
                            var v_discraption = $("#edetDiscraption",idetdialog).val();
                            var param = {};
                            //设置对象里面的键为实体类对应的属性，后台用对象接值
                            param.id = v_id;
                            param.departmentName = v_deptName;
                            param.discraption = v_discraption;
                            $.ajax({
                                type:'post',
                                url:"<%=request.getContextPath()%>/department/update",
                                data:param,
                                success:function (result) {
                                    if(result.code==200){

                                        selectedKey.name = v_deptName;
                                        selectedKey.discraption = v_discraption;
                                        //调用ztree官网的修改节点方法
                                        treeObj.updateNode(selectedKey);
                                    }else if(result.code==500){

                                        bootbox.alert({

                                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>修改失败，系统异常！！！",
                                            size: 'small',
                                            title:"提示信息"

                                        });


                                    }
                                }


                            })



                        }
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                }
            });



        }else{

            bootbox.alert({
                message: "<b style='color:red;'><span class='glyphicon glyphicon-exclamation-sign'></span></b><span style='color: #df8505;margin-left:5px;'>请选择上级部门！</span>",
                size: 'small',
                title:"提示信息"});

        }


    }

    function showAddDlg() {
        //获得ztree树对象
        var treeObj = $.fn.zTree.getZTreeObj("deptTree");
        //获得选中的节点集合
        var nodes = treeObj.getSelectedNodes();
        //当选中的节点为一个时才调用方法
        if (nodes.length == 1) {

            //获得当前选中的节点对象
            var selectedCheck = nodes[0];
            //获得当前选中的节点的名字
            $("#parentName").attr("value",selectedCheck.name);
            //获得选中节点的ID作为父ID新增
            $("#parentId").attr("value",selectedCheck.id);
            var info = $("#dptForm").html();
            // bootbox
            var dialog = bootbox.dialog({
                title: '新增部门节点',
                message: info,
                size:"large",
                buttons: {
                    confirm: {
                        label: '<span class="glyphicon glyphicon-ok"></span>确认',
                        className: 'btn-primary',
                        callback: function(){
                            //获得指定表单的值进行ajax新增

                            var v_parentId = $("#parentId",dialog).val();
                            var v_deptName = $("#brandName",dialog).val();
                            var v_discraption = $("#discraption",dialog).val();
                            //定义一个json对象
                            var param = {};
                            //设置对象里面的键为实体类对应的属性，后台用对象接值
                            param.parentId = v_parentId;
                            param.departmentName = v_deptName;
                            param.discraption = v_discraption;
                            $.ajax({
                                type:'post',
                                url:"<%=request.getContextPath()%>/department/addDept",
                                data:param,
                                success:function (result) {
                                    //判断后台返回的map是否为ok
                                    if(result.code == 200){
                                        var newNodes = {};
                                        newNodes.id = result.data;
                                        newNodes.pId = v_parentId;
                                        newNodes.name = v_deptName;
                                        //把所有参数都附上值，不然修改回显的时候会出bug
                                        newNodes.discraption = v_discraption;
                                        //调用ztree的新增节点函数把父节点对象跟新增的节点对象传进来
                                        treeObj.addNodes(selectedCheck,newNodes);
                                    }else{

                                        bootbox.alert({

                                            message: "<span class='glyphicon glyphicon-exclamation-sign'></span>新增失败，系统异常！！！",
                                            size: 'small',
                                            title:"提示信息"

                                        });

                                    }
                                }

                            })

                        }
                    },
                    cancel: {
                        label: '<span class="glyphicon glyphicon-remove"></span>取消',
                        className: 'btn-danger'
                    }
                }
            });





        }else {
            bootbox.alert({
                message: "<b style='color:red;'><span class='glyphicon glyphicon-exclamation-sign'></span></b><span style='color: #df8505;margin-left:5px;'>请选择上级部门！</span>",
                size: 'small',
                title:"提示信息"});


        }

    }



    function getDepartmentTree() {
        
        $.ajax({
            type:"get",
            url:"<%=request.getContextPath()%>/department/findList",
            success:function (data) {

                var setting = {
                    //点击菜单回调函数
                    callback: {
                        onClick: serach
                    },
                    data: {
                        //这是前台处理方式来解决属性名不匹配的问题，很重要！！！
                        // key: {
                        //     name: "departmentName"
                        // },
                        // 关键点 开启简单模式
                        simpleData: {
                            enable: true
                            // pIdKey: "fatherId"
                        }
                    }
                };
                $.fn.zTree.init($("#deptTree"), setting, data.data);
            }
            
            
            
        })
        
    }

</script>
</body>
</html>
