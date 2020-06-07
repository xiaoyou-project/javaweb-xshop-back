layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        //发送post请求
        $.post("/api/v1/admin/changeUser",{
            userID:$("#id").val(),
            username:$("#username").val(),
            password:$("#password").val(),
            nickname:$("#nickname").val(),
            avatar:$("#avatar").val(),
            sign:$("#sign").val(),
            site:$("#site").val()
        },function (res) {
            if(res.code===1){
                top.layer.close(index);
                top.layer.msg("修改成功");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }else{
                layer.alert('修改失败', {icon: 2});
            }
        });
        return false;
    })
});