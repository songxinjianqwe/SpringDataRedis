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

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		//将GET请求转为DELETE请求，使用form中转
		$(".delete").click(function() {
			$("#deleteForm").attr("action", $(this).attr("href")).submit();
			return false;
		});
	});
</script>
<body>
	<!-- 用于提交DELETE请求 -->
	<form id="deleteForm" action="" method="post">
		<input type="hidden" name="_method" value="DELETE" />
	</form>

	<c:if test="${empty requestScope.emps }">
  		没有员工信息。
  	</c:if>
	<c:if test="${!empty requestScope.emps }">
		<table border="1" cellpadding="10" cellspacing="0">
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
			<c:forEach var="emp" items="${requestScope.emps.content}">
				<tr>
					<th>${emp.id }</th>
					<th>${emp.lastName }</th>
					<th>${emp.email }</th>
					<th>${emp.gender == "M" ? "Male":"Female"}</th>
					<th>${emp.dept.departmentName}</th>
					<th>${emp.birthday }</th>
					<th>${emp.salary }</th>
					<th><a href="<c:url value='/emp/${emp.id}'/>">Edit</a></th>
					<th><a class="delete" href="<c:url value='/emp/${emp.id}'/>">Delete</a></th>
				</tr>
			</c:forEach>
		</table>

		<!-- 底部页码选择区域 -->
		<p style="text-align: center;">
			第${emps.number+1 }页/共${emps.totalPages }页 
			<a href="<c:url value='/emps?pageNum=0'/>">首页</a>
			<c:if test="${emps.number+1 > 1 }">
				<a href="<c:url value='/emps?pageNum=${emps.number-1 }'/>">上一页</a>
			</c:if>

			<%-- 页码列表（显示多少个页码）的长度自己定，这里定为10个 --%>
			<c:choose>
				<%-- 第一条：如果总页数<=10，那么页码列表为1 ~ tp --%>
				<c:when test="${emps.totalPages <= 10}">
					<c:set var="begin" value="1" />
					<c:set var="end" value="${emps.totalPages }" />
				</c:when>
				<c:otherwise>
					<%-- 第二条：按公式计算，让列表的头为当前页-4；列表的尾为当前页+5 --%>
					<c:set var="begin" value="${emps.number-3 }" />
					<c:set var="end" value="${emps.number+6 }" />

					<%-- 第三条：第二条只适合在中间，而两端会出问题。这里处理begin出界！ --%>
					<%-- 如果begin<1，那么让begin=1，相应end=10 --%>
					<c:if test="${begin<1 }">
						<c:set var="begin" value="1" />
						<c:set var="end" value="10" />
					</c:if>
					<%-- 第四条：处理end出界。如果end>tp，那么让end=tp，相应begin=tp-9 --%>
					<c:if test="${end>emps.totalPages }">
						<c:set var="begin" value="${emps.totalPages-9 }" />
						<c:set var="end" value="${emps.totalPages }" />
					</c:if>
				</c:otherwise>
			</c:choose>

			<%-- 循环显示页码列表 --%>
			<c:forEach begin="${begin }" end="${end }" var="i">
				<c:choose>
					<c:when test="${i eq (emps.number+1) }">${i }</c:when>
					<c:otherwise>
						<a href="<c:url value='/emps?pageNum=${i-1}'/>">[${i }]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<%--显示下一页和尾页 --%>
			<c:if test="${emps.number < emps.totalPages-1 }">
				<a href="<c:url value='/emps?pageNum=${emps.number+1 }'/>">下一页</a>
			</c:if>
			
			<a href="<c:url value='/emps?pageNum=${emps.totalPages-1 }'/>">尾页</a>
		</p>
	</c:if> 
</body>