<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<%
		int c1 = Integer.valueOf((String)request.getAttribute("c1"));
		int c2 = Integer.valueOf((String)request.getAttribute("c2"));
		int c3 = Integer.valueOf((String)request.getAttribute("c3"));
		int c4 = Integer.valueOf((String)request.getAttribute("c4"));
	%>
	
	<span>�質��</span> 
	<%
		for(int i=0; i<c1; i++)
		{%><span>��</span><%}
	%>
	<br/>
	<span>������</span>
	<%
		for(int i=0; i<c2; i++)
		{%><span>��</span><%}
	%>
	<br/>
	<span>�����</span>
	<%
		for(int i=0; i<c3; i++)
		{%><span>��</span><%}
	%>
	<br/>
	<span>�ֿ���</span>
	<%
		for(int i=0; i<c4; i++)
		{%><span>��</span><%}
	%>
</body>
</html>