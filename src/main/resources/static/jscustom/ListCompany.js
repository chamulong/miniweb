/************
 * 江成军，客户列表展示模块
 ***********/
require(
    ['/jscustom/GlobleConfig.js'],
    function(){
        requirejs(
            ['jquery','bootstrap','bootstraptable','bootstraptableCN'],
            function($){
                //region start 具体业务代码

                //数据列表展示
                $('#tb_Company').bootstrapTable({
                    url: '/company/findAllSimplePage',         //请求后台的URL（*）
                    method: 'post',                      //请求方式（*）post/get
                    striped: true,                      //是否显示行间隔色
                    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                    pagination: true,                   //是否显示分页（*）
                    sortable: true,                     //是否启用排序
                    sortOrder: "asc",                   //排序方式
                    sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                    pageNumber:1,                       //初始化加载第一页，默认第一页
                    pageSize: 2,                       //每页的记录行数（*）
                    pageList: [2,5,10],                 //可供选择的每页的行数（*）
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
                            //页面动态查询条件
                            //realname:$("#realname").val(),
                            //operetetype:$("#operetetype").val(),
                            //operetedesc:$("#operetedesc").val()
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
                        title: '员工人数'
                    }, {
                        field: 'totalincome',
                        title: '总收入'

                    }, {
                        field: 'legalpersonmobil',
                        title: '法人'
                    }, {
                        field: 'businesslicense',
                        title: '印业执照'
                    },{
                        field: 'cbrief',
                        title: '企业简介'
                    }]
                });

                //region end 具体业务代码
            });
    });