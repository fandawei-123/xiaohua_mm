<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>小花面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<script>
    function getRadio() {
        var radio = "${member.state == 0?'启用':'停用'}";
        var memberId = "${member.id}";
        if (radio == "停用") {
            if (confirm("你确认要停用此用户吗？")) {
                var s = "0";

                location.href = "${ctx}/front/member?operation=updateState&state=" + s + "&id=" + memberId;
            }
        } else {
            if (confirm("你确认要启用此用户吗？")) {
                var s = "1";
                location.href = "${ctx}/front/member?operation=updateState&state=" + s + "&id=" + memberId;
            }
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            会员管理
            <small>会员账户管理</small>
        </h1>
        <ol class="breadcrumb">
            <li>
                <a href="${ctx}/system/user?operation=home"><i class="fa fa-dashboard"></i> 首页
                </a>
            </li>
            <li><a href="${ctx}/front/member?operation=list">会员列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">会员信息</div>
            <form id="editForm" method="post">
                <div class="row data-type" style="margin: 0px">
                    <input type="hidden" name="id" value="${member.id}">

                    <div class="col-md-2 title">用户名</div>
                    <div class="col-md-4 data">
                        ${member.nickName}
                    </div>

                    <div class="col-md-2 title">邮箱</div>
                    <div class="col-md-4 data">
                        ${member.email}
                    </div>

                    <div class="col-md-2 title">性别</div>
                    <div class="col-md-4 data">
                        ${member.gender}
                    </div>

                    <div class="col-md-2 title">状态</div>
                    <div class="col-md-4 data">
                        <div class="col-md-4 data" style="color: red">
                            当前状态:${member.state ==0?'停用':'启用'}
                        </div>
                        <div class="form-group form-inline">
<%--                            <div class="radio"><label><input type="radio" ${member.state==0?'checked':''} name="state"--%>
<%--                                                             value="0" onclick="getRadio(this)">停用</label></div>--%>
<%--                            <div class="radio"><label><input type="radio" ${member.state==1?'checked':''} name="state"--%>
<%--                                                             value="1" onclick="getRadio(this)">启用</label></div>--%>
                            <button type="button"  class="${member.state == 0?'btn btn-success':'btn btn-danger'}" onclick='getRadio()'>${member.state == 0?'启用':'停用'}</button>
                        </div>
                    </div>

                    <div class="col-md-2 title">生日</div>
                    <div class="col-md-4 data">
                        <fmt:formatDate value="${member.birthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>

                    <div class="col-md-2 title">电话</div>
                    <div class="col-md-4 data">
                        ${member.telephone}
                    </div>


                    <div class="col-md-2 title">地址</div>
                    <div class="col-md-4 data">
                        ${member.address}
                    </div>

                    <div class="col-md-2 title">注册时间</div>
                    <div class="col-md-4 data">
                        <fmt:formatDate value="${member.registerDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>


                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" class="btn btn-info" onclick='location.href="${ctx}/front/member?operation=list"'>返回会员列表</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${ctx}/css/style.css">
</html>