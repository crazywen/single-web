package com.crazy.singleweb.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crazy.singleweb.entity.Entity;
import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.service.SingleService;
import com.crazy.singleweb.util.Config;
import com.crazy.singleweb.util.DynamicParam;
import com.crazy.singleweb.util.EntityUtils;
import com.crazy.singleweb.util.FileUtil;
import com.crazy.singleweb.util.JsonUtil;
import com.crazy.singleweb.util.Keys;
import com.crazy.singleweb.util.PageInfo;
import com.crazy.singleweb.util.SessionUtil;
import com.crazy.singleweb.util.StringUtil;

@Controller
public class EntityController {

	private static final Logger logger = LoggerFactory
			.getLogger(EntityController.class);

	private SingleService singleService;

	@RequestMapping(value = Keys.KEY_ADMIN_ENTITYIDX)
	public String menuIdx(Model model) {
		User user = SessionUtil.getCurUser();
		model.addAttribute("user", user);
		return Keys.KEY_ADMIN_ENTITYIDX;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_ENTITYLIST)
	public String menulist(Model model, HttpServletRequest request,
			@RequestParam(value = "page") int page,
			@RequestParam(value = "rp") int rp,
			@RequestParam(value = "name") String name) {
		String jsonData = JsonUtil.toJSON(page, 0, EntityUtils.EMPTY_ENTITYS);
		try {
			Entity entity = new Entity();
			if (!StringUtil.isBlank(name)) {
				entity.setName('%' + name + '%');
			}
			DynamicParam param = new DynamicParam();
			int total = singleService.findEntitysCount(entity, param);
			if (total > 0) {
				param.setPi(new PageInfo(page, rp, total));
				List<Entity> entitys = singleService.findEntitys(entity, param);
				page = param.getPi().getIndex();
				jsonData = JsonUtil.toJSON(page, total, entitys);
			}
		} catch (Exception e) {
			logger.error("entity list failed,cause:{}", e);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_ADDENTITY, params = "action=doAddEntity")
	public String addEntity(Model model, Entity entity) {
		String jsonData = JsonUtil.toJSON(-1);
		entity.setCreateTime(new Date());
		if (singleService.addEntity(entity)) {
			jsonData = JsonUtil.toJSON(0);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_UPDATEENTITY, params = "action=preUpdate")
	public String preUpdateEntity(Model model,
			@RequestParam(value = "id") int id) {
		Entity entity = singleService.findEntityById(id);
		model.addAttribute("entity", entity);
		return Keys.KEY_ADMIN_UPDATEENTITY;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_UPDATEENTITY, params = "action=doUpdateEntity")
	public String updateEntity(Model model, Entity entity) {
		String jsonData = JsonUtil.toJSON(-1);
		if (singleService.updateEntity(entity)) {
			jsonData = JsonUtil.toJSON(0);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	@RequestMapping(value = Keys.KEY_ADMIN_DELEENTITY)
	public String delEntity(Model model, @RequestParam(value = "id") int id) {
		String jsonData = JsonUtil.toJSON(-1);
		if (singleService.deleteEntity(id)) {
			jsonData = JsonUtil.toJSON(0);
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	private static int maxSize = 1024 * 1024 * 20;

	@RequestMapping(value = Keys.KEY_ADMIN_ADDMENU, params = "doUpload")
	public String upload(Model model, HttpServletRequest request) {
		// 案例上传
		String jsonData = JsonUtil.toJSON(-1);
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			String errorFiled = null;
			if (!isMultipart) {
				errorFiled = "no nultipart!";
				jsonData = JsonUtil.toJSON(errorFiled, -1);
				model.addAttribute(Keys.JSON_DATA, jsonData);
				return Keys.AJAX_JSON;
			} else {
				// Create a factory for disk-based file items
				FileItemFactory factory = new DiskFileItemFactory();
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);
				// Set overall request size constraint
				// upload.setSizeMax(config.getMaxUploadFileSize());
				upload.setHeaderEncoding("utf-8");

				FileItem fileItem = null;
				// Parse the request
				List<?> fileItems = upload.parseRequest(request);
				if (fileItems == null) {
					errorFiled = "no fill upload!";
					jsonData = JsonUtil.toJSON(errorFiled, -1);
					model.addAttribute(Keys.JSON_DATA, jsonData);
					return Keys.AJAX_JSON;
				}
				String path = "/files/upload/case/";
				fileItem = (FileItem) fileItems.get(0);
				// upload file
				File file = createImportFile(Config.getContextPath(), path,
						FileUtil.getType(fileItem.getName().toLowerCase()));
				/* 检查文件大小 */
				if (maxSize > 0 && fileItem.getSize() > maxSize) {
					errorFiled = "上传文件的大小不能超过" + maxSize / 1024 / 1024 + "M！";
					jsonData = JsonUtil.toJSON(errorFiled, -1);
					model.addAttribute(Keys.JSON_DATA, jsonData);
					return Keys.AJAX_JSON;
				}
				if (upload(fileItem, file)) {
					jsonData = JsonUtil.toJSON("{\"path\":" + path + "}", "ok",
							0);
				}
			}
		} catch (Exception e) {
			logger.error("Upload file failed,cause by:{}", e);
			if (e instanceof FileUploadBase.SizeLimitExceededException) {
				jsonData = JsonUtil.toJSON("上传文件的大小不能超过限制", -1);
			}
		}
		model.addAttribute(Keys.JSON_DATA, jsonData);
		return Keys.AJAX_JSON;
	}

	public boolean upload(FileItem fileItem, File file) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(fileItem.get());
			return true;
		} catch (Exception e) {
			logger.error("Upload file failed,cause by:{}", e.getMessage());
			return false;
		} finally {
			FileUtil.closeOutputStrem(fos);
		}
	}

	private String toJson(String err, String path, String localname, int width) {
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		sb.append("\"err\":\"").append(err).append("\"");
		sb.append(",\"msg\":");
		sb.append("{\"url\":\"").append(path).append("\"");
		sb.append(",\"localname\":\"").append(localname).append("\"");
		sb.append(",\"id\":\"").append("1\"");
		sb.append(",\"width\":").append(width);
		sb.append("}}");
		return sb.toString();
	}

	private File createImportFile(String contextPath, String path,
			String fileType) {
		return createFile(contextPath, path, fileType);
	}

	private File createFile(String contextPath, String path, String fileType) {
		StringBuilder sb = new StringBuilder();
		sb.append(contextPath);
		sb.append(path);
		File dir = new File(sb.toString());
		if (!dir.exists())
			dir.mkdirs();
		sb.append(fileType);
		return new File(sb.toString());
	}

	@Autowired
	public void setSingleService(SingleService singleService) {
		this.singleService = singleService;
	}

}
