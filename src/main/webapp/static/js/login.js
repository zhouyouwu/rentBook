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
                    localStorage.setItem("role", res.result.role)
                } else {
                    layer.alert(res.message)
                }
            }
        })
        return false;
    });

});