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

    initTable();

    laydate.render({
        elem: '#dateSelect'
        , format: 'yyyy/MM/dd'
        , range: true
        , value
        ,done: function(value, date, endDate){
            let time = value.split(' - ');
            param.opTimeStart = time[0].replaceAll('/','-')
            param.opTimeEnd = time[1].replaceAll('/','-')
        }
    })

    form.on('select(opType)', function (data){
        console.log(data.value)
        param.opType = data.value;

        return false
    })

    $('#search').click(function (){
        param.linkAccount = $('#linkAccount').val();
        initTable();
    })

    function initTable() {
        table.render({
            elem: '#accountData'
            , height: 580
            , width: 1250
            , url: '../getTransfer.do' //数据接口
            , where: JSON.stringify(param)
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'userid', title: '账号'}
                , {field: 'amount', title: '操作金额'}
                , {field: 'opType', title: '操作类型'}
                , {field: 'linkAccount', title: '相关账号'}
                , {field: 'linkUsername', title: '相关用户名'}
                , {field: 'opBalance', title: '操作后余额'}
                , {field: 'operationTime', title: '本次操作时间'}
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