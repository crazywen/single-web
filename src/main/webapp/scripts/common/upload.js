function ajaxFileUpload(type,url) {
	var upMsg = "";
	var name = "";	
	if(type == "image"){
		upMsg = "图片";
		name = "fileUpload";
		var text = $("#"+name).val();
		var idx = text.lastIndexOf(".");
		text = text.substring(idx+1).toLowerCase();
		if(text != "jpg" && text != "png"){
			alert("请选择正确的图片类型：\n[jpg|png]");
			$("#picUrl").val("");
			$("#displyShow").hide();
			return;
		}		
	}else if(type == "audio"){
		upMsg = "音乐";
		name = "musicFile";
		var text = $("#"+name).val();
		var idx = text.lastIndexOf(".");
		text = text.substring(idx+1).toLowerCase();
		if(text != "mp3" && text != "wma" && text != "wav"){
			alert("请选择正确的音频类型：\n[mp3|wma|wav]");
			$("#musicUrl").val("");
			return;
		}		
	}else if(type == "purePic"){
		upMsg = "纯图片";
		name = "purePic_fileUpload";
		var text = $("#"+name).val();
		var idx = text.lastIndexOf(".");
		text = text.substring(idx+1).toLowerCase();
		if(text != "jpg"){
			alert("请选择正确的图片类型：\n[jpg]");
			$("#purePicUrl").val("");
			$("#purePic_disply").hide();
			return;
		}
	}else if(type == "video"){
		upMsg = "视频";
		name = "videoFile";
		var text = $("#"+name).val();
		var idx = text.lastIndexOf(".");
		text = text.substring(idx+1).toLowerCase();
		if(text != "mp4"){
			alert("请选择正确的视频类型：\n[mp4]");
			$("#videoUrl").val("");
			$("#playVideo1").hide();	
			return;
		}	
	}
	
	//alert(upMsg+"文件上传中，请稍候...");
	//$(".prompt_icon_info").after('<img src="../../images/default/loading32.gif" />&nbsp;&nbsp;');
	//$(".prompt_icon_info").remove();
	//$(".popContent").height(80).find(".buttons").remove();
	
	$.ajaxFileUpload({
		url: url+"&type="+type,
		secureuri:false,
		fileElementId:name,
		dataType:"json",
		success: function (json, status){
			console.log(json);
			if (json.ret != 0){
				alert(upMsg+"上传失败！"+json.msg);
			}else{
				var data = json.data;
				$("#baseUrl").val(data.baseUrl);
				console.log("type:"+type+",\n"+data);
				if(type == "image"){
					$("#picUrl").val(data.filePath);
					$("#displayUrl").attr("src",data.baseUrl+data.filePath);
					$("#picWidth").val(data.size);
					$("#displyShow").show();
				}else if(type == "audio"){
					$("#musicUrl").val(data.filePath);
					$("#playMusic1").show();
				}else if(type == "purePic"){
					$("#purePicUrl").val(data.filePath);
					$("#purePicWidth").val(data.size);
					$("#purePic_disply").show();
				}else if(type == "video"){
					$("#videoUrl").val(data.filePath);
					$("#playVideo1").show();				
				}
				alert(upMsg+"上传成功！");
			}
		},
		error: function (data, status, e){
			alert(upMsg+"上传失败！");
		}
	});
};

