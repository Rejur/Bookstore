<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/8
  Time: 下午4:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ssm.controller.ShowDateController"%>
<%@ page import="com.ssm.service.impl.CategoriesServiceImpl"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<div id="top">
    <div class="status">
        <c:choose>
            <c:when test="${empty kyUserName}">
                <a style="text-decoration:none"href="/u/gologin.do"> Sign in </a><i class="fa fa-map-signs"></i>
                <a style="text-decoration:none"href="/u/goregister.do"> Register </a>
            </c:when>
            <c:otherwise>
                您好：${kyUserName}
                <a style="text-decoration:none" href="javascript:void(0);" onclick="logout();">Sign out</a>
            </c:otherwise>
        </c:choose>

    </div>
    <div class="member">
        <ul>
            <li><a href="#">
                <img src="resources/bookstore/Images/payVIP.gif" height="18" alt="开通VIP" /></a></li>
            <li><a href="#">
                <img src="resources/bookstore/Images/payCoin.gif" height="18" alt="学币中心" /></a></li>
        </ul>
    </div>
</div>
<div id="header">
    <div id="logo">
        <img src="resources/bookstore/Images/logo.gif" alt="第三波书店" /></div>
    <div id="nav">
        <div id="a_b_01" style="height: 63px">
        </div>
        <ul id="mainnav">
            <li><a href="index">首页</a></li>
            <li><a href="#">商讯</a></li>
            <li><a href="#">个性化推荐</a></li>
            <li><a href="#">购物流程</a></li>
            <li><a href="#">在线客服</a></li>
            <li><a href="#">积分兑换</a></li>
            <li><a href="/adminSignin">管理入口</a></li>
            <li><a href="#">帮助</a></li>
        </ul>
    </div>
</div>
<div id="container">
    <!--left content-->
    <div id="intro">
        <div id="basket" style="height:auto">
            <c:choose>
                <c:when test="${empty kyUserName}">
                    <a id="hrefShoppingCart">目前您的购物篮是空的</a>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${empty kyCart}">
                            <a id="hrefShoppingCart">目前您的购物篮是空的</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/Cart/scan" id="A1">您目前共购买${kyCart}本书</a>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
            <!--tml.Partial("ShoppingCartInPartial") %>--></div>
        <div id="search">
            <form name="empform" method="get" id="empform" action="/Books/List">
                <input type="text" id="keyword" name="keyword" class="search_key" />
                <input type="submit" id="s_submit" class="search_sub" value="" />
            </form>
        </div>
        <div id="alltype">
            <h1 class="all_type black"><a href="#">查看所有分类&gt;&gt;</a></h1>
            <div id="subnav">
                    <c:choose>
                        <c:when test="${not empty categoriesList}">
                            <ul class="black">
                                <c:forEach items="${categoriesList}" var="var" varStatus="vs">
                                    <li><a href="/Books/List/?order=PublishDate&categoryId=${var.id}">${var.name}</a></li>
                                </c:forEach>
                            </ul>
                        </c:when>
                    </c:choose>
                <!--<tml.RenderAction("CategoryTree", "Home"); %>-->
            </div>
            <!--subnav end-->
        </div>
        <!--link start-->
        <div id="choice_pub">
            <select name="publishs" id="publishs" class="n_select">
                <option>&gt;&gt;根据出版社选择图书</option>
                <option>北京师范大学出版社</option>
                <option>电子工业出版社</option>
            </select>
            <select name="publishs" id="special" class="n_select">
                <option>>>根据专题选择图书</option>
                <option>国庆出游专题</option>
                <option>新年计划专题</option>
            </select>
        </div>
        <div id="s_b_03">
            <a href="#">赖世雄美语从头学</a><br />
            <a href="#">沪江团购 ，价格更优惠</a></div>
        <!--link end-->
    </div>

