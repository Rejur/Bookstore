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
<%@ page import="com.ssm.service.PublishersService"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <link href="resources/bookstore/css/Index.css" rel="stylesheet" type="text/css">
    <link href="resources/bookstore/css/global.css" rel="stylesheet" type="text/css">
    <link href="resources/bookstore/css/member.css" rel="stylesheet" type="text/css">
    <link href="resources/bookstore/css/channel.css" rel="stylesheet" type="text/css">
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
    <script language="javascript">
        $(function(){
            var date=new Date;
            var yue = date.getMonth()+1;
            $("#list_type").find("option[id = '${order}']").attr("selected","selected");
        })
        function showMsg(obj) {
            var opt = obj.options[obj.selectedIndex]
            var opt = obj.options[obj.selectedIndex].id;

            window.location.href='/Books/List?order='+opt+'&${url}&pageindex = 1';
        }
    </script>
</head>
<body>
<jsp:include page="../../frontmaster/head.jsp"></jsp:include>
<div class="main">
    <div class="list_asc">
        <!--choice order type-->
        <div class="type_choice f_left">
            排序方式
            <select name="list_type" id="list_type" onchange="showMsg(this)">
                <option value="1" id="PublishDate" selected>按出版日期排序</option>
                <option value="2" id="UnitPrice" >按价格排序</option>
            </select>

        </div>
    </div>

    <c:choose>
        <c:when test="${not empty booksList.list}">
            <c:forEach items="${booksList.list}" var="var" varStatus="vs">
                <dl class="list_area">
                    <dt><a href="#">
                        <img src="resources/bookstore/Images/BookCovers/${var.isbn}.jpg" width="100" height="100" alt="${var.title}"/>
                    </a></dt>
                    <dd>
                        <h2 class="b_title"><a href="/Books/Detail?id=${var.id}">${var.title}</a></h2>

                    <div class="b_property">
                        作者:${var.author}编著<br/>
                        出版时间:${var.publishdate}<br/>
                    </div>
                    <h4 class="b_intro">${var.contentdescription.substring(0, 50)}</h4>
                    <div class="b_buy">
                        <span class="gray del">${var.unitprice}</span>
                    </div>

                    </dd>
                </dl>
            </c:forEach>
        </c:when>
    </c:choose>
    <div class="pages">
        <label ID="lblCurrentPage"></label>
        第${booksList.pageNum}页 共${booksList.pages}页
        <input type="button" value="首页" onclick="window.location.href='/Books/List?order=${order}&${url}&pageIndex=1'"/>
        <c:if test="${pageIndex > 1}">
            <input type="button" value="上一页" onclick="window.location.href='/Books/List?order=${order}&${url}&pageIndex=${pageindex - 1}'"/>
        </c:if>
        <c:if test="${(pageIndex + 3) < booksList.pages}">
        <c:forEach var="x" begin="${(pageindex - 2) <= 0 ? 1 : (pageindex - 2)}" end="${(pageindex + 2) >= booksList.pages ? booksList.pages : (pageindex + 2)}">
            <input type="button" value="${x}" onclick="window.location.href='/Books/List?order=${order}&${url}&pageIndex=${x}'"/>
        </c:forEach>
        </c:if>
        <c:if test="${booksList.pageNum < booksList.pages}">
            <input type="button" value="下一页" onclick="window.location.href='/Books/List?order=${order}&${url}&pageIndex=${pageindex + 1}'"/>
        </c:if>
    </div>
</div></div>
<jsp:include page="../../frontmaster/footer.jsp"></jsp:include>
<!--href设置为此，为绝对的空连接，点击不返回任何数值，此例中如此设置来制作平滑上滚-->
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
        $('.page-container form .username, .page-container form .password').keyup(function(){
            $(this).parent().find('.error').fadeOut('fast');
        });
        //注册
        $("#register").click(function(){
            window.location.href="register.shtml";
        });
    });
</script>
</body>
</html>