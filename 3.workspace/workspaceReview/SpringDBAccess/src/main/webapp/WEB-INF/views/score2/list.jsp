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
<h2>학생들의 전체 성적 조회</h2>

<c:forEach var = "stu" items = "${list}">
	<p>
		# 학번 : ${stu.stuId}, 이름 : ${stu.stuName}, 국어 : ${stu.kor}, 
		 영어 : ${stu.eng}, 수학 : ${stu.math}, 총점 : ${stu.total}, 
		 평균 : ${stu.average}
		&nbsp;
		<a href = "<c:url value = '/mybatis/score2/delete?stuNum=${stu.stuId}'/>">[삭제]</a>
	</p>
</c:forEach>

	<a href = "<c:url value = '/mybatis/score2/register'/>">다른 점수 등록하기</a>
	<a href = <c:url value = '/mybatis/score2/list'/>>점수 전체 조회</a>
	<a href = "<c:url value = '/mybatis/score2/selectOne'/>">점수 개별 조회</a>

</body>
</html>