
layui.use(['layer','form','jquery','element'], function(){
    window.layer = layui.layer
        ,window.form = layui.form
        ,window.$ = layui.jquery,
        window.element = layui.element;



    form.on('submit(save)', data =>{
        debugger
        const user = data.field;
        save(user);
    });
});

/**
 * 保存数据
 */
function save(user) {

    $.ajax({
            type: 'post',
            url: '/user/save',
            data: user,
            success(res){
                if(res.success){
                    layer.msg(res.message, {icon: 6});

                    setTimeout(() =>{
                        // 干掉弹出层
                        const index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    },2000)
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

function openEdit(id) {

    const url = id ? `/user/toEdit?id=${id}` : '/user/toEdit';

    layer.open({
        type: 2,
        title: '编辑管理员',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['450px', '200px'],
        content: url,
        end() {
            // 页面重新加载
            window.location.reload();
        }
    });
}

function del(id) {
    layer.confirm('你确定要删除当前管理员嘛？', {icon: 3, title:'警告'}, (index) =>{

        // 发送ajax请求删除数据
        $.ajax({
            type: 'DELETE',
            url: '/user/delClassConstManager',
            data: {id},
            success(res){

                if(res.success){
                    layer.msg(res.message, {icon: 6});

                    setTimeout(() => {
                        location.reload();
                    },2000)
                }else{
                    layer.alert(res.message, {icon: 2});
                }

            },
            error(e){
                layer.alert("系统出错了，请联系管理员~~~", {icon: 2});
            }
        });

        layer.close(index);
    });
}


