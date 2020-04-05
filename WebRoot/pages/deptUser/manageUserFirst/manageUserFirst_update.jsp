<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑一级管辖员信息</title>
<link href="<%=path %>/css/select.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">单位管理员</a></li>
    <li><a href="#">一级管辖员信息</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span>用户基本信息</span></div>
    <form method="post" id="fm">
	    <ul class="forminfo">
	        <li>
		    	<label>工号：</label>
			    <input name="ufId" id="ufId" value="${userFirst.ufId}" type="text" required readonly class="dfinput"/>
			    <i><font color="#FF0000">*必填</font>
		    </li>
		    <li>
		    	<label>姓名：</label>
			    <input name="ufName" id="ufName" value="${userFirst.ufName}" type="text" required class="dfinput"/>
			    <i><font color="#FF0000">*必填</font><font id="ufName1">2-5个字符组成</font></i>
		    </li>
		    <li>
		    	<label>用户名：</label>
			    <input name="loginName" id="loginName" value="${userFirst.loginName}" type="text" required class="dfinput"/>
			    <i><font color="#FF0000">*必填</font><font id="loginName1">4-16位字母、数字或下滑线组成</font></i>
		    </li>
		    <li>
		    	<label>密码：</label>
			    <input name="loginPwd" id="loginPwd" value="${userFirst.loginPwd}" type="text" required class="dfinput" />
			    <i><font color="#FF0000">*必填</font><forn id="loginPwd1">4-16位字母、数字或下滑线组成</forn></i>
		    </li>
		    <li>
		    	<label>电话：</label>
			    <input name="ufPhone" id="ufPhone" value="${userFirst.ufPhone}" type="text" required class="dfinput" />
			    <i><font color="#FF0000">*必填</font><forn id="usPhone1">11位数字组成</forn></i>
		    </li>
		    <li>
		    	<label>用户类型：</label>
		    	<c:if test="${userSecond.ufType == 0}">
		    	<input type="radio" value="0" name="ufType" checked/> 管辖责任人&nbsp;&nbsp;
		    	<input type="radio" value="1" name="ufType"/> 管辖员&nbsp;&nbsp;
		    	</c:if>
		    	<c:if test="${userFirst.ufType == 1}">
		    	<input type="radio" value="0" name="ufType"/> 管辖责任人&nbsp;&nbsp;
		    	<input type="radio" value="1" name="ufType" checked/> 管辖员&nbsp;&nbsp;
		    	</c:if>
		    	
		    </li>
		    <li>
		    	<label>单位管理员工号：</label>
			    <input name="deptUser.userId" id="userId" value="${userFirst.deptUser.userId}" type="text" required readonly class="dfinput" />
			    <i><font color="#FF0000">*必填</font>
		    </li>
		    <li>
		    	<label>一级管辖域：</label>
			    <select name="af.afId" class="dfinput">
			       <option value="${userFirst.af.afId}" selected="">${userFirst.af.afName}</option>
			       <s:iterator value="list" id="id">
			       <option value="${afId}">${afName}</option>
			       </s:iterator>
			       
			     </select>
			    <i><font color="#FF0000">*不是必填</font>
		    </li>
		    <li><label>&nbsp;</label>
		    	<input id="btn" type="button" value="确认" class="btn">
		    	<input class="btn" type="button" value="返回" onclick="window.location.href='<%= basePath%>/sys/ManageUserFirst_findByPage.action?deptUser.userId=${userCode}'">
		    </li>
	    </ul>
    </form>
    </div>
<script>
	//表单提交
	$("#btn").click(function(e){
		var ufName = $("#ufName").val(),
			loginName = $("#loginName").val(),
			loginPwd = $("#loginPwd").val(),
			ufPhone = $("#ufPhone").val();
		if(ufName.length<2 || ufName.length>5){
			$("#ufName").css("color", "red");
			return false;
		}else{
			$("#ufName1").removeAttr("style");
		}
		if(loginName.length<4 || loginName.length>16){
			$("#loginName1").css("color", "red");
			return false;
		}else{
			$("#loginName1").removeAttr("style");
		}
		if(loginPwd.length<4 || loginPwd.length>16){
			$("#loginPwd1").css("color", "red");
			return false;
		}else{
			$("#loginPwd1").removeAttr("style");
		}
		if(isNaN(Number(ufPhone)) || ufPhone.length != 11){	//如果不为数字
			$("#ufPhone1").css("color", "red");
			return false;
		}else{
			$("#ufPhone1").removeAttr("style");
		}
		var params = $("#fm").serialize();
		$.post("<%=basePath%>/sys/manageUserFirst_update.action", params, function(data){
			if(data.targ == true){
				window.location.href = "<%=basePath%>/sys/ManageUserFirst_findByPage.action?deptUser.userId=${userCode}";
			}else{
				$("#loginName1").css("color", "red").text("该用户名不存在！");
			}
		});
	});
</script>
</body>
</html>
