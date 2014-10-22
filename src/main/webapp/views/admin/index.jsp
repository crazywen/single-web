<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Welcom to Single Web Admin Controller!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="head">
		<h1>${user.name },Welcom to Single Web Admin Controller! </h1>
	</div>
	<div class="content">
		<div class="nav">
			<ul>
				<li><a href="index">首页</a></li>
				<li><a href="userinfo">个人信息</a></li>
				<li><a href="menu/index">菜单编辑</a></li>
				<li><a href="entity/index">案例管理</a></li>
			</ul>
		</div>
		<div class="">
			${user}
		</div>
	</div>
	<div class="footer">
	</div>
</body>
</html>
