<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加二级管辖域信息</title>
<link href="<%=path %>/css/select.css" rel="stylesheet" type="text/css" />
<link href="<%=path %>/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">一级管辖员</a></li>
    <li><a href="#">二级管辖域信息管理</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    <div class="formtitle"><span>用户基本信息</span></div>
    <form method="post" id="fm">
	    <ul class="forminfo">
		    <li>
		    	<label>管辖域名称：</label>
			    <input name="asName" id="asName" type="text" required class="dfinput"/>
			    <i><font color="#FF0000">*必填</font><font id="asName1">2-5个字符组成</font></i>
		    </li>
		    <li>
		    	<label>一级管辖域编号：</label>
			    <input name="af.afId" id="afId" value="${afId}" type="text" required readonly class="dfinput"/>
			    <i><font color="#FF0000">*必填</font>
		    </li>
		    
		    <li>
		    	<label>二级管辖员：</label>
			    <select name="usId" class="dfinput">
			       <option value="" selected>默认不选择</option>
			       
			       <s:iterator value="list" id="id">
			       <option value="${usId}">${usName}</option>
			       </s:iterator>
			       
			     </select>
			    <i><font color="#FF0000">*不是必填</font>
		    </li>
		    <li><label>&nbsp;</label>
		    	<input id="btn" type="button" value="确认" class="btn">
		    	<input class="btn" type="button" value="返回" onclick="window.location.href='<%= basePath%>/sys/ManageAreaSecond_findByPage.action?ufId=${userCode}'">
		    </li>
	    </ul>
    </form>
    </div>
<script>
	//表单提交
	$("#btn").click(function(e){
		var asName = $("#asName").val();
		
		if(asName.length<2 || asName.length>5){
			$("#asName1").css("color", "red");
			return false;
		}else{
			$("#asName1").removeAttr("style");
		}
		var params = $("#fm").serialize();
		$.post("<%=basePath%>/sys/manageAreaSecond_save.action", params, function(data){
			if(data.targ == true){
				window.location.href = "<%=basePath%>/sys/ManageAreaSecond_findByPage.action?ufId=${userCode}";
			}else{
				$("#asName1").css("color", "red").text("该管辖域已存在！");
			}
		});
	});
</script>
</body>
</html>
