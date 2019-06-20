/**
 * 
 */
function validateForm() {

	var formElement = document.myForm;
	// 依次取出用户名和密码
	var username = formElement.username.value;
	var password = formElement.password.value;
	var email = formElement.email.value;
	// alert("去空格前："+"#"+username+"#"+":"+password+":"+email+"长度："+username.length);
	username = trim(username);
	password = trim(password);
	email = trim(email);
	// alert("去空格后："+username+":"+password+":"+email+"长度："+username.length);
	checkUsername(username);
	checkPassword(password);
	checkEmail(email);
}
// 验证邮箱
function checkEmail(email) {
	var flag = true;
	if (email.length == 0) {
		flag = false;
		alert("邮箱不能为空！");
	} else if (!/^\w+@\w+(\.\w+)+$/.test(email)) {
		flag = false;
		alert("邮箱格式不对");
	}
	return flag;
}

// 验证密码
function checkPassword(password) {
	var flag = true;
	var formElement = document.myForm;
	if (password.length == 0) {
		flag = false;
		alert("密码不能为空！");
		formElement.password.focus();
	} else if (!/^[0-9]{6}$/.test(password)) {
		flag = false;
		alert("密码必须为6位数字！");
	}
	return flag;
}
// 验证用户名
function checkUsername(username) {
	var flag = true;
	var formElement = document.forms[0];
	if (username.length == 0) {
		flag = false;
		alert("用户名不能为空！");
		formElement.username.focus();
	} else if (!/^[a-zA-Z0-9]+$/.test(username)) {
		flag = false;
		alert("用户名必须为英文数字和下划线和美元符号");
	}
	return flag;
}
// 自定义去空格函数
function trim(s) {
	s = s.replace(/(^\s*)|(\s*$)/g, "");// 去前后空格
	// s = s.replace(/^\s*$/,"");
	return s;
}
window.onload = function() {
	var formElement = document.myForm;
	formElement.onsubmit = validateForm;
};
