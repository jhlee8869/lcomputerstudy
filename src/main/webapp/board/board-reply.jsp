<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글</title>
</head>
<style>
	pre{
		font-size:20px;font-color:#0a0a0a;border:2px solid black;
	}
	form {
		border:2px solid black;width:600px;height:300px;
	}
	//
	.input_inp1 {
		width:300px;height:20px;
	}
	
	.input_inp2 {
		width:400px;height:80px;
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
	.p_reply {
		text-align:center;
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
		width:200px;height:50px;
		float:left;
	}
	.div_reply {
		width:50%;height:50px;
		float:left;
	}
	.div_list {
		width:50%;height:50px;
		display:flex;
		align-items:center;
		flex-direction:row;
		justify-content:center;
		
	}
	.a_list {
		width:100%;height:50px;
		text-align:center;
	}
	
	
</style>
<body>
<h1>답글달기 </h1>
		
	<form action="board-reply-process.do" name="board" method="post">
	<input type="hidden" name="b_idx" value="${board.b_idx}">
	<input type="hidden" name="u_idx" value="${board.user.u_idx}">
	<input type="hidden" name="b_group" value="${board.b_group}">
	<input type="hidden" name="b_order" value="${board.b_order}">
	<input type="hidden" name="b_depth" value="${board.b_depth}">
		
		<div class="div_body">
			<div class="div_title">
				<p class=p_title> 제목 : <input class=input_inp1 type="text" name="b_title" value="Re-"></p>
			</div>
			<div class="div_line">
				<p class=p_line></p>
			</div>
			<div class="div_content">
				<p class=p_content> 내용 : <input class=input_inp2 type="text" name="b_content"></p>
			</div>
		</div>
		
	<div class="div_body2">
		<div class="div_reply">
			<p class="p_reply"><input type="submit" value="답글달기"></p>
		</div>
		<div class="div_list">
			<a class="a_list" href="board-list.do">목록보기</a>
		</div>
	</div>
	</form>	
</body>
</html>