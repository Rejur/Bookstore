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
    <link href="resources/bookstore/css/global.css" rel="stylesheet" type="text/css">
    <link href="resources/css/answer.css" rel="stylesheet" type="text/css">
    <link href="resources/bookstore/css/channel.css" rel="stylesheet" type="text/css" />
    <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
    <script src="resources/static/js/frontjs/jquery-1.9.1.min.js" type="text/javascript"></script>
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
<jsp:include page="../../frontmaster/head.jsp"></jsp:include>
<div class="book_view">
    <h1 class="b_title">
        <label ID="lblBookName"></label></h1>
    <div class="b_exa">
        <span class="book_group">书籍分类：${categoriesBook.name}</span> <span class="book_status">正在阅读（150 人），已经阅读（4521
                人） <span><a href="#">放入书架</a></span> <a href="#">什么是书架？</a></span>
    </div>
    <!--book basic start-->
    <dl class="put_book">
        <dt>
            <img src="resources/bookstore/Images/BookCovers/${cBooks.isbn}.jpg"/>
            <div class="chakan">
                <img src="resources/bookstore/Images/zoom.gif" />
                <a class="gray878787a" href="#" name="bigpicture">点击查看大图</a>
            </div>
        </dt>
        <dd>
            <div id="book_editor">
                作 者：
                ${cBooks.author}
                著<br />
                出 版 社：${cPublishers.name}
            </div>
            <ul id="book_attribute">
                <li>出版时间：
                    ${cBooks.publishdate}
                <li>字 数： </li>
                <li>版 次： </li>
                <li>页 数：</li>
                <li>印刷时间： </li>
                <li>开 本： </li>
                <li>印 次： </li>
                <li>纸 张： </li>
                <li>I S B N ：
                    ${cBooks.isbn}
                <li>包 装： 平装</li>
            </ul>
            <div id="book_price">
                定价：${cBooks.unitprice}
            </div>
            <div id="book_point">
                <span>送积分：<span id="pointsTag">354</span></span> <a target="_blank" href="#2">积分说明</a>
                <br />
                <br />
                <a href="/Cart/add?bid=${cBooks.id}">
                    <img id="ibtnBuy" src="resources/bookstore/Images/btn_goumai.gif"
                         onmouseover="this.src='resources/bookstore/Images/btn_goumai_click.gif'"
                         onmouseout="this.src='resources/bookstore/Images/btn_goumai.gif'"/>
                </a>
            </div>

        </dd>
    </dl>
    <!--book basic end-->
    <!--book intro start-->
    <dl class="book_intro">
        <dt>内容提要</dt>
        <dd>
            ${cBooks.contentdescription}
            </dd>
    </dl>
    <dl class="book_intro">
        <dt>目录</dt>
        <dd>
            ${cBooks.toc}
            </dd>
    </dl>
    <!--book intro end-->
</div>
</div>
<jsp:include page="../../frontmaster/footer.jsp"></jsp:include>
<script type="text/javascript"></script>
<script  src="/js/common/jquery/jquery1.8.3.min.js"></script>
<!--<script  src="/js/common/supersized.3.2.7.min.js"></script>-->
<!--<script  src="/js/common/supersized-init.js"></script>-->
<script src="/js/user.login.js"></script>

<script >
    jQuery(document).ready(function() {
        try{
            var _href = window.location.href+"";
            if(_href && _href.indexOf('?kickout')!=-1){
                layer.msg('您已经被踢出，请重新登录！');
            }
        }catch(e){

        }
        //回车事件绑定
        document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){
                $('#login').click();
            }
        };

        $('.page-container form .username, .page-container form .password').keyup(function(){
            $(this).parent().find('.error').fadeOut('fast');
        });
    });
</script>
</body>
</html>