layui.use(['element', 'layer', 'table', 'laydate', 'form'], function () {

    let element = layui.element
        , layer = layui.layer
        , table = layui.table
        , laydate = layui.laydate
        , form = layui.form
        , param = {
        userid: localStorage.getItem("userid"),
        linkAccount: undefined,
        opTimeStart: undefined,
        opTimeEnd: undefined,
        opType: undefined
    };

    if (param.userid != null) {
        initTable()
        //getTableData();
    } else {
        layer.alert("当前没有登录任何用户")
    }


    laydate.render({
        elem: '#dateSelect'
        , format: 'yyyy/MM/dd'
        , range: true
        , done: function (value, date, endDate) {
            let time = value.split(' - ');
            param.opTimeStart = time[0].replaceAll('/', '-')
            param.opTimeEnd = time[1].replaceAll('/', '-')
        }
    })

    form.on('select(opType)', function (data) {
        console.log(data.value)
        param.opType = data.value;

        return false
    })

    $('#search').click(function () {
        param.linkAccount = $('#linkAccount').val();
        getTableData();
    })

    function getTableData() {
        console.log(JSON.stringify(param))
        $.ajax({
            url: '../getTransfer.do',
            method: 'post',
            data: JSON.stringify(param),
            contentType: 'application/json',
            headers: {token: localStorage.getItem("token")},
            success: function (res) {
                if (res.code === 0) {
                    initTable(res);
                } else {
                    layer.alert(res.message)
                }
            }
        })
    }

    function initTable(data) {
        table.render({
            elem: '#accountData'
            , height: 550
            , width: 1250
            , url: '../getTransfer.do' //数据接口
            , method: 'post'
            , where: {
                userid: param.userid,
                linkAccount: param.linkAccount,
                opTimeStart: param.opTimeStart,
                opTimeEnd: param.opTimeEnd,
                opType: param.opType
            }
            , headers: {token: localStorage.getItem("token")}
            , contentType: 'application/json'
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'userid', title: '账号'}
                , {field: 'amount', title: '操作金额'}
                , {
                    field: 'opType', title: '操作类型', templet: function (d) {
                        if (d.opType === 1) {
                            return '转出'
                        }
                        if (d.opType === 2) {
                            return '收到'
                        }
                    }
                }
                , {field: 'linkAccount', title: '相关账号'}
                , {field: 'linkUsername', title: '相关用户名'}
                , {field: 'opBalance', title: '操作后余额'}
                , {field: 'operationTime', title: '本次操作时间'}
                , {field: 'opDesc', title: '备注'}
            ]]
            , parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.result.total, //解析数据长度
                    "data": res.result.list //解析数据列表
                };
            }
        });
    }

});