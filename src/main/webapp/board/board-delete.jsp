<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>

	<h2>삭제 완료</h2>
	
	<form action="board-delete.do" name="user" method="post">
		<input type="hidden" name="b_idx" value="${board.b_idx}">
</form>
	<table>
		<td style="border:none;width:100px;">
			<a href="board-list.do" style="width:50%;font-weight:500;background-color:#ffffff;color:#202020;">돌아가기</a>
		</td>
	</table>
</body>
</html>