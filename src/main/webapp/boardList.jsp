<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
</head>
<body>
	<h2>게시판 리스트</h2>
	<hr>
	<table border="1" cellspacing="0" cellpadding="0">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>글쓴이</th>
			<th>글등록일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach items="${boardDtos}" var="boardDto">
			<tr>
				<td>${boardDto.bnum }</td>
				<td>${boardDto.btitle }</td>
				<td>${boardDto.memberid }</td>
				<td>${boardDto.bdate }</td>
				<td>${boardDto.bhit }</td>
			</tr>
		</c:forEach>
	
	</table>
	<hr>
	<c:forEach begin="1" end="${totalPage}" var="i">
		<c:choose>
			<c:when test="${currentPage == i}">
				<a href="boardlist?page=${i}"style="color: red; text-decoration: underline; font-weight: bold;"> ${i} </a>
			</c:when>
				
			<c:otherwise>
				<a href="boardlist?page=${i}" style="text-decoration: none;"> ${i} </a> 
			</c:otherwise>
		</c:choose>
		
	</c:forEach>
</body>
</html>