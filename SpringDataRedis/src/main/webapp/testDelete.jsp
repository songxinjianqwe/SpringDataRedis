<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'testDelete.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("#a").click(function() {
			$.ajax({
				url : "/RestfulSample/emp/1",
				dataType : "json",
				type : "delete",
				headers : {
					'Accept' : 'application/json',
					'Content-Type' : 'application/json'
				},
				success : function() {
					alert("ok");
				},
				error : function(data) {
					alert(data.codes);
				}
			});
		});
	});
</script>
  <body>
  	<a id="a" >哒哒哒</a>
  </body>
</html>
