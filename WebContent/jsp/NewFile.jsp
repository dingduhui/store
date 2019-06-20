<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!int a=(int)(Math.random()*10+1);%>
<%!int b=(int)(Math.random()*10+1);%>
<%!int c=(int)(Math.random()*10+1);%>
<% 
if(((a+b)<c)||((a+c)<b)||((b+c)<a)){	
	out.println("三边长分别为：a="+a+"，b="+b+"，c="+c);
	out.print("三边不能组成三角形");
}else{
	if(((a+b)>c)&&((a+c)>b)&&((b+c)>a)){
		if(a==b&&b==c){
			out.println("三边长分别为：a="+a+"，b="+b+"，c="+c);
			out.print("三边组成等边角形");
		}
		if((a==b&&b!=c)||(b==c&&a!=c)||(a==c&&b!=c)){
			if((a*a==b*b+c*c)||(b*b==c*c+a*a)||(c*c==b*b+a*a)){
				out.println("三边长分别为：a="+a+"，b="+b+"，c="+c);
				out.print("三边组成等腰直角角形");	
			}
			out.println("三边长分别为：a="+a+"，b="+b+"，c="+c);
			out.print("三边组成等腰角形");
		}else{
			out.println("三边长分别为：a="+a+"，b="+b+"，c="+c);
			out.print("三边组成一般角形");	
		}		
	}
}
 %>
</body>
</html>