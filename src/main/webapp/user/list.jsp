<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록2</title>
</head>
<style>
	// 추가
	h1 {
		text-align:center;
	}
	table {
		border-collapse:collapse;
		margin:40px auto;
	}
	//
	
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	
	// 추가
	ul {
		width:600px;
		height:50px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
	}
	
	div {
		width: 600px;
		height: 60px;
	}
	
	.div_user-pagination {
		margin:10px auto;
		text-align: center;
		
	}
	
	.user-listapply {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
	
	.div_user-button {
		margin:20px auto;
		margin-top:50px;
		width:600px;height:30px;
		text-align:center;
		
	}
	
	.div_user-button-two {
		margin:10px auto;
		width:200px;height:30px;
		text-align:center;
		
	}
	
	.a_user-button1 {
		width:40%;height:25px;
		float:left;
		text-align:center;
	}
	.usertype1 {
		margin:auto;
		width:400px;height:30%;
		text-align:center;
	}
	.usertype2 {
		margin:auto;
		width:40%;height:20%;
		text-align:center;
	}
		
</style>
<body>
<h1>회원 목록</h1>
	<table >
		
		<tr>
			<td colspan="4">전체 회원 수 : ${pagination.count }</td>
		<tr>
		
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
			<th>권한</th>
		</tr>
		<c:forEach items="${list}" var="item" varStatus="status">
			 <tr>
			 	<td><a class=user-listapply href="user-detail.do?u_idx=${item.u_idx}">${item.rownum}</a></td>
				<td>${item.u_id}</td>
				<td>${item.u_name}</td>
				<td>
					<form action="" name="user" method="post">
						<div class="usertype1">
							<div class="usertype2">
							<input type="checkbox" name=userType value="일반회원">일반회원
							<input type="checkbox" name=userType value="관리자">관리자					
							<input class="usertype2" type="submit" value="권한변경">
							</div>
						</div>
					</form>
				</td>
		     <tr>
		</c:forEach>
	</table>
	
	<div class=div_user-pagination>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.startPage > 1}">
					<li style="">
						<a class=user-listapply href="user-list.do?page=${pagination.prevPage}">◀</a>
					</li>
				</c:when>
			</c:choose>
			 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				
					<c:choose>
						<c:when test="${ pagination.page eq i }">
							
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.page ne i }">
							<li>
								<a class=user-listapply href="user-list.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			
			 <c:choose>
				<c:when test="${ pagination.nextPage lt pagination.lastPage }">
					<li style="">
						<a class=user-listapply href="user-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
	
	<div class="div_user-button"> 
		<div class="div_user-button-two">
			<a class="a_user-button1" href="user-login-process.do">돌아가기</a>

		</div>
	</div>
</body>
</html>