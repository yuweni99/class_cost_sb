layui.use(['element','layer'],function(){
    var element = layui.element
        ,layer = layui.layer
        ,$ = layui.jquery;
    //隐藏tab主页关闭标签
    $(".layui-tab-title li:first-child i:last-child").css("display","none");
    //tab点击监控
    element.on('tab(pagetabs)',function(data){
        //tab切换时，左侧菜单对应选中
        var url = $(this).attr("lay-id");
        $(".layui-nav-tree li dd").each(function(i,e){
            if($(this).find("a").attr("lay-href")==url){
                $(this).attr("class","layui-this");
            }else{
                $(this).attr("class","");
            }
        })
    });
    //顶部左侧菜单监控
    element.on('nav(leftmenu)',function(elem){
        //隐藏显示侧边菜单
        if(elem[0].className=="hidetab"){//隐藏
            //侧边菜单伸缩
            $(".layui-side-menu").animate({width:$(".layui-side-menu").width()-144+"px"});
            //正文伸缩
            $(".layui-body").animate({left:$(".layui-body").position().left-144+"px"});
            //底部伸缩
            $(".layui-footer").animate({left:$(".layui-footer").position().left-144+"px"});
            $(this).attr("class","showtab");
            $(this).find("i").attr("class","layui-icon layui-icon-spread-left");
            //侧边菜单只显示图标
            $(".layui-nav-tree").find("li").each(function(em,ind){
                $(this).find("cite").css("display","none");
                $(this).find("dl").css("display","none");
            });
        }else if(elem[0].className=="showtab"){//显示
            $(".layui-side-menu").animate({width:$(".layui-side-menu").width()+144+"px"});
            $(".layui-body").animate({left:$(".layui-body").position().left+144+"px"});
            $(".layui-footer").animate({left:$(".layui-footer").position().left+144+"px"});
            $(this).attr("class","hidetab");
            $(this).find("i").attr("class","layui-icon layui-icon-shrink-right");
            $(".layui-nav-tree").find("li").each(function(em,ind){
                $(this).find("cite").css("display","");
                $(this).find("dl").css("display","");
            });
        }else{

        }
    });
    //顶部右侧菜单监控
    element.on('nav(rightmenu)',function(elem){
        var url = $(this).attr("lay-href");
        if(url!=undefined){
            layer.open({
                title:elem[0].innerText,
                type: 2,
                content:url,
                area: ['600px', '500px']
            });
        }
        if(elem[0].innerText=="锁屏"){
            layer.open({
                title:"已锁屏"
                ,content: '<input name="pass" class="layui-input" type="text" placeholder="请输入密码,默认123123" autocomplete="off"/>'
                ,btnAlign: 'c'
                ,anim: 1
                ,btn: ['解锁']
                ,yes: function(index, layero){
                    var pass = layero.find('.layui-layer-content input').val();
                    if(pass=="123123"){
                        layer.close(index);
                    }else{
                        layer.title("密码不正确！", index);
                    }
                }
                ,cancel: function(){
                    return false //开启该代码可禁止点击该按钮关闭
                }
            });
        }
    })
    //左侧垂直菜单监控
    element.on('nav(navtree)',function(elem){
        if($(".layui-side-menu").width()<200){
            $(".layui-side-menu").animate({width:$(".layui-side-menu").width()+144+"px"});
            $(".layui-body").animate({left:$(".layui-body").position().left+144+"px"});
            $(".layui-footer").animate({left:$(".layui-footer").position().left+144+"px"});
            $(".layui-layout-left li:first-child").find("a").attr("class","hidetab");
            $(".layui-layout-left li:first-child").find("i").attr("class","layui-icon layui-icon-shrink-right");
            $(".layui-nav-tree").find("li").each(function(em,ind){
                $(this).find("cite").css("display","");
                $(this).find("dl").css("display","");
            });
        }else{
            if($(this).attr("lay-href")!=undefined){
                var  flag = true;
                //url
                var url = $(this).attr("lay-href");
                //判断选项卡中是否存在已打开的页面，如果有直接切换到打开页面
                $(".layui-tab-title li").each(function(i,e){
                    if($(this).attr("lay-id")==url){
                        //切换选项卡
                        element.tabChange('pagetabs', url);
                        flag = false;
                    }
                })
                if(flag){
                    //新增选项卡
                    element.tabAdd('pagetabs', {
                        title: elem[0].innerText
                        ,content: '<iframe src="'+url+'" class="layui-admin-iframe" scrolling="no" frameborder="0" onload="setIframeHeight(this)"></iframe>'
                        ,id: url
                    });
                    //切换选项卡
                    element.tabChange('pagetabs', url);
                }
            }
        }
    });
});