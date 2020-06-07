layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : '/api/v1/admin/getAllUser',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'username', title: '用户名', minWidth:100, align:"center"},
            {field: 'nickname', title: '昵称', align:'center'},
            {field: 'avatar', title: '头像',height: 50, minWidth:200, align:'center',templet:function(d){
                return '<img class="my-avatar" src="'+d.avatar+'">';
            }},
            {field: 'sign', title: '个性签名', align:'center'},
            {field: 'site', title: '收货地址',  align:'center'},
            {field: 'lastLogin', title: '最后登录时间', align:'center',minWidth:150},
            {title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //添加用户
    function addUser(edit){
        console.log(edit)
        var index = layui.layer.open({
            title : "修改用户",
            type : 2,
            content : "userAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find("#username").val(edit.username);  //登录名
                    body.find("#password").val(edit.password);  //邮箱
                    body.find("#nickname").val(edit.nickname);  //性别
                    body.find("#avatar").val(edit.avatar);  //会员等级
                    body.find("#sign").val(edit.sign);    //用户状态
                    body.find("#site").val(edit.site);    //用户简介
                    body.find("#id").val(edit.id);    //用户简介
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        });

        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        layui.layer.open({
            title:"添加用户",
            type:2,
            content:"addUser.html",
            area:['500px','300px']
        })
    });

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].id);
            }
            let latId=data[data.length-1].id;
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示'}, function (index) {
               for (let i in newsId){
                   let id=data[i].id;
                   $.post("/api/v1/admin/deleteUser",{userID:id},function (resposne) {
                       if(resposne.code===1){
                           layer.msg("删除id:"+id+"成功");
                           //判断一下是否是最后一个
                           if(id===latId){
                               tableIns.reload();
                               layer.close(index);
                           }
                       }else{
                           layer.msg("删除失败!");
                       }
                   })
               }
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                $.post("/api/v1/admin/deleteUser",{userID:data.id},function (resposne) {
                    if(resposne.code===1){
                        layer.msg("删除成功");
                        tableIns.reload();
                        layer.close(index);
                    }else{
                        layer.msg("删除失败!");
                    }
                })
            });
        }
    });
});
