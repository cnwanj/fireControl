<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑安装点</title>
	<link href="<%=path %>/css/select.css" rel="stylesheet" type="text/css" />
	<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
	 <script type="text/javascript" src="<%=path %>/js/common.js"></script> 
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">二级管辖员</a></li>
    <li><a href="#">安装点管理</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>用户基本信息</span></div>
    
    <form method="post" id="fm">
    
	    <ul class="forminfo">
		    <li>
		    	<label>编号：</label>
			    <input name="insId" id="insId" type="text" value="${installpoint.insId }" readonly required class="dfinput"/>
		    </li>
		    <li>
		    	<label>安装点编号：</label>
			    <input name="insCode" id="insCode" type="text" value="${installpoint.insCode }" readonly required class="dfinput"/>
		    </li>
		    <li>
		    	<label>安装点名称：</label>
			    <input name="insName" id="insName" type="text" value="${installpoint.insName }" required class="dfinput"/>
			    <i><font id="insName1">2-8个字符组成</font></i>
		    </li>
		    <li>
		    	<label>安装地址：</label>
			    <input name="insAddress" id="insAddress" type="text" value="${installpoint.insAddress }" required class="dfinput"/>
			    <i><font id="insAddress1">2-8个字符组成</font></i>
		    </li>
		    <li>
		    	<label>二级管辖域：</label>
		    	<select class="dfinput" name="as.asId">
	    	        <c:forEach items="${manageAreaSecond}" var="i">
	    	             <option value="${i.asId}">${i.asName}</option>
	    	        </c:forEach>
    	    	</select>
		    </li>
		    
		    <li><label>&nbsp;</label>
		    	<input id="btn" type="button" value="确认" class="btn">
		    	<input class="btn" type="button" value="返回" onclick="window.location.href='<%= basePath%>/sys/InstallPoint_findByPage.action'">
		    </li>
	    </ul>
    </form>
    </div>
    <script>
    $("#btn").click(function(e){
		var insName = $("#insName").val(),
		insAddress = $("#insAddress").val();
		if(insName.length<2 || insName.length>8){
			$("#insName1").css("color", "red");
			return false;
		}else{
			$("#insName1").removeAttr("style");
		}
		if(insAddress.length<2 || insAddress.length>8){
			$("#insAddress1").css("color", "red");
			return false;
		}else{
			$("#insAddress1").removeAttr("style");
		}
		var params = $("#fm").serialize();
		$.post("<%=basePath%>/sys/installPoint_update.action", params, function(data){
			if(data.targ == true){
				window.location.href = "<%=basePath%>/sys/InstallPoint_findByPage.action?targ='update'";
			}else{
				$("#insName1").css("color", "red").text("该名称已存在！");
			}
		});
	});
    </script>
</body>
</html>