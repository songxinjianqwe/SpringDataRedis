<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<form:form action="${pageContext.request.contextPath}/emp"  method="post" modelAttribute="employee">
		<form:hidden path="id"/>
		<input type="hidden" name="_method" value="PUT" />
		<br> 
		Email:<form:input path="email" />
		<br> 
		Gender:<form:radiobuttons path="gender" items="${genders }"/>
		<br>
		Department: 
		<form:select path="dept.id" items="${depts }"  itemLabel="departmentName" itemValue="id">
		</form:select>
		<br>
		Birthday:		
		<form:input path="birthday"/>
		<br>
		Salary:
		<form:input path="salary"/>
		<br>
		<input type="submit" value="修改员工信息"/>
	</form:form>
  </body>
</html>
