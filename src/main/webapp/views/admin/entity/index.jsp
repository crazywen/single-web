<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../../config.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>Entity Controller!</title>
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
	<script type="text/javascript" src="${ajaxfileupload_js}"></script>
	<script type="text/javascript" src="${scripts}/common/upload.js"></script>
	<script type="text/javascript" language="javascript">
	var param = {_context:'${context}'};
	$(function(){ 
		loadData(); 
	});
	function getSearchParams() {
		return [{name : "name",value : $("#entityname").val()} ];
	}
	function loadData() {
		var params = getSearchParams();
		$("#entityList").flexigrid({
			url : "${context}/admin/entity/list",
			dataType : "json",
			params : params,
			colModel : [{display:'案例名称',name:'name',width:150,sortable:false,align:'center',showtitle:true}, 
			{display : '案例类型',name : 'type',width : 150,sortable : false,html : true,align : 'center'},
			{display : '创建时间',name : 'createTime',width : 150,sortable : false,align : 'center'}, 
			{display : '存储地址',name : 'url',width : 150,sortable : false,align : 'center'}, 
			{display : '链接地址',name : 'linkUrl',width : 150,sortable : false,align : 'center'}, 
			{display : '标识',name : 'tag',width : 120,sortable : false,align : 'center'}, 
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
					var operate = '<a href="javascript:updateEntity('+o.id+');">修改</a>';
					operate += '&nbsp;&nbsp;<a href="javascript:delEntity('+o.id+');">删除</a>&nbsp;&nbsp;';
					o.operate = operate;
				});
		return data;
	}

	/**
	 * 查询批次
	 */
	function search() {
		var params = getSearchParams();
		$("#entityList").flexOptions({params : params}).flexReload();
		return false;
	}
	
	function showCaseDiv(){
		showHtmlDiag(360, 440, "添加案例", $("#addEntity"));
	}
	
	function addEntity(){
		$.post("add?action=doAddEntity", $("#entityForm").serialize(), 
				function(json) {
					$.unblockUI();
					if(json.ret == 0){
						alert("添加成功！");
					}else{
						alert("添加失败！");
					}
					search();
				}, 'json');
	}
	
	function updateEntity(id){
		showUrlDiag(360, 440, "修改案例", "update?action=preUpdate&id="+id);
	}
	
	function delEntity(id){
		$.post("del", {"id":id}, 
				function(json) {
					$.unblockUI();
					if(json.ret == 0){
						alert("删除成功！");
					}else{
						alert("删除失败！");
					}
					search();
				}, 'json');
	}
	
	function showImg(){
		$("#displayImg").show();
	}
	</script>
</head>
<body>
	<div class="head">
		<h1>${user.name },Welcom to Single Web Case Controller! </h1>
	</div>
	<div class="content">
		<div class="nav">
			<ul>
				<li><a href="../index">首页</a></li>
				<li><a href="../userinfo">个人信息</a></li>
				<li><a href="../menu/index">菜单编辑</a></li>
				<li><a href="index">案例管理</a></li>
			</ul>
		</div>
		<div class="">
			<div>
				<a href="javascript:showCaseDiv();" title="添加示例">AddCase</a>
			</div>
			<ul class="serach">
				<li>名称：<input type="text" name="entityname" id="entityname"/></li>
				<li>&nbsp;</li>
				<li><input type="button" onclick="search();" value="查找"/></li>
			</ul>
			<table id="entityList"></table>
		</div>
		<div id="addEntity" style="display: none;">
			<form id="entityForm" name="entityForm">
				<table>
					<tr>
						<td>案例名称:</td>
						<td><input type="text" name="name" id="name"></input></td>
					</tr>
					<tr>
						<td>存储地址:</td>
						<td><input type="text" name="url" id="picUrl" readonly="readonly"></input>
						<input type="hidden" name="picWidth" id="picWidth" ></input>
						<input type="file" value="上传" name="fileUpload" id="fileUpload" onchange="ajaxFileUpload('image','add?action=doUpload')"/>
						<a id="displyShow" name="displyShow" href="javascript:;" onclick="showImg();" style="display: none;color: #0000EE;position: relative;">预览
							<div id="displayImg" style="display:none;border:1px solid red;position:absolute;bottom:20px; ">
							<img id="displayUrl" src="" alt="" />
						</div>
						</a>
						</td>
					</tr>
					<tr>
						<td>链接地址:</td>
						<td><input type="text" name="linkUrl" id="linkUrl"></input></td>
					</tr>
					<tr>
						<td>标识:</td>
						<td><input type="text" name="tag" id="tag"></input></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="添加" onclick="addEntity();"/>
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
