layui.use(['element', 'layer', 'table'], function () {

    let element = layui.element
        , layer = layui.layer
        , table = layui.table
        , $ = layui.$
        , searchParam = '';

    initTable();

    table.on('tool(accountData)', function (obj) {
        switch (obj.event) {
            case 'del':
                console.log(obj.data.userid)
                delUser(obj.data.userid);
                break
            case 'mdf':
                initMdfView(obj.data.userid);
                break
            case 'useAcc':
                useAcc(obj);
                break

        }
    });

    $('#addBtn').click(function () {
        initAddView();
    });
    $('#searchBtn').click(function () {
        searchParam = $('#searchInput').val();
        initTable();
    })

    function useAcc(obj) {
        layer.open({
            title: '登录',
            type: 2,
            content: './login.html',
            area: ['500px', '350px'],
            scrollbar: false,
            closeBtn: 2,
            success: function (layero, index) {
                let body = layer.getChildFrame('body', index);
                let user = body.find('#userid');
                user.val(obj.data.userid);
            },
            end: function () {
                if (localStorage.getItem("userid") === obj.data.userid) {
                    window.parent.$('#setting').find('label').html(obj.data.username);
                    obj.tr.find('button')[2].css('display', 'none');
                    obj.tr.find('button')[3].css('display', '');
                }
            }
        })
    }

    function delUser(userid) {
        layer.open({
            title: '确定要删除账号？',
            type: 2,
            content: './component/delUser.html',
            area: ['500px', '350px'],
            scrollbar: false,
            closeBtn: 0,
            success: function (layero, index) {
                let body = layer.getChildFrame('body', index);
                let html = body.find('form').find('#title');
                html.attr('lay-data', userid);
                html.html('要删除用户' + userid + '，请输入密码或身份证号');
            },
            end: function () {
                initTable();
            }
        })
    }

    function initAddView() {

        layer.open({
            type: 2,
            content: './component/addUser.html',
            title: '添加用户',
            area: ['650px', '400px'],
            scrollbar: false,
            closeBtn: 0,
            end: function () {
                initTable();
            }
        })
    }

    function initMdfView(userid) {
        $.ajax({
            url: '../getUserById.do',
            method: 'get',
            data: {userid: userid},
            success: function (res) {
                if (res.code === 0) {
                    layer.open({
                        type: 2,
                        content: './component/updateUser.html',
                        title: '修改资料',
                        area: ['650px', '400px'],
                        scrollbar: false,
                        closeBtn: 0,
                        success: function (layero, index){

                            let body = layer.getChildFrame('body', index);
                            body.find('#username').val(res.result.username);
                            body.find('#phoneNo').val(res.result.phoneNo);
                            body.find('#idCardNo').val(res.result.idCardNo);
                            body.find('#registerTime').val(res.result.registerTime);
                        },
                        end: function () {
                            initTable();
                        }
                    })
                } else {
                    layer.alert(res.message)
                }
            },
            error: function (res) {
                layer.msg(res)
            }
        })
    }

    function initTable() {
        table.render({
            elem: '#accountData'
            , height: 580
            , width: 1250
            , url: '../getUser.do' //数据接口
            , method: 'get'
            , where: {searchParam: searchParam}
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'userid', title: '账号'}
                , {field: 'username', title: '用户名'}
                , {field: 'accountBalance', title: '余额'}
                , {field: '', title: '操作', templet: '#tableButton'}
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