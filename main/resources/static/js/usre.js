window.onload = function () {
    // 基准地址
    axios.defaults.baseURL = 'http://localhost:8080/donut/';
    axios.inter
    var table = new Vue({
        el: '#user_table',
        data: {
            user: '',
            user1: 'asdasd'
        },
        methods:{
            queryDate :async function () {
                var ret = await axios.post('user/selectall');
                this.user = ret.data.list;
            },
            popup:function () {
                // 弹出框事件
                $('#item').popup({
                    time: 1000,
                    classAnimateShow: 'flipInX',
                    classAnimateHide: 'hinge',
                    onPopupClose: function e() {
                        // console.log('0')
                    },
                    onPopupInit: function e() {
                        // console.log('1')
                    }
                });
            }
        },
        computed: {
        },
        filters:{
            date(time){
                let date   = new Date(time)//把定义的时间赋值进来进行下面的转换
                let year   = date.getFullYear();
                let month  = date.getMonth()+1;
                let day    = date.getDate();
                let hour   = date.getHours();
                let minute = date.getMinutes();
                let second = date.getSeconds();
                return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
            }
        },
        mounted:function () {
            // axios.post("user/selectall").then( (res) => {
            //     this.user = res.data.list;
            //     console.log("===================")
            //     console.log(that.user);
            // })

            this.queryDate();
        }
    })
}