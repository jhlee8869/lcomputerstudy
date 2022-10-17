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
	form {
		border:2px solid black;width:600px;height:300px;
	}
	//
	a {
		text-decoration:none;
		width:50px;font-weight:500;background-color:#ffffff;color:#202020;
		border:3px solid #818181;
		text-align:center
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

	.p_line:after {
  		content: "";
  		display: block;
  		width: 600px;
  		border-bottom: 3px solid #bcbcbc;
	}
	.div_body {
		margin:auto;
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
	
	.div_body2 {
		margin:auto;
		width:400px;height:50px;
		float:left;
	}
	.div_edit {
		width:20%;height:30px;
		float:left;
	}
	.div_delete {
		width:20%;height:30px;
		float:left;
	}
	.div_insert {
		width:20%;height:30px;
		float:left;
	}
	.div_reply {
		width:20%;height:30px;
		float:left;
	}
	.div_list {
		width:20%;height:30px;
		float:left;
	}
	
	
</style>
<body>
<h1>자유게시글 </h1>
		
	<form action="board-detail.do" name="board" method="post">
	<input type="hidden" name="b_idx" value="${board.b_idx}">
	<input type="hidden" name="u_idx" value="${board.user.u_idx}">
	<input type="hidden" name="b_group" value="${board.b_group}">
	<input type="hidden" name="b_order" value="${board.b_order}">
	<input type="hidden" name="b_depth" value="${board.b_depth}">
		
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
		
	</form>
	<div class="div_body2">
		<div class="div_edit">
			<a href="board-edit.do?b_idx=${board.b_idx}">수정</a>
		</div>
		<div class="div_delete">
			<a href="board-delete.do?b_idx=${board.b_idx}">삭제</a>
		</div>
		<div class="div_insert">
			<a href="board-insert.do?b_idx=${board.b_idx}">글쓰기</a>
		</div>
		<div class="div_reply">
			<a href="board-reply.do?b_idx=${board.b_idx}&b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}">답글달기</a>
		</div>
		<div class="div_list">
			<a href="board-list.do">목록보기</a>
		</div>
	</div>
	
</body>
</html>