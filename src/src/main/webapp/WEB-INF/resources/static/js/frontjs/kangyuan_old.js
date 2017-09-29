/**
 * Created by skywalker on 2016/11/30.
 */
var i=0;
var j=1;
var degs=0;
$(function() {
    /*定时器操作*/
    var t=setInterval(function(){
        if (i < 6) {
            move_img(i);
        }
        else {
            i = 0;
            degs = degs + 360;
            move_img(i);
        }
        i++;
    },3000);

    $(".big_box").hover(function(){
        clearInterval(t);   /*鼠标移入时停止定时器*/
    },function(){
        t=setInterval(function(){
            if (i < 6) {
                move_img(i);
            }
            else {
                i = 0;
                degs = degs + 360;
                move_img(i);
            }
            i++;
        },3000);   /*移出继续*/
    });
    function move_img(i) {
        if (i == 0) {
            document.getElementsByClassName("big_box")[0].style.transform = "rotateY(" + (degs + 61) + "deg) rotateX(-1deg)";
            $(".color5").css({
                "opacity": "1"
            }).siblings().css({"z-index":"0","opacity": "0.5", "transition": "all 1s ease"})
        }
        if (i == 1) {
            document.getElementsByClassName("big_box")[0].style.transform = "rotateY(" + (degs + 121) + "deg) rotateX(1deg)";
            $(".color4").css({
                "opacity": "1"
            }).siblings().css({"z-index":"0","opacity": "0.5", "transition": "all 1s ease"})
        }
        if (i == 2) {
            document.getElementsByClassName("big_box")[0].style.transform = "rotateY(" + (degs + 181) + "deg) rotateX(-1deg)";
            $(".color3").css({
                "opacity": "1"
            }).siblings().css({"z-index":"0","opacity": "0.5", "transition": "all 1s ease"});

        }
        if (i == 3) {
            document.getElementsByClassName("big_box")[0].style.transform = "rotateY(" + (degs + 241) + "deg) rotateX(-1deg)";
            $(".color2").css({
                "opacity": "1"
            }).siblings().css({"z-index":"0","opacity": "0.5", "transition": "all 1s ease"})
        }
        if (i == 4) {
            document.getElementsByClassName("big_box")[0].style.transform = "rotateY(" + (degs + 301) + "deg) rotateX(-1deg)";
            $(".color1").css({
                "opacity": "1"
            }).siblings().css({"z-index":"0","opacity": "0.5", "transition": "all 1s ease"})
        }
        if (i == 5) {
            document.getElementsByClassName("big_box")[0].style.transform = "rotateY(" + (degs + 361) + "deg) rotateX(1deg)";
            $(".color6").css({
                "opacity": "1"
            }).siblings().css({"z-index":"0","opacity": "0.5", "transition": "all 1s ease"})
        }
    }
    /*导航条显示*/
    var top1=$(window).scrollTop();
    $(window).scroll(function(){   /*滚动事件*/
        var top2=$(this).scrollTop();
        if (top2>top1){
            $(".nav").removeClass("on")
        }
        else {
            $(".nav").addClass("on")
        }
        top1=top2;  /*使top1动态更改，保持向上滑动时可检测出来*/
        if(top2>360){
            $(".nav").addClass("navbag")
        }else {
            $(".nav").removeClass("navbag")
        }
    });

    //添加收藏
    $(".favorites").click(function (){
        var ctrl = (navigator.userAgent.toLowerCase()).indexOf('mac') != -1 ? 'Command/Cmd' : 'CTRL';
        if (document.all)
        {
            window.external.addFavorite(window.location, document.title);
            layer.msg('收藏成功');
        }
        else if (window.sidebar)
        {
            window.sidebar.addPanel(document.title,window.location,'');
            layer.msg('收藏成功');
        }
        else
        {//添加收藏的快捷键
            layer.msg('嗷呜~失败了....您可以尝试通过快捷键' + ctrl + ' + D 加入到收藏夹~');
        }
    }) ;


//            回到顶部
    $(window).scroll(function(){   /*滚动事件*/
        var sc_top=$(this).scrollTop();
        if (sc_top>200){
            $('.top').fadeIn(300)
        }
        else {
            $('.top').fadeOut(300)
        }
    })

    $('.top').click(function(){
        $("body,html").stop().animate({scrollTop:0},300)     /*-webkit-内核可对body进行操作,火狐及IE对html进行操作*/
    })

    $(".addHomePage").click(function(){
        layer.msg('这么想了解我？', {
            time: 0 //不自动关闭
            ,btn: ['必须啊', '撩一下~']
            ,yes: function(index){
                layer.close(index);
                layer.msg('雅蠛蝶 O.o', {
                    icon: 6
                    ,btn: ['嗷','嗷','嗷']
                });
            }
        });
    })





    /*右上角动画*/
    $(".devices").hover(function () {
        $(".box1").css({"animation":"mymove_text 2s infinite"})
    },function () {
        $(".box1").css({"animation":"none"});
    });
    $(".icon").click(function () {
        layer.msg('玩命卖萌中', function(){
        });
    });
    $(".icnoss i").click(function () {
        layer.msg('喵呜~<br>功能待开发中，敬请期待哦~', {
            time: 20000, //20s后自动关闭
            btn: ['朕知道了', '撩一下~', '喵呜']
        });
    });
    $(".devices").click(function () {
        layer.confirm('这么急着想换设备？', {
            btn: ['是哒','手滑~'] //按钮
        }, function(){
            layer.msg('壕我们做朋友吧（抱大腿）', {icon: 6});
        }, function(){
            layer.msg('我不信，哼~', {
                time: 20000, //20s后自动关闭
                btn: ['嘿嘿', '傲呜~']
            });
        });
    });


    /*定时器操作2*/
    var t1=setInterval(function(){
        $(".i_box i").eq(j).addClass("choose_i").siblings().removeClass("choose_i");
        if (j==1){
            mac_moveout();
            icno_movein();
        }
        if (j==2){
            icno_moveout();
            prin_movein();
        }
        if (j==3){
            mac_movein();
            prin_moveout();
            $(".i_box i").eq(0).addClass("choose_i").siblings().removeClass("choose_i");
            j=0;
        }
        j++;
    },7000);

    $(".changbox").hover(function(){
        clearInterval(t1);   /*鼠标移入时停止定时器*/
    },function(){
        t1=setInterval(function(){
            $(".i_box i").eq(j).addClass("choose_i").siblings().removeClass("choose_i");
            if (j==1){
                mac_moveout();
                icno_movein();
            }
            if (j==2){
                icno_moveout();
                prin_movein();
            }
            if (j==3){
                prin_moveout();
                mac_movein();
                $(".i_box i").eq(0).addClass("choose_i").siblings().removeClass("choose_i");
                j=0;
            }
            j++;
        },7000);   /*移出继续*/
    });



    /*控制动画出入*/
    function mac_moveout() {
        $(".devices").animate({"right":"-600px"},1000);
        $(".box1").animate({"left":"-700px"},1000);
    }
    function mac_movein() {
        $(".devices").animate({"right":"-33px"},1000);
        $(".box1").animate({"left":"-86px"},1000);
    }
    function icno_moveout() {
        $(".wrapper").animate({"top":"-370px"},1000);
        $(".box2").animate({"top":"200px"},1000);
    }
    function icno_movein() {
        $(".wrapper").animate({"top":"-10px"},1000);
        $(".box2").animate({"top":"20px"},1000);
        $(".box2 .mess_box").css({"animation":"textout 2s normal,mymove_mess_box 2s normal"});
        $(".icnoss i").css({"animation":"textout 2s normal,mymove_i 2s normal"});
    }
    function prin_moveout() {
        $(".printerWrapper").fadeOut(1000);
        $(".box3").fadeOut(1600);
    }
    function prin_movein() {
        $(".printerWrapper").fadeIn(800);
        $(".box3").fadeIn(1600);
    }

    /*小块切换动画*/
    $(".i_box i").click(function () {
        var num=$(this).index();
        $(this).addClass("choose_i").siblings().removeClass("choose_i");
        if (j==1){
            mac_moveout();
        }
        if(j==2){
            icno_moveout();
        }
        if (j==3){
            prin_moveout();
        }
        if (num==0){
            mac_movein()
        }
        if(num==1){
            icno_movein();
        }
        if (num==2){
            prin_movein();
        }
        j=num+1;
    });

});