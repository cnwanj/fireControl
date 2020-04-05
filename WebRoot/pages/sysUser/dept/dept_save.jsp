<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加单位信息</title>
<link href="<%=path %>/css/select.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统管理员</a></li>
    <li><a href="#">单位信息管理</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span>单位基本信息</span></div>
    <form method="post" id="fm">
	    <ul class="forminfo">
		    <li>
		    	<label>编号：</label>
			    <input name="deptId" id="deptId" type="text" required class="dfinput"/>
			    <i><font color="#FF0000">*必填</font><font id="deptId1">2-16个字符组成</font></i>
		    </li>
		    <li>
		    	<label>单位名称：</label>
			    <input name="deptName" id="deptName" type="text" required class="dfinput"/>
			    <i><font color="#FF0000">*必填</font><font id="deptName1">不能为空</font></i>
		    </li>
		    <li>
		    	<label>单位地址：</label>
			    <input name="deptAddress" id="deptAddress" type="text" required class="dfinput" />
			    <i><font color="#FF0000">*必填</font><forn id="deptAddress1">不能为空</forn></i>
		    </li>
		    
		    <li><label>&nbsp;</label>
		    	<input id="btn" type="button" value="确认" class="btn">
		    	<input class="btn" type="button" value="返回" onclick="window.location.href='<%= basePath%>/sys/Dept_findByPage.action'">
		    </li>
	    </ul>
    </form>
    </div>
<script>
	//表单提交
	$("#btn").click(function(e){
		var deptId = $("#deptId").val(),
			deptName = $("#deptName").val(),
			deptAddress = $("#deptAddress").val();
		if(deptId.length<2 || deptId.length>16){
			$("#deptId1").css("color", "red").css("font-size", "15px");
			return false;
		}else{
			$("#deptId1").removeAttr("style");
		}
		if(deptName.length == 0){
			$("#deptName1").css("color", "red").css("font-size", "15px");
			return false;
		}else{
			$("#deptName1").removeAttr("style");
		}
		if(deptAddress.length == 0){
			$("#deptAddress1").css("color", "red").css("font-size", "15px");
			return false;
		}else{
			$("#deptAddress1").removeAttr("style");
		}
		var params = $("#fm").serialize();
		$.post("<%=basePath%>/sys/dept_save.action", params, function(data){
			if(data.targ == true){
				window.location.href = '<%=basePath%>/sys/Dept_findByPage.action?t=save';
			}else{
				$("#deptId1").css("color", "red").text("该单位信息已存在！").css("font-size", "15px");
			}
		});
	});
</script>
</body>
</html>
