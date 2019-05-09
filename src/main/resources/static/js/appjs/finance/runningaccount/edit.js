$().ready(function() {
	validateRule();
    $('#timedate').datetimepicker({
        format: 'YYYY-MM-DD',
        locale: moment.locale('zh-cn')
    });
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/manage/runningAccount/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
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
            costAttribute : {
                required : true
            },
            costAttributeType : {
                required : true
            },
            companyName : {
                required : true
            },
            projectName : {
                required : true
            },
            abstractContent : {
                required : true
            },
            entrys : {
                isMyNumber : true
            },
            outs : {
                isMyNumber : true
            }
        },
        messages : {
            timedate : {
                required : icon + "请选择日期"
            },
            costAttribute : {
                required : icon + "请输入费用属性"
            },
            costAttributeType : {
                required : icon + "请输入属性类别"
            },
            companyName : {
                required : icon + "请输入单位名称"
            },
            projectName : {
                required : icon + "请输入项目"
            },
            abstractContent : {
                required : icon + "请输入摘要"
            },
            entrys : {
                isMyNumber : icon + "请输入正确的数字,最多两位小数"
            },
            outs : {
                isMyNumber : icon + "请输入正确的数字,最多两位小数"
            }
        }
    })
}