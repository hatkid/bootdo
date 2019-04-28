;jQuery.validator.addMethod("isMyNumber", function (value, element) {
    if (null == value || 0 == value.length) {
        return true;
    }
    var myNumber = /^(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
    return this.optional(element) || myNumber.test(value);
}, "请输入正确的数字,最多两位小数");