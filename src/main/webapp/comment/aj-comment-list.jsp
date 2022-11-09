<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<input type="hidden" name="b_idx" value="${board.b_idx}">
		<div class="div_body">	
		
			<div class="div_title">
				<p class=p_title> 제목 : ${board.b_title}</p>
			</div>
			<div class="div_writer">
				<p class=p_writer>작성자 : ${board.user.u_name}</p>
			</div>
	
			<div class="div_date">
				<p class=p2>작성일자 : ${board.b_date}</p>
			</div>
			<div class="div_line">
				<p class=p_line></p>
			</div>
			<div class="div_content">
				<p class=p_content> 내용 : ${board.b_content}</p>
			</div>
		</div>
		
		<div id="div_body_all">
			<div id="div_body1">
				<div id="div_bd" class="div_bd_edit">
					<a href="board-edit.do?b_idx=${board.b_idx}">수정</a>
				</div>
				<div id="div_bd" class="div_bd_delete">
					<a href="board-delete.do?b_idx=${board.b_idx}">삭제</a>
				</div>
				<div id="div_bd" class="div_bd_insert">
					<a href="board-insert.do?b_idx=${board.b_idx}">글쓰기</a>
				</div>
				<div id="div_bd2" class="div_bd_reply">
					<a href="board-reply.do?b_idx=${board.b_idx}&b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}">답글달기</a>
				</div>
				<div id="div_bd2" class="div_bd_list">
					<a href="board-list.do">목록보기</a>
				</div>
			</div>
		</div>

	<div id="div_body_all2">
		<form action="comment-insert-process.do" name="comment" method="post">
		<input type="hidden" name="b_idx" value="${board.b_idx}">
		<input type="hidden" name="c_idx" value="${comment.c_idx}">

			<div id="div_body2">
				<p class=p_comment> <input class=input_inp1 type="text" name="c_content" value="${comment.c_content}" placeholder="댓글을 입력해주세요."></p>
			</div>
		
			<div id="div_body3">
				<div id="div_bd3" class="div_bd_comment">		
					<p class="p_comment_write"><input type="submit" value="댓글달기"></p>
				</div>
			</div>

			<div id="div_bd4" class="div_bd_comment_list">
				
				<c:forEach items="${commentList}" var="comment">
					<div id="div_bd5" class="comment_body3">
						<div class="comment-box">
							<p>작성자:${comment.user.u_name}</p>
						</div>
						
						<div class="comment-box">
							<p>작성일자:${comment.c_date}</p>
						</div>
						
						<div class="comment-box">
							<p>내용:${comment.c_content}</p>
						</div>
						
						<div class="comment-box">
							
							<div id="div_commentBd" class="">
								<a class="commentReplyForm">답변</a>
							</div>
							
							<div id="div_commentBd2" class="">
								<a class="commentEditForm">수정</a>
							</div>
							
							<div id="div_commentBd3" class="">
								<!--<a href="comment-delete.do?b_idx=${board.b_idx}&c_idx=${comment.c_idx}">삭제</a> -->
								<a href="comment-delete.do?b_idx=${comment.b_idx}&c_group=${comment.c_group}">삭제</a>
							</div>
						</div>
						
						<div style="display: none;">

								<textarea rows="1" cols="60"></textarea>
								<button type="button" class="commentReply" c_group="${comment.c_group}" c_order="${comment.c_order}" c_depth="${comment.c_depth}">등록</button>
								<button type="button">취소</button>
								
						</div>
						
						<div style="display: none;">

								<textarea rows="1" cols="60" ></textarea>
								<button type="button" class="commentEdit" c_idx="${comment.c_idx}" c_content="${comment.c_content}">수정</button>
								<button type="button">취소</button>

						</div>
					
					</div>
				</c:forEach>
			</div>
		
		</form>
	</div>