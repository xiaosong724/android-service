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
            <a href="#" class="gooey-menu-item" data-toggle="modal" data-target="#exampleModal"> <i class="fa fa-train">写日志</i>
            </a>
            <a href="#" class="gooey-menu-item"> <i class="fa fa-bicycle">改日志</i> </a>
            <a href="#" class="gooey-menu-item"><i class="fa fa-rocket">搜索 </i> </a>
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
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/lovelogup" method="post" enctype="multipart/form-data" onsubmit="return examinetab()">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">New message</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="recipient-title" class="control-label">标题:</label>
                        <input type="text" name="title" class="form-control" id="recipient-title">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">作者:</label>
                        <input type="text" name="username" class="form-control" id="recipient-name">
                    </div>
                    <div class="form-group">
                        <label for="recipient-logtype" class="control-label">搜索关键词:</label>
                        <input type="text" name="logtype" class="form-control" id="recipient-logtype">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">日志:</label>
                        <textarea class="form-control"  name="message" id="message-text"></textarea>
                    </div>
                    <div class="form-group">
                        <section role="main" class="l-main">

                            <div class="uploader__box js-uploader__box l-center-box">
                                <div class="uploader__contents">
                                    <label class="button button--secondary" for="fileinput">请选择文件</label>
                                    <input id="fileinput" class="uploader__file-input" type="file" multiple
                                           value="Select Files">
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Send message</button>
                </div>
                <input type="hidden" name="deleteindex" class="form-control" value="" id="delete_file_index">

            </form>
        </div>
    </div>
</div>
<script src="anniu/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="anniu/gooey.min.js"></script>
<script src="homepagetwo/js/main.js"></script> <!-- Resource jQuery -->
<script src="js/jquery.imageuploader.js"></script>
<script>
    //日志填写验证
    function examinetab() {
        if($("#recipient-title").val().length<1){
            alert("标题长度大于1");
            return false;
        }
        if($("#recipient-name").val().length<1){
            alert("作者名未填写");
            return false;
        }
        if($("#recipient-logtype").val().length<1){
            alert("搜索类型未填写");
            return false;
        }
        if($("#message-text").val().length<1){
            alert("日记不能不写哦");
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
