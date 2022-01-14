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
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            题目管理
            <small>题目审核日志</small>
        </h1>
        <ol class="breadcrumb">
            <li>
                <a href="${ctx}/system/user?operation=home"><i class="fa fa-dashboard"></i> 首页
                </a>
            </li>
            <li><a href="${ctx}/store/examineLog?operation=list">日志列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">日志信息</div>
            <form id="editForm" method="post">
                <div class="row data-type" style="margin: 0px">
                    <input type="hidden" name="id" value="${examineLog.id}">

                    <div class="col-md-2 title">题目</div>
                    <div class="col-md-4 data">
                        ${examineLog.question.subject}
                    </div>

                    <div class="col-md-2 title">创建部门</div>
                    <div class="col-md-4 data">
                        ${examineLog.question.createUser.dept.deptName}
                    </div>

                    <div class="col-md-2 title">创建日期</div>
                    <div class="col-md-4 data">
                        <fmt:formatDate value="${examineLog.question.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>

                    <div class="col-md-2 title">创建人</div>
                    <div class="col-md-4 data">
                        ${examineLog.question.createUser.userName}
                    </div>


                    <div class="col-md-2 title">评论</div>
                    <div class="col-md-4 data">
                        ${examineLog.comments}
                    </div>

                    <div class="col-md-2 title">审核结果</div>
                    <div class="col-md-4 data">
                        <c:choose>
                            <c:when test="${examineLog.status eq '1'}"><span style="color: green; ">已审核</span></c:when>
                            <c:when test="${examineLog.status eq '-1'}"><span style="color: red; ">已拒绝</span></c:when>
                        </c:choose>
                    </div>

                    <div class="col-md-2 title">审核人</div>
                    <div class="col-md-4 data">
                        ${examineLog.userId}
                    </div>

                    <div class="col-md-2 title">审核时间</div>
                    <div class="col-md-4 data">

                        <fmt:formatDate value="${examineLog.reviewTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </div>


                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" class="btn btn-info" onclick='location.href="${ctx}/store/examineLog?operation=list"'>返回日志列表</button>
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