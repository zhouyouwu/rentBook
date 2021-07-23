layui.use(['element', 'layer', 'util'], function () {

    const element = layui.element
        , layer = layui.layer
        , util = layui.util
        , $ = layui.$;

    setInterval("document.getElementById('datetime').innerHTML = new Date().toLocaleString();", 1000);

    element.on('nav(aside)', function (data) {
        let main = $("#content");
        switch (data.attr("lay-data")) {
            case 'account':
                main.attr('src', './view/account-list.html');break
            case 'transfer':
                main.attr('src', './view/transfer.html');break
            case 'statement':
                main.attr('src', './view/statement.html');break
        }
    })
});