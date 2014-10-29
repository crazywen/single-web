/*   
 * Copyright (c) 2012 by XUANWU INFORMATION TECHNOLOGY CO. 
 *             All rights reserved                         
 */
package com.crazy.singleweb.file;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author <a href="hw86xll@163.com">Wei.Huang</a>
 * @Date 2012-9-25
 * @Version 1.0.0
 */
public class UploadResult {
	public enum StatusCode {
		/** 成功 */
		Success(0),
		/** 失败:单个文件大小超过限制 */
		FileSizeExceeded(-1),
		/** 失败:请求表单类型不正确 */
		InvalidContentType(-2),
		/** 失败:文件类型不正确 */
		InvalidFileType(-3),
		/** 失败：文件内容为空 */
		NoContent(-4),
		/** 失败:其它错误 */
		Other(-9);

		private int index;

		private StatusCode(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public String getStateDesc() {
			switch (index) {
			case 0:
				return "成功。";
			case -1:
				return "文件大小超过限制！";
			case -2:
				return "请求表单类型不正确！";
			case -3:
				return "文件类型不正确！";
			case -4:
				return "文件内容为空！";
			default:
				return "其它错误！";
			}
		}

		public static StatusCode getStatus(int index) {
			for (StatusCode ret : StatusCode.values()) {
				if (ret.getIndex() == index)
					return ret;
			}
			throw new RuntimeException("Invalid status value: " + index);
		}
	}

	private String originFileName;
	private String newFileName;
	private String fileName;
	private StatusCode statusCode;
	private long fileSize;

	public UploadResult(StatusCode statusCode) {
		this(statusCode, null);
	}

	public UploadResult(StatusCode statusCode, String fileName) {
		this.fileName = fileName;
		this.statusCode = statusCode;
	}

	public String getFileName() {
		return fileName;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
}
