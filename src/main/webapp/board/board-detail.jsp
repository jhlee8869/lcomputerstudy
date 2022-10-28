<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세페이지</title>
</head>
<style>
	pre{
		font-size:20px;font-color:#0a0a0a;border:2px solid black;
	}
	a {
		text-decoration:none;
		color:#FFFFFF;
	}

	.p_title {
		width:400px;
		font-weight:800;font-size:30px;
		text-align:left;
	}
	
	.p_writer {
		width:250px;height:30px;
		text-align:left;
	}
	.p_date {
		width:250px;height:30px;
		text-align:left;
	}
	.p_content {
		border:2px solid #818181;
		width:500px;height:100px;
		text-align:left;
	}
	
	.p_comment {
		border:2px solid #E0E0E0;
		width:500px;height:100px;
		text-align:left;
	}
	
	.p_comment_write {
		text-align:center;
	}

	.p_line:after {
  		content: "";
  		display: block;
  		width: 600px;
  		border-bottom: 3px solid #bcbcbc;
	}
	.div_body {
		border:2px solid black;
		margin-left:1%;	
		width:800px;height:300px;
	}
	.div_title {
		width:100%;height:60px;
		float:left;
	}
	.div_writer {
		width:20%;height:30px;
		float:left;
	}
	.div_date {
		width:80%;height:30px;
		float:left;
	}
	.div_content {
		width:100%;height:150px;
		float:left;
	}
	.div_line {
		width:100%;height:20px;
		float:left;
	}
	
	#div_body_all {
		margin:10px auto;
		width:400px;height:30px;
		float:left;
	}
	
	#div_body1 {
		margin:10px auto;
		width:400px;height:30px;
		float:left;
	}
	
	#div_bd {
		width:15%;height:25px;
		background-color:#66B2FF;
		border-radius:10px;
		float:left;
		text-align:center;
		font-size:17px;font-family:굴림;
		
	}
	
	#div_bd2 {
		width:20%;height:25px;
		background-color:#66B2FF;
		border-radius:10px;
		float:left;
		text-align:center;
		font-size:17px;font-family:굴림;
		
	}
	
	.div_bd_edit {
		margin-left:3%;
		
	}
	.div_bd_delete {
		margin-left:3%;
		
	}
	.div_bd_insert {
		margin-left:3%;
	
	}
	.div_bd_reply {
		margin-left:3%;

	}
	.div_bd_list {
		margin-left:3%;
	
	}
	
	#div_body2 {
		margin-bottom:5%;
		width:400px;height:30px;
		float:left;
	}
	
	#div_body3 {
		margin-top:70px;
		margin-bottom:40px;
		width:400px;height:30px;
		float:left;
	}
	
	#div_bd3 {
		width:20%;height:25px;
		float:left;
		text-align:center;
		font-size:17px;font-family:굴림;
		
	}
	
	#div_bd4 {
		width:300px;height:100%;
		float:left;
		text-align:center;
		font-size:17px;font-family:굴림;
		
	}
	.comment_body2 {
		width:100%;height:25px;
		float:left;
		text-align:center;
		font-size:17px;font-family:굴림;
	}
	.comment_body3 {
		border:2px solid #818181;
	}
	
	.div_bd_comment {
		margin-top:auto;
		margin-left:3%;
		
	}
	
	.div_bd_comment_list {
		margin-bottom:25px;
		margin-left:3%;
		
	}
	.comment-box {
		margin-left:1%;	
		width:300px;height:30px;
		text-align:left;
	}
	
	
</style>
<body>
<h1>자유게시글 </h1>

		<div class="div_body">
			<div class="div_title">
				<p class=p_title> 제목 : ${board.b_title}</p>
			</div>
			<div class="div_writer">
				<p class=p_writer>작성자 : ${board.user.u_name}</p>
			</div>
	
			<div class="div_date">
				<p class=p2>작성일자 : ${board.b_date}</p>
			</div>
			<div class="div_line">
				<p class=p_line></p>
			</div>
			<div class="div_content">
				<p class=p_content> 내용 : ${board.b_content}</p>
			</div>
		</div>
		
	<div id="div_body_all">
		<div id="div_body1">
			<div id="div_bd" class="div_bd_edit">
				<a href="board-edit.do?b_idx=${board.b_idx}">수정</a>
			</div>
			<div id="div_bd" class="div_bd_delete">
				<a href="board-delete.do?b_idx=${board.b_idx}">삭제</a>
			</div>
			<div id="div_bd" class="div_bd_insert">
				<a href="board-insert.do?b_idx=${board.b_idx}">글쓰기</a>
			</div>
			<div id="div_bd2" class="div_bd_reply">
				<a href="board-reply.do?b_idx=${board.b_idx}&b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}">답글달기</a>
			</div>
			<div id="div_bd2" class="div_bd_list">
				<a href="board-list.do">목록보기</a>
			</div>
	</div>
		
	<form action="comment-insert.do" name="comment" method="post">
	<input type="hidden" name="b_idx" value="${board.b_idx}">
	<input type="hidden" name="b_idx" value="${comment.b_idx}">

	<!-- <input type="hidden" name="c_idx" value="${comment.c_idx}"> -->
	
		<div id="div_body2">
			<p class=p_comment> <input class=input_inp1 type="text" name="c_content" value="${comment.c_content}" placeholder="댓글을 입력해주세요."></p>

		</div>
		<div id="div_body3">
			<div id="div_bd3" class="div_bd_comment">		
				<p class="p_comment_write"><input type="submit" value="댓글달기"></p>
			</div>
			
		</div>
		
		<div id="div_bd4" class="div_bd_comment_list">
				<c:forEach items="${commentList}" var="comment">
					<div class="comment_body3">
						<div class="comment-box">
							<p>${board.user.u_name}</p>
						</div>
						
						<div class="comment-box">
							<p>${comment.c_date}</p>
						</div>
						
						<div class="comment-box">
							<p>${comment.c_content}</p>
						</div>
					
					</div>					
				</c:forEach>
				<!-- a href="comment-list.do?b_idx=${comment.b_idx}">목록보기</a-->
				<!-- <a href="board-detail.do?c_idx=${comment.c_idx}">목록보기</a> -->
			</div>
		
	</form>
	</div>

</body>
</html>