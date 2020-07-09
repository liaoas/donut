let bool = false;   // 禁用按钮
$(function () {
    /**
     * 注册
     */
    let signUpuserName;       // 用户名
    let signUpuserPass;       // 用户密码

    // 用户名输入框失去焦点事件
    $("#sign-up-name").blur(function () {
        // 获取输入框文本
        signUpuserName = $("#sign-up-name").val().trim();
        // 判断是否为空
        if (signUpuserName == '' || signUpuserName.length <= 6 || signUpuserName.length > 10) {
            bool = false;
            let parm = $(this);
            options.msg = "用户名不合法";
            show(parm);
        }else {
            bool = true;
        }
    });

    // 密码输入框失去焦点事件
    $("#sign-up-password").blur(function () {
        // 获取输入框文本
        signUpuserPass = $("#sign-up-password").val().trim();
        // 判断是否为空
        if (signUpuserPass == ''  || signUpuserPass.length <= 6 || signUpuserPass.length > 10) {
            bool = false;
            let parm = $(this);
            options.msg = "密码不合法";
            show(parm);
        }else {
            bool = true;
        }
    });
    // 注册单击事件
    $('.sign-up-button').click(function () {

        if (!bool) {
            let parm = $(this);
            options.msg = "注册数据校验失败，请重新填写";
            show(parm);
        }else {
            // 执行注册
            $.ajax({
                url:"http://localhost:8080/donut/user/registered",
                dataType:"json",
                type:"post",
                data:{
                    userAccount:$("#sign-up-name").val().trim(),
                    userPassword:$("#sign-up-password").val().trim()
                },
                success:function (data) {
                    console.log(data);
                    if (data.bool){
                        // 注册成功
                        spop({
                            template: "注册成功",
                            group: 'submit-satus',
                            style: 'success',
                            autoclose: true
                        });
                    }else {
                        spop({
                            template: data.message,
                            group: 'submit-satus',
                            style: 'error',
                            autoclose: true
                        });
                    }
                    if (data.map != null){
                        var map = data.map;
                        for(var key in map){
                            spop({
                                template: map[key],
                                group: 'submit-satus',
                                style: 'error',
                                autoclose: true
                            });
                        }
                    }
                }
            })
        }
    })

    /**
     * 登录
     */
    let signInUserEmail;    // 登录账号
    let signInUserPass;    // 登录账号

    $("#sign-in-email").blur(function () {
        // 获取输入框文本
        signInUserEmail = $("#sign-in-email").val().trim();
        // 邮箱验证
        if (signInUserEmail == '') {
            bool = false;
            let parm = $(this);
            options.msg = "邮箱为空";
            show(parm);
        } else {
            bool = true;
        }
    });

    // 密码输入框失去焦点事件
    $("#sign-in-password").blur(function () {
        // 获取输入框文本
        signInUserPass = $("#sign-in-password").val().trim();
        // 判断是否为空
        if (signInUserPass == '') {
            bool = false;
            let parm = $(this);
            options.msg = "密码为空";
            show(parm);
        }else {
            bool = true;
        }
    });

    // 登录单击事件
    $('.sign-in-submit').click(function () {
        if (!bool) {
            let parm = $(this);
            options.msg = "数据校验失败，请重新填写";
            show(parm);
        }else {
            // 执行登录
            $.ajax({
                url: 'http://localhost:8080/donut/user/login',
                dataType: 'json',
                type: 'post',
                data: {
                    userAccount:signInUserEmail,
                    userPassword:signInUserPass
                },
                success:function (data) {
                    if (data.bool){
                        // 登录成功，页面跳转
                        spop({
                            template: "欢迎您："+data.list[0].userAccount,
                            group: 'submit-satus',
                            style: 'success',
                            autoclose: 2000
                        });
                        if(data.list[0].userStatus != '1'){
                            spop({
                                template: "账号信息不存在",
                                group: 'submit-satus',
                                style: 'error',
                                autoclose: 2000
                            });
                            return;
                        }
                        if (data.list[0].userRoles == '管理员'){
                            setTimeout(function () {
                                window.location.href='http://localhost:8080/donut/html/user.html';
                            },3000);
                        }else {
                            setTimeout(function () {
                                window.location.href='http://localhost:8080/donut/html/home.html';
                            },3000);
                        }
                    }else {
                        // 密码错误
                        spop({
                            template: data.message,
                            group: 'submit-satus',
                            style: 'error',
                            autoclose: true
                        });
                    }
                }
            })
        }
    })
})


