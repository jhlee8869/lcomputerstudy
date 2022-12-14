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
	
	.div_board-pagination {
		margin:10px auto;
		text-align: center;
		
	}
	
	.board-listapply {
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
	
	.div_board-searchBady {
		margin:10px auto;
		margin-right:50px;
		width:600px;height:30px;
		float:right;
		
	}
	
	.div_board-search-combo1 {
		width:25%;height:25px;
		float:left;
		text-align:center;
		font-size:17px;font-family:굴림;
		
	}
	
	.div_board-search-input1 {
		width:50%;height:25px;
		float:left;
		text-align:center;
		font-size:17px;font-family:굴림;
	}
	
	.div_board-search-button1 {
		width:15%;height:25px;
		float:left;
		text-align:center;
		font-size:17px;font-family:굴림;
	}
	
	.p_inputbox1 {
		border:2px solid #818181;
		width:90%;height:90%;
		text-align:center;
	}
	
	.input-search {
		width:90%;height:90%;
		text-align:center;
	}
	
	.div_board-button {
		margin:20px auto;
		margin-top:50px;
		width:600px;height:30px;
		text-align:center;
		
	}
	
	.div_board-button-two {
		margin:10px auto;
		width:200px;height:30px;
		text-align:center;
		
	}
	
	.a_board-button1 {
		width:40%;height:25px;
		float:left;
		text-align:center;
	}
	
	.a_board-button2 {
		width:40%;height:25px;
		float:right;
		text-align:center;
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
			 	<td><a class=board-listapply href="board-detail.do?b_idx=${board.b_idx}">${board.rownum}</a></td>
			 	<td>${board.user.u_name}</td>
			 	<td><a class=board-listapply href="board-detail.do?b_idx=${board.b_idx}">${board.b_title}</a></td>
				<td>${board.b_date}</td>
				<td>${board.b_viewcount}</td>
		     <tr>
		</c:forEach>	
	</table>
	<div class=div_board-pagination>
		<ul>
			 <c:choose>
				<c:when test="${ pagination.startPage > 1}">
					<li style="">
						<a class=board-listapply href="board-list.do?page=${pagination.prevPage}&searchType=${search.searchType}&searchName=${search.searchName}">◀</a>
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
								<!-- <a class=board-listapply href="board-list.do?page=${i}">${i}</a> -->
								<a class=board-listapply href="board-list.do?page=${i}&searchType=${search.searchType}&searchName=${search.searchName}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			
			 <c:choose>
				<c:when test="${ pagination.nextPage le pagination.lastPage }">
					<li style="">
						<a class=board-listapply href="board-list.do?page=${pagination.nextPage}&searchType=${search.searchType}&searchName=${search.searchName}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>

	<div class="div_board-searchBady">
		<form action="board-list.do" name="board" method="get">
			<div class="div_board-search-combo1">
				<select name=searchType id=searchType>
					<option value="none"> === 선택 === </option>
					<option value="title"> 제목 </option>
					<option value="content" > 내용 </option>
					<option value="write"> 작성자 </option>
					<!-- <option value="title_content"> 제목 + 내용 </option> -->
				</select>
			</div>	
		
			<div class="div_board-search-input1">
				<input class="p_inputbox1" type="text" name=searchName id=searchName value="${search.searchName}" placeholder="검색어를 입력하세요.">
			</div>
		
			<div class="div_board-search-button1">
				<input class=input-search type="submit" value="검색">
			</div>
		</form>
		
	</div>
	
	<div class="div_board-button"> 
		<div class="div_board-button-two">
			<a class="a_board-button1" href="board-insert.do">글쓰기</a>

			<a class="a_board-button2" href="board-home.do">돌아가기</a>
		</div>
	</div>

</body>
</html>