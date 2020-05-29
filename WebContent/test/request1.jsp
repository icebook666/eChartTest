<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String myresult = "";
	if (request.getAttribute("myresult")!=null)
		myresult = request.getAttribute("myresult").toString();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test Get/Set Attribute</title>
</head>
<body>
	<form action="./forward.jsp" method="post">
		查詢BU：<input type="text" name="bu_code"><input type="submit" name="SB" value="Search">
	</form>
	<table>
		<tr>
			<td>My Result</td>
			<td><%=myresult%></td>
		</tr>
	</table>
</body>
</html>