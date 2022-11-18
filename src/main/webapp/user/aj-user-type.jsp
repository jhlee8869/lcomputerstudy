<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<td>${item.u_id}</td>
				<td>${item.u_name}</td>
				<td>

						<div class="usertype1">
								<input class="usertype2" type="checkbox" name=userType2 value="일반회원">일반회원
								<input class="usertype3" type="checkbox" name=userType3 value="관리자">관리자					
						</div>
						
						<div style="display: none;">
							<button type="button" class="userTypechange" u_type="${user.u_type}">권한변경</button>
						</div>
				</td>