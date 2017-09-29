<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/19
  Time: 上午4:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div id="header">
    <img src="/resources/bookstore/Areas/Admin/Images/admin_top.gif" alt="" />
</div>
<div id="main">
    <div id="opt_list">
        <h1>管理员，您好！</h1>
        <ul>
            <li><a href="/Order/showOrder">订单管理</a></li>
            <li><a href="/aUsers/List">用户管理</a></li>
            <li><a href="/aUsers/List2">用户状态管理</a></li>
            <li><a href="/aBooks/List2">分类管理</a> </li>
            <li><a href="/aBooks/List">书籍管理</a></li>

            <li><a href="/adminLogout">注销账户</a></li>
        </ul>
    </div>
    <div id="opt_area">
        <div id="breadcrumb" class="black">您现在的位置：  <a href="/index">第三波</a>>
            <a href="/Admin">管理员后台</a>>
