<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제</title>
</head>
<body>

	<h2>삭제 완료</h2>
	
	<form action="user-delete.do" name="user" method="post">
		<input type="hidden" name="u_idx" value="${user.u_idx}">
</form>
<script>
setTimeout(function () {
	window.location.href = "userlist.jsp";
</script>
</body>
</html>