$().ready(function() {
	validateRule();
    $('#timedate').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    });
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/manage/salesReport/save",
		data : $('#signupForm').serialize(), // 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("网络超时");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        ignore:":hidden:not(select)",
        rules : {
            timedate : {
                required : true
            },
            customerName : {
                required : true
            },
            teaName : {
                required : true
            },
            color : {
                required : true
            },
            setCount : {
                digits : true
            },
            saleCount : {
                digits : true
            },
            totalCount : {
                digits : true
            },
            price : {
                digits : true
            },
            totalPrice : {
                digits : true
            }
        },
        messages : {
            timedate : {
                required : icon + "请选择日期"
            },
            customerName : {
                required : icon + "请输入客户名称"
            },
            teaName : {
                required : icon + "请输入品名"
            },
            color : {
                required : icon + "请输入色别"
            },
            setCount : {
                digits : icon + "请输入正确的整数"
            },
            saleCount : {
                digits : icon + "请输入正确的整数"
            },
            totalCount : {
                digits : icon + "请输入正确的整数"
            },
            price : {
                digits : icon + "请输入正确的整数"
            },
            totalPrice : {
                digits : icon + "请输入正确的整数"
            }
        }
    })
}