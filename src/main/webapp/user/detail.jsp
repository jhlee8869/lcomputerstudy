<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세</title>
</head>
<style>
	table {
		border-collapse:collapse;
	}
	
	table tr th {
		font-weight:700;
	}
	
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text-decoration:none;
		color:#000;
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
	}
</style>
	
<body>
<h1>회원 상세페이지</h1>

<form action="user-detail.do" name="user" method="post">
	
	<p> 회원 번호 : ${user.u_idx}</p>
	<p> 회원 ID : ${user.u_id}</p>
	<p> 회원 이름 : ${user.u_name}</p>
	<p> 회원 전화번호 : ${user.u_tel}</p>
	<p> 회원 나이 : ${user.u_age}</p>
</form>


	<table>
	<tr style="height:50px;">
		<td style="border:none;">
			<a href="user-edit.do?u_idx=${user.u_idx}" style="width:70%;font-weight:700;background-color:#818181;color:#fff;">수정</a>
		</td>

		<td style="border:none;">
			<a href="user-delete.do?u_idx=${user.u_idx}" style="width:70%;font-weight:700;background-color:red;color:#fff;">삭제</a>
		</td>
	</tr>
</table>
<a href="/lcomputerstudy/user-login-process.do?u_idx=${user.u_idx}">돌아가기</a>

</body>
</html>