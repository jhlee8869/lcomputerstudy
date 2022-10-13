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
		border:2px solid black;
	}
	
	table {
		border-collapse:collapse;
		margin:20px auto;
	}
	//
	
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:500px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
</style>
<body>
<h1>답글 달기</h1>
		
	<form action="board-reply-process.do" name="board" method="post">
	<input type="hidden" name="b_idx" value="${board.b_idx}">
	
	<p> 제목 : ${board.b_title}</p>
	<p> 작성일자 : <input type="text" name="date" value="${board.b_date}"></p>
	<p> 내용 : <input type="text" name="content" value="${board.b_content}"></p>
	<p> <input type="submit" value="답글 수정"></p>

</form>
</body>
</html>