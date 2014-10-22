<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Menu Controller!</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="${css}/common.css" />
	<link type="text/css" rel="stylesheet" href="${flexigrid_css}" />
	
	<script type="text/javascript" src="${jquery_js }"></script>
	<script type="text/javascript" src="${jquery_cookie_js}"></script>
	<script type="text/javascript" src="${jquery_ui_js}"></script>
	<script type="text/javascript" src="${blockUI_js}"></script>
	<script type="text/javascript" src="${scripts}/public.js"></script>
	<script type="text/javascript" src="${flexigrid_js}"></script>
	<script type="text/javascript" src="${flexigrid_total_js}"></script>
	<script type="text/javascript" src="${inputSelectStyle_js}"></script>
	<script type="text/javascript" language="javascript">
	$(function(){ 
		loadData(); 
	});
	function getSearchParams() {
		return [{name : "name",value : $("#menuname").val()} ];
	}
	function loadData() {
		var params = getSearchParams();
		$("#menuList").flexigrid({
			url : "${context}/admin/menu/list",
			dataType : "json",
			params : params,
			colModel : [{display:'菜单名称',name:'name',width:150,sortable:false,align:'center',showtitle:true}, 
			{display : '菜单展示名称',name : 'displayName',width : 150,sortable : false,html : true,align : 'center'},
			{display : '链接地址',name : 'linkUrl',width : 150,sortable : false,align : 'center'}, 
			{display : '图片地址',name : 'picUrl',width : 150,sortable : false,align : 'center'}, 
			{display : '备注',name : 'remark',width : 120,sortable : false,align : 'center'}, 
			{display : '操作',name : 'operate',width : 250,sortable : false,align : 'center'		} ],
			width : "auto",
			height : "auto",
			preProcess : processData
		});
	}

	function processData(json) {
		if (json.ret != 0) {
			alert(json.msg);
			return null;
		}
		var data = json.data;
		$.each(data.rows,
				function(i, o) {
					var operate = '';
					o.operate = operate;
				});
		return data;
	}

	/**
	 * 查询批次
	 */
	function search() {
		var params = getSearchParams();
		$("#menuList").flexOptions({params : params}).flexReload();
		return false;
	}
	
	function showMenuDiv(){
		showHtmlDiag(360, 440, "添加菜单", $("#addMenu"));
	}
	
	function addMenu(){
		console.log($("#menuForm").serialize());
		$.post("add?action=doAddMenu", $("#menuForm").serialize(), 
				function(json) {
					if(json.ret == 0){
						alert("添加成功！");
					}else{
						alert("添加失败！");
					}
					search();
				}, 'json');
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
				<li><a href="../index">首页</a></li>
				<li><a href="../userinfo">个人信息</a></li>
				<li><a href="index">菜单编辑</a></li>
				<li><a href="../entity/index">案例管理</a></li>
			</ul>
		</div>
		<div class="">
			<div>
				<a href="javascript:showMenuDiv();" title="添加菜单">AddMenu</a>
			</div>
			<ul class="serach">
				<li>名称：<input type="text" name="menuname" id="menuname"/></li>
				<li>&nbsp;</li>
				<li><input type="button" onclick="search();" value="查找"/></li>
			</ul>
			<table id="menuList"></table>
		</div>
		<div id="addMenu" style="display: none;">
			<form id="menuForm" name="menuForm">
				<table>
					<tr>
						<td>菜单名称:</td>
						<td><input type="text" name="name" id="name"></input></td>
					</tr>
					<tr>
						<td>菜单展示名称:</td>
						<td><input type="text" name="displayName" id="displayName"></input></td>
					</tr>
					<tr>
						<td>链接地址:</td>
						<td><input type="text" name="linkUrl" id="linkUrl"></input></td>
					</tr>
					<tr>
						<td>图片地址:</td>
						<td><input type="text" name="picUrl" id="picUrl"></input></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><input type="text" name="remark" id="remark"></input></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="添加" onclick="addMenu();"/>
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
