<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"  %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>점수 등록 성공!</h2>
	
	<a href = "<c:url value = '/score/register'/>">다른 점수 등록하기</a>
	<a href = <c:url value = '/score/list'/>>점수 전체 조회</a>
	<a href = "<c:url value = '/score/selectOne'/>">점수 개별 조회</a>

</body>
</html>