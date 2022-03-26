<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>小花面面管理系统</title>
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            题库管理
            <small>题目管理</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/system/user?operation=home"><i class="fa fa-dashboard"></i> 首页
            </a></li>
        </ol>
    </section>
    <section class="content">
        <div class="box-body">
            <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab-form" data-toggle="tab">审核题目</a>
                    </li>
                </ul>
                <div class="tab-content">

                    <input type="hidden" name="id" value="${question.id}">

                    <div class="tab-pane active" id="tab-form">
                        <div class="row data-type">

                            <div class="col-md-2 title">所属企业</div>
                            <div class="col-md-4 data">
                                ${question.company.name}
                            </div>

                            <div class="col-md-2 title">所属类别</div>
                            <div class="col-md-4 data">
                                ${question.catalog.name}
                            </div>

                            <div class="col-md-2 title rowHeight2x">题目简介</div>
                            <div class="col-md-10 data rowHeight2x">
                                ${question.remark}
                            </div>

                            <div class="col-md-2 title rowHeight2x">题干</div>
                            <div class="col-md-10 data rowHeight2x">
                                ${question.subject}
                            </div>

                            <c:if test="${question.picture.length() > 0}">
                                <div class="col-md-2 title">题干图片</div>
                                <div class="col-md-10 data " style="block-size: auto">
                                    <img src="${ctx}/upload/${question.picture}"/>
                                </div>
                            </c:if>

                            <div class="col-md-2 title rowHeight2x">题目分析</div>
                            <div class="col-md-10 data rowHeight2x">
                                ${question.analysis}
                            </div>

                            <div class="col-md-2 title">题目类型</div>
                            <div class="col-md-4 data">
                                <c:choose>
                                    <c:when test="${question.type eq '1'}">单选</c:when>
                                    <c:when test="${question.type eq '2'}">多选</c:when>
                                    <c:when test="${question.type eq '3'}">简答</c:when>
                                </c:choose>
                            </div>

                            <div class="col-md-2 title">难易程度</div>
                            <div class="col-md-4 data">
                                <c:forEach begin="1" end="${question.difficulty}">
                                    ★
                                </c:forEach>
                            </div>

                            <div class="col-md-2 title">是否经典</div>
                            <div class="col-md-4 data">
                                ${question.isClassic eq "1" ? "经典题":"普通题"}
                            </div>

                            <div class="col-md-2 title">审核状态</div>

                            <div class="col-md-4 data" style="color: ${question.state eq '0'?'red':'green'}">
                                <c:choose>
                                    <c:when test="${question.reviewStatus eq '1'}"><span style="color: green; ">审核通过</span></c:when>
                                    <c:when test="${question.reviewStatus eq '0'}">审核中</c:when>
                                    <c:when test="${question.reviewStatus eq '-1'}"><span style="color: red; ">审核不通过</span></c:when>
                                </c:choose>
                            </div>
                        </div>
                        <!--工具栏-->
                    </div>

                </div>
                <div class="tab-content">
                <form id="editForm" action="${ctx}/store/question?operation=examine" method="post"
                      enctype="multipart/form-data">
                    <div class="col-md-2 title rowHeight2x">评论</div>
                    <div class="col-md-10 data rowHeight2x">
                        <textarea id="text" class="form-control" rows="3" name="subject" placeholder="请在此输入您对本题的评论"></textarea>
                    </div>
                </form>
                <br>
                <div class="box-tools text-center">
                    <c:if test="${question.reviewStatus eq '-1' || question.reviewStatus eq '0'}">
                    <button type="button" class="btn btn-success" value="1" onclick='review(this)'>通过</button>
                    </c:if>
                    <c:if test="${question.reviewStatus eq '1' || question.reviewStatus eq '0'}">
                    <button type="button" class="btn btn-danger" value="-1" onclick='review(this)'>不通过</button>
                    </c:if>
                    <button type="button" class="btn bg-default"
                            onclick='location.href="${ctx}/store/question?operation=list"'>返回
                    </button>
                </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
<script>
    function review(obj) {
        var comments = document.getElementById("text").value;
        var questionId = "${question.id}";
        if (obj.value == "-1") {
            if (confirm("你确认要修改该题审核状态吗？")) {
                var s = "-1";
                location.href = "${ctx}/store/question?operation=examine&status=" + s + "&id=" + questionId + "&comments=" + comments;
            }
        } else {
            if (confirm("你确认要修改该题审核状态吗？")) {
                var s = "1";
                location.href = "${ctx}/store/question?operation=examine&status=" + s + "&id=" + questionId + "&comments=" + comments;

            }
        }
    }
</script>

</html>