$(function () {
    // 页面加载，获取Session：
    userId = '';
    var donutImg = new Array();
    // 获取产品
    $.ajax({
        type: "post",
        url: 'http://localhost:8080/donut/product/select',
        data: {},
        success: function (res) {
            var prouct = res.data.list;
            // 遍历商品图片
            for (let i = 0; i < prouct.length; i++) {
                donutImg[i] = prouct[i].dImgs[0].img;
            }
            console.log(donutImg);
            $("#prouctIMg00").attr("src", "http://localhost:8080/donut/uploads/" + donutImg[0]);
            $("#prouctIMg000").attr("href", "http://localhost:8080/donut/uploads/" + donutImg[0]);
            $("#prouctIMg01").attr("src", "http://localhost:8080/donut/uploads/" + donutImg[1]);
            $("#prouctIMg001").attr("href", "http://localhost:8080/donut/uploads/" + donutImg[1]);
            $("#prouctIMg02").attr("src", "http://localhost:8080/donut/uploads/" + donutImg[2]);
            $("#prouctIMg002").attr("href", "http://localhost:8080/donut/uploads/" + donutImg[2]);
            $("#prouctIMg03").attr("src", "http://localhost:8080/donut/uploads/" + donutImg[3]);
            $("#prouctIMg003").attr("href", "http://localhost:8080/donut/uploads/" + donutImg[3]);
            $("#prouctIMg04").attr("src", "http://localhost:8080/donut/uploads/" + donutImg[4]);
            $("#prouctIMg004").attr("href", "http://localhost:8080/donut/uploads/" + donutImg[4]);
        }
    });
    // 获取Session
    // 获取当前登录用户
    $.ajax({
        type: "post",
        url: 'http://localhost:8080/donut/user/obtainloginuser',
        data: {},
        success: function (res) {
            if (res.bool) {
                userId = res.list[0].id;
            }
        }
    });

    // 提交按钮

    $("#submitMessage").click(function () {
        //获取用户ID
        var bbsUserid = userId;
        if (bbsUserid == '' || bbsUserid == null) {
            spop({
                template: '当前用户未登录',
                group: 'submit-satus',
                style: 'error',
                autoclose: 2000
            });

            setTimeout(function () {
                // 页面跳转
                window.location.href = '../login.html';
            }, 3000)
        } else {
            $.ajax({
                type: "post",
                url: 'http://localhost:8080/donut/bbs/add',
                data: {
                    'bbsUserid': bbsUserid,
                    'bbsContent': $("#leaveAMessage").val()
                },
                success: function (res) {
                    if (res.bool) {
                        spop({
                            template: '添加成功',
                            group: 'submit-satus',
                            style: 'error',
                            autoclose: 2000
                        });
                    }
                }
            });
        }

    });

    $(".fa-user").click(function () {
        //获取用户ID
        var bbsUserid = userId;
        if (bbsUserid == '' || bbsUserid == null) {
            spop({
                template: '当前用户未登录',
                group: 'submit-satus',
                style: 'error',
                autoclose: 2000
            });

            setTimeout(function () {
                // 页面跳转
                window.location.href = '../login.html';
            }, 3000)
        } else {
            // 页面跳转 到个人中心
            window.location.href = 'http://localhost:8080/donut/html/userBulletin.html';
        }
    })

})
