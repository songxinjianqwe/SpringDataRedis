<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'input.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function(){
		$("#lastName").blur(function(){
			var lastName = $.trim($(this).val());
			$(this).val(lastName);
			$.post("${pageContext.request.contextPath}/ajaxValidateLastName",{"lastName":lastName},function(result){
				if(result == true){
					$("#lastNameMsg").text("  用户名可用");
				}else if(result == false){
					$("#lastNameMsg").text("  用户名不可用");
				}
			});
		});		
	});
</script>
<body>
	<form:form action="${pageContext.request.contextPath}/emp"  method="post" modelAttribute="employee">
		<fmt:message key="i18n.lastName"></fmt:message>:<form:input id="lastName" path="lastName" /><span id="lastNameMsg" ></span>
		<form:errors path="lastName"></form:errors>
		<br> 
		<fmt:message key="i18n.email">:</fmt:message>:<form:input path="email" />
		<form:errors path="email"></form:errors>
		<br> 
		<fmt:message key="i18n.gender"></fmt:message>:<form:radiobuttons path="gender" items="${genders }"/>
		<br>
		<fmt:message key="i18n.dept"></fmt:message>:
		<form:select path="dept.id" items="${depts}"  itemLabel="departmentName" itemValue="id">
		</form:select>
		<br>
		<fmt:message key="i18n.birthday"></fmt:message>:
		<form:input path="birthday"/>
		<form:errors path="birthday"></form:errors>
		<br>
		<fmt:message key="i18n.salary"></fmt:message>:
		<form:input path="salary"/>
		<br>
		<input type="submit" value="添加员工"/>
	</form:form>
</body>
</html>
