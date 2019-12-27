
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
 * 缴费
 * @param id
 */
function payment(id) {
        // 发送ajax请求删除数据
       /* $.ajax({
            type: 'get',
            url: '/chargingHistory/payment',
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
        });*/

        // layer.close(index);
    window.location.href = "/pay/aliPayPayment?id=" + id;
}