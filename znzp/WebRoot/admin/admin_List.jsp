<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'Book_List.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css" />
	</head>

	<body style="text-align: center" onload="noteAlert()">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0" style="margin-bottom: 8px">
			<tr>
				<td>
					<div style='float: left'>
						<img height="16" src="<%=path%>/images/add.png" width="16" />
						&nbsp;
						<strong>administrator list</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" background="<%=path%>/images/sp_bg.gif" style='padding: 0px'>
				</td>
			</tr>
		</table>
		<table class="table">
			<thead>
				<tr>
					<td style="width: 5%;" class="gvtitle">
						number
					</td>
					<td style="width: 10%;" class="gvtitle">
						account
					</td>
					<td style="width: 15%;" class="gvtitle">
						password
					</td>
					
					<td style="width: 15%;" class="gvtitle">
						Name
					</td>
					
                    <td style="width: 15%;" class="gvtitle">
						creation date
					</td>
					<td class="gvtitle" style="width: 10%;">
						operation
					</td>
				</tr>
				<c:forEach var="list" items="${AdminList}" varStatus="status">
					<tr>
						<td>
							${status.count}
						</td>
						<td>
							${list.LOGINNAME}
						</td>
						<td>
							${list.LOGINPSW}
						</td>
						<td>
							${list.USERNAME}
						</td>
						<td>
							${list.CREATETIME}
						</td>
						<td>
							<a href="<%=path%>/AdminServlet?action=edit&id=${list.ID}">Edit</a>&nbsp;|&nbsp;
							<a href="<%=path%>/AdminServlet?action=delete&id=${list.ID}">Delete</a>

						</td>

					</tr>
				</c:forEach>
			</thead>
			<tbody>
			</tbody>
		</table>

	</body>
</html>
<script>
	//
	function noteAlert() {
 
		var alertNote = "${alertNote}";
		if (alertNote == "1") {
			alert("success!");

		} else if (alertNote == "0") {
			alert("Failed ,please contact Admin!");
		}
	}
</script>