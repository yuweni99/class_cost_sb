
layui.use(['layer','form','jquery','element'], function(){
    window.layer = layui.layer
        ,window.form = layui.form
        ,window.$ = layui.jquery
        , window.element = layui.element;

    form.on('submit(save)', data =>{
        const classes = data.field;
        save(classes);
    });
});

/**
 * 保存数据
 */
function save(classes) {

    $.ajax({
            type: 'post',
            url: '/charging/save',
            data: classes,
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

    const url = id ? `/charging/toEdit?id=${id}` : '/charging/toEdit';

    layer.open({
        type: 2,
        title: '编辑班级信息',
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['450px', '400px'],
        content: url,
        end() {
            // 页面重新加载
            window.location.reload();
        }
    });
}

function payer(chargingId,status) {
    window.location.href = `/chargingHistory/findAll?chargingId=${chargingId}&status=${status}`
}
