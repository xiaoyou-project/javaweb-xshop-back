layui.use(['form','layer','laydate','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;

    //新闻列表
    var tableIns = table.render({
        elem: '#newsList',
        url : '/api/v1/admin/getAllShop',
        page : true,
        limit : 5,
        limits : [5,10,15,20,25],
        id : "newsListTable",
        cols : [
            [
            {field: "id", title: '商品ID', width: 150},
            {field: 'name', title: '商品名字'},
            {field: 'price', title: '商品价格', width: 150},
            {field: 'oldPrice', title: '商品原价', width: 150},
            {field: 'description', title: '商品描述' },
            {field: 'img', title: '商品图片', templet: function(img) {
                return '<img src=' + img.img.split("&&")[0] +' />';
            }, width: 150},
            {field: 'sort', title: '商品类别', width: 150},
            {field: 'other', title: '其他附加的内容'},
            {fixed: 'right', title: '操作', toolbar: '#newsListBar'}
        ]
        ]
    });

    function addShop(){ //添加商品
        var index = layui.layer.open({
            title : "添加商品",
            type : 2,
            content : "newsAdd.html",
            success : function(){
                console.log("添加商品成功");
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addShop").click(function(){
        addShop();
    })


    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            editShop(data);
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此商品？',{icon:3, title:'提示信息'},function(index){
                $.post("/api/v1/admin/deleteShop",{
                    shopID : data.id  //将需要删除的newsId作为参数传入
                },function(data){
                    if(data.code != 1){
                        layer.msg('未知错误');
                    }else {
                        tableIns.reload();
                        layer.close(index);
                    }
                })
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });
    function editShop(data){ // 修改商品信息
        var index = layui.layer.open({
            title : "修改商品信息",
            type : 2,
            content : "newsAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                // 给每一个框都设置值
                body.find("#inputValue").val(data.id);
                body.find(".shopName").val(data.name);
                body.find(".price").val(data.price);
                body.find(".oldPrice").val(data.oldPrice);
                body.find(".description").val(data.description);
                body.find(".img").val(data.img);
                body.find(".sort").val(data.sort);
                body.find(".other").val(data.other);
                console.log("修改商品信息成功", data);
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

})
