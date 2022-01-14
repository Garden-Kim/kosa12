<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>goodsList.jsp</title>
</head>
<body>
제품리스트 페이지입니다.<br>

<table border=1>
	<tr><td>그림</td><td>이름</td><td>가격</td></tr>
	
<c:forEach items="${list }" var="dto">
      <tr><td><!-- 스플릿과 반복문을 포함시킨 것 -->
         <c:forTokens items="${dto.goodsImages } " delims="`" var="img" begin="0" 
         end="0"> 
      <a href="goodsInfo.gd?num=${dto.goodsNum }"><img src="goods/upload/${img }" height="30px"/></a><br/>
      </c:forTokens>
      </td><td><a href="goodsInfo.gd?num=${dto.goodsNum }">${dto.goodsName }</a></td><td>${dto.goodsPrice }</td></tr>
      </c:forEach>	
</table>


<a href="goodsEnter.gd">제품 등록</a> <br>
</body>
</html>
<%--
<%@ page import="java.util.*, model.DTO.*"%>
<%
	List<GoodsDTO> list = (List<GoodsDTO>)request.getAttribute("list");
	for(GoodsDTO dto: list){
		out.print(dto.getGoodsImages() + "<br>");
		String [] images = dto.getGoodsImages().split("`");
		int i = 0;
		for(String img : images){
			i++;
			if(i <= 1)break;
			out.print(img +"<br>");
			
		}
	}
%>
 --%>
