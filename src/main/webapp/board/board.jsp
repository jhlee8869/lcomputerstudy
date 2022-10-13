<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<style>
	// 추가
	h1 {
		text-align:center;
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
	
	// 추가
	ul {
		width:600px;
		height:100px;
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
	
		
</style>
<body>
	<h1>자유게시판</h1>
	<table >
		<tr>
			<td colspan="5">전체 게시글 수 : ${pagination.count}</td>
		<tr>
		
		<tr>
			<th>No</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일시</th>
			<th>조회수</th>
		</tr>
			 	
		<c:forEach items="${list}" var="board" varStatus="status">
			 <tr>
			 	<td><a href="board-detail.do?b_idx=${board.b_idx}">${board.rownum}</a></td>
			 	<td>${board.user.u_name}</td>
			 	<td><a href="board-reply.do?b_idx=${board.b_idx}">${board.b_title}</a></td>
				<td>${board.b_date}</td>
				<td>${board.b_viewcount}</td>
		     <tr>
		</c:forEach>	
	</table>
	<div>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.startPage > 1}">
					<li style="">
						<a href="board-list.do?page=${pagination.prevPage}">◀</a>
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
								<a href="board-list.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			
			 <c:choose>
				<c:when test="${ pagination.nextPage le pagination.lastPage }">
					<li style="">
						<a href="board-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
	<table>
		<tr>
			<td style=border:1pxsolid#818181;width:70px;text-align:center;>
				<a href="board-insert.do">글쓰기</a>
			</td>
		</tr>
	</table>
	<table style=float:left;>
		<tr>
			<td style=border:1pxsolid#818181;width:70px;text-align:center;>
				<a href="board-home.do">돌아가기</a>
			</td>
		</tr>
	</table>
</body>
</html>