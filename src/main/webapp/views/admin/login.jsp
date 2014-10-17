<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台登录</title>
<style type="text/css">
body{margin:0;padding:0;font-size:12px;font-family:"宋体";}
table,tr,td,h1{margin:0;padding:0;}
.wrap{width:383px;height:340px;margin:50px auto;border:1px solid #44A5C5;}
h1{width:362px;height:27px;background:url(h1_title.jpg) repeat-x;color:#FFF;padding-top:9px;padding-left:21px; font-size:18px;}
.content{width:383xp;height:235px;background:url(guagua_con.jpg) repeat-x;padding-top:26px;}
table{width:246px;height:209px;margin:0 83px 0 54px;}
.left{width:68px;text-align:right;}
.right{width:150px;height:18px;border:1px solid #43A0BF;line-height:22px;padding-top:2px;margin-left:5px;}
.one{background:url(1.jpg)no-repeat 8px 5px;}
.two{}
.three{width:100px;}
.four{margin-left:5px;width: 82px;}
a,span{color:red;}
</style>
</head>

<body>
	<div class="wrap">
    	<h1>呱呱用户请直接登录</h1>
    	<div class="content">
    		<form action="login?action=doLogin" method="post" >
        	<table>
            	<tr>
                	<td class="left"></td>
                    <td><span>你还未登录，请先登录</span></td>
                </tr>
            	<tr>
                	<td class="left">账号/号码:</td>
                    <td><input class="right one" type="text" name="username" id="username"/></td>
                </tr>
            	<tr>
                	<td class="left">密码:</td>
                    <td><input class="right two" type="text" name="password" id="password"/></td>
                </tr>
                <!-- 
                <tr>
                	<td class="left">验证码:</td>
                    <td><input class="right three" type="text"name="user"/></td>
                </tr> -->
                <tr>
                	<td></td>
                    <td><input class="four" type="submit"value="确&nbsp;&nbsp;认"/></td>
                </tr>
                <!-- 
                <tr>
                	<td></td>
                    <td><a href="#">免费注册</a>&nbsp;|&nbsp;<a href="#">忘记密码</a></td>
                </tr> -->
                <tr>
                	<td></td>
                    <td></td>
                </tr>
            </table>
            </form>
        </div>
    </div>



</body>
</html>
