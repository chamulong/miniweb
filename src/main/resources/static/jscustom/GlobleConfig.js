//RequireJS全局配置（包括css）
require.config({
    paths:{
        jquery:['/jslib/Hplus-v.4.1.0/js/jquery.min'],
        bootstrap:['/jslib/Hplus-v.4.1.0/js/bootstrap.min'],
        metismenu:['/jslib/Hplus-v.4.1.0/js/plugins/metisMenu/jquery.metisMenu'],
        slimscroll:['/jslib/Hplus-v.4.1.0/js/plugins/slimscroll/jquery.slimscroll.min'],
        hplus:['/jslib/Hplus-v.4.1.0/js/hplus'],
        contabs:['/jslib/Hplus-v.4.1.0/js/contabs'],
        pace:['/jslib/Hplus-v.4.1.0/js/plugins/pace/pace.min'],
        bootstraptable:['/jslib/Hplus-v.4.1.0/js/plugins/bootstrap-table/bootstrap-table.min'],
        bootstraptableCN:['/jslib/Hplus-v.4.1.0/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN'],
        layer:['/jslib/layer-v3.1.1/layer/layer'],
        jqueryform:['/jslib/Hplus-v.4.1.0/js/jquery.form.min'],
        jqueryupload:['/jslib/jQuery.upload/jQuery.upload.min'],
        ztree:['/jslib/zTree_v3/js/jquery.ztree.all'],
        validator:['/jslib/bootstrapvalidator.0.5.3/js/bootstrapValidator.min']
    },
    map:{
        '*':{
            css:['/jslib/require2.3.6/css.min.js']}
    },
    shim:{
        bootstrap:{
            deps:['jquery','css!/jslib/Hplus-v.4.1.0/css/bootstrap.min.css','css!/jslib/Hplus-v.4.1.0/css/font-awesome.min.css','css!/jslib/Hplus-v.4.1.0/css/animate.css','css!/jslib/Hplus-v.4.1.0/css/style.css']
        },
        metismenu:{
            deps:['jquery']
        },
        slimscroll:{
            deps:['jquery']
        },
        hplus:{
            deps:['jquery','bootstrap']
        },
        contabs:{
            deps:['jquery','bootstrap','hplus']
        },
        pace:{
            deps:['jquery','bootstrap','hplus']
        },
        bootstraptable:{
            deps:['jquery','bootstrap','css!/jslib/Hplus-v.4.1.0/css/plugins/bootstrap-table/bootstrap-table.min.css']
        },
        bootstraptableCN:{
            deps:['jquery','bootstrap','bootstraptable']
        },
        layer:{
            deps:['css!/lib/layer/skin/layer.css']
        },
        jqueryform:{
            deps:['jquery']
        },
        jqueryupload:{
            deps:['jquery','css!/jslib/jQuery.upload/upload.css']
        },
        ztree:{
            deps:['jquery','css!/jslib/zTree_v3/css/zTreeStyle/img/zTreeStyle.css']
        },
        validator:{
            deps:['jquery','bootstrap','css!/jslib/bootstrapvalidator.0.5.3/bootstrapValidator.css']
        }
    }
})