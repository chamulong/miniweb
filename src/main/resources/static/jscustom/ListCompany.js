/************
 * 江成军，客户列表展示模块
 ***********/
require(
    ['/jscustom/GlobleConfig.js'],
    function(){
        requirejs(
            ['jquery','bootstrap','bootstraptable','bootstraptableCN','layer'],
            function($){
                //region start 具体业务代码

                layer.config({
                    path:'/jslib/layer-v3.1.1/layer/'
                });

                //绑定列表中各按钮的事件
                window.operateEvents={
                    'click .detail':function(e,value,row,index){
                        alert("企业简介:"+row.cbrief);
                    },
                    'click .delete':function(e,value,row,index) {
                        alert("当前UUID:"+row.uuid);

                    },
                    'click .modify':function(e,value,row,index){
                        alert("当前行的下标:"+index);
                    }
                };

                //数据列表展示
                $('#tb_Company').bootstrapTable({
                    url: '/company/queryDynamic',         //请求后台的URL（*）
                    method: 'post',                      //请求方式（*）post/get
                    striped: true,                      //是否显示行间隔色
                    //toolbar: '#toolbar',                //工具按钮用哪个容器
                    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    pagination: true,                   //是否显示分页（*）
                    sortable: true,                     //是否启用排序
                    sortOrder: "asc",                   //排序方式
                    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                    pageNumber:1,                       //初始化加载第一页，默认第一页
                    pageSize: 10,                       //每页的记录行数（*）
                    pageList: [5,10,20],                 //可供选择的每页的行数（*）
                    search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                    strictSearch: true,                  //默认为false，设置为 true启用 全匹配搜索，否则为模糊搜索
                    showColumns: false,                  //是否显示所有的列
                    showRefresh: false,                  //是否显示刷新按钮
                    minimumCountColumns: 2,             //最少允许的列数
                    clickToSelect: true,                //是否启用点击选中行
                    //height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数设置表格高度
                    uniqueId: "cname",                     //每一行的唯一标识，一般为主键列
                    showToggle:false,                    //是否显示详细视图和列表视图的切换按钮
                    cardView: false,                    //是否显示详细视图
                    detailView: false,                   //是否显示父子表
                    queryParams : function (params) {
                        //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
                        var temp = {
                            size: params.limit,//页面大小
                            page: (params.offset / params.limit),//页码
                            //页面动态查询条件（公司名称和法人姓名）
                            cname:$("#cname").val(),
                            legalpersonname:$("#legalpersonname").val()
                        };
                        return temp;
                    },
                    columns: [{
                        title: '编号',
                        formatter:function(value,row,index)
                        {
                            var pageSize=$('#tb_Company').bootstrapTable('getOptions').pageSize;
                            var pageNumber=$('#tb_Company').bootstrapTable('getOptions').pageNumber;
                            return pageSize * (pageNumber - 1) + index + 1;
                        },
                        align:'center',
                        width:'50'
                    },{
                        field: 'uuid',
                        title: 'UUID'

                    },{
                        field: 'cname',
                        title: '名称'
                    }, {
                        field: 'caddress',
                        title: '地址'
                    }, {
                        field: 'curl',
                        title: '网址'
                    }, {
                        field: 'cemail',
                        title: '邮箱'
                    },{
                        field: 'cpersonnum',
                        title: '员工人数',
                        formatter: function indexFormatter(value, row, index){
                            var newvalue="";
                            if(value<=10){newvalue= '<span style="color:red;font-weight: bold">'+value+'</span>';}
                            else if(value>10&&value<=100) {newvalue= '<span style="color:blue;font-weight: bold">'+value+'</span>';}
                            else if(value>100) {newvalue= '<span style="color:green;font-weight: bold">'+value+'</span>';}
                            return newvalue;
                        }
                    }, {
                        field: 'totalincome',
                        title: '总收入'

                    },{
                        field: 'legalpersonname',
                        title: '法人姓名'
                    },  {
                        field: 'legalpersonmobil',
                        title: '法人手机'
                    }, {
                        field: 'businesslicense',
                        title: '印业执照'
                    },{
                        field: 'cbrief',
                        title: '企业简介'
                    },{
                        field:'',
                        title:'操 作',
                        width:'160',
                        events:operateEvents,
                        formatter:function (value, row, index){
                            var btnInfo='';
                                btnInfo+='<button style="margin-right: 10px;padding: 2px" type="button" class="detail btn btn-outline btn-info btn-sm">详 情</button>';
                                btnInfo+='<button style="margin-right: 10px;padding: 2px" type="button" class="delete btn btn-outline btn-danger btn-sm">删 除</button>';
                                btnInfo+='<button style="margin-right: 10px;padding: 2px" type="button" class="modify btn btn-outline btn-warning btn-sm">修 改</button>';
                            return btnInfo;
                        }
                    }]
                });

                //多条件查询刷新
                $("#btnSearch").click(function(){
                    $("#tb_Company").bootstrapTable('refresh');
                });

                //弹出新增客户窗口
                $("#btn_add").on('click',function(){
                    layer.open({
                        type: 2,
                        skin: 'layui-layer-molv',
                        title: '新增客户',
                        shadeClose: true,
                        shade: 0.2,
                        maxmin: false,
                        area: ['61%', '90%'],
                        content: '/company/addcompanyhtml',
                        end: function () {
                            $("#tb_Company").bootstrapTable('refresh');
                        }
                    });
                });

                //region end 具体业务代码
            });
    });