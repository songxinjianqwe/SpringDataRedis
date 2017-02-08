<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		//将列表和编辑表单隐藏
		$("#list").hide();
		$("#input").hide();

		$("#listEmps").click(function() {
			$("#list").show();
			$.post($(this).attr("href"), function(emps) {
				if (emps.length == 0) {
					$("#list").append("<p>没有员工信息。</p>");
				} else {
					$.each(emps,function(i,emp){
						var $tr = $("<tr></tr>");
						$tr.appendTo("#listTab");
						$("<th>"+emp.id+"</th>").appendTo($tr);
						$("<th>"+emp.lastName+"</th>").appendTo($tr);
						$("<th>"+emp.email+"</th>").appendTo($tr);
						$("<th>"+(emp.gender == "M" ? "Male":"Female")+"</th>").appendTo($tr);
						$("<th>"+emp.dept.departmentName+"</th>").appendTo($tr);
						$("<th>"+emp.salary+"</th>").appendTo($tr);
						$("<th><a class= 'update' href='/RestfulSample/emp/'+emp.id+">Edit</a></th>").appendTo($tr);
						$("<th><a class= 'delete' href='/RestfulSample/emp/'+emp.id+''>Delete</a></th>").appendTo($tr);
					});
				}
			}, "json");
			return false;
		});

		$("#addEmp").click(function() {
			$("#input").show();
			return false;
		});




		//将GET请求转为DELETE请求，使用form中转
		$(".delete").click(function() {
			$("#deleteForm").attr("action", $(this).attr("href")).submit();
			return false;
		});
	});
</script>

<body>
	<a id="listEmps" href="/RestfulSample/emps">List All Employees</a>
	<br>
	<a id="addEmp" href="/RestfulSample/emp">Add New Employee</a>
	<br>
	<br>
	<br>

	<div id="list">
		<table id="listTab" border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ID</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Gender</th>
				<th>Department</th>
				<th>Birthday</th>
				<th>Salary</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</table>
	</div>
</body>
</html>