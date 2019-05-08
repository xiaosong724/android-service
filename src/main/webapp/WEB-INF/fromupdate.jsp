<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/8
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发表或修改日志</title>
</head>
<body>
<%--发表日志--%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/lovelogup" method="post" enctype="multipart/form-data" onsubmit="return examinetab()">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel">New LoveLog</h4>

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
                    <span id="himt_value"style="color: red"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                    <button type="submit" class="btn btn-primary">发表</button>
                </div>
                <input type="hidden" name="deleteindex" class="form-control" value="" id="delete_file_index">

            </form>
        </div>
    </div>
</div>



<%--修改日志--%>
<div class="modal fade" id="exampleModal_up" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="/lovelogup" method="post" enctype="multipart/form-data" onsubmit="return examinetab()">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="exampleModalLabel_up">Update LoveLog</h4>


                </div>
                <div class="modal-body">
                    <div class="form-group">

                        <div class="pull-right"><button class="button" onclick="ajax_query_log()">搜索ID</button></div>
                        <div class="pull-right"><button class="button">查看目录</button></div>
                        <label for="recipient_id_up" class="control-label">修改日志的专属ID:</label>
                        <span id="id_standard" style="color:red;"></span>
                        <input type="text" name="title" class="form-control" id="recipient_id_up">

                    </div>
                    <div class="form-group">
                        <label for="recipient-title" class="control-label">标题:</label>
                        <input type="text" name="title" class="form-control" id="recipient_title_up">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="control-label">作者:</label>
                        <input type="text" name="username" class="form-control" id="recipient_name_up">
                    </div>
                    <div class="form-group">
                        <label for="recipient-logtype" class="control-label">搜索关键词:</label>
                        <input type="text" name="logtype" class="form-control" id="recipient_logtype_up">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">日志:</label>
                        <textarea class="form-control"  name="message" id="message_text_up"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-img">日志原图:</label>
                        <div class="songimgsize" id="img_val">

                        </div>
                    </div>
                    <div class="form-group">
                        <section role="main" class="l-main">

                            <div class="uploader__box js-uploader__box l-center-box">
                                <div class="uploader__contents">
                                    <label class="button button--secondary" for="fileinput">添加新的图片</label>
                                    <input id="fileinput_up" class="uploader__file-input" type="file" multiple accept="image/*"
                                           value="Select Files">
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="himt_value_up"style="color: red"></span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
                    <button type="submit" class="btn btn-primary">修改</button>
                </div>
                <input type="hidden" name="deleteindex" class="form-control" value="" id="delete_file_index_up">
                <input type="hidden" name="deleteimg" class="form-control" value="" id="delete_file_img_up">

            </form>
        </div>
    </div>
</div>

<%--删除框--%>
<div class="modal bs-example-modal-sm" tabindex="-1" id="showdetele">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="pull-left">
                <button class="button"del="" id="del_msg" onclick="delete_img()">删除</button>
            </div>
            <div class="pull-right">
                <button class="button" onclick="delete_box()">放弃</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
