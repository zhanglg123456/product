
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="https://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/fileinput/js/fileinput.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/validator/dist/js/language/zh_CN.js"></script>
    <link href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/bootstrap/fileinput/css/fileinput.min.css" rel="stylesheet"/>


</head>
<body>
<div class="container-fluid">
    <form id="form" action="upload/insert" method="post" enctype="multipart/form-data">
        <%--  存放上传后的文件路径--%>
        <span id="photoinputhidden"></span>
        <div class="row form-group">
            <label class="col-md-4">图片上传:</label>
            <div class="col-sm-12" style="height:500px;" >
                <%--name="photo1"上传的文件名，在控制层直接接就可以--%>
                <input id="uploadfile" name="photo" multiple type="file" data-show-caption="true">
            </div>
        </div>
    </form>
</div>



</body>

<script>
    $(function(){
        upFile("uploadfile");
    })

    function upFile(ctrlName){
        var files = $('#' + ctrlName);


        files.fileinput({

        language: 'zh_CN', //设置语言

        uploadUrl:"<%=request.getContextPath()%>/movie/ossUpFile", //上传的地址

        allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀

        //uploadExtraData:{"id": 1, "fileName":'123.mp3'},

        uploadAsync: true, //默认异步上传

        showUpload:true, //是否显示上传按钮

        showRemove :true, //显示移除按钮

        showPreview :true, //是否显示预览

        showCaption:false,//是否显示标题

        browseClass:"btn btn-primary", //按钮样式

        dropZoneEnabled: true,//是否显示拖拽区域

        //minImageWidth: 50, //图片的最小宽度

        //minImageHeight: 50,//图片的最小高度

        maxImageWidth: 1000,//图片的最大宽度

        maxImageHeight: 1000,//图片的最大高度

        //maxFileSize:0,//单位为kb，如果为0表示不限制文件大小

        //minFileCount: 0,

        maxFileCount:10, //表示允许同时上传的最大文件个数

        enctype:'multipart/form-data',

        validateInitialCount:true,

        previewFileIcon: "<iclass='glyphicon glyphicon-king'></i>",

        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

    }).on("fileuploaded", function (event, data, previewId, index){

            //上传中
            var form = data.form, files = data.files, extra = data.extra,
                response = data.response, reader = data.reader;
            console.log('文件正在上传');
        }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
            //上传成功后把文件名存到隐藏表单域
            $("#photoinputhidden").append('<input type="hidden" value='+data.response+' name="photo">');
            console.log('文件上传成功！'+data.id);

        }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
            console.log('文件上传失败！'+data.id);


        });
}

</script>
</html>
