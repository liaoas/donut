// 消息弹出属性
var options = {
    side: 2,      //1,2,3,4 分别代表 上右下左
    msg: 'hhhh',//tips的文本内容
    color: '#FFF',//文字颜色，默认为白色
    bg: '#585856',//背景色，默认为红色
    time: 0.5,      //默认为2 自动关闭时间 单位为秒 0为不关闭 （点击提示也可以关闭）
    x: 0,         // 默认为0 横向偏移 正数向右偏移 负数向左偏移
    y: 0          // 默认为0 纵向偏移 正数向下偏移 负数向上偏移
}

// 消息弹出方法
function show(parm) {
    parm.tips(options);
}

var messageOpts = {
    "closeButton": false,
    "debug": false,
    "positionClass": "toast-top-right",
    "onclick": null,
    "showDuration": "300",
    "hideDuration": "1000",
    "timeOut": "5000",
    "extendedTimeOut": "1000",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};
    //美化版彈窗
    $(function(){

        /*toastr.options = messageOpts;
        $('#showtoast').click(function() {
            //提示
            //调用方法1
            toastr.info('内容1');
            //调用方法2
            //toastr.info('内容2', '标题2');
            //调用方法3
            //toastr['info']('内容3', '标题3');
            //调用方法4
            //toastr.info('内容4', '标题4',messageOpts);
        });

        $('#showtoastsuccess').click(function() {
            //成功
            toastr.success('内容success', '标题success');
        });

        $('#showtoasterror').click(function() {
            //错误
            toastr.error('内容error', '标题error');
        });

        $('#showtoastwarning').click(function() {
            //警告
            toastr.warning('内容warning', '标题warning');
        });*/
    })

/**
 * 正则验证
 * @param regName
 * @returns {string}
 */
function returnRegString(regName) {
    if (regName == "email") {
        return "^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$"; //邮箱
    } else if (regName == "tel") {
        return "^(86)?(-)?(0[0-9]{2,3})?(-)?([0-9]{7,8})(-)?([0-9]{3,5})?$"; //电话
    } else if (regName == "phone") {
        return "^(13[0-9]|15[0-9]|18[0-9])([0-9]{8})$"; //手机
    } else if (regName == "postcode") {
        return "^([0-9]{6})$"; //邮编
    } else if (regName == "number") {
        return "^(0|([1-9]+[0-9]*))(.[0-9]+)?$"; //数字
    } else if (regName == "decimal") {
        return "^[0-9]+([.][0-9]+)?$"; //浮点
    } else if (regName == "money") {
        return "^([0-9])$"; //货币
    } else if (regName == "website") { //网址
        return "(http://|https://){0,1}[\w\/\.\?\&\=]+";
    } else if (regName == "fax") { //传真
        return "^[+]{0,1}([0-9]){1,3}[ ]?([-]?(([0-9])|[ ]){1,12})+$";
    } else if (regName == "int") { //整数
        return "^(-){0,1}\d+$";
    } else if (regName == "pInt") { //正整数
        return "^\d+$";
    } else if (regName == "nInt") { //负整数
        return "^-\d+$";
    } else if (regName == "nandl") { //数字与字母
        return "[a-zA-Z0-9]";
    } else if (regName == "chinese") { //是否含有中文字符
        return "[\u4e00-\u9fa5]";
    }
}