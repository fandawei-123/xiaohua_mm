<%--
  Created by IntelliJ IDEA.
  User: Huahua
  Date: 2021/12/17
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="pull-left">
    <div class="form-group form-inline">
        总共${page.pages} 页,共${page.total} 条数据。
    </div>
</div>
<div class="box-tools pull-right">
    <ul class="pagination" style="margin: 0px;">
        <li>
            <a href="javascript:goPage(1)" aria-label="Previous">首页</a>
        </li>
        <li><a href="javascript:goPage(${page.prePage})">上一页</a></li>
        <c:forEach begin="${page.navigateFirstPage}" end="${page.navigateLastPage}" var="i">
            <li class="paginate_button ${page.pageNum==i ? 'active':''}"><a href="javascript:goPage(${i})">${i}</a></li>
        </c:forEach>
        <li><a href="javascript:goPage(${page.nextPage})">下一页</a></li>
        <li>
            <a href="javascript:goPage(${page.pages})" aria-label="Next">尾页</a>
        </li>
    </ul>
</div>
<form id="pageForm" action="${param.pageUrl}" method="post">
    <input type="hidden" name="page" id="pageNum">
</form>
<script>
    function goPage(page){
        document.getElementById("pageNum").value = page
        document.getElementById("pageForm").submit()
    }
</script>
</body>
</html>
