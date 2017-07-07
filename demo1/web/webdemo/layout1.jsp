<%--
  Created by IntelliJ IDEA.
  User: 隔壁老王
  Date: 2017/6/23
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>layout页面2</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height: 100px" title="xx管理系统">北部区域</div>
<div data-options="region:'west'" style="width: 100px" title="系统菜单">
    <div id="aa" class="easyui-accordion" style="height:100%;" >
        <div title="基本功能" data-options="iconCls:'icon-mini-add'" style="overflow:auto;padding:10px;">
            <a id="btn" href="#" class="easyui-linkbutton" data-options="width:60">度娘</a>
        </div>
        <script>
            function addTab() {
                
            }
        </script>
        <div title="系统管理" data-options="iconCls:'icon-mini-add'" style="padding:10px;">

        </div>
        <div title="其他选项" data-options="iconCls:'icon-mini-add',selected:true" style="padding:10px;">

        </div>
    </div>

</div>
<div data-options="region:'center'">
    <%--中间区域选项卡--%>
    <div id="tt" class="easyui-tabs" style="width:100%;height:100%;">
        <div title="选项卡1" data-options="iconCls:'icon-mini-add',closable:true"  style="padding:20px;display:none;">
            tab1
        </div>
        <div title="选项卡2" data-options="iconCls:'icon-mini-add',closable:true"  style="display:none;">
            tab2
        </div>
        <div title="选项卡3" data-options="iconCls:'icon-mini-add',closable:true" style="padding:20px;display:none;">
            tab3
        </div>
    </div>

</div>
<div data-options="region:'east'" style="width: 100px">东部区域</div>
<div data-options="region:'south'" style="height: 100px">南部区域</div>

</body>
</html>
