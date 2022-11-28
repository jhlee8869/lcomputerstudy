<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
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
<h1>자유게시판</h1>
		<pre>  게시글 작성</pre>
		
	<!-- <form action="board-insert-process.do" name="board" method="post" enctype="multipart/form-data"> -->
	<form action="board-insert-process.do" name="board" method="post" enctype="multipart/form-data">
		<input type="hidden" name="b_idx" value="${board.b_idx}">
	
			<p> 제목 : <input type="text" name="b_title"  value="${board.b_title}"></p>
			<p> 내용 : <input type="text" name="b_content"  value="${board.b_content}"></p>
			<p> 첨부파일 : <input type="file" name="b_filename" value="${board.b_filename}"></p>
			<p> <input type="submit" value="작성"></p>

	</form>

	<table>
		<td style="border:none;width:100px;">
			<a href="board-list.do" style="width:50%;font-weight:500;background-color:#ffffff;color:#202020;">목록보기</a>
		</td>
	</table>
</body>
</html>