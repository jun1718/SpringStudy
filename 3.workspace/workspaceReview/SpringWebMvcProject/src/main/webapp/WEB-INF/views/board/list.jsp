<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
.page-active {
	background-color: #ff52a0;
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
						<c:if test = "${articles.size() > 0}">
						<c:forEach var = "article" items = "${articles}">
							<tr style="color: #ff52a0;">
								<td>${article.boardNo}</td>
								<td>${article.writer}</td>

								<td><a style="margin-top: 0; height: 40px; color: orange;" href="<c:url value = '/board/content/${article.boardNo}${param.page == null ? pc.makeURI(1) : pc.makeURI(param.page)}'/>">
										${article.title}
									</a>
									&nbsp;
									<c:if test = "${article.isNewMark()}">
										<span class="label label-dange">
											new
										</span>
									</c:if>> 
									
								</td>

								<td><fmt:formatDate value="${article.regDate}" pattern="yyyy년 MM월 dd일 a hh:mm" /></td>
								<td>${article.viewCnt}</td>
							</tr>
						</c:forEach>
						</c:if>
						
						<c:if test = "${articles.size() <= 0}">
							<tr>
								<td colspan="5" align="center">
									<strong>검색 결과가 없습니다.</strong>						
								</td>
							</tr>
						</c:if>
					</table>
					
					<!-- 페이징 처리 부분  -->
					<ul class="pagination justify-content-center">
						<c:if test = "${pc.prev}">
	                       	<li class="page-item">
								<a class="page-link" href="<c:url value = '/board/list${pc.makeURI(pc.beginPage - 1)}'/>" 
								style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">이전</a>
							</li>
						</c:if>
						<c:forEach var = "pageNum" begin = "${pc.beginPage}" end = "${pc.endPage}">
						<li class="page-item">
						   <a href="<c:url value = '/board/list${pc.makeURI(pageNum)}'/>" class="page-link ${pc.paging.page == pageNum ? 'page-active' : ''}" style="margin-top: 0; height: 40px; color: pink; border: 1px solid pink;">${pageNum}</a>
						</li>
						
						</c:forEach>
					   	<c:if test = "${pc.next}">
						    <li class="page-item">
						      <a class="page-link" href="<c:url value = '/board/list${pc.makeURI(pc.endPage + 1)}'/>" 
						      style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">다음</a>
						    </li>
					    </c:if>
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
	                            <option value="title" ${param.condition == 'title' ? 'selected' : ''}>제목</option>
	                            <option value="content" ${param.condition == 'content' ? 'selected' : ''}>내용</option>
	                            <option value="writer" ${param.condition == 'writer' ? 'selected' : ''}>작성자</option>
	                            <option value="titleContent" ${param.condition == 'titleContent' ? 'selected' : ''}>제목+내용</option>
	                        </select>
	                    </div>
	                    <div class="form-group col-sm-4">
	                        <div class="input-group">
	                            <input type="text" class="form-control" value = "${param.keyword}"  name="keyword" id="keywordInput" placeholder="검색어">
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
	const result = "${msg}";
	if (result === "regSuccess") {
		alert("게시글 등록 완료했습니다.");
	} else if (result == "delSuccess") {
		alert("게시글 삭제 완료했습니다.");
	}
	
	$(function() {
		let countPerPage = $("#count-per-page .btn-izone");
		countPerPage.click(function() {
			let count = $(this).val();
			location.href = "/board/list?countPerPage=" + count + "&condition=${param.condition}&keyword=${param.keyword}";

		});
		
		
		$("#searchBtn").click(function() {
			const keyword = $("#keywordInput").val();
			const condition = $("#condition option:selected").val();
			
			location.href = "/board/list?keyword=" + keyword + "&condition=" + condition;
		});
		
		$("#keywordInput").keydown(function(key) {
			if (key.keyCode == 13) {
				$("#searchBtn").click();
			}
		});
		
		
	});
	
</script>
<jsp:include page="../include/footer.jsp" />