<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/19
  Time: 上午5:27
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/19
  Time: 上午5:27
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<head>
    <meta name="viewport" content="width=device-width" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta name="Robots" content="index,follow" />
    <title>第三波书店-管理后台</title>
    <link href="/resources/bookstore/Content/themes/base/jquery-ui.css" rel="stylesheet" />
    <link href="/resources/bookstore/Content/themes/base/jquery.ui.datepicker.css" rel="stylesheet" />
    <script type="text/javascript" src="/resourcesScripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="/resources/Scripts/jquery-ui-1.8.20.min.js"></script>
    <script type="text/javascript" src="/resources/Scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <script src="/resources/Scripts/jquery-1.7.1.min.js" type="text/javascript"></script>
    <script src="/resources/Scripts/jquery.validate.min.js" type="text/javascript"></script>
    <script src="/resources/Scripts/jquery.validate.unobtrusive.min.js" type="text/javascript"></script>
    <link href="/resources/bookstore/Areas/Admin/Css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="../../adminmaster/head.jsp"></jsp:include>
</div>
<div id="action_area" class="">
    <h2 class="action_type">

    <form id="_form" action="" method="post" class="member_form">
        <p>
            <label><span></span>用户名</label><input type="text" readonly="readonly" name="loginid" id="loginid" class="username" placeholder="Nickname" value="${user.loginid}" style="width:200px">
        </p>
        <p>
            <label><span></span>真实姓名</label><input type="text" name="name" id="name" class="name" placeholder="true_name" style="width:200px" value="${user.name}">
        </p>
        <p>
            <label><span></span>邮箱地址</label><input type="text" readonly="readonly" name="mail"  id="mail" placeholder="Email Account" style="width:200px" value="${user.mail}">
        </p>
        <p>
            <label><span></span>出生年月日</label><input type="text" name="birthday" id="birthday" class="birthday" placeholder="yyyy-mm-dd" style="width:200px" value="${birthday}">
        </p>
        <p>
            <label><span></span>手&nbsp;&nbsp;&nbsp;&nbsp;机</label><input type="text" name="phone" id="phone"  placeholder="Repeat the password" style="width:200px" value="${user.phone}">
        </p>
        <p>
            <label><span></span>地&nbsp;&nbsp;&nbsp;&nbsp;址</label><input type="text" name="address" id="address"  placeholder="Repeat the password" style="width:200px" value="${user.address}">
        </p>
        <p>
            <input style="visibility: hidden" name="id" value="${user.id}"/>
        </p>
        <label></label>
        <button type="button" class="register">保存修改</button>
        <div style="display:none"class="error"><span>+</span></div>
    </form>

</div>
    </div>
</div>
<script type="text/javascript"></script>
<script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
<!--<script  src="${basePath}/js/common/supersized.3.2.7.min.js"></script>-->
<!--<script  src="${basePath}/js/common/supersized-init.js"></script>-->
<script  src="${basePath}/js/common/layer/layer.js"></script>
<script >
    jQuery(document).ready(function() {
        //验证码
        $('.register').click(function(){
            var form = $('#_form');
            var error= form.find(".error");
            var tops = ['27px','96px','165px','235px','304px','372px'];
            var inputs = $("form :text,form :password");
            for(var i=0;i<inputs.length;i++){
                var self = $(inputs[i]);
                if(self.val() == ''){
                    error.fadeOut('fast', function(){
                        $(this).css('top', tops[i]);
                    });
                    error.fadeIn('fast', function(){
                        self.focus();
                    });
                    return !1;
                }
            }

            var mail=$("#mail").val();
            if(!mail.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
            {
                $("#mail").focus();
                return layer.msg('邮箱格式不正确! ',function (){}), !1;
            }
            if (!$("#phone").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) {
                $("#phone").focus();
                return layer.msg('电话格式不正确！',function(){}), !1;
            }
            if(!$("#birthday").val().match(/^(\d{4})-(0\d{1}|1[0-2])-(0\d{1}|[12]\d{1}|3[01])$/)) {
                $("#birthday").focus();
                return layer.msg('日期格式不正确! ',function (){}), !1;
            }
            var load = layer.load();
            $.post("${basePath}/aUsers/change",$("#_form").serialize() ,function(result){
                layer.close(load);
                if(result && result.status!= 200){
                    return layer.msg(result.message,function(){}),!1;
                }else{
                    layer.msg('修改成功！' );
                    window.location.href="/aUsers/Detail?id=${user.id}";
                }
            },"json");

        });
        $("form :text,form :password").keyup(function(){
            $(this).parent().find('.error').fadeOut('fast');
        });
        //跳转
        $("#login").click(function(){
            window.location.href="/u/gologin.shtml";
        });
        $("#register").click(function(){
            window.location.href="register.shtml";
        });
    });
</script>
</body>
</html>

