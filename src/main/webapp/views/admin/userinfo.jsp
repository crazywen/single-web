<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Welcom to Single Web Admin Controller!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="../../scripts/jquery.js"></script>
	<script type="text/javascript">
		function showUpdateDiv(){
			$("updatePwdDiv").show();
		}
		function verifyPwd(){
			var newPwd = $("newPwd").val();
			var confirmPwd = $("confirmPwd").val();
			if(newPwd != confirmPwd){
				return false;
			}
			
		}
	</script>
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
				<li><a href="menumgmt">菜单编辑</a></li>
				<li><a href="entitymgmt">案例管理</a></li>
			</ul>
		</div>
		<div class="">
			<fieldset>
				<legend>个人信息</legend>
				<label>昵称：</label>${user.name }<br/>
				<label>登录账号：</label>${user.account }<br/>
				<label>邮箱：</label>${user.email }<br/>
				<label>电话号码：</label>${user.phone }<br/>
				<label>地址：</label>${user.address }<br/>
				<label>个人说明：</label>${user.remark }<br/>
				<a href="">修改密码</a><br/>
			</fieldset>
		</div>
		<div id="updatePwdDiv" style="display: none;">
			<form action="updatePwd" method="post" onsubmit="verifyPwd();">
				<label>&nbsp;原密码:</label><input type="text" name="oldPwd" id="oldPwd" /><br/>
				<label>&nbsp;新密码:</label><input type="text" name="newPwd" id="newPwd" /><br/>
				<label>确认密码:</label><input type="text" name="confirmPwd" id="confirmPwd" /><br/>
				<input type="submit" value="修改"/>
			</form>
		</div>
	</div>
	<div class="footer">
	</div>
</body>
</html>
