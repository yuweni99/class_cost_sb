
layui.use(['layer','form','jquery','element'], function(){
    window.layer = layui.layer
        ,window.form = layui.form
        ,window.$ = layui.jquery,
        window.element = layui.element;

    form.on('submit(updatePwd)', data =>{

        updatePwd(data.field);
    });
});

/**
 * 保存数据
 */
function updatePwd(pwd) {

    if(pwd.newPwd != pwd.newPwd2){
        layer.alert("请重新输入密码，两次密码不一致~~", {icon: 2});
        return;
    }

    $.ajax({
            type: 'post',
            url: '/user/updatePwd',
            data: pwd,
            success(res){
                if(res.success){
                    layer.msg(res.message, {icon: 6});
                   setTimeout(() =>{
                       $("#pwdForm")[0].reset();
                   },1000)
                }else{
                    layer.alert(res.message, {icon: 2});
                }

            },
            error(e){
                console.log(e)
                layer.alert("系统出错了，请联系管理员~~~", {icon: 2});
            }
        })
}

/**
 * 激活
 * @param id
 */
function active(id) {
    $.ajax({
        type: 'get',
        url: '/user/active',
        data: {id},
        success(res){
            if(res.success){
                layer.msg(res.message, {icon: 6});
                setTimeout(() =>{
                    location.reload();
                },1000)
            }else{
                layer.alert(res.message, {icon: 2});
            }

        },
        error(e){
            console.log(e)
            layer.alert("系统出错了，请联系管理员~~~", {icon: 2});
        }
    })
}


/**
 * 重置密码
 * @param id
 */
function resetPwd(id) {
    $.ajax({
        type: 'get',
        url: '/user/resetPwd',
        data: {id},
        success(res){
            if(res.success){
                layer.msg(res.message, {icon: 6});
            }else{
                layer.alert(res.message, {icon: 2});
            }

        },
        error(e){
            console.log(e)
            layer.alert("系统出错了，请联系管理员~~~", {icon: 2});
        }
    })
}
