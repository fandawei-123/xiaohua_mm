<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <base href="${ctx}/">
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>小花面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
    <link rel="stylesheet" href="${ctx}/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>

    <SCRIPT type="text/javascript">
        // 定义页面对应的树形组件
        var zTreeObj;
        var setting = {check: {enable: true},data: {simpleData: {enable: true}}};

        var zNodes =${roleModuleJson}

        $(document).ready(function(){
            /*
            $.get("${ctx}/system/role?operation=getModuleByRoleId&id=${role.id}",function(data) {
                var json = eval('(' + data + ')');
                initZtree(json);
            });
            */
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes)
            var zTree = $.fn.zTree.getZTreeObj("treeDemo")
            zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" }
            zTreeObj.expandAll(true);//true：展开所有
        });

        //实现权限分配
        function submitCheckedNodes() {
            //1.获取所有的勾选权限节点
            var nodes = zTreeObj.getCheckedNodes(true);//true:被勾选，false：未被勾选
            //2.循环nodes，获取每个节点的id， 拼接模块字符串（以，分隔）
            var moduleIds = "";
            for(var i=0;i<nodes.length;i++) {
                moduleIds += nodes[i].id +",";
            }
            if(moduleIds.length>0) {
                //substr : 字符串剪切    0 ：起始位置， 第二个参数：最大长度
                //123456789,      0   9
                moduleIds = moduleIds.substr(0,moduleIds.length-1);
            }
            $("#moduleIds").val(moduleIds);
            $("#icform").submit();
        }

</SCRIPT>


</head>

<body style="overflow: visible;">
<div id="frameContent" class="content-wrapper" style="margin-left:0px;height: 1200px" >
    <section class="content-header">
        <h1>
            菜单管理
            <small>菜单列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="${ctx}/system/user?operation=home"><i class="fa fa-dashboard"></i> 首页
            </a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">角色 [${role.name}] 权限列表</h3>
            </div>
            <div class="box-body">
                <!-- 数据表格 -->
                <div class="table-box">
                    <!-- 树菜单 /-->
                    <form id="icform" method="post" action="${ctx}/system/role?operation=updateRoleModule">
                        <input type="hidden" name="roleId" value="${role.id}"/>
                        <input type="hidden" id="moduleIds" name="moduleIds" value=""/>
                        <ul id="treeDemo" class="ztree"></ul>
                    </form>
                    <!--工具栏-->
                    <div class="box-tools text-left">
                    <button type="button" class="btn bg-maroon" onclick="submitCheckedNodes();">保存</button>
                    <button type="button" class="btn bg-default" onclick='location.href="${ctx}/system/role?operation=list"'>返回</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>