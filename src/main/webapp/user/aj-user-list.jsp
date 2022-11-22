<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		
		<tr id=trlistall>
			<td colspan="4">전체 회원 수 : ${pagination.count }</td>
		<tr>
		
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
			<th>권한</th>
		</tr>
		
		<c:forEach items="${list}" var="user" varStatus="status">

			 <tr id=trlist>
			 	<td><a class=user-listapply href="user-detail.do?u_idx=${user.u_idx}">${user.rownum}</a></td>
				<td>${user.u_id}</td>
				<td>${user.u_name}</td>
				<td id="tdlist1">
					<form action="user-list.do" name="user" method="post">
						<div id="displayShow1" class="usertype1">
						<p>${user.u_type}</p>
							
							<c:if test="${user.u_type == 1}">
								<input class="userType3" type="button" name="userType3" value="관리자">

							</c:if>
							
							<c:if test="${user.u_type == 2}">
								<input class="userType2" type="button" name="userType2" value="일반회원">
							</c:if>
							
							<c:if test="${user.u_type == 0}">
								<input class="userType4" type="button" name="userType4" value="일반회원">
							</c:if>

						</div>
						
						<div id="displayShow2" style="display: none;">
							<button type="button" class="userTypechange1" u_type="${user.u_type}" u_idx="${user.u_idx}">일반회원</button>
						</div>
						<div id="displayShow3" style="display: none;">
							<button type="button" class="userTypechange2" u_type="${user.u_type}" u_idx="${user.u_idx}">관리자</button>
						</div>
					</form>
				</td>
		     </tr>
		</c:forEach>
