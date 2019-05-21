<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/21
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>pk</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        .top {
            margin-top: 80px
        }

        .imgtop {
            margin-top: -80px
        }
        .renwu_one_top{
            margin-top: 190px;
            margin-left: 140px;
        }
        .renwu_tow_top{
            margin-top: 190px;
            margin-left: 500px;
        }
    </style>
</head>
<body>
<div class="panel-body">
    <div class="row" style="background: url(yx/pkbackgroud.jpg);background-size:100% 100%;height: 100%" ;>

        <!--左边血条头像-->
        <div class="col-xs-4 col-md-4 top">
            <div class="pull-left">
                <div class="row">
                    <div class="pull-right">
                        <div class="progress" style="width:200px">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 60%;">
                                60%
                            </div>
                        </div>
                    </div>
                    <div class="pull-left imgtop">
                        <p style="color: white;font-size: 25px">[潜水狗]</p>
                        <img src="/yx/head/qianshui_s.gif" alt="40x40" style="width: 100px; height: 100px;"
                             class="img-rounded">

                    </div>
                </div>

            </div>

            <!--左边边宠物-->
            <div class="row">
                <img src="/yx/head/qianshui.gif" alt="40x40" style="width: 166px; height: 166px;"
                     class="img-rounded renwu_one_top">
                <div><img src=""></div>
            </div>
            <!--左边宠物-->
        </div>
        <!--左边血条头像-->

        <div class="col-xs-4 col-md-4 top"></div>

        <!--右边血条头像-->
        <div class="col-xs-4 col-md-4 top">
            <div class="pull-left">
                <div class="row">
                    <div class="pull-left">
                        <div class="progress" style="width:200px">
                            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
                                 aria-valuemax="100" style="width: 60%;">
                                60%
                            </div>
                        </div>
                    </div>
                    <div class="pull-right imgtop">
                        <p style="color: white;font-size: 25px">[洗澡狗]</p>
                        <img src="/yx/head/xizao_s_r.gif" alt="40x40" style="width: 100px; height: 100px;"
                             class="img-rounded">
                    </div>
                </div>
            </div>
        </div>
        <!--右边血条头像-->

        <!--右边宠物-->
        <div class="row">
            <img src="/yx/head/xizao_r.gif" alt="40x40" style="width: 166px; height: 166px;"
                 class="img-rounded renwu_tow_top">
        </div>
        <!--右边宠物-->
    </div>

</div>
</body>
<script src="anniu/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<script>
    $(function ($){

    });

</script>
</html>
