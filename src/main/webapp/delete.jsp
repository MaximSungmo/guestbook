<%@page import="org.apache.catalina.connector.Request"%>
<%@page import="com.cafe24.guestbook.vo.GuestbookVo"%>
<%@page import="com.cafe24.guestbook.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String no = request.getParameter("no");
	String name = request.getParameter("name");

	GuestbookVo vo = new GuestbookVo();
	vo.setNo(Long.parseLong(no));
	vo.setName(name);
	
	new GuestbookDao().delete(vo);
	

	response.sendRedirect("/guestbook/index.jsp");
%>