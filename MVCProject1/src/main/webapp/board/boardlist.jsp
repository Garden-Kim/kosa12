<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
게시글 목록<br/>
<table>
<table border=1 width="600px">
	<thead>
		<tr><th>글번호</th><th>글쓴이</th><th>제목</th><th>조회수</th></tr>
	</thead>
	<tbody>
		<!--  set var=i value=1,2,3,4,5가
								 --> 
		<c:forEach items="${list }" var="dto"> <!--   -->
		<tr><td><a href="boardDetail.kosa?num=${dto.boardNum }">${dto.boardNum }</a></td>
			<td>${dto.boardWrite }</td>
			<td><a href="boardDetail.kosa?num=${dto.boardNum }">${dto.boardSubject }</a></td>
			<td>${dto.visitCount }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</table>
<a href="boardwrite.kosa">게시글쓰기</a><br>
<a href="index.jsp">메인페이지</a>
</body>
</html>