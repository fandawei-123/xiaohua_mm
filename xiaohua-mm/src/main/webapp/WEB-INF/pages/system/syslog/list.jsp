<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if (id) {
            if (confirm("你确认要删除此条记录吗？")) {
                location.href = "${ctx}/system/sysLog?operation=delete&id=" + id;
            }
        } else {
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            平台系统管理
            <small>系统日志管理</small>
        </h1>
        <ol class="breadcrumb">
            <li>
                <a href="${ctx}/system/user?operation=home"><i class="fa fa-dashboard"></i> 首页
                </a>
            </li>
        </ol>
    </section>
    <section class="content">
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">日志列表</h3>
            </div>
            <div class="box-body">
                <div class="table-box">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <c:if test="${sessionScope.authorStr.contains('system/sysLog?operation=delete')}">
                                    <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i
                                            class="fa fa-trash-o"></i> 删除
                                    </button>
                                </c:if>
                                <button type="button" class="btn btn-default" title="刷新"
                                        onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <c:if test="${sessionScope.authorStr.contains('system/sysLog?operation=delete')}">
                                <th class="" style="padding-right:0px;"></th>
                            </c:if>
                            <th class="sorting">登陆人</th>
                            <th class="sorting">登陆IP</th>
                            <th class="sorting">登陆时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="item" varStatus="status">
                            <tr>
                                <c:if test="${sessionScope.authorStr.contains('system/sysLog?operation=delete')}">
                                <td><input name="ids" value="${item.id}" type="checkbox"></td>
                                </c:if>
                                <td>${item.user.userName}</td>
                                <td>${item.ip}</td>
                                <td><fmt:formatDate value="${item.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="${ctx}/system/sysLog?operation=list" name="pageUrl"/>
                </jsp:include>
            </div>
        </div>

    </section>
</div>
</body>

</html>