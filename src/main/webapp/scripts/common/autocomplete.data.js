$.fn.extend({
	/**
	 * @param url 请求数据URL
	 * @param params 请求数据参数
	 * @param _value 默认显示值
	 * @param _id 默认选中id
	 * @param _val 无效选项值
	 * @param iptId 选中值文本框id
	 * @param nameKey 填充对象名称
	 * @param idKey 填充对象id
	 */
	completeData : function(url, params, _value, _id, _val, iptId, nameKey, idKey){
		var ipt = $(this);
		$(".ac_results").remove();
		$.post(url, params, function(json){
			if(json.ret!=0){
				alert("加载["+nameKey+"]数据出错，请联系系统管理员。");
				return;
			}
			$(ipt).autocomplete(json.data, {
				minChars:0,				//自动完成激活之前填入的最小字符
				matchContains:true,		//包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
				autoFill:true,			//自动填充
				scroll:false,
				formatItem:function (row, i, max) {
					return row[nameKey];
				}
			}).result(function (event, row, formatted) {
				$(ipt).val(row[nameKey]);
				$("#"+iptId).val(row[idKey]);				
			});
			if(_val!=null){
				$(ipt).blur(function(){
					var val = $(ipt).val();
					var id = $("#"+iptId).val();
					var notFind = true;
					if(val=="" || val==_value){
						$("#"+iptId).val(_id);
					}else{
						var check = false;						
						$.each(json.data,function(i,o){
							if(o[idKey]==id && o[nameKey]==val){
								$("#"+iptId).val(id);
								check = true;
								notFind = false;
								return false;
							}
						});
						if(notFind){
							$.each(json.data,function(i,o){							
								if(o[nameKey]==val){
									$("#"+iptId).val(o[idKey]);
									check = true;
									return false;
								}
							});
						}
						if(!check){
							$("#"+iptId).val(_val);
						}
					}
				});
			}
		},"json");
	},
	/**
	 * @param url 请求数据URL
	 * @param params 请求数据参数
	 * @param _val 无效选项值
	 * @param iptId 选中值文本框id
	 * @param nameKey 填充对象名称
	 * @param idKey 填充对象id
	 * @param callback 回调方法，当匹配后调用
	 */
	completeAsyncData: function(url, params, _val, iptId, nameKey, idKey, callback){
		var ipt = $(this);
		$(ipt).flushCache();
		$(ipt).autocomplete(url, {
			delay:400,
			minChars:0,
			matchContains:true,
			autoFill:false,
			scroll:false,
			matchCase:true,
			extraParams:params,
			dataType:"json",		
			parse: function(json) {
				if(json.ret!=0){
					alert("加载["+nameKey+"]数据出错，请联系系统管理员。");
					return;
				}
				var rows = new Array();
				$.each(json.data,function(i,o){
					rows[i] = {data:o,value:o[nameKey],result:o[nameKey]};
				});
				return rows;
			},
			formatItem:function (row, i, max) {
				return row[nameKey];
			}
		}).result(function (event, row, formatted) {
			if(typeof(row) == "undefined"){
				$(ipt).val("");
				$("#"+iptId).val(_val);
			} else {
				$(ipt).val(row[nameKey]);
				$("#"+iptId).val(row[idKey]);
			}
			if(typeof(callback) == 'function'){
				$(callback(row));
			}
		}).blur(function(){
		    $(ipt).search();
		});
	},
	completeAsyncDataWithoutSearch: function(url, params, _val, iptId, nameKey, idKey, callback){
		var ipt = $(this);
		$(ipt).flushCache();
		$(ipt).autocomplete(url, {
			delay:400,
			minChars:0,
			matchContains:true,
			autoFill:false,
			scroll:false,
			matchCase:true,
			extraParams:params,
			dataType:"json",		
			parse: function(json) {
				if(json.ret!=0){
					alert("加载["+nameKey+"]数据出错，请联系系统管理员。");
					return;
				}
				var rows = new Array();
				$.each(json.data,function(i,o){
					rows[i] = {data:o,value:o[nameKey],result:o[nameKey]};
				});
				return rows;
			},
			formatItem:function (row, i, max) {
				return row[nameKey];
			}
		}).result(function (event, row, formatted) {
			if(typeof(row) == "undefined"){
				$(ipt).val("");
				$("#"+iptId).val(_val);
			} else {
				$(ipt).val(row[nameKey]).attr("autoCompleteName",row[nameKey]);
				$("#"+iptId).val(row[idKey]);
			}
			if(typeof(callback) == 'function'){
				$(callback(row));
			}
		}).blur(function(){
		    if($(ipt).val()!=$(ipt).attr("autoCompleteName")){
		    	$(ipt).val("").attr("autoCompleteName","");
				$("#"+iptId).val(_val);
		    }
		});
	},
	completeDeptData : function(url, params, _value, _id){
		$(this).completeData(url,params,_value,_id,"0","deptId","name","id");
	}
});
 