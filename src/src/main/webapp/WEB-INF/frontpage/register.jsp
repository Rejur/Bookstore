<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/10
  Time: 上午12:19
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

<%

    //el表达式
    String names="";
    String pwd="";
//取出Cookie
    Cookie [] c=request.getCookies();
    if(c!= null && c.length > 0)
        for (Cookie aC : c) {
            if (aC.getName().equals("users")) {
                //存着数据（用户名+密码）
                names = aC.getValue().split("-")[0];
                pwd = aC.getValue().split("-")[1];

                //再一次的存起来（备用）
                request.setAttribute("userName", names);
                request.setAttribute("password", pwd);
            }
        }

%>

<head lang="en">
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta name="Robots" content="index,follow" />
    <title>第三波书店-最方便的网上书店</title>
    <link rel="stylesheet" href="resources/static/css/bootstrap/dist/css/bootstrap.css">
    <link href="resources/bookstore/css/member.css"  rel="stylesheet" type="text/css" />
    <script src="resources/static/js/frontjs/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script src="resources/Scripts/jquery.validate.min.js" type="text/javascript"></script>
    <script src="resources/Scripts/jquery.validate.unobtrusive.min.js" type="text/javascript"></script>
    <link href="resources/bookstore/css/Index.css" rel="stylesheet" type="text/css">
    <link href="resources/bookstore/css/global.css" rel="stylesheet" type="text/css">
    <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
    <script src="resources/static/js/frontjs/kangyuan.js" type="text/javascript"></script>
    <script src="resources/static/js/frontjs/layer/layer.js" type="text/javascript"></script>
    <style type="text/css">
        /*.text_long{
            width:300px;
        }
        #noticesList{
            margin-top:6px;
        }
        #dynamicsList{
            margin-top:3px;
        }*/
    </style>
</head>
<body>
<jsp:include page="../frontmaster/head.jsp"></jsp:include>
<div id="action_area" class="">
    <h2 class="action_type">
        <img src="resources/bookstore/Images/register.gif" alt="会员注册" /></h2>

    <form id="_form" action="" method="post" class="member_form">
        <p>
            <label><span>*</span>用户名</label><input type="text" name="loginid" id="loginid" class="username" placeholder="Nickname" style="width:200px">
        </p>
        <p>
            <label><span>*</span>真实姓名</label><input type="text" name="name" id="name" class="name" placeholder="true_name" style="width:200px">
        </p>
        <p>
            <label><span>*</span>邮箱地址</label><input type="text" name="mail"  id="mail" placeholder="Email Account" style="width:200px">
        </p>
        <p>
            <label><span>*</span>密&nbsp;&nbsp;&nbsp;&nbsp;码</label><input type="password" name="loginpwd" id="loginpwd" class="password" placeholder="Password" style="width:200px">
        </p>
        <p>
            <label><span>*</span>重复密码</label><input type="password" id="re_loginpwd"  placeholder="Repeat the password" style="width:200px">
        </p>
        <p>
            <label><span>*</span>出生年月日</label><input type="text" name="birthday" id="birthday" class="birthday" placeholder="yyyy-mm-dd" style="width:200px">
        </p>
        <p>
            <label><span>*</span>手&nbsp;&nbsp;&nbsp;&nbsp;机</label><input type="text" name="phone" id="phone"  placeholder="Repeat the password" style="width:200px">
        </p>
        <p>
            <label><span>*</span>地&nbsp;&nbsp;&nbsp;&nbsp;址</label><input type="text" name="address" id="address"  placeholder="Repeat the password" style="width:200px">
        </p>
        <div id="vcode">
            <label><span></span></label>
            &nbsp;
            <input type="text" name="vcode"   placeholder="Verification code" style="width: 100px; margin-left: -8px; margin-right: 8px;">
            <img src="${basePath}/open/getGifCode.shtml" />
        </div>
        <label></label>
        <button type="button" class="register">确定了，马上提交</button>
        <div style="display:none"class="error"><span>+</span></div>
        <p class="form_sub">加<span>*</span>的为必填项目</p>
        <p class="form_sub"><br />
            如果你已经有“第三波书店”社区账号，请<a href="/u/gologin.do">点此登录</a></p>
    </form>

</div>
    <jsp:include page="../frontmaster/footer.jsp"></jsp:include>
<script type="text/javascript"></script>
<script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
<!--<script  src="${basePath}/js/common/supersized.3.2.7.min.js"></script>-->
<!--<script  src="${basePath}/js/common/supersized-init.js"></script>-->
<script  src="${basePath}/js/common/layer/layer.js"></script>
<script >
    jQuery(document).ready(function() {
        //验证码
        $("#vcode").on("click",'img',function(){
            /**动态验证码，改变地址，多次在火狐浏览器下，不会变化的BUG，故这样解决*/
            var i = new Image();
            i.src = '${basePath}/open/getGifCode.shtml?'  + Math.random();
            $(i).replaceAll(this);
            //$(this).clone(true).attr("src",'${basePath}/open/getGifCode.shtml?'  + Math.random()).replaceAll(this);
        });
        $('.register').click(function(){
            var form = $('#_form');
            var error= form.find(".error");
            var tops = ['27px','96px','165px','235px','304px','372px'];
            var inputs = $("form :text,form :password");
            for(var i=0;i<inputs.length;i++){
                var self = $(inputs[i]);

                if(self.val() == '' && self.attr("name") != 'keyword'){
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
            var re_password = $("#re_loginpwd").val();
            var password = $("#loginpwd").val();
            if(password != re_password){
                return layer.msg('2次密码输出不一样！',function(){}),!1;
            }
            if($('[name=vcode]').val().length !=4){
                return layer.msg('验证码的长度为4位！',function(){}),!1;
            }
            var load = layer.load();
            $.post("${basePath}/u/subRegister",$("#_form").serialize() ,function(result){
                layer.close(load);
                if(result && result.status!= 200){
                    return layer.msg(result.message,function(){}),!1;
                }else{
                    layer.msg('注册成功！' );
                    window.location.href= result.back_url || "${basePath}/";
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