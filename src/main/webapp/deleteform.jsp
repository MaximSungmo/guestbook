<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ë°©ëªë¡</title>
</head>
<body>
	<form method="post" action="/guestbook/delete.jsp">
	<input type='hidden' name="id" value="<%=no%>">
	<table>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password"></td>
			<td><input type="submit" value="íì¸"></td>
			<td><a href="/guestbook">메인으로 돌아가기</a></td>
		</tr>
	</table>
	</form>
</body>
</html>