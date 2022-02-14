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
<h2>${stu.stuName}학생 성적 정보 조회</h2>

<p>	
	# 국어 : ${stu.kor} <br>
	# 영어 : ${stu.eng} <br>
	# 수학 : ${stu.math} <br>
	# 총점 : ${stu.total} <br>
	# 평균 : ${stu.average} 
</p>

<a href = "<c:url value = '/score/register'/>">다른 점수 등록하기</a>
<a href = "<c:url value = '/score/list'/>">점수 전체 조회</a>
<a href = "<c:url value = '/score/selectOne'/>">점수 개별 조회</a>

</body>
</html>