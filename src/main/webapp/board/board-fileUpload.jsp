<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<!DOCTYPE html>
<%
	String saveDir = "C:\\Users\\l5-morning";
	int size = 10 * 1024 * 1024;
	MultipartRequest multi = new MultipartRequest(request, saveDir, size, "UFT-8", new DefaultFileRenamePolicy());
	String name = multi.getFilesystemName("b_filename");
	String origin = multi.getOriginalFileName("b_filename");
	long fileSize = multi.getFile("b_filename").length();
%>
<html>
<head>
<meta charset="UTF-8">

<title>파일 업로드</title>
</head>
<body>

</body>
</html>