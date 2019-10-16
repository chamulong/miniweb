/************
 * 添加客户信息
 ***********/
require(
    ['/js/GlobleConfig.js'],
    function(){
        requirejs(
            ['jquery','bootstrap','jqueryform','layer','validator'],
            function($){
                //*****自定义功能块 region*****

                //利用bootstrapvalidator进行表单验证
                $(document).ready(function(){
                    $('#FormCompany')
                        .bootstrapValidator({
                            feedbackIcons: {
                                valid: 'glyphicon glyphicon-ok',
                                invalid: 'glyphicon glyphicon-remove',
                                validating: 'glyphicon glyphicon-refresh'
                            },
                            fields: {
                                legalpersonname: {
                                    validators: {
                                        notEmpty: {message: '姓名不能为空'},
                                        stringLength: {
                                            min: 2,
                                            max: 5,
                                            message: '姓名长度在2-5个汉字范围内'
                                        }
                                    }
                                },
                                legalpersonmobil: {
                                    validators: {
                                        notEmpty: {message: '手机号不能为空'},
                                        regexp: {
                                            regexp: /^[1][3,4,5,7,8][0-9]{9}$/,
                                            message: '手机号码格式错误'
                                        }
                                    }
                                },
                                cemail: {
                                    validators: {
                                        notEmpty: {message: '邮箱不能为空'},
                                        emailAddress: {message: '邮箱格式不正确'}
                                    }
                                },
                                totalincome:{
                                    notEmpty: {message: '营业总收入不能为空'},
                                    number: {message: '格式错误，只能是数字、包括小数点'}
                                },
                                curl:{
                                    notEmpty: {message: '网址不能为空'},
                                    uri: {message: '网址格式错误'}
                                }
                            }
                        })
                });

                //异步提交表单
                $("#btn_Save").click(function() {
                        var bootstrapValidator = $('#FormCompany').data('bootstrapValidator');//获取表单对象
                        bootstrapValidator.validate();//手动触发验证
                        if (bootstrapValidator.isValid())//全部验证通过，才能提交表单
                        {
                            var options = {
                                complete: function (data) {
                                    alert("客户添加成功")
                                },
                                url: '/company/save',
                                dataType: 'json',
                                resetForm: true,  // 成功提交后，重置所有的表单元素的值
                                timeout: 5000
                            };
                            $('#FormCompany').ajaxSubmit(options);
                        }
                    }
                );


                //*****自定义功能块 EndRegion*****
            });
    });