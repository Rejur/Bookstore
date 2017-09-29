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
    <script type="text/javascript" src="/resources/Scripts/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="/resources/Scripts/jquery-ui-1.8.20.min.js"></script>
    <script type="text/javascript" src="/resources/Scripts/jquery.ui.datepicker-zh-CN.js"></script>
    <link href="/resources/bookstore/Areas/Admin/Css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="../../adminmaster/head.jsp"></jsp:include>
</div>
<h1>欢迎进入管理界面！</h1>

    </div>
</div>
</body>
</html>

