<%@page import="com.cafe24.guestbook.vo.GuestbookVo"%>
<%@page import="java.util.List"%>
<%@page import="com.cafe24.guestbook.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	GuestbookDao dao = new GuestbookDao();
	List<GuestbookVo> list = dao.getList();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="/guestbook/add.jsp" method="post">
		<table border=1 width=500>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan=4 align=right><input type="submit" VALUE=" íì¸ "></td>
			</tr>
		</table>
	</form>
	<%
		int count = list.size();
		int index = 0;
		for (GuestbookVo vo : list) ;
	%>
	<br>
	<table width=510 border=1>
		<tr>
			<td>[1]</td>
			<td>안대혁</td>
			<td>2013-01-15</td>
			<td><a href="deleteform.jsp">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4>안녕하세요.</td>
		</tr>
	</table>
</body>
</html>