<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>登录界面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
</head>
<body>
	<div class="wrap login_wrap">
		<div class="content">
			<div class="logo"></div>
			<div class="login_box">
				<div class="login_form">
					<div class="login_title">管理员登录</div>
					<form action="${pageContext.request.contextPath}/UserServlet?method=userLogin" method="post">
						<div class="form_text_ipt">
							<input name="username" type="text" placeholder="账号" >
						</div>
						<div class="ececk_warning">
							<span>手机号不能为空</span>
						</div>
						<div class="form_text_ipt">
							<input name="password" type="password" placeholder="密码" >
						</div>
						<div class="ececk_warning">
							<span>密码不能为空</span>
						</div>
						<div class="form_check_ipt">
							<div class="left check_left">
								
							</div>
							<div class="right check_right">
								
							</div>
						</div>
						<div class="form_btn">
						 <button type="submit">进入管理界面</button>
						</div>
						<div class="form_reg_btn">
							<span ><img src="${pageContext.request.contextPath}/img/htLogo.png" / width="288px"></span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<div style="text-align: center;"></div>
</body>
</html>
