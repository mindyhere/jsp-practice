<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="/jsp03/product_servlet/list.do">상품목록</a> | 
<c:if test="${sessionScope.userid != null }">
	<a href="/jsp03/cart_servlet/list.do">장바구니</a> | 
</c:if>
<div style="text-align: right;">
	<c:choose>
		<c:when test="${sessionScope.userid ==null }">
			<a href="/jsp03/shop/login.jsp">로그인</a> | 
			<a href="/jsp03/shop/admin_login.jsp">관리자 로그인</a> | 
		</c:when>
		<c:otherwise>
			${sessionScope.name }님이 로그인중입니다.
			<a href="/jsp03/login_servlet/logout.do">로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>
<hr>