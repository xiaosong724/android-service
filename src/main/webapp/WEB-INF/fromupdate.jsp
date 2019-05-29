<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/8
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--发表日志--%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form  method="post" enctype="multipart/form-data" onsubmit="return examinetab()">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">New LoveLog</h4>

                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="recipient_title" class="control-label">标题:</label>
                        <input type="text" name="title" class="form-control" id="recipient_title">
                    </div>
                    <div class="form-group">
                        <label for="recipient_name" class="control-label">作者:</label>
                        <input type="text" name="username" class="form-control" id="recipient_name">
                    </div>
                    <div class="form-group">
                        <label for="recipient_logtype" class="control-label">搜索关键词:</label>
                        <input type="text" name="logtype" class="form-control" id="recipient_logtype">
                    </div>
                    <div class="form-group">
                        <label for="message_text" class="control-label">日志:</label>
                        <textarea class="form-control" name="message" id="message_text"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="newlogfile" class="control-label"><h2>新的照片上传:</h2></label>
                        <input type="file" name="logtype" multiple id="newlogfile">
                        <div id="image-holder2" > </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="himt_value" style="color: red"></span>
                    <button type="reset" class="btn btn-default" data-dismiss="modal">返回</button>
                    <button type="button" id="one_click" onclick="upload_log_text()" class="btn btn-primary">发表</button>
                </div>

            </form>
        </div>
    </div>
</div>


<%--修改日志--%>
<div class="modal fade" id="exampleModal_up" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form  method="post" enctype="multipart/form-data" onsubmit="return examinetab()">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel_up">Update LoveLog</h4>

                </div>
                <div class="modal-body">
                    <div class="form-group">

                        <div class="pull-right">
                            <button class="btn btn-info" onclick="ajax_query_log()">搜索ID</button>
                        </div>
                        <div class="pull-right">
                            <button class="btn btn-default">查看目录</button>
                        </div>
                        <label for="recipient_id_up" class="control-label">修改日志的专属ID:</label>
                        <span id="id_standard" style="color:red;"></span>
                        <input type="text" name="title" class="form-control" id="recipient_id_up">
                        <input type="hidden" name="hiddenid"  id="recipient_hidden_id_up">

                    </div>
                    <div class="form-group">
                        <label for="recipient_title_up" class="control-label">标题:</label>
                        <input type="text" name="title" class="form-control" id="recipient_title_up">
                    </div>
                    <div class="form-group">
                        <label for="recipient_name_up" class="control-label">作者:</label>
                        <input type="text" name="username" class="form-control" id="recipient_name_up">
                    </div>
                    <div class="form-group">
                        <label for="recipient_logtype_up" class="control-label">搜索关键词:</label>
                        <input type="text" name="logtype" class="form-control" id="recipient_logtype_up">
                    </div>
                    <div class="form-group">
                        <label for="message_text_up" class="control-label">日志:</label>
                        <textarea class="form-control" name="message" id="message_text_up"></textarea>
                    </div>


                    <div class="form-group">
                        <label for="img_val" class="control-img">日志原图:</label>
                        <div class="songimgsize" id="img_val">

                        </div>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label for="scscsc" class="control-label"><h2>新的照片上传:</h2></label>
                        <input type="file" name="logtype" multiple id="scscsc">
                        <div id="image-holder" > </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="himt_value_up" style="color: red"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                    <button type="button" id="one_click2" onclick="update_log_text()" class="btn btn-primary">修改</button>
                </div>

            </form>
        </div>
    </div>
</div>

<%--修改欢迎背景--%>
<div class="modal fade" id="exampleModal_back" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form  method="post" enctype="multipart/form-data" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel_back">New Bcakground</h4>

                </div>
                <div class="modal-body">


                    <div class="form-group">
                        <label class="btn btn-primary active">
                            <input type="radio" name="background_img"  autocomplete="off" value="show" checked> 设为背景
                        </label>
                        <label class="btn btn-primary active">
                            <input type="radio" name="background_img"  autocomplete="off" value="hide"> 只是上传
                        </label>

                        <label class="btn btn-success active">
                            <input type="checkbox" name="options_hight" id="ckbox_hight"  autocomplete="off"> 背景拉伸
                        </label>
                    </div>

                    <div class="form-group">
                        <input type="file" name="file" class="form-control" accept="image/*" id="recipient_backpath_back">
                        <div id="image-holder3"> </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <span id="himt_value_back" style="color: red"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                    <button type="button" onclick="uploadback()" id="onlyone_click" class="btn btn-primary">上传</button>
                </div>

            </form>
        </div>
    </div>
</div>


<%--登录框--%>
<div class="modal fade" id="exampleModal_login" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form  method="post" enctype="multipart/form-data" >
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="exampleModalLabel_login">输入后就创建,下次失效后再输入一样的不会再创建而是登录!</h3>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                            <label for="recipient_login" class="control-label">用户名:</label>
                            <input type="text" name="logtype" class="form-control" id="recipient_login">
                    </div>
                    <div class="form-group">
                        <label for="recipient_login_password" class="control-label">登录码:</label>
                        <input type="text" name="logtype" class="form-control" id="recipient_login_password">
                    </div>
                    <span id="himt_value_login" style='color:red'></span>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                    <button type="button" onclick="login_log()" id="onlyone_click_login" class="btn btn-primary">登录</button>
                </div>

            </form>
        </div>
    </div>
</div>

<%--图片显示框--%>
<div class="modal fade" id="exampleModal_imgshow" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
<img id="pathimg_click" onclick="pathimg_click()" style="width: 100%" src="">
</div>
<div role="zeromodal-loading" zero-unique-loading="3fef3e4b04d04ce182e581cee7d6aac5" class="pacman" style="z-index:9999999;left:46%;top:203px;display: none">  <div></div>  <div></div>  <div></div>  <div></div>  <div></div>  </div>
<div zero-unique-overlay="3fef3e4b04d04ce182e581cee7d6aac5" class="zeromodal-overlay" style="opacity:0.2;z-index:999999999;display: none">
</div>
