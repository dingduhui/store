/**
 * 
 */
var flag1 = false;
var flag2 = false;
var flag3 = false;
var flag4 = false;
var flag5 = false;
function checkUserName() {
	var name = $("#username").val();
	var obj = {
		"method" : "CheckUser",
		"name" : name
	};
	var url = "/store/UserServlet";
	$.post(url, obj, function(data, status) {
		if (data == "0") {
			$("#warning-username").html("<font color='green'>该账户已注册！</font>");
		}
		if (data == "1") {
			$("#warning-username").html("<font color='red'>该账户可用！</font>");
			flag1 = true;
		}
	})
}
function checkPws1() {
	var name = $("#inputPassword3").val();
	var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/;
	if (!reg.test(name)) {
		$("#warning-psw1").html(
				"<font color='green'>密码长度要大于6位小于12位，由数字和字母组成</font>");

	} else {
		$("#warning-psw1").html("<font color='green'>密码可用！</font>");
		flag2 = true;
	}
}
function checkPws() {
	var name = $("#confirmpwd").val();
	var name1 = $("#inputPassword3").val();
	if (name == name1) {
		$("#warning-psw").html("<font color='green'>两次输入密码一致。</font>");
		flag3 = true;
	} else {
		$("#warning-psw").html("<font color='red'>两次输入密码不一致！</font>");
	}

}
function checkname() {
	var name = $("#xm").val();
	var reg = /^[\u4E00-\u9FA5A-Za-z]+$/;
	if (!reg.test(name)) {
		$("#warning-name").html("<font color='green'>只能输入中文和英文</font>");
	} else {
		$("#warning-name").html("<font color='green'>可用</font>");
		flag4 = true;
	}
}
function checkphone() {
	var name = $("#p1").val();
	var reg = /^[1][3,4,5,7,8][0-9]{9}$/;
	if (!reg.test(name)) {
		$("#warning-phone").html("<font color='green'>手机格式不正确</font>");
	} else {
		$("#warning-phone").html("<font color='green'>手机格式正确</font>");
		flag5 = true;
	}
}

function formSub() {
	if (flag1 && flag2 && flag3 && flag4 && flag5 ) {
		return true;
	} else {
		alert("请完善信息");
		return false;
	}

}
