
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

<%--
	1. 다음 생년월일을 받아서 콘솔에 출력하는 
	   메서드를 생성 (url: /birth : POST)
          조건) BirthVO 커맨드객체 사용, 
          콘솔에 전송된 값을 붙여서 출력합니다 ex)20180615
	2. birth-result.jsp 페이지에 
	"당신의 생일은 ~~~~년 ~~월 ~~일입니다." 을 출력하세요
--%>

<form action = "<c:url value = '/birth'/>" method = "post" >
	<fieldset>
		<legend>생일 등록 양식</legend>
		<p>
			# 생년월일 :
			<input type = "text" name = "year" size = "6" maxlength = "4" placeholder = "연도(4자리)"/>
			<select name = "month">
				<c:forEach var = "m" begin = "1" end = "12" step = "1"> <%-- step이 1이면 생략가능 --%>
					<option>${m}월</option>
				</c:forEach>
			</select>
			<input type = "text" name = "day" maxlength = "2" size = "4" placeholder = "일(1-31)" />
			<input type = "submit" value = "확인" />
		</p>
	</fieldset>
</form>

</body>
</html>