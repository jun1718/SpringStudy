<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp" />
<style>
header.masthead {
	
	display: none;
}	
</style>
<br/><br/>
<div class="container">

<div class="row">
  <div class="col-lg-12">
    <div class="card">
      <div class="card-header text-white" style="background-color: #ff52a0;"> 게시글 등록</div>
      <div class="card-body">

        <form role="form" method="post">
        
          <div class="form-group" hidden = "hidden"> <!-- 테그에는 남아있지만  html 구조에는 남아있지만 사용자에게는 작성자 입력창이 안보이게된다-->
            <label>작성자</label> <!-- type hidden은 어차피 위에서 안보이게처리해서 안해도 되지만 확실하게 하자는 측면에서 한것이다. -->
            <input type="hidden" class="form-control" name='writer' value = "${login.name}">
          </div>
          
          <div class="form-group">
            <label>제목</label>
            <input type="text" class="form-control" name='title'>
          </div>

          <div class="form-group">
            <label>내용</label>
            <textarea class="form-control" rows="5" name='content'></textarea>
          </div>

         <input type="submit" value="등록" class="btn form-control"
			style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
          &nbsp;&nbsp;
          <a class="btn form-control"	href="#"
		style="cursor: pointer; margin-top: 0; height: 40px; color: white; background-color: orange; border: 0px solid #388E3C; opacity: 0.8">취소</a>
        </form>



      </div>
    </div>
  </div>
</div>
</div>


<jsp:include page="../include/footer.jsp" />