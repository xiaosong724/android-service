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
    <link rel="stylesheet" href="anniu/gooey.min.css">
    <link rel="stylesheet" href="anniu/livedemo.css">
    <link rel="stylesheet" href="homepagetwo/css/reset.css"> <!-- CSS reset -->
    <link rel="stylesheet" type="text/css" href="homepagetwo/css/default.css">
    <link rel="stylesheet" href="homepagetwo/css/style.css"> <!-- Resource style -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="css/styles.imageuploader.css" rel="stylesheet" type="text/css">
    <script src="homepagetwo/js/modernizr.js"></script> <!-- Modernizr -->
    <style>

    </style>
</head>
<body>
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
            <a href="javascript:;" class="gooey-menu-item" data-toggle="modal" data-target="#exampleModal"> <i class="fa fa-train">写日志</i>
            </a>
            <a href="#" class="gooey-menu-item"data-toggle="modal" data-target="#exampleModal_up"> <i class="fa fa-bicycle">改日志</i> </a>
            <a href="javascript:;" class="gooey-menu-item"><i class="fa fa-rocket">搜索 </i> </a>
            <%--<a href="#" class="gooey-menu-item"> <i class="fa fa-automobile"></i> </a>--%>
        </nav>
    </div>

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
                    <c:param name="imgsrc" value="${lovelog.imgsrc}"/>
                </c:url>
                <a href="/<c:out value="${myURL}"/>">
                    <div>
                        <h2>${lovelog.title}</h2>
                        <p>Time:<fmt:formatDate
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
<script src="js/jquery.imageuploader.js"></script>
<script>
    //日志填写验证
    function examinetab() {
        var himt_value=$("#himt_value");
        if($("#recipient-title").val().length<1){
            himt_value.text("标题长度大于1");
            //alert("标题长度大于1");
            return false;
        }
        if($("#recipient-name").val().length<1){
            himt_value.text("作者名未填写");
          //  alert("作者名未填写");
            return false;
        }
        if($("#recipient-logtype").val().length<1){
            himt_value.text("搜索类型未填写");
          //  alert("搜索类型未填写");
            return false;
        }
        if($("#message-text").val().length<1){
            himt_value.text("日记不能不写哦");
           // alert("日记不能不写哦");
            return false;
        }

        return true;
    }

    (function () {
        var options = {};
        $('.js-uploader__box').uploader({
            'selectButtonCopy': '请选择或拖拽文件',
            'instructionsCopy': '你可以选择或拖拽多个文件',
            'submitButtonCopy': '上传选择的文件',
            'furtherInstructionsCopy': '你可以选择或拖拽更多的文件',
            'secondarySelectButtonCopy': '选择更多的文件'
        });
    }());

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
                if(msg==null){
                    id_standard.text("ID: "+recipient_id_up.val()+",未找到!");
                    return;
                }
                val_up(msg);

            }
        });
    }
    //把请求的val放入输入框
    function val_up(loveLog){
        var recipient_title_up =$("#recipient_title_up");
        var recipient_name_up =$("#recipient_name_up");
        var recipient_logtype_up =$("#recipient_logtype_up");
        var message_text_up =$("#message_text_up");
        var img_val =$("#img_val");
        recipient_title_up.val(loveLog.title);
        recipient_name_up.val(loveLog.username);
        recipient_logtype_up.val(loveLog.logtype);
        message_text_up.val(loveLog.message);
        img_val.html(loveLog.imgsrc);
        //去除a标签跳转并添加是否删除图片标记
        $("#img_val").on("click",".song_a",function(){
            var alist=$("#img_val img");
            $("#img_val a").each(function(){
                var img_url = $(this).attr("src");
                $(this).attr('href','javascript:');
            });
           var imgstr= $(this).html();
            //添加移动端长按弹宽
            $(this).on({
                touchstart: function(e) {
                    // 长按事件触发
                    timeOutEvent = setTimeout(function() {
                        timeOutEvent = 0;
                        $("#showdetele").show();
                        $("#del_msg").attr("del",imgstr);

                    }, 400);
                    //长按400毫秒
                    // e.preventDefault();
                },
                touchmove: function() {
                    clearTimeout(timeOutEvent);
                    timeOutEvent = 0;
                },
                touchend: function() {
                    clearTimeout(timeOutEvent);
                    if (timeOutEvent != 0) {
                        // 点击事件
                        // location.href = '/a/live-rooms.html';
                        alert(typeof $(this).html());

                    }
                    return false;
                }
            })


        });
    }
    function delete_img(delmsg){

        $("#showdetele").hide();
    }
    function delete_box(){
        $("#showdetele").hide();
    }





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

    });
</script>
</body>
</html>
