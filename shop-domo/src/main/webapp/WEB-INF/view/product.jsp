<%--
  Created by IntelliJ IDEA.
  User: 张鹿阁
  Date: 2018/8/28
  Time: 18:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>--商品管理--</title>
    <link href="<%=request.getContextPath()%>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/js/datatable/DataTables-1.10.18/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/js/bootstrap/fileinput/css/fileinput.min.css">
</head>
<body>

<div class="container-fluid">
    <div class="row" style="min-height:50px; background-color: #0d3349;border-radius: 5px;">
        <div class="col-lg-12" style="text-align: center;line-height: 50px;">

        <h1 style="font-style: inherit;color: #9acfea">商&nbsp;品&nbsp;后&nbsp;台&nbsp;管&nbsp;理&nbsp;系&nbsp;统</h1>

        </div>



    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-success">
                <div class="panel-heading">商品查询
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" id="serachForm">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">商品名称：</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="productName" id="productName" placeholder="商品名">

                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">商品价格：</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="minPrice" id="minPrice" placeholder="最低价格">
                                    <span class="input-group-addon">$</span>
                                    <input type="text" class="form-control" name="maxPrice" id="maxPrice" placeholder="最高价格">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">库存数量：</label>
                            <div class="col-sm-6">
                                <div class="input-group">
                                    <input type="text" class="form-control" name="minStock" id="minStock" placeholder="最低">
                                    <span class="input-group-addon">--</span>
                                    <input type="text" class="form-control" name="maxStock" id="maxStock" placeholder="最高">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="button" class="btn btn-primary" onclick="serach()" style="margin-left:410px;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询</button>
                            <button type="reset" class="btn btn-info"  ><span class="glyphicon glyphicon-repeat" aria-hidden="true"></span>重置</button>
                        </div>
                    </form>
                </div>
            </div>




        </div>

    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-success">
                <div class="panel-heading">商品列表
                    <button type="button" class="btn btn-primary" onclick="showAddpdtDlg()" style="margin-left:1000px;"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增</button>
                    <button type="button" class="btn btn-info" onclick="showEditPdtDlg()" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改</button>
                    <button type="button" class="btn btn-danger" onclick="deletepdtDlg()" ><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除</button>
                </div>
                <div class="panel-body">
                    <table id="pdtTableList" class="table table-striped table-bordered" style="width:100%">
                        <thead>
                        <tr>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品图片</th>
                            <th>价格</th>
                            <th>库存</th>
                            <th>添加时间</th>
                            <th>修改时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                        <tfoot>
                        <tr>
                            <th>商品编号</th>
                            <th>商品名称</th>
                            <th>商品图片</th>
                            <th>价格</th>
                            <th>库存</th>
                            <th>添加时间</th>
                            <th>修改时间</th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!--新增商品的div-->

<div style="display:none" id="productForm">

        <form class="form-horizontal" id="add_form_id" >
            <div class="form-group">
                <label  class="col-sm-2 control-label">商品名称:</label>
                <div class="col-sm-6" id="deptName" >
                    <input type="text"  class="form-control"    id="add_productName" placeholder="请输入商品名称" />
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">商品图片:</label>
                <div class="col-sm-6">
                    <input id="add_fileInput" name="up_mainImg"  type="file" data-show-caption="true">
                    <input id="add_mainImage"   type="hidden" data-show-caption="true">
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">价格:</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="add_price" placeholder="请输入价格">
                </div>
            </div>
            <div class="form-group">
                <label  class="col-sm-2 control-label">库存:</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control"  id="add_stock" placeholder="库存数量">
                </div>
            </div>
        </form>
    </div>


<%--修改商品的表单--%>
<div id="edit_pdtForm" style="display:none;" >
    <form class="form-horizontal" id="edit_pdt">
        <div class="form-group">
            <label class="col-sm-2 control-label">商品名</label>
            <div class="col-sm-6">
                <div class="input-group">
                    <input type="text" class="form-control" id="edit_pdtName">
                    <input type="hidden" class="form-control" id="edit_pdtId">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">价格</label>
            <div class="col-sm-6">
                <div class="input-group">
                    <input type="text" id="edit_price"class="form-control" />
                </div>
            </div>
        </div>


        <div class="form-group">
            <label class="col-sm-2 control-label">库存</label>
            <div class="col-sm-6">
                <div class="input-group">
                    <input type="text" class="form-control" id="edit_stock">
                </div>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">修改商品图片</label>
            <div class="col-sm-6" style="width: 350px;height: 350px">
                <input id="edit_mainImg"  type="hidden" >
                <input id="edit_upFile"  name="up_mainImg"  type="file" data-show-caption="true">
            </div>
        </div>
    </form>
</div>



<script src="<%=request.getContextPath()%>/js/Jquery/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/bootbox/bootbox.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/DataTables-1.10.18/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatable/DataTables-1.10.18/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap/fileinput/js/locales/zh.js"></script>

<script>

    $(function () {
        inutdatatible();

        onTrChecked();
    });


    var productTable;
    var productAdd;
    var editEmpdialog;
    var selectedRows=[];


    //选中行点击事件
    function onTrChecked() {


        var table = $('#pdtTableList').DataTable();
        $('#pdtTableList tbody').on('click', 'tr', function () {
            var input = $(this).find("input[type='checkbox']");

            if ($(input).prop("checked")==true){
                this.style.backgroundColor="";
                $(input).prop("checked",false);

                for(var i=0;i<selectedRows.length;i++){

                    if(input.val()==selectedRows[i]){

                        //移除数组当前元素
                        selectedRows.splice(i,1);
                        console.log(selectedRows);
                    }
                }

            }else {
                this.style.backgroundColor = "pink";

                $(input).prop("checked",true);
                //把选中的复选框id存到数组中，翻页照样选中！！！
                selectedRows.push(input.val());

                /* this.childNodes[0].childNodes[0].checked=true*/
            }

            /* $(this).toggleClass('selected');
             var input = $(this).find("input");
             //alert($(input).prop("checked"));
             if (!$(input).prop("checked")) {
                 $(input).prop("checked",true);

             }else{
                 $(input).prop("checked",false);
             }
 */
        });

    }


    function initIDetFileInput(previewArr) {

        var settings = {
            theme:'fa', //显示按钮风格
            initialPreview:previewArr,//图片回显
            initialPreviewAsData:true,//图片回显，显示图片，比较重要*/
            // ，传进来一个图片路径的数组[]就行
            language: 'zh', //设置语言
            uploadUrl: "<%=request.getContextPath()%>/product/upPhoto", //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
            uploadAsync: true, //默认异步上传
            showUpload: false, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            autoReplace:true,
            enctype: 'multipart/form-data',
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",


        };

        $("#edit_upFile").fileinput(settings).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
            $("#edit_mainImg").val(data.response.data);


            console.log('文件上传成功！'+data.id);

        })
    }


    var editform = $("#edit_pdtForm").html();

    function showEditPdtDlg() {
        $("#edit_pdtForm").html(editform);

       console.log(selectedRows);

        if (selectedRows.length == 1) {

            $.ajax({
                url: "<%=request.getContextPath()%>/product/findProductById",
                type: "post",
                data: {"id": selectedRows[0]},
                success: function (result) {
                    if (result.code == 200) {
                        //定义一个数组，初始化fileinput的时候放入，用来回显
                        var previewArr = [];

                        //将信息回填到表单中
                        var data = result.data;
                        //把查到的图片路径放入数组中
                        previewArr.push(data.mainImage);
                        //初始化数组，并加载旧图片
                        initIDetFileInput(previewArr);

                        $("#edit_pdtId").val(data.id);
                        $("#edit_pdtName").val(data.productName);
                        $("#edit_price").val(data.price);
                        $("#edit_stock").val(data.stock);
                        $("#edit_mainImg").val(data.mainImage);

                        editEmpdialog = bootbox.dialog({
                            title: '修改员工信息',
                            message: $("#edit_pdtForm form"),
                            size: "large",
                            buttons: {
                                confirm: {
                                    label: '<span class="glyphicon glyphicon-ok"></span>确认',
                                    className: 'btn-primary',
                                    callback: function () {
                                        //获取表单中的值
                                        var v_id = $("#edit_pdtId", editEmpdialog).val();
                                        var v_pdtName = $("#edit_pdtName", editEmpdialog).val();
                                        var v_price = $("#edit_price", editEmpdialog).val();
                                        var v_stock = $("#edit_stock", editEmpdialog).val();
                                        var v_mainImg = $("#edit_mainImg", editEmpdialog).val();

                                        //把旧图片的路径也传到后台，进行对比，如果跟新图片不一致就删除旧图
                                        var v_oldMainImg = data.mainImage;


                                        //将表单中的值放到参数中
                                        var param = {};
                                        param.id = v_id;
                                        param.productName = v_pdtName;
                                        param.price = v_price;
                                        param.stock = v_stock;
                                        param.mainImage = v_mainImg;
                                        param.oldMainImg = v_oldMainImg;
                                        //调用ajax进行修改
                                        $.ajax({
                                            type: 'post',
                                            url: '<%=request.getContextPath()%>/product/update',
                                            data: param,
                                            success: function (result) {
                                                if (result.code == 200) {
                                                    bootbox.alert({
                                                        title: "提示信息",
                                                        message: "修改成功!",
                                                        size: 'small',
                                                    });
                                                    selectedRows=[];
                                                    serach();

                                                } else {
                                                    bootbox.alert({
                                                        title: "提示信息",
                                                        message: result.massage,
                                                        size: 'small',
                                                    })
                                                }
                                            }
                                        })
                                    }
                                },
                                cancel: {
                                    label: '<span class="glyphicon glyphicon-remove"></span>取消',
                                    className: 'btn-danger',
                                    callback: function () {

                                    }
                                }
                            }
                        });
                    }
                    else {
                        bootbox.alert({
                            title: "提示信息",
                            message: "找不到您要修改的商品信息",
                            size: 'small',
                        })
                    }
                }
            })
        } else {
            bootbox.alert({
                title: "提示信息",
                message: "只能选择一个要修改的商品",
                size: 'small',
            })
        }

    }




    
    //新增商品信息

    function initAddProductFile() {
        var settings = {
            theme:'fa', //显示按钮风格
          /*  initialpreview:previewArr,//图片回显
            initialpreriewAsData:true,//图片回显，显示图片，比较重要*/
            // ，传进来一个图片路径的数组[]就行
            language: 'zh', //设置语言
            uploadUrl: "<%=request.getContextPath()%>/product/upPhoto", //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            //uploadExtraData:{"id": 1, "fileName":'123.mp3'},
            showUpload: false, //是否显示上传按钮
            showRemove : true, //显示移除按钮
            showPreview : true, //是否显示预览
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            autoReplace:true,//是否自动替换当前图片
            enctype: 'multipart/form-data',
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",


        };

        $("#add_fileInput").fileinput(settings).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
            $("#add_mainImage").val(data.response.data);

            console.log('文件上传成功！'+data.id);

        })
    }




    //获得form表单文本
    var addform = $("#add_form_id").prop("outerHTML");

    function showAddpdtDlg() {


        //重新初始化弹出框
        $("#productForm").html(addform);

        initAddProductFile();


        productAdd = bootbox.dialog({
            title: '新增商品',
            message: $("#add_form_id"),
            size: "large",
            buttons: {
                confirm: {
                    label: '<span class="glyphicon glyphicon-ok"></span>确认',
                    className: 'btn-primary',
                    callback: function () {

                        //AJAX提交

                        var v_productName = $("#add_productName",productAdd).val();
                        var v_mainImage = $("#add_mainImage",productAdd).val();
                        var v_price = $("#add_price",productAdd).val();
                        var v_stock = $("#add_stock",productAdd).val();
                        var param = {};
                        param.productName = v_productName;
                        param.mainImage = v_mainImage;
                        param.price = v_price;
                        param.stock = v_stock;

                        $.ajax({
                            type:"post",
                            url:"<%=request.getContextPath()%>/product/addproduct",
                            data:param,
                            success:function (result) {
                                if(result.code==200){
                                    serach();
                                    selectedRows=[];
                                    bootbox.alert({
                                        message: "<b style='color:red;'>" +
                                        "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
                                        "</b><span style='color: #df8505;margin-left:5px;'>新增成功！</span>",
                                        size: 'small',
                                        title: "提示信息"
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


    function deletepdtDlg() {

        //获得所有选中的复选框
     var  checkboxObj =   $("#pdtTableList tbody input[type='checkbox']:checked");

     if(checkboxObj.length>0){

         var ids = [];
         for(var i=0; i<checkboxObj.length; i++){

             ids.push(checkboxObj[i].value);


         }

         //发送ajax删除

         $.ajax({
             type:"post",
             url:"<%=request.getContextPath()%>/product/deletById",
             data:{"ids":ids},
             success:function (result) {
                 if(result.status==200){

                     serach();
                     bootbox.alert({
                         message: "<b style='color:red;'>" +
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


     }else {

         bootbox.alert({
             message: "<b style='color:red;'>" +
             "<span class='glyphicon glyphicon-exclamation-sign'></span>" +
             "</b><span style='color: #df8505;margin-left:5px;'>请选择要删除的员工！</span>",
             size: 'small',
             title: "提示信息"
         });


     }
       console.log(checkboxObj);


    }


    function serach() {
         var v_productName = $("#productName").val();
         var v_minPrice = $("#minPrice").val();
         var v_maxPrice = $("#maxPrice").val();
         var v_minStock = $("#minStock").val();
         var v_maxStock = $("#maxStock").val();

         var v_param = {};
        v_param.productName = v_productName;
        v_param.minPrice = v_minPrice;
        v_param.maxPrice = v_maxPrice;
        v_param.minStock = v_minStock;
        v_param.maxStock = v_maxStock;

        //给请求对象重新赋值，添加请求参数
        productTable.settings()[0].ajax.data = v_param;
        //刷新页面，重新请求
        productTable.ajax.reload();


    }


    function inutdatatible() {

      productTable =  $('#pdtTableList').DataTable({

            "searching":false,
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
                url:"<%=request.getContextPath()%>/product/findList",
                type:"post",
                //解决后台数据格式不匹配问题，分页自动处理
                dataSrc: function (json) {
                    json.draw = json.data.draw;
                    json.recordsTotal = json.data.recordsTotal;
                    json.recordsFiltered = json.data.recordsFiltered;
                    return json.data.data;
                }


            },
            //datatable会自动把数据查询到的数据放到data里面

            "columns": [
                {data: 'id',
                    render: function (data, type, row,meta) {
                        return '<input type="checkbox"  name = "edit_empId" value="'+data+'" />';
                    }


                },

                {
                    data: 'productName'

                },
                {data: 'mainImage',

                    render: function (data, type, row,meta) {
                        return '<img src="'+data+'" width="50" />';
                    }

                },
                {
                    data: 'price',
                    render: function (data, type, row,meta) {
                        return '$' + data;
                    }
                },
                {data: 'stock'},
                {data: 'createTime'},
                {data: 'updateTime'}

            ]


        });

    }


</script>
</body>
</html>
