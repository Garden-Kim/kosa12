<%@page import="model.DTO.AuthInfo"%>
<%@page import="model.DAO.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%
	Cookie [] cookies = request.getCookies();
	if(cookies != null && cookies.length > 0){
		for(Cookie c: cookies){
			if(c.getName().equals("storeId")){
				request.setAttribute("isId", c.getValue());
				out.print(c.getValue());
			}
			if(c.getName().equals("autoLogin")){
				LoginDAO dao = new LoginDAO();
				AuthInfo authInfo =  dao.loginCk(c.getValue(), "abcd");
				session.setAttribute("authInfo", authInfo);
			}
		}
	}

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
메인페이지 입니다. <br>
<!-- 로그인되지 않은 상태 -->
<c:if test="${empty authInfo }">
<form action="LoginPro.login" method="post" name="frm" id="frm">
<table border = 1>
<tr><td colspan=2>
		<input type="checkbox" value="autoLogin" name="autoLogin"> 로그인유지 |
		<input type="checkbox" value="store" name="storeId" <c:if test="${!empty isId }">checked</c:if> >아이디저장
	</td></tr>
	
<tr><td>
		<input type="text" name="id" placeholder="아이디" value="${isId }"/>
	</td>
	<td rowspan=2>
		<input type="image" src="images/blueheart.png" width="50px" height="50px"/>	
	</td>
	
</tr>

<tr><td>
		<input type="password" name="pw" placeholder="비밀번호">	
	</td>
</tr>
<tr>
	<td colspan=2>아이디/비밀번호찾기 | 	
	<a href="memberAgree.mem">회원가입</a></td></tr>
</table>
</form>
</c:if>
<!-- 로그인된 상태 -->
<c:if test="${!empty authInfo }">
${authInfo.userId }님 방문을 환영합니다.<br>
<a href="boardList.kosa">게시글 목록</a> |
	<c:if test="${authInfo.grade == 'emp' }">
		<a href="employeeList.emp">직원 리스트</a> |
		<a href="memberList.mem">회원리스트</a> |
		<a href="goodsList.gd">상품리스트</a> 
	</c:if>
	
	<c:if test="${authInfo.grade == 'mem' }">
		<a href="mypage.html">마이페이지</a> |
	</c:if>
<a href="logout.login">로그아웃</a>
</c:if>
</body>
</html>