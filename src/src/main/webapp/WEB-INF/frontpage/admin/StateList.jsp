<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/19
  Time: 下午12:11
  To change this template use File | Settings | File Templates.
--%>

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
    <script type="text/javascript" src="/resources/Scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="/resources/Scripts/jquery-ui-1.8.20.min.js"></script>
    <script type="text/javascript" src="/resources/Scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <link href="/resources/bookstore/Areas/Admin/Css/admin.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        function selectAll(parentItem) {
            var items = document.getElementsByTagName("input");
            for (i = 0; i < items.length; i++) {
                if (parentItem.checked) {
                    if (items[i].type == "checkbox") {
                        items[i].checked = true;
                    }
                }
                else {
                    if (items[i].type == "checkbox") {
                        items[i].checked = false;
                    }
                }
            }
        }
        function setCheckBoxValue()
        {
            var items = document.getElementsByTagName("input");
            for (i = 0; i < items.length; i++) {
                if (items[i].type == "checkbox") {
                    items[i].value = items[i].checked;
                }

            }
        }
    </script>
</head>
<body>
<jsp:include page="../../adminmaster/head.jsp"></jsp:include>
>
<a href="/aUsers/StateList">用户状态列表</a>


<form name = "operator" method = "post">
    <p>
        <input type="button" name="btnEnable" value=" 启用用户 "
               onclick='this.form.action="/aUsers/Enable";this.form.submit();' />
        <input type="button" name="btnDisable" value=" 禁止用户 "
               onclick='this.form.action="/aUsers/Disable";this.form.submit();' />
    </p>
    <br />
<table class="data_table" cellspacing="0" cellpadding="0" rules="all" border="1" id="ctl00_cphAdmin_gvMain" style="border-collapse: collapse;">

    <tr>
        <th scope="col">
            <input id="cbAll" type="checkbox" onclick="selectAll(this)" />
        </th>
        <th scope="col">地址</th>
        <th scope="col">用户名</th>
        <th scope="col">电话</th>
        <th scope="col">姓名</th>
        <th scope="col">Email</th>
        <th scope="col">角色</th>
        <th scope="col">状态</th>
    </tr>


    <c:choose>
        <c:when test="${not empty usersList}">
            <c:forEach items="${usersList.list}" var="var" varStatus="vs">
                <c:choose>
                    <c:when test="${vs.index} % 2 == 0">
                        <tr style="background-color: White">
                    </c:when>
                    <c:otherwise>
                        <tr style="background-color: #DDF5D9">
                    </c:otherwise>
                </c:choose>

                <td align="center">
                    <input id="check" type="checkbox" name="checkstate" value="${var.id}"/>

                    <input type="hidden" name="checkedUsers.index" value="${vs.index}" />
                    <input type="hidden" name="checkedUsers[${vs.index}].Id" value="${var.id}" />
                </td>
                <td>${var.address}</td>
                <td>${var.loginid}</td>
                <td>${var.phone}</td>
                <td>${var.name}</td>
                <td>${var.mail}</td>
                <td>
                    <c:choose>
                        <c:when test="${var.userroleid == 1}">
                        user
                        </c:when>
                        <c:otherwise>
                        admin
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${var.userstateid == 1}">
                            正常
                        </c:when>
                        <c:otherwise>
                            无效
                         </c:otherwise>
                    </c:choose>
            </c:forEach>
            </td>
            </tr>
        </c:when>
    </c:choose>
    第${usersList.pageNum}页 共${usersList.pages}页
    <input type="button" value="首页" onclick="window.location.href='/aUsers/List2?pageIndex=1'"/>
    <c:if test="${pageindex > 1}">
        <input type="button" value="上一页" onclick="window.location.href='/aUsers/List2?pageIndex=${pageindex - 1}'"/>
    </c:if>
    <c:if test="${(pageindex + 3) < usersList.pages}">
        <c:forEach var="x" begin="${(pageindex - 2) <= 0 ? 1 : (pageindex - 2)}" end="${(pageindex + 2) >= usersList.pages ? usersList.pages : (pageindex + 2)}">
            <input type="button" value="${x}" onclick="window.location.href='/aUsers/List2?pageIndex=${x}'"/>
        </c:forEach>
    </c:if>
    <c:if test="${usersList.pageNum < usersList.pages}">
        <input type="button" value="下一页" onclick="window.location.href='/aUsers/List2?pageIndex=${pageindex + 1}'"/>
    </c:if>


</table>
</form>

</body>
</html>
