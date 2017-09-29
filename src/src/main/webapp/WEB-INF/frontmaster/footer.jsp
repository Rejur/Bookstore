<%--
  Created by IntelliJ IDEA.
  User: arbitrary
  Date: 2017/9/8
  Time: 下午3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" isELIgnored="false" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<div id="footer">
    <!--contac us-->
    <div class="telephone">
        <strong>热线</strong> 021-61508168 <strong>传真</strong> 021-61508168-8020
        <br />
        <strong>Q Q</strong>375013071 13483528 562655482 1143735195（技术)<br />
        <strong>MSN</strong> hjservice@hotmail.com <strong>信箱</strong> shop@hjenglish.com<br />
        <strong>帮助</strong> <a href="/help/help.aspx" target="_blank">银行汇款帐户</a> <a href="/help/help.aspx#help_post"
                                                                                    target="_blank">邮局汇款地址</a> <a href="/help/help.aspx#help_ship" target="_blank">送货方式及费用</a>
        <a href="http://www.hjenglish.com/down/faq_2.htm" target="_blank">如何进行下载</a>
    </div>
    <!---->
    <div class="imp_link">
        <img src="resources/bookstore/Images/alipay.gif" alt="支付宝支付" /><img src="resources/bookstore/Images/online_pay.gif" alt="在线支付" /><br />
        <a href="http://www.hjenglish.com/about/aboutus.htm" target="_blank">网站介绍</a> <a
            href="http://www.hjenglish.com/about/partner.htm" target="_blank">合作伙伴</a> <a href="#"
                                                                                          target="_blank">网站地图</a> <a href="#" target="_blank">联系我们</a><br />
        <a href="#" target="_blank">增值电信业务经营许可证沪B2-20040503</a>
    </div>
</div>
<div id="child_site">
    <strong>分站</strong> <a href="#" target="_blank">沪江网</a> <a href="#" target="_blank">
    听说</a> <a href="#" target="_blank">口译</a> <a href="#" target="_blank">CET</a>
    <a href="#" target="_blank">考研</a> <a href="#" target="_blank">雅思</a> <a href="#"
                                                                             target="_blank">托福</a> <a href="#" target="_blank">日语</a> <a href="#" target="_blank">
    法语</a> <a href="#" target="_blank">下载</a> <a href="#" target="_blank">文库</a>
    <a href="#" target="_blank">部落</a> <a href="#" target="_blank">博客</a> <a href="#"
                                                                             target="_blank">词典</a> <a href="#" target="_blank">IT新闻</a> <a href="#" target="_blank">
    博客园</a> <a title="新世界日语" href="#" target="_blank">新世界日语</a> <a title="2010考研书籍推荐专题"
                                                                   href="#" target="_blank">2010考研书籍</a>
</div>
<a href="javascript:;" class="top">回到顶部</a>
<script  src="/js/common/jquery/jquery1.8.3.min.js"></script>
<script baseUrl="<%=basePath%>" src="<%=basePath%>/js/user.login.js"></script>
<script  baseUrl="<%=basePath%>" src="<%=basePath%>/js/common/layer/layer.js"></script>
