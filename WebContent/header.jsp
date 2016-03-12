<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="spms.vo.Member" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div style="background-color:#00008b;color:#ffffff;height:20px;padding:5px;">
SPMS(Simple Project Menagememt System)
<jsp:useBean id="member" scope="session" class="spms.vo.Member"></jsp:useBean>
	<%
	if(member.getEmail() != null) {
	%>
	<span style="float:right;"><%=member.getName() %>님, 반갑습니다. <a style="color:white;" href="<%=request.getContextPath()%>/auth/logout">로그아웃</a>
	</span> 
	<%}else{ %>
	<span style="float:right;"><a style="color:white;" href="<%=request.getContextPath()%>/auth/login">로그인</a>
	</span>
	<%} %>
</div>