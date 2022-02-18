<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 포매팅 관련 태그라이브러리 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp" />
<style>
header.masthead {
	
	display: none;
}	
.btn-orange {
	background-color: orange;
	color: white;
}
.btn-izone {
	background-color: #ff52a0;
	color: white;
}
</style>

<br><br> 
 
    <!-- Begin Page Content -->
	

	<div class="container">
		<div class="row">
			<div class="col-lg-2">
			</div>
			<div class="col-lg-8">
				<div class="panel-body">
				<h2 class="page-header"><span style="color: #ff52a0;">IZONE</span> 자유 게시판
					<span id="count-per-page" style="float: right;">
	                     <input class="btn btn-izone" type="button" value="10">  
	                     <input class="btn btn-izone" type="button" value="20">   
	                     <input class="btn btn-izone" type="button" value="30">
                     </span>
					
				</h2>
					<table class="table table-bordered table-hover">
						<thead>
							<tr style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
								<th>#번호</th>
								<th>작성자</th>
								<th>제목</th>
								<th>작성일</th>
								<th>조회수</th>
							</tr>
						</thead>

						<!-- 게시물이 들어갈 공간 -->
						<c:if test = "${articles.size() <= 0}">
							<h2>게시물이 없습니다.</h2>
						</c:if>
						<c:if test = "${articles.size() > 0}">						
							<c:forEach var = "article" items = "${articles}">
								<tr style="color: #ff52a0;">
									<td>${article.boardNo}</td>
									<td>${article.writer}</td>
	
									<td><a style="margin-top: 0; height: 40px; color: orange;" href="<c:url value = '/board/content/${article.boardNo}'/>">
											${article.title}
										</a>
									</td>
	
									<!-- 이방식은 날짜시간이 드릅게 나온다
									<td>${article.regDate}</td>
									 -->
									<td>
										<fmt:formatDate value="${article.regDate}" pattern = "yyyy년 MM월 dd일 a hh:mm"/>
									</td>
									<td>${article.viewCnt}</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
					
					<!-- 페이징 처리 부분  -->
					<ul class="pagination justify-content-center">
                       	<li class="page-item">
							<a class="page-link" href="#" 
							style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">이전</a>
						</li>
						
						<li class="page-item">
						   <a href="#" class="page-link" style="margin-top: 0; height: 40px; color: pink; border: 1px solid pink;">1</a>
						</li>
					   
					    <li class="page-item">
					      <a class="page-link" href="#" 
					      style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">다음</a>
					    </li>
				    </ul>
					<!-- 페이징 처리 끝 -->
					</div>
				</div>
			</div>
					<!-- 검색 버튼 -->
					<div class="row">
						<div class="col-sm-2"></div>
	                    <div class="form-group col-sm-2">
	                        <select id="condition" class="form-control" name="condition">                            	
	                            <option value="title">제목</option>
	                            <option value="content">내용</option>
	                            <option value="writer">작성자</option>
	                            <option value="titleContent">제목+내용</option>
	                        </select>
	                    </div>
	                    <div class="form-group col-sm-4">
	                        <div class="input-group">
	                            <input type="text" class="form-control" name="keyword" id="keywordInput" placeholder="검색어">
	                            <span class="input-group-btn">
	                                <input type="button" value="검색" class="btn btn-izone btn-flat" id="searchBtn">                                       
	                            </span>
	                        </div>
	                    </div>
	                    <div class="col-sm-2">
							<a href="<c:url value = '/board/write'/>" class="btn btn-izone float-right">글쓰기</a>
						</div>
						<div class="col-sm-2"></div>
					</div>
					
		
	</div>
	
<script type="text/javascript">
	//글쓰기 완료시 띄울 알림창처리
	const result = "${msg}";
	
	if (result === "regSuccess") {
		alert("게시글 등록이 완료되었습니다.");
	} else if (result === "delSuccess") {
		alert("게시글 삭제가 완료되었습니다.");
	}
</script>
<jsp:include page="../include/footer.jsp" />