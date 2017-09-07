/**
 * Created by skywalker on 2016/11/30.
 */
$(function() {

    /*判断填写内容是否为空*/
    actions();
    function actions(){
        $(".btn_sub").click(function(){
            if_null();
        })
    }
    var phoneif = true;
    var emailif = true;
    function if_null() {
        var text = $("#text_box").val();
        var name = $("#name").val();
        var phone = $("#phone").val();
        var email = $("#email").val();
        if (email != "") {
            var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            isok= reg.test(email);
            if (!isok) {
                emailif=false;
            }
        }
        if (phone != ""){
            phoneif = !isNaN(phone);
        }
        if (text == "" && name == "" && phone == "" && email == "") {
            promptbox("warning","没有填入任何信息!");
            $("#text_box,#name,#phone,#email").css({"border":"solid 1px #ff5566"});
        }
        else if (text == "") {
            promptbox("warning","留言栏为空!");
            $("#text_box").css({"border":"solid 1px #ff5566"});
        }
        else if (name == "") {
            promptbox("warning","姓名为空!");
            $("#name").css({"border":"solid 1px #ff5566"});
        }
        else if (phone == "") {
            promptbox("warning","电话为空!");
            $("#phone").css({border:"solid 1px #ff5566"});
        }
        else if (email == "") {
            promptbox("warning","邮箱为空!");
            $("#email").css({"border":"solid 1px #ff5566"});
        }
        else if(phoneif==false){
            promptbox("warning","电话格式不正确!");
            $("#phone").css({"border":"solid 1px #ff5566"});
        }
        else if(emailif==false){
            promptbox("warning","邮箱格式不正确!");
            $("#email").css({"border":"solid 1px #ff5566"});
        }
        else {
            $("#text_box").val("");
            $("#name").val("");
            $("#phone").val("");
            $("#email").val("");
        }
    }

    /*恢复空框焦点*/
    ex();
    function ex(){
        $("#text_box").focus(function (){
            $("#text_box").css({"border":"solid 1px #b7b7b7"});
        });
        $("#name").focus(function (){
            $("#name").css({"border":"solid 1px #b7b7b7"});
        });
        $("#phone").focus(function (){
            $("#phone").css({"border":"solid 1px #b7b7b7"});
        });
        $("#email").focus(function (){
            $("#email").css({"border":"solid 1px #b7b7b7"});
            emailif=true;
        });
    }

    var ibox=0;
    function promptbox(text1,text2){
        var ale=$('.alert');
        console.log(ibox)
        if (ibox<3){
            if (ale.length>2){
                ale.remove();
            }
            if (ale.css("display")=="none") {
                ale.remove();
            }
             $('body').append("<div class='alert alert-"+text1+" alert-dismissible fade in bootboxs' role='alert'><button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>×</span><span class='sr-only'>Close</span></button><span>"+text2+"</span></div>")
            $('.alert').fadeOut(4000);
            $('.alert').eq(1).css("top","135px");
            $('.alert').eq(2).css("top","195px");
            ibox++;
        }else {
            ibox=0;
        }
        setTimeout("ibox=0",6000);
    }

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



});