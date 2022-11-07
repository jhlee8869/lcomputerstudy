<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
				
				<input type="hidden" name="b_idx" value="${board.b_idx}">
				<input type="hidden" name="c_idx" value="${comment.c_idx}">
				
				<c:forEach items="${commentList}" var="comment">
					<div class="comment_body3">
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
							
							<div id="div_commentBd" class="">
								<a class="commentEditForm">수정</a>
							</div>
							
							<div id="div_commentBd" class="">
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

								<textarea rows="1" cols="60"></textarea>
								<button type="button" class="commentEdit" c_group="${comment.c_group}" c_content="${comment.c_content}">수정</button>
								<button type="button">취소</button>

						</div>
					
					</div>					
				</c:forEach>
	<!-- 		
	<script>
	$(document).on('click', '.commentReplyForm', function () {
		
		$(this).parent().parent().next().css('display', '');
	});
	
	$(document).on('click', '.commentEditForm', function () {
		
		$(this).parent().parent().next().next().css('display', '');
	});
	
	
	
	$(document).on('click', '.commentReply', function () {
		let cContent = $(this).attr('c_content');
		let cGroup = $(this).attr('c_group');
		let cOrder = $(this).attr('c_order');
		let cDepth = $(this).attr('c_depth');
		console.log(cGroup);
		console.log(cOrder);
		console.log(cDepth);
		
		$.ajax({
			method: "POST",
			url: "comment-list-process.do",
			data: { b_idx: '${board.b_idx}', c_content: cContent, c_group: cGroup, c_order: cOrder, c_depth: cDepth}
		})
	   .done(function( html ) {
	   		console.log(html);
	   		$('#div_bd4').html(html);
	   });
		
		$(this).parent().submit();
	});
	
	$(document).on('click', '.commentEdit', function () {
		let cGroup = $(this).attr('c_group');
		let cContent = $(this).attr('c_content');
		console.log(cGroup);
		
		$.ajax({
			method: "POST",
			url: "comment-edit.do",
			data: { b_idx: '${board.b_idx}', c_content: cContent, c_group: cGroup}
		})
	   .done(function( html ) {
	   		console.log(html);
	   		$('#div_bd4').html(html);
	   });
		$(this).parent().submit();
	});
	</script>
	 -->	