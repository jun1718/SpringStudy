
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

<a href = "<c:url value = '/response/test?age=30' />"> 테스트 1페이지로~</a>
	<%-- 여기서 get으로 보내는건데(아래도 get인건 마찬가지) 나이에 대해서를 링크에 ?붙여서(30으로)
		보내고 별명은 nick, 뽀삐로 Controller에서 model에 담은뒤 보내라 
		즉 나이는 jsp에서 파라미터로 보내고 별명은 controller에서 model에 담아서 보낸걸
		받아서 사용해라
--%>
<a href = "<c:url value = '/response/test2' />"> 테스트 2페이지로~</a>

</body>
</html>