<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/9
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4种超酷Bootstrap图片画廊和lightbox效果模板|DEMO1_jQuery之家-自由分享jQuery、html5、css3的插件库</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/htmleaf-demo.css">
    <link rel="stylesheet" href="css/baguetteBox.min.css">
    <link rel="stylesheet" href="css/gallery-clean.css">
</head>
<body>
<div class="htmleaf-container">

    <div class="container gallery-container">

        <h1>设置欢迎界面背景图</h1>

        <p class="page-description text-center">${listimg.pageNum}/${listimg.pages}页数</p>
        <nav aria-label="...">
            <ul class="pager">
                <li class="previous"><a href="/upadteback?page=${listimg.prePage}" style="color: black"><span aria-hidden="true">&larr;</span> Older</a></li>
                <li class="next"><a href="/upadteback?page=${listimg.nextPage}" style="color: black">Newer <span aria-hidden="true">&rarr;</span></a></li>
            </ul>
        </nav>
        <div class="tz-gallery">

            <div class="row">
                <c:forEach var="imgback" items="${listimg.list}">
                    <div class="col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <a class="lightbox" href="${imgback.backpath}">
                                <img src="${imgback.smbackpath}" alt="Park">
                            </a>
                            <div class="caption">
                                <h3 style="text-align: center;overflow: hidden; white-space: nowrap;text-overflow: ellipsis;webkit-text-overflow: ellipsis;">${imgback.backname}</h3>

                                <label class="btn btn-primary active">
                                    <input type="radio" name="options_back"  onclick="update_background(${imgback.id})" autocomplete="off" value="${imgback.yesback}"  ${imgback.yesback=="show"?"checked":""}> 设为背景
                                </label>

                                <label class="btn btn-success active">
                                    <input type="checkbox" name="options_hight" id="ckbox${imgback.id}" onclick="update_background_hight(${imgback.id})" autocomplete="off" value="${imgback.yesback}"  ${imgback.hightback=="100%"?"checked":""}> 背景拉伸
                                </label>

                            </div>
                        </div>
                    </div>
                </c:forEach>
                <p class="page-description text-center">${listimg.pageNum}/${listimg.pages}页数</p>
                <nav aria-label="...">
                    <ul class="pager">
                        <li class="previous"><a href="/upadteback?page=${listimg.prePage}" style="color: black"><span aria-hidden="true">&larr;</span> Older</a></li>
                        <li class="next"><a href="/upadteback?page=${listimg.nextPage}" style="color: black">Newer <span aria-hidden="true">&rarr;</span></a></li>
                    </ul>
                </nav>
            </div>

        </div>

    </div>

</div>
<%--修改欢迎背景--%>
<div class="modal fade" id="exampleModal_back" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/lovelogup" method="post" enctype="multipart/form-data" onsubmit="return examinetab()">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel_back">New Bcakground</h4>

                </div>
                <div class="modal-body">


                    <div class="form-group">
                        <label for="recipient-logtype_back" class="control-label">搜索关键词:</label>
                        <input type="text" name="logtype" class="form-control" id="recipient-logtype_back">
                    </div>

                    <div class="form-group">
                        <input type="file" name="file" class="form-control" id="recipient-backpath_back">
                    </div>
                </div>
                <input type="hidden" name="showid" class="form-control" value="" id="index_back_show">
                <input type="hidden" name="hideid" class="form-control" value="" id="index_back_hide">
                <div class="modal-footer">
                    <span id="himt_value_back" style="color: red"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                    <button type="submit" class="btn btn-primary">发表</button>
                </div>

            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="js/baguetteBox.min.js"></script>
<script src="anniu/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
    baguetteBox.run('.tz-gallery');

    //设置点击当前单选框为欢迎背景图
    function update_background(my){
        $.ajax({
            type: "POST",
            url: "/radioupdate",
            data: "id=" + my,
            dataType: "text",
            success: function (msg) {
                alert(msg);
            }
        });
    }
    //设置欢迎界面是否拉伸
    function update_background_hight(my){
        var checkdd=document.getElementById("ckbox"+my);
        var ckbox="auto";
        if(checkdd.checked){
            ckbox="100";
        }
        $.ajax({
            type: "POST",
            url: "/checkedupdate",
            data: "id="+ my+"&hightback="+ckbox,
            dataType: "text",
            success: function (msg) {
                alert(msg);
            }
        });
    }
</script>
</body>
</html>
