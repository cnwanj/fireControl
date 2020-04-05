<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑单位信息</title>
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
			    <input name="deptId" id="deptId" type="text" value="${dept.deptId }" readonly required class="dfinput"/>
		    </li>
		    <li>
		    	<label>单位名称：</label>
			    <input name="deptName" id="deptName" type="text" value="${dept.deptName }" required class="dfinput"/>
			    <i><font id="deptName1">*必填项不能为空</font></i>
		    </li>
		    <li>
		    	<label>单位地址：</label>
			    <input name="deptAddress" id="deptAddress" type="text" value="${dept.deptAddress }" required class="dfinput"/>
			    <i><font id="deptAddress1">*必填项不能为空</font></i>
		    </li>
		    
		    <li><label>&nbsp;</label>
		    	<input id="btn" type="button" value="确认" class="btn">
		    	<input class="btn" type="button" value="返回" onclick="window.location.href='<%= basePath%>/sys/Dept_findByPage.action'">
		    </li>
	    </ul>
    </form>
    </div>
    <script>
    	$("#btn").click(function(){
    		var deptName = $("#deptName").val(),
				deptAddress = $("#deptAddress").val();
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
			$.post('<%=basePath%>/sys/dept_update.action', $('#fm').serialize(), function(data){
    			if(data.targ == true){
    				window.location.href = "<%=basePath%>/sys/Dept_findByPage.action?t=update";
    			}else{
    				$("#loginName1").css("color", "red").text("该单位信息不存在！");
    			}
    		});
    	});
    </script>
</body>
</html>