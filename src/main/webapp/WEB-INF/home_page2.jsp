<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="zh" class="no-js">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主页</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="anniu/gooey.min.css">
    <link rel="stylesheet" href="anniu/livedemo.css">
    <link rel="stylesheet" href="homepagetwo/css/reset.css"> <!-- CSS reset -->
    <link rel="stylesheet" type="text/css" href="homepagetwo/css/default.css">
    <link rel="stylesheet" href="homepagetwo/css/style.css"> <!-- Resource style -->

    <script src="homepagetwo/js/modernizr.js"></script> <!-- Modernizr -->

</head>
<style type="text/css">
    .container {
        margin: 0 auto;
    }

    .content {
        max-width: 660px;
        margin: 0 auto;
    }

    .content img {
        max-width: 100%;
    }

    @media (max-width: 800px) {
        .content {
            max-width: 100%;
            margin: 20px;
        }
    }
</style>
<body id="showbodyimg">
<header class="htmleaf-header">
    <h1>爱你哦~繁狗</h1>
    <div style="width: 100%">
        <nav id="gooey-h">
            <input type="checkbox" class="menu-open" name="menu-open2" id="menu-open2"/>
            <label class="open-button" for="menu-open2">
                <span class="burger burger-1"></span>
                <span class="burger burger-2"></span>
                <span class="burger burger-3"></span>
            </label>
            <a href="#" class="gooey-menu-item" data-toggle="modal" data-target="#exampleModal"> <i class="fa fa-train">写</i>
            </a>
            <a href="#" class="gooey-menu-item"data-toggle="modal" data-target="#exampleModal_up"> <i class="fa fa-bicycle">改</i> </a>
            <a href="#" class="gooey-menu-item"><i class="fa fa-rocket">搜索 </i> </a>
            <a href="/upadteback" class="gooey-menu-item" ><i class="fa fa-rocket">改背</i> </a>
            <a href="#" class="gooey-menu-item" data-toggle="modal" data-target="#exampleModal_back"><i class="fa fa-rocket">添加</i> </a>
            <a href="/home" class="gooey-menu-item"><i class="fa fa-rocket">刷新 </i> </a>
            <%--<a href="#" class="gooey-menu-item"> <i class="fa fa-automobile"></i> </a>--%>
        </nav>
    </div>
    <p class="page-description text-center">${loveLogs.pageNum}/${loveLogs.pages}页数</p>
    <nav aria-label="...">
        <ul class="pager">
            <li class="previous"><a href="/home?page=${loveLogs.prePage}" style="color: black"><span aria-hidden="true">&larr;</span> Older</a></li>
            <li class="next"><a href="/home?page=${loveLogs.nextPage}" style="color: black">Newer <span aria-hidden="true">&rarr;</span></a></li>
        </ul>
    </nav>
</header>
<main class="cd-main">

    <ul class="cd-gallery">
        <c:forEach var="lovelog" items="${loveLogs.list}">
            <li class="cd-item"
                style="background-image:url('${lovelog.coversrc}');background-size: 100% auto;background-position: center center;">
                <c:url var="myURL" value="../item-1.jsp">
                    <c:param name="id" value="${lovelog.id}"/>
                    <c:param name="username" value="${lovelog.username}"/>
                    <c:param name="title" value="${lovelog.title}"/>
                    <c:param name="logtype" value="${lovelog.logtype}"/>
                    <c:param name="message" value="${lovelog.message}"/>
                    <c:param name="viewcount" value="${lovelog.viewcount}"/>
                </c:url>
                <a onclick="show_img_list(${lovelog.id})" href="/<c:out value="${myURL}"/>">
                    <div>
                        <h2>${lovelog.title}</h2>
                        <p>ID:${lovelog.id} Time:<fmt:formatDate
                                value="${lovelog.nowtime}" pattern="yyyy-MM-dd"/></p>
                        <b>Forever Love</b>
                    </div>
                </a>
            </li>
        </c:forEach>
    </ul> <!-- .cd-gallery -->

</main> <!-- .cd-main -->


<div class="cd-folding-panel">

    <div class="fold-left"></div> <!-- this is the left fold -->

    <div class="fold-right"></div> <!-- this is the right fold -->

    <div class="cd-fold-content">
        <!-- content will be loaded using javascript -->
    </div>

    <a class="cd-close" href="#0"></a>
</div> <!-- .cd-folding-panel -->
<%@include file="fromupdate.jsp"%>
<script src="anniu/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="anniu/gooey.min.js"></script>
<script src="homepagetwo/js/main.js"></script> <!-- Resource jQuery -->
<script src="js/jquery.cookie.js"></script>
<script>
    var formDatalog = new FormData();
    //日志填写验证
    function examinetab() {

        return false;
    }


/* -------------------------显示点开的日志图片------------------------------- */
    function show_img_list(logid) {
        setTimeout(function () {  $.ajax({
                type: "POST",
                url: "/showloglist",
                data: "logid="+logid,
                dataType: "json",
                success: function (msg) {
                    $("#imgshow_log").html("");
                    for(var imgp in msg){
                        $("#imgshow_log").html($("#imgshow_log").html()+"\n" +
                            "<img src=\"/img/mini/"+msg[imgp].imgpath+"\" id=\""+msg[imgp].imgid+"\" onclick=\"show_hide_title('"+msg[imgp].imgpath+"')\" class=\"songsize\">")
                    }

                }
            })}

        ,1500);

    }
    //点击显示点击的图片
    function show_hide_title(pathimg){
        if($("#exampleModal_imgshow").is(":hidden")){
            $("#exampleModal_imgshow").modal('show');
        }else {
            $("#exampleModal_imgshow").modal('hide');
        }

        $("#pathimg_click").attr("src","/img/"+pathimg);

    }
    //再次点击隐藏
    function pathimg_click() {
        $("#exampleModal_imgshow").modal('hide');
    };
/* -------------------------显示点开的日志图片------------------------------- */



/* -------------------------对日志发表评论------------------------------- */
    //点击评论按钮,按钮变成文本框
    function btnchangetext(my) {
        $(my).after("<input id='comment_val' type=\"text\" class=\"form-control\" placeholder=\"不能为空哦...\">\n" +
            "      <span class=\"input-group-btn\">\n" +
            "        <button onclick='add_log_comment(this)' class=\"btn btn-default\" type=\"button\">To</button>\n" +
            "      </span>");
        $(my).parent().before("<nav aria-label=\"Page navigation\">\n" +
            "  <ul class=\"pagination\">\n" +
            "    <li>\n" +
            "      <a href=\"#\" id='per_page' onclick='pagecomment(0)' aria-label=\"Previous\">\n" +
            "        <span aria-hidden=\"true\">&laquo;</span>\n" +
            "      </a>\n" +
            "    </li>\n" +
            "    <li>\n" +
            "      <a href=\"#\" id='next_page' onclick='pagecomment(0)' aria-label=\"Next\">\n" +
            "        <span aria-hidden=\"true\">&raquo;</span>\n" +
            "      </a>\n" +
            "    </li>\n" +
            "  </ul>\n" +
            "</nav>");
        $(my).remove();
        var cmt_id=$("#cmt_id").attr("cmtid");
        $.ajax({
            type: "POST",
            url: "/showcomment",
            data: "logid="+cmt_id,
            dataType:"json",
            success: function (msg) {
                $("#per_page").attr("onclick","pagecomment("+msg[1]+")");
                $("#next_page").attr("onclick","pagecomment("+msg[0]+")");
                $("#comment_show_list").html("");
                var commentlist=JSON.parse(msg[2]);
                for (var val in commentlist) {
                    $("#comment_show_list").html($("#comment_show_list").html()+"<div>\n" +
                        "\t<em>"+commentlist[val].logname+":</em><em class=\"pull-right\">"+commentlist[val].id+"楼 TOP</em>\n" +
                        "\t<p>"+commentlist[val].comment+"</p>\n" +
                        "\t<p style=\"font-size: x-small\" class=\"pull-right\">"+commentlist[val].texttime+"</p>\n" +
                        "</div>\n" +
                        "<h2 class=\"page-header\"></h2>");

                }
                if(commentlist.length==0){
                    $("#comment_show_list").html("<em>当前日志没有评论~</em>");
                }
            }
        });
    }
    //发送日志评论
    function add_log_comment(my) {
        var comment_val=$("#comment_val").val();
        var user_name_log=$.cookie("user_name_log");
        var cmt_id=$("#cmt_id").attr("cmtid");
        var cmttitle=$("#cmt_id").attr("cmttitle");
        if(comment_val.length<1){
            alert("评论不能为空");
            return;
        }
        if(user_name_log==undefined){
            alert("你没有登录");
            return;
        }
        $(my).attr("disabled","disabled");
        $.ajax({
            type: "POST",
            url: "/addcommert",
            data: "comment="+comment_val+"&logname="+user_name_log+"&logid="+cmt_id+"&title="+cmttitle,
            dataType: "json",
            success: function (msg) {
                $("#comment_show_list").html("<div>\n" +
                    "\t<em>"+msg.logname+":</em><em class=\"pull-right\">"+msg.id+"楼 TOP</em>\n" +
                    "\t<p>"+msg.comment+"</p>\n" +
                    "\t<p style=\"font-size: x-small\" class=\"pull-right\">"+msg.texttime+"</p>\n" +
                    "</div>\n" +
                    "<h2 class=\"page-header\"></h2>"+ $("#comment_show_list").html());
                $("#comment_val").val("");
                $(my).attr("disabled",false);
            }
        });

    }
/* -------------------------对日志发表评论------------------------------- */






    //修改日志
    function ajax_query_log(){
        var recipient_id_up=$("#recipient_id_up");
        var zhengzhe=/^[0-9]*$/;
        var id_standard=$("#id_standard");
        if(recipient_id_up.val().length<1){
            id_standard.text("ID未填写");
            return;
        }
        if(!zhengzhe.test(recipient_id_up.val())){
            id_standard.text("请填写正确的ID(纯数字)");
            return;
        }
        $.ajax({
            type: "POST",
            url: "/querylog",
            data: "logid="+recipient_id_up.val(),
            dataType: "json",
            success: function (msg) {
                if(msg[0]==null){
                    id_standard.text("ID: "+recipient_id_up.val()+",未找到!");
                    return;
                }
                val_up(msg);
            }
        });
    }
    //把请求修改的日志的val放入输入框
    function val_up(loveLog){
        var recipient_title_up =$("#recipient_title_up");
        var recipient_name_up =$("#recipient_name_up");
        var recipient_logtype_up =$("#recipient_logtype_up");
        var message_text_up =$("#message_text_up");
        var recipient_hidden_id_up =$("#recipient_hidden_id_up");
        var img_val =$("#img_val");
        recipient_title_up.val(loveLog[0].title);
        recipient_name_up.val(loveLog[0].username);
        recipient_logtype_up.val(loveLog[0].logtype);
        message_text_up.val(loveLog[0].message);
        recipient_hidden_id_up.val(loveLog[0].id);

        var imgp=loveLog[1];
        img_val.html("");
        for(var imgpa in imgp){
            img_val.html(img_val.html()+
                "<span><button style='display: none;float: left;position: absolute;' ltitle=\""+loveLog[0].title+"\" lid=\""+loveLog[0].id+"\" dename=\""+imgp[imgpa].imgpath+"\"deid=\""+imgp[imgpa].imgid+"\" onclick=\"delete_img(this)\" type=\"button\"class='btn btn-danger'>删除</button><img onclick='show_button(this)' id=\""+imgp[imgpa].imgid+"\" class=\"songsize\" src=\"img/mini/"+imgp[imgpa].imgpath+"\"></span>"
            );

        }

    }


        //显示隐藏的删除button
        function show_button(my){
            //添加移动端长按弹宽
            $(my).on({
                touchstart: function(e) {
                    // 长按事件触发
                    timeOutEvent = setTimeout(function() {
                        timeOutEvent = 0;
                       console.log($(my).prev())
                        $(my).prev().show()
                    }, 400);
                    //长按400毫秒
                    // e.preventDefault();
                },
                touchmove: function() {
                    clearTimeout(timeOutEvent);
                    timeOutEvent = 0;
                },
                // touchend: function() {
                //     clearTimeout(timeOutEvent);
                //     if (timeOutEvent != 0) {
                //         // 点击事件
                //         // location.href = '/a/live-rooms.html';
                //         //alert(typeof $(this).html());
                //     }
                //     return false;
                // }
            })
        };
    //修改页面:删除当前点击的图片
    function delete_img(my){
        var imgid =$(my).attr("deid");
        var impath=$(my).attr("dename");
        var logid=$(my).attr("lid");
        var ltitle=$(my).attr("ltitle");
        $(my).parent().remove();
        $.ajax({
            url:"/deleteimgid",
            type:"post",
            data: "imgid="+imgid+"&impath="+impath+"&logid="+logid+"&title="+ltitle,
            dataType: "json",
            success: function (msg) {

            }
        });

    }


/* -------------------------新添加的日志并上传新的图片------------------------------- */
    //预览新添加的日志图片
    $("#newlogfile").on('change',function (e) {
        var file=e.target.files || e.dataTransfer.files || e.dataTransfer.getData;
        for(var i = 0; i < file.length; i++){
            formDatalog.append("file",file[i]);
            console.log(file[i].name)
        }
        var countFiles = $(this)[0].files.length;

        var imgPath = $(this)[0].value;
        var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
        var image_holder = $("#image-holder2");
        //image_holder.empty();

        if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
            if (typeof (FileReader) != "undefined") {

                // 循环所有要上传的图片
                for (var i = 0; i < countFiles; i++) {

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("<img />", {
                            "src": e.target.result,
                            "class": "thumb-image",
                            "style": "width:33.3%"
                        }).appendTo(image_holder);
                    }

                    image_holder.show();
                    reader.readAsDataURL($(this)[0].files[i]);
                }

            } else {
                alert("你的浏览器不支持FileReader！");
            }
        } else {
            alert("请选择图像文件。");
        }
        //console.log(FormData);
        // console.log(file);
    });
    //
    function upload_log_text() {
        var recipient_title =$("#recipient_title").val();
        var recipient_name =$("#recipient_name").val();
        var recipient_logtype =$("#recipient_logtype").val();
        var message_text =$("#message_text").val();
        var himt_value=$("#himt_value");
        if(recipient_title.length<1){
            himt_value.text("标题长度大于1");
            return false;
        }
        if(recipient_name.length<1){
            himt_value.text("作者名未填写");
            return false;
        }
        if(recipient_logtype.length<1){
            himt_value.text("搜索类型未填写");
            return false;
        }
        if(message_text.length<1){
            himt_value.text("日记不能不写哦");
            return false;
        }
        $("#one_click").attr("disabled","disabled");

        formDatalog.append("username",recipient_name);
        formDatalog.append("title",recipient_title);
        formDatalog.append("logtype",recipient_logtype);
        formDatalog.append("message",message_text);
        $.ajax({
            url: "/lovelogup",
            type: "POST",
            data: formDatalog,
            /**
             *必须false才会自动加上正确的Content-Type
             */
            contentType: false,
            /**
             * 必须false才会避开jQuery对 formdata 的默认处理
             * XMLHttpRequest会对 formdata 进行正确的处理
             */
            processData: false,
            dataType:"text",
            success: function (msg) {
                alert(msg);
                window.location.reload();
            }
        });

    }
    /* -------------------------新添加的日志并上传新的图片------------------------------- */





/* -------------------------修改日志的文本信息,或者并上传新的图片------------------------------- */
    //修改日志,新添加的图片预览
    $("#scscsc").on('change',function (e) {
        var file=e.target.files || e.dataTransfer.files || e.dataTransfer.getData;
        for(var i = 0; i < file.length; i++){
            formDatalog.append("file",file[i]);
            console.log(file[i].name)
        }
        var countFiles = $(this)[0].files.length;
        var imgPath = $(this)[0].value;
        var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
        var image_holder = $("#image-holder");
        //image_holder.empty();

        if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
            if (typeof (FileReader) != "undefined") {

                // 循环所有要上传的图片
                for (var i = 0; i < countFiles; i++) {

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("<img />", {
                            "src": e.target.result,
                            "class": "thumb-image",
                            "style": "width:33.3%"
                        }).appendTo(image_holder);
                    }

                    image_holder.show();
                    reader.readAsDataURL($(this)[0].files[i]);
                }

            } else {
                alert("你的浏览器不支持FileReader！");
            }
        } else {
            alert("请选择图像文件。");
        }
        //console.log(FormData);
        // console.log(file);
    });
    //上传图片,修改日志
    function update_log_text() {
        var recipient_title_up =$("#recipient_title_up").val();
        var recipient_name_up =$("#recipient_name_up").val();
        var recipient_logtype_up =$("#recipient_logtype_up").val();
        var message_text_up =$("#message_text_up").val();
        var recipient_hidden_id_up =$("#recipient_hidden_id_up").val();
        formDatalog.append("id",recipient_hidden_id_up);
        formDatalog.append("username",recipient_name_up);
        formDatalog.append("title",recipient_title_up);
        formDatalog.append("logtype",recipient_logtype_up);
        formDatalog.append("message",message_text_up);
        $("#one_click2").attr("disabled","disabled");
        $.ajax({
            url: "/uploadimgupdate",
            type: "POST",
            data: formDatalog,
            /**
             *必须false才会自动加上正确的Content-Type
             */
            contentType: false,
            /**
             * 必须false才会避开jQuery对 formdata 的默认处理
             * XMLHttpRequest会对 formdata 进行正确的处理
             */
            processData: false,
            dataType:"text",
            success: function (data) {
                alert(data);
                window.location.reload();
            }
            });

    }
/* -------------------------修改日志的文本信息,或者并上传新的图片------------------------------- */



/* ----------------------------上传新添加的背景图显示预览---------------------------------- */
    $("#recipient-backpath_back").on('change', function () {

        if (typeof (FileReader) != "undefined") {

            var image_holder = $("#image-holder3");
            image_holder.empty();

            var reader = new FileReader();
            reader.onload = function (e) {
                $("<img />", {
                    "src": e.target.result,
                    "class": "thumb-image",
                    "style": "width:50%"
                }).appendTo(image_holder);

            }
            image_holder.show();
            reader.readAsDataURL($(this)[0].files[0]);
        } else {
            alert("你的浏览器不支持FileReader.");
        }
    });
    // 上传欢迎背景图
    function  uploadback(){
        var formData = new FormData();
        var radioval= $("input[name='background_img']:checked").val();
        var fileback= $("#recipient-backpath_back").val();
        var ckbox_hight=$('#ckbox_hight').prop('checked');
        if(fileback==""){
            alert("文件不能为空");
            return false;
        }
        var hightback="auto";
        if(ckbox_hight){
            hightback="100%";
        }
    $("#onlyone_click").attr("disabled","disabled");//点击一次后上传按钮失效
        formData.append("myfile", document.getElementById("recipient_backpath_back").files[0]);
        formData.append("yesback", radioval);
        formData.append("hightback",hightback);
        $.ajax({
            url: "/uploadbackground",
            type: "POST",
            data: formData,
            /**
             *必须false才会自动加上正确的Content-Type
             */
            contentType: false,
            cache: false,
            /**
             * 必须false才会避开jQuery对 formdata 的默认处理
             * XMLHttpRequest会对 formdata 进行正确的处理
             */
            processData: false,
            dataType:"text",
            success: function (data) {

                document.getElementById("recipient_backpath_back").value="";
                $("#onlyone_click").removeAttr("disabled");
                $("#exampleModal_back").modal('hide');//模态框手动关闭
                alert(data);
            },
            error: function () {
                alert("上传失败！");
            }
        });
    }
/* ----------------------------上传新添加的背景图显示预览---------------------------------- */


/* ----------------------------登录或注册---------------------------------- */

    //输入查询用户是否存在
    $("#recipient_login").on('change',function (){
        var name_log=$("#recipient_login").val();
        var himt_value_login=$("#himt_value_login");
        $.ajax({
            url:"/existlogname",
            type:"post",
            data: "logname="+name_log,
            dataType:"text",
            success: function (msg) {
                himt_value_login.text(msg);
            }
        });
    });
    //用户登录或注册验证
    function login_log(){
      var name_log=$("#recipient_login").val();
      var pass_log=$("#recipient_login_password").val();
      var himt_value_login=$("#himt_value_login");
      if(name_log.length<1||pass_log.length<3){
          himt_value_login.text("用户名长度大于1,密码大于3!");
          return;
      }
        $.ajax({
            url:"/loginandregister",
            type:"post",
            data: "logname="+name_log+"&logpassword="+pass_log,
            dataType:"text",
            success: function (msg) {
                if(msg=="用户存在,但是密码不正确"){
                    himt_value_login.text(msg);
                }
                if(msg=="新用户注册成功!"||msg=="登陆成功!"){
                    $("#exampleModal_login").modal('hide');
                }
            }
        });

    }
/* ----------------------------登录或注册---------------------------------- */


    $(function ($) {
        $("#gooey-h").gooeymenu({
            bgColor: "#68d099",
            contentColor: "white",
            style: "horizontal",
            horizontal: {
                menuItemPosition: "glue"
            },
            vertical: {
                menuItemPosition: "spaced",
                direction: "up"
            },
            circle: {
                radius: 90
            },
            margin: "small",
            size: 50,
            bounce: true,
            bounceLength: "small",
            transitionStep: 100,
            hover: "#5dbb89"
        });
            if($.cookie('user_name_log')==undefined){
                $("#exampleModal_login").modal('show');
            }
    });


</script>
</body>
</html>
