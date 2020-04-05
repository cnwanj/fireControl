<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑单位管理员信息</title>
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
    <li><a href="#">单位管理员信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>单位基本信息</span></div>
    
    <form method="post" id="fm">
    
	    <ul class="forminfo">
		    <li>
		    	<label>编号：</label>
			    <input name="userId" id="userId" type="text" value="${deptUser.userId }" readonly required class="dfinput"/>
		    </li>
		    <li>
		    	<label>姓名：</label>
			    <input name="userName" id="userName" value="${deptUser.userName }" type="text" required class="dfinput"/>
			    <i><font color="#FF0000">*必填</font><font id="userName1">2-5个字符组成</font></i>
		    </li>
		    <li>
		    	<label>用户名：</label>
			    <input name="loginName" id="loginName" value="${deptUser.loginName }" type="text" required class="dfinput"/>
			    <i><font color="#FF0000">*必填</font><font id="loginName1">4-16位字母、数字或下滑线组成</font></i>
		    </li>
		    <li>
		    	<label>密码：</label>
			    <input name="loginPwd" id="loginPwd" value="${deptUser.loginPwd }" type="text" required class="dfinput"/>
			    <i><font color="#FF0000">*必填</font><font id="loginPwd1">4-16位字母、数字或下滑线组成</font></i>
		    </li>
		    <li>
		    	<label>电话：</label>
			    <input name="userPhone" id="userPhone" value="${deptUser.userPhone }" type="text" required class="dfinput" />
			    <i><font color="#FF0000">*必填</font><forn id="userPhone1">11位数字组成</forn></i>
		    </li>
		    <li>
		    	<label>所属单位：</label>
		    	<select name="dept.deptId" id="deptId" class="dfinput" required>
	    			<option value="${deptUser.dept.deptId }">${deptUser.dept.deptName }</option>
		    		<s:iterator value="list">
		    			<option value="${deptId }">${deptName}</option>
		    		</s:iterator>
		    	</select>
			    <!-- <input name="dept.deptId" id="deptId" type="text" required class="dfinput" /> -->
			    <i><font color="#FF0000"></font><forn id="deptId1"></forn></i>
		    </li>
		    
		    <li><label>&nbsp;</label>
		    	<input id="btn" type="button" value="确认" class="btn">
		    	<input class="btn" type="button" value="返回" onclick="window.location.href='<%= basePath%>/sys/DeptUser_findByPage.action'">
		    </li>
	    </ul>
    </form>
    </div>
    <script>
    	$("#btn").click(function(){
    		var userName = $("#userName").val(),
				loginName = $("#loginName").val(),
				loginPwd = $("#loginPwd").val(),
				userPhone = $("#userPhone").val(),
				deptId = $("#deptId").val();
			if(userName.length<2 || userName.length>5){
				$("#userName1").css("color", "red").css("font-size", "15px");
				return false;
			}else{
				$("#userName1").removeAttr("style");
			}
			if(loginName.length<4 || loginName.length>16){
				$("#loginName1").css("color", "red").css("font-size", "15px");
				return false;
			}else{
				$("#loginName1").removeAttr("style");
			}
			if(loginPwd.length<4 || loginPwd.length>16){
				$("#loginPwd1").css("color", "red").css("font-size", "15px");
				return false;
			}else{
				$("#loginPwd1").removeAttr("style");
			}
			if(isNaN(Number(userPhone)) || userPhone.length != 11){	//如果不为数字
				$("#userPhone1").css("color", "red").css("font-size", "15px");
				return false;
			}else{
				$("#userPhone1").removeAttr("style");
			}
			if(deptId == "暂无" || deptId == ""){	
				$("#deptId1").css("color", "red").css("font-size", "15px").text("无单位信息可选");
				return false;
			}else{
				$("#deptId1").removeAttr("style");
			}
			$.post('<%=basePath%>/sys/deptUser_update.action', $('#fm').serialize(), function(data){
    			if(data.targ == true){
    				window.location.href = "<%=basePath%>/sys/DeptUser_findByPage.action?t=update";
    			}else{
    				$("#loginName1").css("color", "red").css("font-size", "16px").text("该用户信息不存在！");
    			}
    		});
    	});
    </script>
</body>
</html>