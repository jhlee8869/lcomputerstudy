<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
				
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