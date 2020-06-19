<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

		<title>My JSP 'news_List.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="stylesheet" type="text/css" href="css/base.css" />
	</head>

	<body style="text-align: center" onload="noteAlert()">
		<table width="98%" border="0" align="center" cellpadding="0"
			cellspacing="0" style="margin-bottom: 8px">
			<tr>
				<td>
					<div style='float: left'>
						<img height="16" src="images/add.png" width="16" />
						&nbsp;
						<strong>Job Infos</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" background="images/sp_bg.gif" style='padding: 0px'>
				</td>
			</tr>
		</table>
		<table class="table">
			<thead>
				<tr>
					<td style="width: 5%;" class="gvtitle"> 
						Number 
					</td>
					<td style="width: 10%;" class="gvtitle">
						Domain
					</td>
					<td style="width: 10%;" class="gvtitle">
						Title
					</td>
				<td style="width: 10%;" class="gvtitle">
						Name
					</td>
					<td style="width: 15%;" class="gvtitle">
						CV
					</td>
                     <td style="width: 10%;" class="gvtitle">
						Post Time
					</td>
					<td class="gvtitle" style="width: 10%;">
						Action
					</td>
				</tr>
				<c:forEach var="list" items="${news1List}" varStatus="status">
					<tr>
						<td>
							${status.count}
						</td>
						<td>
							${list.TYPENAME}
						</td>
						<td>
							${list.TITLE}
						</td>				
						<td>
							${list.USERNAME}
						</td>
                         <td>
							${(list.CONTENT)}

						</td>
						<td>
							${(list.CREATETIME)}

						</td>
						<td>

							<a href="<%=path%>/News1Servlet?action=delete&id=${list.ID}">Delete</a>&nbsp;

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
	function noteAlert() {

		var alertNote = "${alertNote}";
		if (alertNote == "1") {
			alert("Success!");

		} else if (alertNote == "0") {
			alert("failed， please contact admin!");
		}
	}
</script>