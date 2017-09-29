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
    <link href="resources/bookstore/css/Index.css" rel="stylesheet" type="text/css">
    <link href="resources/bookstore/css/global.css" rel="stylesheet" type="text/css">
    <link href="resources/bookstore/css/member.css" rel="stylesheet" type="text/css">
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
<script>

</script>
<jsp:include page="../../frontmaster/head.jsp"></jsp:include>
<div id="action_area">

    <h2 class="action_type backet">
        <p class="imp_link">
            全场运费一律2元 <a href="#"><strong>简易计算器</strong></a><br />
        <p class="mark">
            确认商品价格与交易条件
        </p>
    </h2>
    <div>

        <table class="data_table" cellspacing="3" cellpadding="0" rules="all" border="0" id="ctl00_cphContent_gvCart" style="border-width: 0px; width: 96%;">
            <tr>
                <th scope="col">图示</th>
                <th scope="col">书名</th>
                <th scope="col">数量</th>
                <th scope="col">单价</th>

                <th scope="col">&nbsp;</th>
            </tr>


            <c:choose>
                <c:when test="${not empty cartList}">
                    <c:forEach items="${cartList}" var="var" varStatus="vs">

                        <c:choose>
                            <c:when test="${vs.index} % 2 == 0">
                                <tr style="background-color: #FEF7DB">
                            </c:when>
                            <c:otherwise>
                                <tr style="background-color: #E2E2E2">
                            </c:otherwise>
                        </c:choose>

                        <td>
                            <img src="/resources/bookstore/Images/BookCovers/${var.isbn}jpg" id="imgbook" width="49" height="56" />
                        </td>
                        <td>


                            <span id="BookName">${var.title}</span>
                        </td>
                        <td>
                            <input type="hidden" name="item.Book.Id" value="${var.title}" />
                            <input type="text" name="item.Quantity" value="${var.quantity}"  onchange="this.form.submit()"/>
                            <br />
                        </td>
                        <td>
                            <span id="UnitPrice">${var.unitprice}</span>
                        </td>

                        <td style="width: 50px;">

                            <a onclick="return confirm('确定删除吗?')" id="btnDelete" href="/Cart/delete?bookid=${var.bookid}">删除</a>

                        </td>
                    </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
        </table>
    </div>
    <br />
    <table width="96%">
        <tr>
            <td width="68%" align="right">
                <a href="index">继续挑选商品</a>&nbsp;&nbsp;&nbsp;&nbsp; 商品金额总计：￥<em><strong>${sumValue}</strong></em>

            </td>
            <td align="center">
                <button type="button" name="CheckOut" value="" id="login" class="basker_ok" />
            </td>
        </tr>
    </table>

</div>
</div>
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
        //回车事件绑定
        document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==13){
                $('#login').click();
            }
        };

        //登录操作
        $('#login').click(function(){
            var load = layer.load();
            $.ajax({
                url:"${basePath}/Order/addOrder.shtml",
                type:"post",
                dataType:"json",
                beforeSend:function(){
                    layer.msg('开始下单，请注意后台控制台。');
                },
                success:function(result){
                    layer.close(load);
                    if(result && result.status != 200){
                        layer.msg(result.message,function(){});
                        $('.password').val('');
                        return;
                    }else{
                        layer.msg('购买成功！');
                        setTimeout(function(){
                            //登录返回
                            window.location.href= "index";
                        },1000)
                    }
                },
                error:function(e){
                    console.log(e,e.message);
                    layer.msg('请看后台Java控制台，是否报错，确定已经配置数据库和Redis',new Function());
                }
            });
        });
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