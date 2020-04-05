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
    <li><a href="#">设备管理</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>用户基本信息</span></div>
    
    <form method="post" id="fm">
    
	    <ul class="forminfo">
		    <li>
		    	<label>设备编号：</label>
			    <input name="facId" id="facId" type="text" value="${facility.facId }" readonly required class="dfinput"/>
		    </li>
		    <li>
		    	<label>设备名称：</label>
			    <input name="facName" id="facName" type="text" value="${facility.facName }" required class="dfinput"/>
			    <i><font id="facName1">2-8个字符组成</font></i>
		    </li>
		    <!--<li>
		    	<label>安装时间：</label>
			    <input name="facTime" id="facTime" type="text" value="${facility.facTime }" required class="dfinput"/>
			    <i><font id="facTime1">2-8个字符组成</font></i>
		    </li>  -->		    
		    <li>
		    	<label>安装地址：</label>
			    <input name="facAddress" id="facAddress" type="text" value="${facility.facAddress }" required class="dfinput"/>
			    <i><font id="facAddress1">2-8个字符组成</font></i>
		    </li>
		    <li>
		    	<label>设备状态：</label>
			    <select class="dfinput" name="facStatus">
	    	        <option value ="1">正常</option>	    	          	    	       
	    	        <option value ="0">报警</option>	    	          	    	       
    	    	</select>
		    </li>
		    <li>
		    	<label>绑定状态：</label>
			    <select class="dfinput" name="facBind">
	    	        <option value ="1">已绑定</option>	    	          	    	       
	    	        <option value ="0">已解绑</option>	    	          	    	       
    	    	</select>
		    </li>
		    <li>
		    	<label>安装人员：</label>
		    	<select class="dfinput" name="sysUsers.userId">
	    	        <c:forEach items="${sysUsers}" var="i">
	    	             <option value="${i.userId}">${i.userName}</option>
	    	        </c:forEach>
    	    	</select>
		    </li>
		    <li>
		    	<label>报警联系人：</label>
		    	<select class="dfinput" name="warnUser.userId">
	    	        <c:forEach items="${warnUser}" var="i">
	    	             <option value="${i.userId}">${i.userName}</option>
	    	        </c:forEach>
    	    	</select>
		    </li>
		    <li>
		    	<label>安装点：</label>
		    	<select class="dfinput" name="point.insId">
	    	        <c:forEach items="${installPoint}" var="i">
	    	             <option value="${i.insId}">${i.insName}</option>
	    	        </c:forEach>
    	    	</select>
		    </li>
		    <li><label>&nbsp;</label>
		    	<input id="btn" type="button" value="确认" class="btn">
		    	<input class="btn" type="button" value="返回" onclick="window.location.href='<%= basePath%>/sys/Facility_findByPage.action'">
		    </li>
	    </ul>
    </form>
    </div>
    <script>
    $("#btn").click(function(e){
		var facName = $("#facName").val(),
			facAddress = $("#facAddress").val();
		if(facName.length<2 || facName.length>8){
			$("#facName1").css("color", "red");
			return false;
		}else{
			$("#facName1").removeAttr("style");
		}
		if(facAddress.length<2 || facAddress.length>8){
			$("#facAddress1").css("color", "red");
			return false;
		}else{
			$("#facAddress1").removeAttr("style");
		}
		var params = $("#fm").serialize();
		$.post("<%=basePath%>/sys/facility_update.action", params, function(data){
			if(data.targ == true){
				window.location.href = "<%=basePath%>/sys/Facility_findByPage.action?targ='update'";
			}else{
				$("#facName1").css("color", "red").text("该名称已存在！");
			}
		});
	});
    </script>
</body>
</html>