layui.use(['element', 'layer', 'util', 'form'], function () {

    let element = layui.element
        , layer = layui.layer
        , util = layui.util
        , form = layui.form;

    $('#selectAccount').click(function (){

        layer.open({
            type: 2,
            content: './component/account-list-selector.html',
            area: ['500px', '350px'],
            scrollbar: false,
            closeBtn: 0,
            title: '选择付款账号',
            success: function (layero, index){
                let body = layer.getChildFrame('body',index);
                body.find('#accountData').attr('lay-data', 'account')
            },
        })
    });

    $('#selectLinkAccount').click(function (){

        layer.open({
            type: 2,
            content: './component/account-list-selector.html',
            area: ['500px', '350px'],
            scrollbar: false,
            closeBtn: 0,
            title: '选择收款账号',
            success: function (layero, index){
                let body = layer.getChildFrame('body',index);
                body.find('#accountData').attr('lay-data', 'link')
            },
        })
    });

    form.on("submit(*)", function (data) {
        let balance = $('#accountBalance').val();
        let amount = $('#amount').val();
        let desc = $('#desc').val();
        if(balance < amount){
            layer.msg("余额不足，请修改转账金额");
            return false;
        }
        if(desc.length > 10){
            layer.msg("备注长度超过10");
            return false;
        }

        let transfer = {};
        transfer.userid = $('#userid').val();
        transfer.amount = amount;
        transfer.linkAccount = $('#linkAccount').val();
        transfer.opDesc = desc;
        $.ajax({
            url: '../transfer.do',
            method: 'post',
            data: JSON.stringify(transfer),
            contentType: 'application/json',
            success: function (res){
                if(res.code === 0){
                    layer.alert("转账成功");
                    $("#transferForm")[0].reset();
                }else {
                    layer.alert(res.message)
                }
            },
            error: function (res){
                layer.msg(res)
            }
        })
        return false;
    });

});