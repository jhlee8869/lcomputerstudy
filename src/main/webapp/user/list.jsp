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
	
	.div_user-pagination {
		margin:10px auto;
		width: 600px;
		height: 60px;
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
		width:300px;height:30%;
		text-align:center;
	}
	.usertype2 {
		margin:auto;
		width:7%;height:20%;
		text-align:center;
	}
	.usertype3 {
		margin:auto;
		width:7%;height:20%;
		text-align:center;
	}
	.usertype4 {
		margin:auto;
		width:30%;height:20%;
		text-align:center;
	}
		
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<body>
<h1>회원 목록</h1>

<div id="utest1">
<form action="user-list.do" name="user" method="post">
	<table>
		
		<tr>
			<td colspan="4">전체 회원 수 : ${pagination.count}</td>
		<tr>
		
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
			<th>권한</th>
		</tr>
	

		<c:forEach items="${list}" var="user" varStatus="status">

			 <tr>
			 	<td><a class=user-listapply href="user-detail.do?u_idx=${user.u_idx}">${user.rownum}</a></td>
				<td>${user.u_id}</td>
				<td>${user.u_name}</td>
				<td>
						<div id="displayShow1" class="usertype1">
						<p>${user.u_type}</p>
							
							<c:if test="${user.u_type == 2}">
								<input class="userType3" type="button" name="userType3" value="관리자">

							</c:if>
							
							<c:if test="${user.u_type == 1}">
								<input class="userType2" type="button" name="userType2" value="일반회원">
							</c:if>
							<!--
							<c:if test="${user.u_type == 0}">
								<input class="userType4" type="button" name="userType4" value="일반회원">
							</c:if>
 							-->
						</div>
						
						<div id="displayShow2" style="display: none;">
							<button type="button" class="userTypechange1" u_type="${user.u_type}" u_idx="${user.u_idx}" page="${pagination.page}">일반회원</button>
						</div>
						<div id="displayShow3" style="display: none;">
							<button type="button" class="userTypechange2" u_type="${user.u_type}" u_idx="${user.u_idx}" page="${pagination.page}">관리자</button>
						</div>
						
				</td>
		     </tr>
		</c:forEach>

	</table>
</form>
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
				<c:when test="${ pagination.nextPage le pagination.lastPage }">
					<li style="">
						<a class=user-listapply href="user-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	
	
	<div class="div_user-button"> 
		<div class="div_user-button-two">
			<a class="a_user-button1" href="user-login-process.do">돌아가기</a>

		</div>
	</div>
</div>
</div>
	<script>
	
	$(document).on('click', '.userType2', function () {
		$(this).parent().next().next().css('display', 'block');	
	});
	
	$(document).on('click', '.userType3', function () {

		$(this).parent().next().css('display', 'block');
	});	
	
	$(document).on('click', '.userTypechange1', function () {
		let uType = 1;
		let uIdx = $(this).attr('u_idx');
		let Page = $(this).attr('page'); 
		console.log(uType);
		console.log(uIdx);
		
		$.ajax({
			method: "POST",
			url: "user-list-process.do",
			<!-- data: { u_idx: '${user.u_idx}', u_type: uType} -->
			data: { u_idx: uIdx, u_type: uType, page: Page}
		})
	   .done(function( html ) {
	   		console.log(html);
	   		$('#utest1').html(html);
	   });
		
		$(this).parent().submit();
	});
	
	$(document).on('click', '.userTypechange2', function () {
		let uType = 2;
		let uIdx = $(this).attr('u_idx');
		let Page = $(this).attr('page'); 
		console.log(uType);
		
		$.ajax({
			method: "POST",
			url: "user-list-process.do",
			<!-- data: { u_idx: '${user.u_idx}', u_type: uType} -->
			data: { u_idx: uIdx, u_type: uType, page: Page}
		})
	   .done(function( html ) {
	   		console.log(html);
	   		$('#utest1').html(html);
	   });
		
		$(this).parent().submit();
	});
	
	</script>
	
</body>
</html>