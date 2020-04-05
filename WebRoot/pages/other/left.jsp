<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ include file="/WEB-INF/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <base href="<%= basePath%>"/>
		<title>系统功能菜单</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="js/jquery.js"></script>

		<script type="text/javascript">
			$(function() {
				//导航切换
				$(".menuson li").click(function() {
					$(".menuson li.active").removeClass("active");
					$(this).addClass("active");
				});
		
				$('.title').click(function() {
					var $ul = $(this).next('ul');
					$('dd').find('ul').slideUp();
					if ($ul.is(':visible')) {
						$(this).next('ul').slideUp();
					} else {
						$(this).next('ul').slideDown();
					}
				});
			});
		</script>

	</head>

	<body style="background: #f0f9fd;">
		<div class="lefttop">
			<span></span>功能菜单
		</div>

		<%-- <dl class="leftmenu">
			
			<p:permissions menu="addMerchantUnit,editMerchantUnit,deleteMerchantUnit,addUser,editUser,deleteUser,addRole,
			editRole,deleteRole,addRight,editRight,deleteRight,addComTag,editComTag,deleteComTag,addLoginRecord,viewMessage">
			<dd>
				<div class="title">
					<span><img src="images/leftico04.png" /> </span>系统管理
				</div>
				<ul class="menuson">
					<p:permissions menu="addUser,editUser,deleteUsere">
					<li>
						<cite></cite><a href="<%= basePath%>/biz/SysUser_list.action" target="rightFrame">用户管理</a><i></i>
					</li>
					</p:permissions>
					<p:permissions menu="addUser,editUser,deleteUsere">
					<li>
						<cite></cite><a href="<%= basePath%>/biz/SysScore_list.action" target="rightFrame">成绩管理</a><i></i>
					</li>
					</p:permissions>
					<p:permissions menu="addRole,editRole,deleteRole">
					<li>
						<cite></cite><a href="<%= basePath%>/biz/SysRole_list.action" target="rightFrame">角色管理</a><i></i>
					</li>
					</p:permissions>
					<p:permissions menu="addRight,editRight,deleteRight">
					<li>
						<cite></cite><a href="<%= basePath%>/biz/SysRight_list.action" target="rightFrame">权限管理</a><i></i>
					</li>
					</p:permissions>
					<p:permissions menu="deleteLoginRecord">
					<li>
						<cite></cite><a href="<%= basePath%>/biz/SysLoginRecord_list.action" target="rightFrame">登陆记录列表</a><i></i>
					</li>
					</p:permissions>
					<li>
						<cite></cite><a href="<%= basePath%>/biz/ServicePostPgy_openAdd.action" target="rightFrame">新增岗位</a><i></i>
					</li>
				</ul>
			</dd>
			</p:permissions>
		</dl> --%>
		
		<!-- 系统管理员 -->
		<dl class="leftmenu">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>系统管理员
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="<%= basePath%>/sys/SysUser_findByPage.action" target="rightFrame">人员信息管理</a><i></i>
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/Dept_findByPage.action" target="rightFrame">单位信息管理</a><i></i>
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/DeptUser_findByPage.action" target="rightFrame">单位管理员信息</a><i></i>
					</li>
					<!-- <li>
						<cite></cite><a href="" target="rightFrame">管辖域管理</a><i></i>
					</li> -->
				</ul>
			</dd>
		</dl>
		<!-- 设备安装员 -->
		<dl class="leftmenu">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>设备安装员
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="" target="rightFrame">设备信息管理</a><i></i>
					</li>
				</ul>
			</dd>
		</dl>
		<!-- 单位管理员 -->
		<dl class="leftmenu">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>单位管理员
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="<%= basePath%>/sys/ManageUserFirst_findByPage.action?deptUser.userId=${userCode}" target="rightFrame">一级管辖员信息</a><i></i>
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/ManageAreaFirst_findByPage.action?userId=${userCode}" target="rightFrame">一级管辖域信息</a><i></i>						
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/SysLog_findByPage.action" target="rightFrame">日记信息</a><i></i>						
					</li>
				</ul>
			</dd>
		</dl>
		<!-- 一级管辖员 -->
		<dl class="leftmenu">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>一级管辖员
				</div>
				<ul class="menuson">
					<li>
						<!-- 二级管辖员、报警联系人 -->
						<cite></cite><a href="" target="rightFrame">人员信息管理</a><i></i>
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/ManageUserSecond_findByPage.action?uf.ufId=${userCode}" target="rightFrame">二级管辖员管理</a><i></i>
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/ManageAreaSecond_findByPage.action?ufId=${userCode}" target="rightFrame">二级管辖域管理</a><i></i>
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/Facility_findByPage2.action?ufId=${userCode}" target="rightFrame">设备信息管理</a><i></i>
					</li>
					<li>
						<cite></cite><a href="" target="rightFrame">数据统计</a><i></i>
					</li>
				</ul>
			</dd>
		</dl>
		<!-- 二级管辖员 -->
		<dl class="leftmenu">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>二级管辖员
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="<%= basePath%>/sys/InstallPoint_findByPage.action" target="rightFrame">安装点管理</a><i></i>
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/Facility_findByPage.action" target="rightFrame">设备信息管理</a><i></i>
					</li>
					<li>
						<cite></cite><a href="<%= basePath%>/sys/WarnUser_findByPage.action" target="rightFrame">报警联系人信息</a><i></i>
					</li>
				</ul>
			</dd>
		</dl>
		<!-- 报警联系人 -->
		<dl class="leftmenu">
			<dd>
				<div class="title">
					<span><img src="images/leftico01.png" /> </span>报警联系人
				</div>
				<ul class="menuson">
					<li>
						<cite></cite><a href="" target="rightFrame">设备信息</a><i></i>
					</li>
				</ul>
			</dd>
		</dl>

	</body>
</html>
