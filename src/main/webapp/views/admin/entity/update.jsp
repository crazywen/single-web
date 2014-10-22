<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Case Controller!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" language="javascript">
	function doUpdateEntity(){
		$.post("update?action=doUpdateEntity", $("#updateEntityForm").serialize(), 
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
		<div id="updateEntity">
			<form id="updateEntityForm" name="updateEntityForm">
				<input type="hidden" id="id" name="id" value="${entity.id }"/>
				<table>
					<tr>
						<td>案例名称:</td>
						<td><input type="text" name="name" id="name"  value="${entity.name }"></input></td>
					</tr>
					<tr>
						<td>存储地址:</td>
						<td><input type="text" name="url" id="url"  value="${entity.url }"></input></td>
					</tr>
					<tr>
						<td>链接地址:</td>
						<td><input type="text" name="linkUrl" id="linkUrl"  value="${entity.linkUrl}"></input></td>
					</tr>
					<tr>
						<td>标识:</td>
						<td><input type="text" name="tag" id="tag" value="${entity.tag }"></input></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="修改" onclick="doUpdateEntity();"/>
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
