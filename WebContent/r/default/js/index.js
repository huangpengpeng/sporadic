/**
 * 注册
 */
$('#J_register').live('click', function() {
	var modal = $.scojs_modal({
		keyboard : true
	});
	modal.close();
	modal = $.scojs_modal({
		keyboard : true,
		title : "用户注册",
		remote : $(this).attr("href")
	});
	modal.show();
	return false;
});
/**
 * 登录
 */
$('#J_login').live('click', function() {
	var modal = $.scojs_modal({
		keyboard : true
	});
	modal.close();
	modal = $.scojs_modal({
		keyboard : true,
		title : "用户登录",
		remote : $(this).attr("href")
	});
	modal.show();
	return false;
});

$("#J_username,#J_password,#J_verifyPassword").live('focus', function() {
	$(this).parents(".control-group").removeClass("error");
	$(this).parents(".control-group").find(".help-inline").remove();
});

$("#J_username,#J_password,#J_verifyPassword").live('blur', function() {
	if ($(this).attr("id") == "J_username") {
		return checkUsername(this);
	}
	if ($(this).attr("id") == "J_password") {
		return checkPassword(this);
	}
	if ($(this).attr("id") == "J_verifyPassword") {
		return checkVerifyPassword(this, $("#J_password"));
	}
});

function checkUsername(obj) {
	if (!chackNull(obj)) {
		return false;
	}
	var val = $(obj).val();
	var Regex = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;
	if (!Regex.test(val)) {
		$(obj).parents(".control-group").addClass("error");
		$(obj).after('<span class="help-inline">格式错误</span>');
		return false;
	}
	if ($("#J_verifyPassword").length > 0) {
		$.post("/check.jhtml", {
			username : val
		}, function(resp) {
			if (resp.errors) {
				$(obj).parents(".control-group").addClass("error");
				$(obj).after('<span class="help-inline">已存在</span>');
			}
		});
	}
	return true;
}

function checkPassword(obj) {
	if (!chackNull(obj)) {
		return false;
	}
}

function checkVerifyPassword(arg0, arg1) {
	if (!chackNull(arg0)) {
		return false;
	}
	if (!chackNull(arg1)) {
		return false;
	}
	var val0 = $(arg0).val();
	var val1 = $(arg1).val();
	if (val0 != val1) {
		$(arg0).parents(".control-group").addClass("error");
		$(arg0).after('<span class="help-inline">确认密码错误</span>');
	}
}

function chackNull(obj) {
	var val = $(obj).val();
	if (val == null || val.length == 0) {
		$(obj).parents(".control-group").addClass("error");
		$(obj).after('<span class="help-inline">不能为空</span>');
		return false;
	}
	return true;
}

/**
 * 提交表单
 */
$('#J_form').live('submit', function() {
	/** *********提交修改表单**************** */
	var options = {
		dataType : 'json', // target element(s) to be updated with server
		// response
		beforeSubmit : showRequest, // pre-submit callback
		success : showResponse
	// post-submit callback
	// other available options:
	// url: url // override for form's 'action' attribute
	// type: type // 'get' or 'post', override for form's 'method' attribute
	// dataType: null // 'xml', 'script', or 'json' (expected server response
	// type)
	// clearForm: true // clear all form fields after successful submit
	// resetForm: true // reset the form after successful submit

	// $.ajax options can be used here too, for example:
	// timeout: 3000
	};
	$(this).ajaxSubmit(options);
	return false;
});

// pre-submit callback
function showRequest(formData, jqForm, options) {
	var queryString = $.param(formData);
	console.log('About to submit: \n\n' + queryString);
	if ($(".help-inline").length > 0) {
		return false;
	}
	checkUsername($("#J_username"));
	checkPassword($("#J_password"));
	checkVerifyPassword($("#J_verifyPassword"), $("#J_password"));
	if ($(".help-inline").length > 0) {
		return false;
	}
	$("#J_save").button('loading');
	return true;
}

// post-submit callback
function showResponse(responseText, statusText, xhr, $form) {
	console
			.log('status: '
					+ statusText
					+ '\n\nresponseText: \n'
					+ responseText
					+ '\n\nThe output div should have already been updated with the responseText.');
	if (!responseText.errors) {
		location.href = responseText.returnUrl;
	} else {
		if ("password invalid" == responseText.errors[0]) {
			$("#J_password").parents(".control-group").addClass("error");
			$("#J_password").after('<span class="help-inline">密码错误</span>');
		}
		if (responseText.errors[0].indexOf("username") >= 0) {
			$("#J_username").parents(".control-group").addClass("error");
			$("#J_username").after('<span class="help-inline">用户名不存在</span>');
		}
	}
	$("#J_save").button('reset');
}