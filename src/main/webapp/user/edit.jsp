<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
</head>
<body>

<h2> 회원 정보 수정 </h2>
<form action="user-edit-process.do" name="user" method="post">
	<input type="hidden" name="u_idx" value="${user.u_idx}">
	
	<p> 아이디 : <input type="text" name="id" value="${user.u_id}"><input type="button" value="아이디 중복 검사"></p>
	<p> 비밀번호 : <input type="password" name="password"></p>
	<p> 이름 : <input type="text" name="name" value="${user.u_name}">
	<p> 연락처 : <input type="text" maxlength="4" size="4" name="tel1"> -
			   <input type="text" maxlength="4" size="4" name="tel2"> -
			   <input type="text" maxlength="4" size="4" name="tel3"> 
	</p>
	<p> 나이 : <input type="text" name="age" value="${user.u_age}"></p>
	<p> <input type="submit" value="수정완료"></p>
</form>

</body>
</html>