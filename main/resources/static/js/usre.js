window.onload = function () {
    axios.default.baseURL = '';
    var table = new Vue({
        el: '#user_table',
        data: {
            user: '',
            user1: 'asdasd'
        },
        computed: {
            createcode() {
                var userData = '';
                $.ajax({
                    url: 'http://localhost:8080/donut/user/selectall',
                    dataType: 'json',
                    type: 'post',
                    data: {},
                    success: function (datas) {
                        userData = datas.data.list;
                        console.log(userData);
                    }
                })
                return userData;
            }
        },
        methods: {
            add() {
                alert(this.user)
            }
        }
    })
}