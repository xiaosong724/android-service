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
    <link rel="stylesheet" href="css/youxi.css">

</head>
<body>
<div class="row" style="background: url(yx/pkbackgroud.jpg);background-size: 100% 100% ;height: 100%;">

    <!--左边血条头像-->
    <div class="top-l">
        <div class="progress xuetiao-l" style="width:150px">
            <div class="progress-bar" role="progressbar" aria-valuenow="602" aria-valuemin="0"
                 aria-valuemax="100" style="width: 100%;color: red" id="left_hp">
                ${petme.hp}
            </div>
        </div>
        <p id="zuo_name" style="color: white;font-size: 15px;margin-top: -30px">[${petme.petname}]</p>
        <img src="${petme.petpath}" alt="40x40" class="img-rounded imgtop-l">
    </div>
    <!--左边边宠物-->
    <img src="${petme.petmodel}" id="left_cw" alt="40x40" class="img-rounded renwu_one_top">
    <img src="" class="jineng_l" id="jineng_show_l">
    <p class="jineng_l_name" id="jineng_name_show_l">普通攻击</p>
    <!--左边宠物-->
    <!--左边血条头像-->

    <!--PK按钮-->
    <button id="hidepkrun" class="btn btn-danger pkbtn" onclick="pk_game()">开始PK</button>
    <!--PK按钮-->

    <!--右边血条头像-->
    <p style="position: absolute;left:230px;color: #fffe31;font-size: 9px;">
        我的当前等级:${petme.herolevel}--经验:[${petme.maxexp}/${petme.exp}]<br>
        最高攻击:${petme.akt}--防御:${petme.con}<br>
        当前金币:${petme.rmb}--技能点:${petme.wit}<br>
        今天经验金币加成PK还剩:${petme.pknum}次
    </p>
    <p style="position: absolute;left:230px;top:80px;color: #06d4a5;font-size: 9px;">
        敌方当前等级:${petto.herolevel}--经验:[${petto.maxexp}/${petto.exp}]<br>
        最高攻击:${petto.akt}--防御:${petto.con}<br>
        当前金币:${petto.rmb}
    </p>
    <div class="top-r">


        <div class="progress xuetiao-r" style="width:150px">
            <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0"
                 aria-valuemax="100" style="width: 100%;color: red" id="right_hp">
                ${petto.hp}
            </div>
        </div>
        <p id="you_name" style="color: white;font-size: 15px;margin-top: -30px">[${petto.petname}]</p>
        <img src="${petto.petpath}" alt="40x40" class="img-rounded imgtop-r">
    </div>
    <!--右边血条头像-->

    <!--右边宠物-->
    <img src="${petto.petmodel}" alt="40x40" id="right_cw" class="img-rounded renwu_tow_top">
    <img src="" class="jineng_r" id="jineng_show_r">
    <p class="jineng_r_name" id="jineng_name_show_r">普通攻击</p>
    <!--右边宠物-->
</div>

</body>
<script src="anniu/jquery-2.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.cookie.js"></script>
<script>
    $(function ($) {
        // var lfjz = "六方剑阵[防]";
        // var gmxz = "光明星阵[防]";
        // var kjmf = "科技魔法[防]";
        // var jmkjl = "极光空气流[攻]";
        // var fbjq = "风暴聚集[攻]";
        // var jgz = "极光锥[攻]";
        // var ahz = "必杀.暗黑锥[攻]";
        // var ptgj = "普通攻击";
        // var f = "右";
    });

    /*---------------------PK-----------------------*/
    function pk_game() {
        var username = $.cookie("user_name_log");
        if (username == undefined) {
            alert("你没有登录");
            return;
        }
        $("#hidepkrun").attr("disabled","disabled");
        $.ajax({
            url: "/pkgame",
            type: "post",
            data: "mname=" + username,
            dataType: "json",
            success: function (msg) {
                console.log(msg.length);
                if (msg[0].whoakt == '${petme.petname}') {
                    $("#zuo_name").text(msg[0].whoakt);
                    $("#you_name").text(msg[1].whoakt);
                } else {
                    $("#zuo_name").text(msg[1].whoakt);
                    $("#you_name").text(msg[0].whoakt);
                }
                i = 0;
                jn = setInterval(function () {

                    if (i < msg.length) {
                        beidong_show(msg[i].skillname, (msg[i].whoakt =='${petme.petname}') ? "左" : "右", msg[i].harm, msg[i].passivity, msg[i].hpadd,msg[i].csc);
                        if (msg[i].passivity != "") {
                            setTimeout(show_beidong_show(msg[i].passivity, ((msg[i].whoakt == '${petme.petname}') ? "右" : "左"), msg[i].hpadd, 3000));
                        }
                    } else {
                        clearInterval(jn);
                        alert(msg[msg.length - 1].win);
                        window.location.reload();
                        return;
                    }
                    if (msg[i].whoakt == '${petme.petname}') {
                        var left_hp=$("#left_hp");
                        var right_hp=$("#right_hp");
                        left_hp.text(msg[i].hpreduceme);
                        left_hp.css("width",(parseInt(msg[i].hpreduceme)/parseInt(msg[0].hpreduceme)*100+"%"));
                        right_hp.text(msg[i].hpreduceto);
                        right_hp.css("width",(parseInt(msg[i].hpreduceto)/parseInt(msg[0].hpreduceme)*100+"%"));
                    }else {
                        var left_hp=$("#left_hp");
                        var right_hp=$("#right_hp");
                        left_hp.text(msg[i].hpreduceto);
                        left_hp.css("width",(parseInt(msg[i].hpreduceto)/parseInt(msg[0].hpreduceme)*100+"%"));
                        right_hp.text(msg[i].hpreduceme);
                        right_hp.css("width",(parseInt(msg[i].hpreduceme)/parseInt(msg[0].hpreduceme)*100+"%"));
                    }
                    console.log(i);
                    console.log(msg[i].whoakt + ":" + msg[i].skillname);
                    console.log(msg[i].passivity + ":" + msg[i].hpadd);

                    i++;
                }, 3000);
            }
        });
    }


    /*------------------显示技能-------------------*/
    function beidong_show(jn_name, l_or_r, atk_harm, bdname, addhp,csc) {
        switch (jn_name) {
            case "普通攻击":
                putong_gongji(l_or_r, atk_harm, bdname, addhp,csc);
                break;

            case "极光空气流[攻]":
                jineng_name_show(jn_name, l_or_r, atk_harm, bdname, addhp,csc);
                break;
            case "极光锥[攻]":
                jineng_name_show(jn_name, l_or_r, atk_harm, bdname, addhp,csc);
                break;
            case "风暴聚集[攻]":
                jineng_name_show(jn_name, l_or_r, atk_harm, bdname, addhp,csc);
                break;
            case "必杀.暗黑锥[攻]":
                jineng_name_show(jn_name, l_or_r, atk_harm, bdname, addhp,csc);
                break;
        }
    }

    function show_beidong_show(jn_name, l_or_r, hpadd) {
        switch (jn_name) {
            case "六方剑阵[防]":
                jineng_name_show(jn_name, l_or_r, hpadd);
                break;
            case "光明星阵[防]":
                jineng_name_show(jn_name, l_or_r, hpadd);
                break;
            case "科技魔法[防]":
                jineng_name_show(jn_name, l_or_r, hpadd);
                break;
        }
    }

    //显示左边还是右边,及显示的效果判断
    function jineng_name_show(jn_name, l_or_r, atk, bdname, addhp,csc) {
        update_class_name(jn_name, l_or_r);
        switch (jn_name) {
            case "六方剑阵[防]":
                if (l_or_r == "左") {
                    var j_show_l = $("#jineng_show_l");
                    var jname_show_l = $("#jineng_name_show_l");
                    jname_show_l.css("color", "#00ff23");
                    j_show_l.show();
                    j_show_l.fadeOut(1500, "linear");
                } else {
                    var j_show_r = $("#jineng_show_r");
                    var jname_show_r = $("#jineng_name_show_r");
                    jname_show_r.css("color", "#00ff23");
                    j_show_r.show();
                    j_show_r.fadeOut(1500, "linear");
                }
                break;
            case "光明星阵[防]":
                if (l_or_r == "左") {
                    var j_show_l = $("#jineng_show_l");
                    var jname_show_l = $("#jineng_name_show_l");
                    jname_show_l.css("color", "#00ff23");
                    j_show_l.show();
                    j_show_l.fadeOut(1500, "linear");
                } else {
                    var j_show_r = $("#jineng_show_r");
                    var jname_show_r = $("#jineng_name_show_r");
                    jname_show_r.css("color", "#00ff23");
                    j_show_r.show();
                    j_show_r.fadeOut(1500, "linear");
                }
                break;
            case "科技魔法[防]":
                if (l_or_r == "左") {
                    var j_show_l = $("#jineng_show_l");
                    var jname_show_l = $("#jineng_name_show_l");
                    jname_show_l.css("color", "#00ff23")
                    j_show_l.show();
                    j_show_l.fadeOut(1500, "linear");
                } else {
                    var j_show_r = $("#jineng_show_r");
                    var jname_show_r = $("#jineng_name_show_r");
                    jname_show_r.css("color", "#00ff23")
                    j_show_r.show();
                    j_show_r.fadeOut(1500, "linear");
                }
                break;
            case "极光空气流[攻]":
                if (l_or_r == "左") {
                    run_jineng(l_or_r, atk, bdname, addhp,csc);
                } else {
                    run_jineng(l_or_r, atk, bdname, addhp,csc);
                }
                break;
            case "极光锥[攻]":
                if (l_or_r == "左") {
                    run_jineng(l_or_r, atk, bdname, addhp,csc);
                } else {
                    run_jineng(l_or_r, atk, bdname, addhp,csc);
                }
                break;
            case "风暴聚集[攻]":
                if (l_or_r == "左") {
                    run_jineng(l_or_r, atk, bdname, addhp,csc, 40);
                } else {
                    run_jineng(l_or_r, atk, bdname, addhp,csc, 40);
                }
                break;
            case "必杀.暗黑锥[攻]":
                if (l_or_r == "左") {
                    run_jineng(l_or_r, atk, bdname, addhp,csc, 100);
                    var jineng_show_l = $("#jineng_show_l");
                    jineng_show_l.fadeOut(1000, "linear", function () {
                        jineng_show_l.attr("src", "yx/jineng/anheizhuixili.gif");
                        jineng_show_l.show();

                    });
                } else {
                    run_jineng(l_or_r, atk, bdname, addhp,csc, 100);
                    var jineng_show_l = $("#jineng_show_r");
                    jineng_show_l.fadeOut(1000, "linear", function () {
                        jineng_show_l.attr("src", "yx/jineng/anheizhuixili_r.gif");
                        jineng_show_l.show();
                    });
                }
                break;
        }
    }

    //修改class名字
    function update_class_name(cname, r_or_l) {
        switch (cname) {
            case "光明星阵[防]":
                if (r_or_l == "右") {
                    var j_name_r = $("#jineng_name_show_r");
                    var j_img_r = $("#jineng_show_r");
                    j_img_r.prop("class", "jineng_gmxz_r");
                    j_img_r.attr("src", "yx/jineng/beidong/guangmingxizhen.gif");
                    j_name_r.prop("class", "jineng_gmxz_name_r");
                } else {
                    var j_name_l = $("#jineng_name_show_l");
                    var j_img_l = $("#jineng_show_l");
                    j_img_l.prop("class", "jineng_gmxz_l");
                    j_img_l.attr("src", "yx/jineng/beidong/guangmingxizhen.gif");
                    j_name_l.prop("class", "jineng_gmxz_name_l");
                }
                break;
            case "科技魔法[防]":
                if (r_or_l == "右") {
                    var j_name_r = $("#jineng_name_show_r");
                    var j_img_r = $("#jineng_show_r");
                    j_img_r.prop("class", "jineng_kjmf_r");
                    j_img_r.attr("src", "yx/jineng/beidong/kejimofa.gif");
                    j_name_r.prop("class", "jineng_kjmf_name_r");
                } else {
                    var j_name_l = $("#jineng_name_show_l");
                    var j_img_l = $("#jineng_show_l");
                    j_img_l.prop("class", "jineng_kjmf_l");
                    j_img_l.attr("src", "yx/jineng/beidong/kejimofa.gif");
                    j_name_l.prop("class", "jineng_kjmf_name_l");
                }
                break;

            case "六方剑阵[防]":
                if (r_or_l == "右") {
                    var j_name_r = $("#jineng_name_show_r");
                    var j_img_r = $("#jineng_show_r");
                    j_img_r.prop("class", "jineng_r");
                    j_img_r.attr("src", "yx/jineng/beidong/liufangjianzheng.gif");
                    j_name_r.prop("class", "jineng_r_name");
                } else {
                    var j_name_l = $("#jineng_name_show_l");
                    var j_img_l = $("#jineng_show_l");
                    j_img_l.prop("class", "jineng_l");
                    j_img_l.attr("src", "yx/jineng/beidong/liufangjianzheng.gif");
                    j_name_l.prop("class", "jineng_l_name");
                }
                break;
            case "极光空气流[攻]":
                if (r_or_l == "右") {
                    var j_name_r = $("#jineng_name_show_r");
                    var j_img_r = $("#jineng_show_r");
                    j_name_r.text(cname);
                    j_img_r.prop("class", "jineng_jgkql_r");
                    j_img_r.attr("src", "yx/jineng/jiguangkongqiliu_r.gif");
                    j_name_r.prop("class", "jineng_jgkql_name_r");
                    j_name_r.css("color", "white");

                } else {
                    var j_name_l = $("#jineng_name_show_l");
                    var j_img_l = $("#jineng_show_l");
                    j_name_l.text(cname);
                    j_img_l.prop("class", "jineng_jgkql_l");
                    j_img_l.attr("src", "yx/jineng/jiguangkongqiliu.gif");
                    j_name_l.prop("class", "jineng_jgkql_name_l");
                    j_name_l.css("color", "white");
                }
                break;
            case "极光锥[攻]":
                if (r_or_l == "右") {
                    var j_name_r = $("#jineng_name_show_r");
                    var j_img_r = $("#jineng_show_r");
                    j_name_r.text(cname);
                    j_img_r.prop("class", "jineng_jgz_r");
                    j_img_r.attr("src", "yx/jineng/jiguangzhui_r.gif");
                    j_name_r.prop("class", "jineng_jgkql_name_r");
                    j_name_r.css("color", "white");
                } else {
                    var j_name_l = $("#jineng_name_show_l");
                    var j_img_l = $("#jineng_show_l");
                    j_name_l.text(cname);
                    j_img_l.prop("class", "jineng_jgz_l");
                    j_img_l.attr("src", "yx/jineng/jiguangzhui.gif");
                    j_name_l.prop("class", "jineng_jgkql_name_l");
                    j_name_l.css("color", "white");
                }
                break;
            case "风暴聚集[攻]":
                if (r_or_l == "右") {
                    var j_name_r = $("#jineng_name_show_r");
                    var j_img_r = $("#jineng_show_r");
                    j_name_r.text(cname);
                    j_img_r.prop("class", "jineng_fbjj_r");
                    j_img_r.attr("src", "yx/jineng/fengbaojuji.gif");
                    j_name_r.prop("class", "jineng_jgkql_name_r");
                    j_name_r.css("color", "white");
                } else {
                    var j_name_l = $("#jineng_name_show_l");
                    var j_img_l = $("#jineng_show_l");
                    j_name_l.text(cname);
                    j_img_l.prop("class", "jineng_fbjj_l");
                    j_img_l.attr("src", "yx/jineng/fengbaojuji.gif");
                    j_name_l.prop("class", "jineng_fbjjname_l");
                    j_name_l.css("color", "white");
                }
                break;
            case "必杀.暗黑锥[攻]":
                if (r_or_l == "右") {
                    var j_name_r = $("#jineng_name_show_r");
                    var j_img_r = $("#jineng_show_r");
                    j_name_r.text(cname);
                    j_img_r.prop("class", "jineng_ahz_r");
                    j_img_r.attr("src", "yx/jineng/anheizhui_r.gif");
                    j_name_r.prop("class", "jineng_ahz_name_r");
                    j_name_r.css("color", "#6193ff");
                } else {
                    var j_name_l = $("#jineng_name_show_l");
                    var j_img_l = $("#jineng_show_l");
                    j_name_l.text(cname);
                    j_img_l.prop("class", "jineng_ahz_l");
                    j_img_l.attr("src", "yx/jineng/anheizhui.gif");
                    j_name_l.prop("class", "jineng_ahz_name_l");
                    j_name_l.css("color", "#6193ff");
                }
                break;
        }
    }

    function run_jineng(l_or_r, atk, bdname, addhp,csc,num) {
        var fangxiang = "";
        var cw_int;
        var jineng_run;
        var jineng_name_run;
        if (num == undefined) {
            num = 0
        }

        if (l_or_r == "左") {
            cw_int = parseInt($("#right_cw").css("left"));
            fangxiang = "left";
            jineng_run = $("#jineng_show_l");
            jineng_run.show();
            jineng_name_run = $("#jineng_name_show_l");
            jineng_name_run.show();
            //console.log("左边动画")
        } else {
            // console.log("右边动画")
            cw_int = parseInt($("#left_cw").css("right"));
            fangxiang = "right";
            jineng_run = $("#jineng_show_r");
            jineng_run.show();
            jineng_name_run = $("#jineng_name_show_r");
            jineng_name_run.show();
        }
        var huifu = parseInt(jineng_run.css(fangxiang));//获取初始值后恢复
        var jn = window.setInterval(function () {
            var left = parseInt(jineng_run.css(fangxiang));

            if (left < cw_int - (100 + num)) {
                jineng_run.css(fangxiang, left + 40 + "px");
            } else {
                if (l_or_r == "左") {
                    if (bdname == "") {
                        $("#jineng_name_show_r").show();
                        $("#jineng_name_show_r").text((csc==2?"暴击:":"")+"-" + atk);
                        $("#jineng_name_show_r").fadeOut(1000)
                    } else {
                        $("#jineng_name_show_r").show();
                        $("#jineng_name_show_r").text(bdname + "{HP+:" + addhp + (csc==2?"暴击:":"")+"HP-" + atk + "}");
                        $("#jineng_name_show_r").fadeOut(1000)
                    }
                } else {
                    if (bdname == "") {
                        $("#jineng_name_show_l").show();
                        $("#jineng_name_show_l").text((csc==2?"暴击:":"")+"-" + atk);
                        $("#jineng_name_show_l").fadeOut(1000)
                    } else {
                        $("#jineng_name_show_l").show();
                        $("#jineng_name_show_l").text(bdname + "{HP+:" + addhp +(csc==2?"暴击:":"")+ "HP-" + atk + "}");
                        $("#jineng_name_show_l").fadeOut(1000)
                    }
                }
                jineng_run.fadeOut(1000, "linear", jineng_name_run.fadeOut(1000, function () {
                    jineng_run.css(fangxiang, huifu + "px");
                }));

                clearInterval(jn);
            }

        }, 30);
    }

    function putong_gongji(r_or_l, atk, bdname, addhp,csc) {
        var left_cw = $("#left_cw");
        var right_cw = $("#right_cw");
        var jineng_name_run;
        var beigj_name_run;
        var jn = window.setInterval(function () {
            if (r_or_l == "左") {
                jineng_name_run = $("#jineng_name_show_l");
                beigj_name_run = $("#jineng_name_show_r");
                var cw_int_l = parseInt(left_cw.css("left"));
                var cw_size_l = parseInt(right_cw.css("left"));
                if (cw_int_l < cw_size_l) {
                    left_cw.css("left", cw_int_l + 40 + "px");
                } else {
                    clearInterval(jn);
                    left_cw.css("left", "110px");
                    jineng_name_run.css("color", "white")
                    jineng_name_run.show();
                    jineng_name_run.text("普通攻击");
                    beigj_name_run.show();

                    if (bdname == "") {
                        beigj_name_run.text((csc==2?"暴击:":"")+"-" + atk);
                    } else {
                        beigj_name_run.text(bdname + "{HP+:" + addhp + (csc==2?"暴击:":"")+"HP-" + atk + "}");
                    }

                }
            } else {
                jineng_name_run = $("#jineng_name_show_r");
                beigj_name_run = $("#jineng_name_show_l");
                var cw_int_r = parseInt(right_cw.css("right"));
                var cw_size_r = parseInt(left_cw.css("right"));
                if (cw_int_r < cw_size_r) {
                    right_cw.css("right", cw_int_r + 40 + "px");
                } else {
                    clearInterval(jn);
                    right_cw.css("right", "120px");
                    jineng_name_run.css("color", "white")
                    jineng_name_run.show();
                    jineng_name_run.text("普通攻击");
                    beigj_name_run.show();
                    if (bdname == "") {
                        beigj_name_run.text("-" + atk);
                    } else {
                        beigj_name_run.text(bdname + "{HP+:" + addhp + "HP-" + atk + "}");
                    }

                }
            }
            jineng_name_run.fadeOut(1000, "linear", beigj_name_run.fadeOut(1000));

        }, 30);
    }
</script>
</html>
