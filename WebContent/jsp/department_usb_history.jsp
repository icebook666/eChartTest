<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="java.net.*"%>
<jsp:useBean id="myStr" class="usb.department.DepartmentUSBkanban" scope="page" />
<%
	String classify = request.getParameter("classify");
	String user_id = "10008433";
	Object tt = myStr.dataJsonByDepartmentMember(user_id, classify);
	out.print(tt);
%>

