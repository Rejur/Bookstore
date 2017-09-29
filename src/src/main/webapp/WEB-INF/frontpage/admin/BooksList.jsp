<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/19
  Time: 下午12:41
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
> <a href="/aBooks/List">书籍列表</a>
</div>
<table>
    <tr>
        <td>&nbsp;&nbsp; 检索类别：
            <form id = "search" action="/aBooks/List">
            <select id = "categoryId" name="categoryId" onclick="">
                <option value="">"--请选择--"</option>
                <c:forEach items="${categories}" var="var" varStatus="vs">
                    <option value="${var.id}">${var.name}</option>
                </c:forEach>
            </select>

            关键字：<input name="keyword" type="text" id="keyword" value="${key}"/>

            <input type="submit" name="query" value="查询" id="query" />
            </form>
        </td>

        <td style="width: 100px;">&nbsp; &nbsp;
            <a href="/aBooks/addBook">添加书籍</a>
        </td>
    </tr>
</table>


<br />

<table class="data_table" cellspacing="0" cellpadding="0" rules="all" border="1" id="ctl00_cphAdmin_gvMain" style="border-collapse: collapse;">

    <tr>
        <th scope="col">书名</th>
        <th scope="col">作者</th>
        <th scope="col">类别</th>

        <th scope="col">&nbsp;</th>
        <th scope="col">&nbsp;</th>
    </tr>

    <c:forEach items="${booksPageBean.list}" var="var" varStatus="vs">
    <c:choose>
        <c:when test="${vs.index} % 2 == 0">
             <tr style="background-color: White" onmouseover="currentcolor=this.style.backgroundColor;this.style.backgroundColor='#6699ff'"
                 onmouseout="this.style.backgroundColor=currentcolor">
        </c:when>
        <c:otherwise>
        <tr style="background-color: #DDF5D9"onmouseover="currentcolor=this.style.backgroundColor;this.style.backgroundColor='#6699ff'"
        onmouseout="this.style.backgroundColor=currentcolor">
        </c:otherwise>
        </c:choose>
        <td>${var.title}</td>
        <td>${var.author}</td>
        <td><c:forEach items="${categories}" var="var1" varStatus="vs1">
            <c:if test="${var1.id == var.categoryid}">
                ${var1.name}
            </c:if>
        </c:forEach></td>
    <td>
        <a href="/aBooks/Delete?id=${var.id}" onclick ="return(confirm('是否要删除?'))">删除</a></td>
    <td>
        <a href="/aBooks/Detail?id=${var.id}">详细</a>
    </td>
    </c:forEach>
    </tr>
    <div class="pages">
        <label ID="lblCurrentPage"></label>
        第${booksPageBean.pageNum}页 共${booksPageBean.pages}页
        <input type="button" value="首页" onclick="window.location.href='/aBooks/List?${url}&pageIndex=1'"/>
        <c:if test="${pageindex > 1}">
            <input type="button" value="上一页" onclick="window.location.href='/aBooks/List?${url}&pageIndex=${pageindex - 1}'"/>
        </c:if>
        <c:if test="${(pageindex + 3) < booksPageBean.pages}">
            <c:forEach var="x" begin="${(pageindex - 2) <= 0 ? 1 : (pageindex - 2)}" end="${(pageindex + 2) >= booksPageBean.pages ? booksPageBean.pages : (pageindex + 2)}">
                <input type="button" value="${x}" onclick="window.location.href='/aBooks/List?${url}&pageIndex=${x}'"/>
            </c:forEach>
        </c:if>
        <c:if test="${booksPageBean.pageNum < booksPageBean.pages}">
            <input type="button" value="下一页" onclick="window.location.href='/aBooks/List?${url}&pageIndex=${pageindex + 1}'"/>
        </c:if>
    </div>
</table>

</div>
</div>
</body>
</html>
