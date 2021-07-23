layui.use(['element', 'layer', 'table'], function () {

    const element = layui.element
        , layer = layui.layer
        , table = layui.table
        , $ = layui.$;

    initTable();

    function initTable() {
        table.render({
            elem: '#accountData'
            , height: 580
            , width: 1250
            , url: '../getUser.do' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'id', title: 'ID'}
                , {field: 'userid', title: '账号'}
                , {field: 'username', title: '用户名'}
                , {field: 'accountBalance', title: '余额'}
                , {field: '', title: '操作', templet: '#tableButton'}
            ]]
            , toolbar: '#toolBar'
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