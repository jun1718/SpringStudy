<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>개별 점수 조회</h2>
<form action = "/database/score/selectOne" method = "post">
	<p>
		#조회할 학번 : <input type = "text" name = "stuNum" size = "5">
				   <input type = "submit" value = "조회">
	</p>
</form>

<p style = "color:red;">
	${message}
</p>

</body>
</html>