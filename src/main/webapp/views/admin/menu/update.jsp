<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Menu Controller!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" language="javascript">
	function doUpdateMenu(){
		$.post("update?action=doUpdateMenu", $("#updateMenuForm").serialize(), 
				function(json) {
					$.unblockUI();
					if(json.ret == 0){
						alert("修改成功！");
					}else{
						alert("修改失败！");
					}
					search();
				}, 'json');
	}
	
	
	</script>
</head>
<body>
		<div id="updateMenu">
			<form id="updateMenuForm" name="updateMenuForm">
				<input type="hidden" id="id" name="id" value="${menu.id }"/>
				<table>
					<tr>
						<td>菜单名称:</td>
						<td><input type="text" name="name" id="name" value="${menu.name }"></input></td>
					</tr>
					<tr>
						<td>菜单展示名称:</td>
						<td><input type="text" name="displayName" id="displayName" value="${menu.displayName}"></input></td>
					</tr>
					<tr>
						<td>链接地址:</td>
						<td><input type="text" name="linkUrl" id="linkUrl" value="${menu.linkUrl}"></input></td>
					</tr>
					<tr>
						<td>图片地址:</td>
						<td><input type="text" name="picUrl" id="picUrl"  value="${menu.picUrl}"></input></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><input type="text" name="remark" id="remark"  value="${menu.remark}"></input></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="修改" onclick="doUpdateMenu();"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="footer">
	</div>
</body>
</html>
