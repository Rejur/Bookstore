<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/20
  Time: 下午2:13
  To change this template use File | Settings | File Templates.
--%>
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
<table class="data_table" cellspacing="0" cellpadding="0" rules="all" border="1" id="ctl00_cphAdmin_gvMain" style="border-collapse: collapse;">
    <tr>
        <th scope="col">订单号</th>
        <th scope="col">订单时间</th>
        <th scope="col">用户id</th>
        <th scope="col">总价</th>
        <th scope="col">&nbsp;</th>
    </tr>


    <c:choose>
        <c:when test="${not empty ordersList}">
            <c:forEach items="${ordersList.list}" var="var" varStatus="vs">
                <c:choose>
                    <c:when test="${vs.index} % 2 == 0">
                        <tr style="background-color: White">
                    </c:when>
                    <c:otherwise>
                        <tr style="background-color: #DDF5D9">
                    </c:otherwise>
                </c:choose>
                <td>${var.id}</td>
                <td>${var.orderdate}</td>
                <td>${var.userid}</td>
                <td>${var.totalprice}</td>
                <td>
                    <a href="/Order/Delete?id=${var.id}" onclick ="return(confirm('是否要删除?'))">删除</a></td>
            </c:forEach>
            </tr>
        </c:when>
    </c:choose>
    第${ordersList.pageNum}页 共${ordersList.pages}页
    <input type="button" value="首页" onclick="window.location.href='/Order/showOrder?pageIndex=1'"/>
    <c:if test="${pageindex > 1}">
        <input type="button" value="上一页" onclick="window.location.href='/Order/showOrder?pageIndex=${pageindex - 1}'"/>
    </c:if>
    <c:if test="${(pageindex + 3) < ordersList.pages}">
        <c:forEach var="x" begin="${(pageindex - 2) <= 0 ? 1 : (pageindex - 2)}" end="${(pageindex + 2) >= ordersList.pages ? ordersList.pages : (pageindex + 2)}">
            <input type="button" value="${x}" onclick="window.location.href='/Order/showOrder?pageIndex=${x}'"/>
        </c:forEach>
    </c:if>
    <c:if test="${ordersList.pageNum < ordersList.pages}">
        <input type="button" value="下一页" onclick="window.location.href='/Order/showOrder?pageIndex=${pageindex + 1}'"/>
    </c:if>
</table>
</div>
</div>
</body>
</html>
