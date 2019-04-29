$().ready(function() {
	validateRule();
    selectLoad();
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
		url : "/manage/supplierDetail/update",
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
            companyId : {
                isSelectEmpty : true
            },
            purchaseName : {
                required : true
            },
            amount : {
                isDigitsOrEmpty : true
            },
            price : {
                isMyNumber : true
            },
            total : {
                isMyNumber : true
            },
            paid : {
                isMyNumber : true
            }
        },
        messages : {
            timedate : {
                required : icon + "请选择日期"
            },
            companyId : {
                isSelectEmpty : icon + "请选择供应商名称"
            },
            purchaseName : {
                required : icon + "请输入采购名称"
            },
            amount : {
                isDigitsOrEmpty : icon + "请输入正确的整数"
            },
            price : {
                isMyNumber : icon + "请输入正确的数字,最多两位小数"
            },
            total : {
                isMyNumber : icon + "请输入正确的数字,最多两位小数"
            },
            paid : {
                isMyNumber : icon + "请输入正确的数字,最多两位小数"
            }
        }
    })
}

function selectLoad() {
    var html = "";
    $.ajax({
        url : '/manage/supplierDetail/getCompanyName',
        success : function(data) {
            //加载数据
            for (var i = 0; i < data.length; i++) {
                html += '<option value="' + data[i].id + '">' + data[i].companyName + '</option>'
            }
            $(".chosen-select").append(html);
            $(".chosen-select").chosen({
                maxHeight : 200
            });
        }
    });
}