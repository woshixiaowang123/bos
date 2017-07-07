<%--
  Created by IntelliJ IDEA.
  User: 隔壁老王
  Date: 2017/6/23
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>layout测试页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>


</head>
<body class="easyui-layout">

    <div data-options="region:'north'" style="height: 100px">上方</div>
    <div data-options="region:'west'" style="width: 50px">左方</div>
    <div data-options="region:'center'">中间</div>
    <div data-options="region:'east'" style="width: 50px">右方</div>
    <div data-options="region:'south'" style="height: 50px">下方</div>


</body>
</html>
