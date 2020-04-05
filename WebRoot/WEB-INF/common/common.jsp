<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";


//百度地图的密钥
//String BAIDU_MAP_KEY = "1iVjCXdjZs2GyNlBXUczpmUV";
String BAIDU_MAP_KEY = "gwAqopVuagNVYzC5i58ESyrtIChyd1B0";
// 梧州市政府
double start_longitude = 111.285575;		//初始化经度
double start_latitude = 23.482807;			//初始化经度
// 梧州学院
double dept_longitude = 111.323585;
double dept_latitude = 23.504256;

String currentLocation = "梧州市政府";      	//初始化地图中心点地址
String currentCity = "梧州市";
// 显示的位置
String currentPlace = "梧州学院";
String currentAddress = "广西壮族自治区梧州市万秀区富民三路82号";
%>
