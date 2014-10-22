<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.crazy.singleweb.util.Keys" %>
<%@ page import="com.crazy.singleweb.util.SessionUtil" %>
<%@ page trimDirectiveWhitespaces="true"%>
<%
final String context = request.getContextPath();
final String source = context;
final String theme = "default";//风格主题样式

//folder
final String css = source+"/css/"+theme;
final String images = source+"/images/"+theme;
final String scripts = source+"/scripts";
final String datePicker = scripts+"/my97DatePicker";

//js
final String jquery_js = scripts+"/jquery-1.8.1.min.js";
final String jquery_cookie_js = scripts+"/jquery-cookie.min.js";
final String jquery_ui_js = scripts+"/jquery-ui-1.9.2.custom.min.js";
final String blockUI_js = scripts+"/jquery.blockUI.js";
final String flexigrid_js = scripts+"/flexigrid.pack.js";
final String flexigrid_total_js = scripts+"/flexigrid.async.total.js";
final String ajaxfileupload_js = scripts+ "/ajaxfileupload.2.1.js";
final String datePicker_js = datePicker+ "/WdatePicker.js";
final String inputSelectStyle_js = scripts+ "/input-select.style.js";
final String autocomplete_js = scripts + "/jquery.autocomplete.min.js";

//css
final String flexigrid_css = source+"/css/flexigrid/flexigrid.pack.css";
final String autocomplete_css = source + "/css/autocomplete/jquery.autocomplete.css";

pageContext.setAttribute("source",source);
pageContext.setAttribute("context",context);
pageContext.setAttribute("css",css);
pageContext.setAttribute("images",images);
pageContext.setAttribute("scripts",scripts);
pageContext.setAttribute("datePicker",datePicker);
pageContext.setAttribute("jquery_js",jquery_js);
pageContext.setAttribute("jquery_cookie_js",jquery_cookie_js);
pageContext.setAttribute("jquery_ui_js",jquery_ui_js);
pageContext.setAttribute("blockUI_js",blockUI_js);
pageContext.setAttribute("flexigrid_js",flexigrid_js);
pageContext.setAttribute("flexigrid_total_js",flexigrid_total_js);
pageContext.setAttribute("ajaxfileupload_js", ajaxfileupload_js);
pageContext.setAttribute("datePicker_js", datePicker_js);
pageContext.setAttribute("inputSelectStyle_js", inputSelectStyle_js);
pageContext.setAttribute("flexigrid_css",flexigrid_css);
pageContext.setAttribute("autocomplete_js", autocomplete_js);
pageContext.setAttribute("autocomplete_css", autocomplete_css);
%>