

;(function ($) {
    $.fn.tips = function(options){
        var defaults = {
            side 	 : 1, 			 //1,2,3,4 分别代表【上右下左】  [1,4]代表位置在1~4随机 例如：[2,3]代表位置在2~3随机
            msg 	 : '你好！世界', //tips的文本内容
            color 	 : '#FFF', 		 //文字颜色，默认为白色
            bg 		 : '#F00', 		 //背景色，默认为红色
            maxWidth : "130", 		 //tips内容区域的最大宽度（px）
            scroll 	 : null, 		 // 该参数接受选择器，定义的选择器的dom在滚动时将重新计算input距离窗口边界的值。在一些特殊的弹出层比较有用
            time 	 : 2,			 //默认为2 自动关闭时间 单位为秒 0为不关闭 （点击提示也可以关闭）
            x 		 : 0, 			 // 默认为0 横向偏移 正数向右偏移 负数向左偏移
            y 		 : 0, 			 // 默认为0 纵向偏移 正数向下偏移 负数向上偏移
            appendto : document.body,//将tips追加到页面的哪里，dom对象选择器，默认追加到body最后
            tipsCount : "only"  	 //[only,each,more],only代表当前页面只能有一个tip，each代表每个input都能有一个tip，more代表当前页面不限制tip，新的添加 旧的也不关闭
        };

        if(! $(this)[0].getAttribute("tipNo")){  //判断 给tip和input添加序号
            var thisIndex 	 = Math.random();  //定义随机数，以便记录错误提示的位置（关闭时使用）
            $(this)[0].setAttribute("tipNo",thisIndex);
        };

        var options = $.extend(defaults, options);
        if (!options.msg||isNaN(options.side)) {  //自定义异常
            throw new Error('参数错误，请检查');
        };
        if(!$('#tips_box_style').length){ //页面中没有本函数追加的css时进行追加
            var style='<style id="tips_box_style" type="text/css">';
            style+='.jq_tips_box{padding:10px;position:absolute;overflow:hidden;display:inline;display:none;z-index:19920311;}';
            style+='.jq_tips_arrow{display:block;width:0px;height:0px;position:absolute;}';
            style+='.jq_tips_top{border-left:10px solid transparent;left:20px;bottom:0px;}';
            style+='.jq_tips_left{border-top:10px solid transparent;right:0px;top:18px;}';
            style+='.jq_tips_bottom{border-left:10px solid transparent;left:20px;top:0px;}';
            style+='.jq_tips_right{border-top:10px solid transparent;left:0px;top:18px;}';
            style+='.jq_tips_info{word-wrap: break-word;word-break:normal;border-radius:4px;padding:5px 8px;max-width:'+options.maxWidth+'px;overflow:hidden;box-shadow:1px 1px 1px #999;font-size:12px;cursor:pointer;}';
            style+='</style>';
            $(document.body).append(style); //将样式放入页面
        };


        function setTipsSide(tips,element_top,element_left,element_width,element_height){  //设置tips距离窗口左边以及上边的距离函数
            switch(options.side){
                case 1:
                    tips.css({
                        top:element_top-tips.outerHeight()+options.x,
                        left:element_left-10+options.y
                    });
                    break;
                case 2:
                    tips.css({
                        top:element_top-20+options.x,
                        left:element_left+element_width+options.y
                    });
                    break;
                case 3:
                    tips.css({
                        top:element_top+element_height+options.x,
                        left:element_left-10+options.y
                    });
                    break;
                case 4:
                    tips.css({
                        top:element_top-20+options.x,
                        left:element_left-tips.outerWidth()+options.y
                    });
                    break;
                default:
            };
        };

        //根据值取随机整数函数
        var rand = function(num, from, to) {
            return Math.round(Math.random() * (to - from)) + num + from;
        };

        //判断是否允许多个tip同时显示在页面上，不同意清除其他tip
        if(options.tipsCount=="only") {$(options.appendto).find(".jq_tips_box").remove();console.log("only");}
        else if(options.tipsCount=="each") {$(options.appendto).find("div[thistipNo='"+$(this).attr("tipNo")+"']").remove();};

        if (options.scroll!=null) {  //判断是否开启滚动监听
            var element = $(this);
            $(options.scroll).scroll(function() {
                var tips = $("div.jq_tips_box[thistipNo='"+element.attr("tipNo")+"']");
                if (tips.length) {
                    var element_top=element.offset().top,element_left=element.offset().left,element_height=element.outerHeight(),element_width=element.outerWidth();
                    setTipsSide(tips,element_top,element_left,element_width,element_height);
                };
            });
        };
        for(var i = 0;i<this.length;i++){
            var element=$(this[i]);
            //获取当前input的距离窗口顶部px,距离窗口左px,自身的高度px,与自身的宽度px。
            var element_top=element.offset().top,element_left=element.offset().left,element_height=element.outerHeight(),element_width=element.outerWidth();
            //运算当前tip的位置，side=0时随机取1~4的整数，side<0时取1，side>4时取4，其余情况取整数
            options.side=(typeof(options.side)=="object"?rand(0,options.side[0],options.side[1]):options.side<0?1:options.side>4?4:Math.round(options.side));
            //运算当前tip的位置，取样式名称。以便定义不同的css（不同的位置显示）
            var sideName=options.side==1?'top':options.side==2?'right':options.side==3?'bottom':options.side==4?'left':'top';
            var tips=$('<div class="jq_tips_box" thistipNo='+element.attr("tipNo")+'><i class="jq_tips_arrow jq_tips_'+sideName+'"></i><div class="jq_tips_info">'+options.msg+'</div></div>').appendTo($(options.appendto));
            tips.find('.jq_tips_arrow').css('border-'+sideName,'10px solid '+options.bg);
            tips.find('.jq_tips_info').css({
                color:options.color, 			//设置文字颜色
                backgroundColor:options.bg  	//设置背景颜色
            });

            setTipsSide(tips,element_top,element_left,element_width,element_height);//设置tipss的位置高度

            var closeTime;						//定义关闭时间（秒）
            tips.show().click(function(){ 		//tip快速淡入以及点击内容关闭
                clearTimeout(closeTime);
                tips.fadeOut('fast',function(){
                    tips.remove();
                });
            });
            if(options.time){
                closeTime=setTimeout(function(){ //设置计时关闭
                    tips.click();
                },options.time*1000);
                tips.hover(function(){ 			//设置鼠标放入延时
                    clearTimeout(closeTime);
                },function(){					//离开重新计时
                    closeTime=setTimeout(function(){
                        tips.click();
                    },options.time*1000);
                });
            };
        };
        //this.each(function(index){});
        return this;
    };
    //闭包结束
})(jQuery);