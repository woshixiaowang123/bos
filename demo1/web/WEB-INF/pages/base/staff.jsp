<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/outOfBounds.js">

    </script>
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	


	//作废取派员的方法
	function doDelete(){
        var v=$('#grid').datagrid("getSelections");
        //获得的v是一个数组,数组里是选中的内容
        if(v.length==0){
            //说明没有选中,要给客户一个提示信息
            $.messager.alert("提示信息","请选中要作废的项!","warning");
        }else{
            //说明有选中的项,所以要把选中项的id传到后台,可以拼接一个字符串,把id拼接到URL后面
            var arr=new Array();
            for(var i=0;i<v.length;i++){
                arr.push(v[i].id);
            }
           var string = arr.join(",");
            window.location.href="${pageContext.request.contextPath}/staffAction_delete.action?ids="+string;
        }
	}


	//还原取派员的方法
	function doRestore(){
        var v=$('#grid').datagrid("getSelections");
        if(v.length==0){
            $.messager.alert("提示信息","请选中要还原的项!","warning");
        }else{
            //说明有选中的项,所以要把选中项的id传到后台,可以拼接一个字符串,把id拼接到URL后面
            var arr=new Array();
            for(var i=0;i<v.length;i++){
                if(v[i].deltag=="1"){
                    $.messager.alert("提示信息","此员工已在职!","warning");
                    return;
                }else{
                    arr.push(v[i].id);
                }

            }
            var string = arr.join(",");
            window.location.href="${pageContext.request.contextPath}/staffAction_restar.action?ids="+string;

        }
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "正常使用"
			}else{
				return "已离职";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){

	    //自定义的验证电话号码规
        $.extend($.fn.validatebox.defaults.rules, {
            phoneTex: {
                validator: function(value, param){
                    var phone= /^1[3|5|7|8][0-9]{9}$/;
                    return phone.test(value);
                },
                message: '手机号输入有误!'
            }
        });
        $.extend($.fn.validatebox.defaults.rules, {
            phoneNumber: {
                validator: function(value,param){
                    var phone = /^1[3|5|7|8][0-9]{9}$/;
                    return phone.test(value);
                },
                message: '手机号输入有误！'
            }
        });




        // 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [3,5,10],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staffAction_pageFind.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });

		//修改取派员的窗口
        $('#updateStaffWindow').window({
            title: '修改取派员',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false
        });

        //查看取派员信息的窗口
        $('#searchStaffWindow').window({
            title: '查看取派员信息',
            width: 400,
            modal: true,
            shadow: true,
            closed: true,
            height: 400,
            resizable:false
        });


	});
    function doView(){
        $('#searchStaffWindow').window("open");

    }


    //给"保存"按钮添加提交表单事件
	$(function () {
		$("#save").click(function () {
            var v= $("#newForm").form("validate");
            if(v){

                $("#addForm").submit();
			}

        })
    })
    
    //给修改信息的保存按钮添加时间
    $(function () {
        $("#update").click(function () {
            $("#updateForm").submit();
        })
    })

	function doDblClickRow(rowIndex, rowData){
       $('#updateStaffWindow').window("open");
       $("#updateForm").form("load",rowData);
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
    	<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="addForm" action="staffAction_add.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone" class="easyui-validatebox" required="true" validType="phoneNumber" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox" name="haspda" value="1"/>
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
							<input type="text" name="standard" class="easyui-validatebox" required="true"/>  
						</td>
					</tr>
					</table>
			</form>
		</div>

	</div>

<%--修改信息窗口--%>
    <div class="easyui-window" title="对收派员进行添加或者修改" id="updateStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
        <div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
            <div class="datagrid-toolbar">
                <a id="update" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
            </div>
        </div>

        <div region="center" style="overflow:auto;padding:5px;" border="false">
            <form id="updateForm" action="staffAction_update.action" method="post">
                <table class="table-edit" width="80%" align="center">
                    <tr class="title">
                        <td colspan="2">收派员信息</td>
                    </tr>
                    <!-- TODO 这里完善收派员添加 table -->
                    <tr>
                        <td>取派员编号</td>
                        <td><input type="text" name="id" class="easyui-validatebox" required="true" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                    </tr>
                    <tr>
                        <td>手机</td>
                        <td><input type="text" name="telephone" class="easyui-validatebox" required="true" validType="phoneNumber" /></td>
                    </tr>
                    <tr>
                        <td>单位</td>
                        <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="checkbox" name="haspda" value="1"/>
                            是否有PDA</td>
                    </tr>
                    <tr>
                        <td>取派标准</td>
                        <td>
                            <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>

    </div>


<%--查看用户的信息--%>
    <div class="easyui-window" title="对收派员进行添加或者修改" id="searchStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">

        <div region="center" style="overflow:auto;padding:5px;" border="false">

                <table class="table-edit" width="80%" align="center">
                    <tr class="title">
                        <td colspan="2">收派员信息</td>
                    </tr>
                    <!-- TODO 这里完善收派员添加 table -->
                    <tr>
                        <td>取派员编号</td>
                        <td><input type="text" name="id" class="easyui-validatebox" required="true" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" name="name" class="easyui-validatebox" required="true"/></td>
                    </tr>
                    <tr>
                        <td>手机</td>
                        <td><input type="text" name="telephone" class="easyui-validatebox" required="true" validType="phoneNumber" /></td>
                    </tr>
                    <tr>
                        <td>单位</td>
                        <td><input type="text" name="station" class="easyui-validatebox" required="true"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="checkbox" name="haspda" value="1"/>
                            是否有PDA</td>
                    </tr>
                    <tr>
                        <td>取派标准</td>
                        <td>
                            <input type="text" name="standard" class="easyui-validatebox" required="true"/>
                        </td>
                    </tr>
                </table>

        </div>

    </div>
</body>
</html>	