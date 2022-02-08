<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<form action = "<c:url value = '/response/res-login' />" method="post">
	<p>
		# ID: <input type="text" name="userId" size="10"><br>
		# PW: <input type="password" name="userPw" size="10"><br>
		<input type="submit" value="로그인">
	</p>
</form>


</body>
</html>
</body>
</html>