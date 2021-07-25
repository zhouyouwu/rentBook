layui.use(['form', 'layer', 'element'], function () {

    let element = layui.element,
        form = layui.form,
        layer = layui.layer;

    form.on('submit(login)', function (){
        let param = {};
        param.userid = $('#userid').val();
        param.password = $('#pwd').val();

        $.ajax({
            url: '../login.do',
            method: 'post',
            data: JSON.stringify(param),
            contentType: 'application/json',
            success: function (res) {
                if (res.code === 0) {
                    layer.msg("登录成功");
                    localStorage.setItem("token", res.result.token)
                    localStorage.setItem("userid", param.userid)
                    let index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                } else {
                    layer.alert(res.message)
                }
            }
        })
        return false;
    });

});