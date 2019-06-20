<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css" />
</head>
<body>
	<div class="container-fluid">
		<div class="col-md-4">
			<img src="${pageContext.request.contextPath}/img/logo2.png" />
		</div>
		<div class="col-md-5">
			<img src="${pageContext.request.contextPath}/img/header.png"   />
		</div>
		<div class="col-md-3" style="padding-top: 20px">
			<ol class="list-inline">
				<c:if test="${empty loginUser}">
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUI"><i class="fa fa-address-book" style="font-size:24px">&nbsp;|&nbsp;</i>登录</a>
					</li>
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=registUI"><i class="fa fa-align-justify" style="font-size:24px">&nbsp;|&nbsp;</i>注册</a>
					</li>
				</c:if>
				<c:if test="${not empty loginUser}">
					<li>欢迎${loginUser.username}</li>
					<i class="fa fa-user-circle" style="font-size:24px">&nbsp;</i>
					<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginOut">退出</a>
					</li>
					<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a>
					</li>
					<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findOrdersWithPage&num=1">我的订单</a>
					<i class="fa fa-bars"></i>
					</li>
				</c:if>
			</ol>
		</div>
	</div>
	<!--
            	描述：导航条
            -->
	<div class="container-fluid" >
		<nav class="navbar navbar-inverse" style="background:#C0C0C0">
			<div class="container-fluid" style="background:#C0C0C0">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header" >
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/store/index.jsp">首页</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" id="myUL">					
					</ul>
					<form class="navbar-form navbar-right" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search">
						</div>
						<button type="submit" class="btn btn-default">搜索</button>
					</form>

				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>
</body>
<script>
	$(function() {
		//向服务端发起一个ajax请求，获取json分类数据
		var url = "/store/CategoryServlet";
		var obj = {
			"method" : "findAllCats"
		};
		$.post(url, obj, function(data) {
			//获取响应数据，遍历数据动态显示商品分类区域
			$.each(data, function(i, obj) {
				var li = "<li><a href='/store/ProductServlet?method=findProductsByCidWithPage&num=1&cid="+obj.cid+"'>" + obj.cname + "</a></li>";
				$("#myUL").append(li);
			});
		}, "json");
	});
</script>
</html>