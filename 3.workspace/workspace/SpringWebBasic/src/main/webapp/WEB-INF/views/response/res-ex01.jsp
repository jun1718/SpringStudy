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

<h2>Model 객체에 대해 알아보기</h2>

<%--
	<c:url value='' />
		-value 속성안에 컨텍스트 루트 경로를 제외한 절대경로를 작성
		-그러면 컨텍스트 루트를 제외하고 연결가능
		<a href = "/web/response/test">테스트페이지로~</a>
		위에 놈을 아래로 전환
		<a href = "<c:url value = '/response/test' />"> 테스트페이지로~</a>
		
		
 --%>

<a href = "<c:url value = '/response/test?age=30' />"> 테스트 1페이지로~</a>
<a href = "<c:url value = '/response/test2' />"> 테스트 2페이지로~</a>

</body>
</html>