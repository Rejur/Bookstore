/**
 * Created by skywalker on 2016/8/13.
 */
$(function(){
    var i=0;
    var $img=$('.img');
    var img_widths;
    var img_size;
    /*复制第一张图，将其加至末尾，以实现无缝连播*/
    var clone=$('.img>li').first().clone();
    $img.append(clone);  /*clone为复制，此处将复制的内容添加至.img的DOM中*/

    img_widths=$(".banner").width();
    img_size=$('.img>li').size();
    $(".img img").css({"width":img_widths});
    $img.css({"width":img_size*img_widths});

    /*动态添加焦点圆*/
    for (var j=0;j<img_size-1;j++){
        $(".num").append("<li class='num_cir'></li>")
    }

    var img_width=img_widths;
    var $num_li=$('.num>li');
    $num_li.first().addClass("on");  /*给第一个焦点添加样式*/

    /*向右滑动*/
    $('.btn_right').click(function(){
        i++;
        move();
    });
    /*向左滑动*/
    $('.btn_left').click(function(){
        i--;
        move();
    });
    function move(){
        if (i==img_size){
            /*在拉至最后一张图后（此时的最后一张图是之前复制好加在最后的图1），再向右滑时用css快速的将展示的图片拉回第一张图*/
            $img.css({left:0});
            i=1;   /*设置滑动后停留在第二张图上*/
        }
        if (i==-1){
            /*第一张图跳至最后一张图，然后再滑向倒数第二张图*/
            $img.css({left:-img_width*(img_size-1)});
            i=img_size-2;
        }
        /*$img.css({opacity:0.6,left:-img_width*i});
        $img.stop().animate({opacity: 1},1500);*/
        $img.stop().animate({left:-img_width*i},900);   /*加上stop()可防止如：点了100次，每次的执行还未完成就又加上了多次执行，每次执行需要500ms的话，在停止点击后还会有十几秒的时间继续执行余下的执行指令，有了stop既可停止后续的冗余指令*/
        if (i==img_size-1){
            $num_li.eq(0).addClass("on").siblings().removeClass("on");
        }
        else {
            $num_li.eq(i).addClass("on").siblings().removeClass("on");   /*siblings()选中被jq所选元素的同胞元素（其自身除外，不被选中），在此处选中其余li元素并去除类on*/
        }
    }

    /*对下方焦点的操作*/
    $(".num>li").hover(function(){
        var index=$(this).index();/*获取这是第几个焦点,index方法返回指定元素相对于其他指定元素的index位置*/
        i=index;
        $img.stop().animate({left:-img_width*index},800);
        $(this).addClass("on").siblings().removeClass("on");
    })

    /*定时器操作*/
    var t=setInterval(function(){
        i++;
        move();
    },5500);

    $(".banner").hover(function(){
        clearInterval(t);   /*鼠标移入时停止定时器*/
    },function(){
        t=setInterval(function(){
            i++;
            move();
        },5500);   /*移出继续*/
    })


});
