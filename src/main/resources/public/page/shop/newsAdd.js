layui.use(['form','layer'],function(){
    var form = layui.form
    layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    form.on("submit(addUser)",function(data){
        //弹出loading
        var shopID = $("#inputValue").val();
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8}); // 点击提交后会出现一个显示页码
        if(shopID == "none"){ // 是添加商品
            console.log("添加商品信息", data, shopID);
            // 实际使用时的提交信息
            $.post("/api/v1/admin/addShop",{
                name: data.field.shopName,
                price: data.field.price,
                oldPrice: data.field.oldPrice,
                description: data.field.description,
                img: data.field.img,
                sort: data.field.sort,
                other: data.field.other
            },function(res){
                if(res.code == 1){
                    top.layer.close(index);
                    top.layer.msg("添加成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }else {
                    top.layer.close(index);
                    top.layer.msg("未知错误！");
                }
            })
        }else { // 是修改商品信息
            console.log("修改商品信息", data, shopID);
            $.post("/api/v1/admin/changeShop",{
                shopID: shopID,
                name: data.field.shopName,
                price: data.field.price,
                oldPrice: data.field.oldPrice,
                description: data.field.description,
                img: data.field.img,
                sort: data.field.sort,
                other: data.field.other
            },function(res){
                if(res.code == 1){
                    top.layer.close(index);
                    top.layer.msg("修改成功！");
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                }else {
                    top.layer.close(index);
                    top.layer.msg("未知错误！");
                }
            })
        }
        return false;
    })

})