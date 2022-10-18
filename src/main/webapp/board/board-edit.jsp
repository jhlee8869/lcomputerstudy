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
		border:2px solid black;width:800px;height:300px;
	}
	.input_inp1 {
		width:300px;height:20px;
	}
	
	.input_inp2 {
		width:400px;height:80px;
	}

	.p_title {
		width:500px;height:30px;
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
	.p_editbutton {
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
		width:100%;height:130px;
		float:left;
	}
	.div_editbutton{
		width:100%;height:40px;
		float:left;
	}
	
	.div_line {
		width:100%;height:20px;
		float:left;
	}
	
</style>
<body>
<h1>게시글 수정</h1>
		
	<form action="board-edit-process.do" name="board" method="post">
	<input type="hidden" name="b_idx" value="${board.b_idx}">
	
	<div class="div_body">
			<div class="div_writer">
				<p class=p_writer>작성자 : ${board.user.u_name}</p>
			</div>	
			<div class="div_date">
				<p class=p_date>작성일자 : ${board.b_date}</p>
			</div>
			<div class="div_line">
				<p class=p_line></p>
			</div>
			<div class="div_title">
				<p class=p_title> 제목 : <input class=input_inp1 type="text" name="b_title" value="${board.b_title}"></p>
			</div>
			<div class="div_content">
				<p class=p_content> 내용 : <input class=input_inp2 type="text" name="b_content" value="${board.b_content}"></p>
			</div>
			<div class="div_editbutton">
				<p> <input type="submit" value="수정"></p>
			</div>
		</div>
		
	</form>
</body>
</html>