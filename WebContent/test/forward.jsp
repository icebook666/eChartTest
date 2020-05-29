<%@ page contentType="text/html;charset=UTF-8"%>
<%
	
	request.setCharacterEncoding("UTF-8");
	String bu_code = "";
	if (request.getParameter("bu_code")!=null)
		bu_code = request.getParameter("bu_code").toString();
	String new_url =  "./request1.jsp";
	
	String result = "";
	if (!bu_code.equals(""))
		result = "Your input data is " + bu_code;
	
	request.setAttribute("myresult", result);
	RequestDispatcher dispatcher = request.getRequestDispatcher(new_url);
	dispatcher.forward(request, response);
%>
