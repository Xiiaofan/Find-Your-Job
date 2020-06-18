<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script type="text/javascript" src="<%=path%>/ckeditor/ckeditor.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css" />

		<title>Insert title here</title>
	</head>
	<body leftmargin="8" topmargin='8'>
		<form id="form" action="<%=path%>/NewsServlet?action=add"
			method="post">
			<table width="98%" border="0" align="center" cellpadding="0"
				cellspacing="0" style="margin-bottom: 8px">
				<tr>
					<td>
						<div style='float: left'>
							<img height="16" src="<%=path%>/images/add.png" width="16" />
							&nbsp;
							<strong>add -news</strong>
						</div>
					</td>
				</tr>
				<tr>
					<td height="1" background="<%=path%>/images/sp_bg.gif"
						style='padding: 0px'>
					</td>
				</tr>
			</table>
			<table width="98%" align="center" border="0" cellpadding="4"
				cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom: 8px">
				<tr>
					<td colspan="4" background="<%=path%>/images/wbg.gif" class='title'>
						<div style='float: left'>
							<span>required <font color="red">(*)</font> </span>
						</div>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="10%" align="center">
						title:
					</td>

					<td width="90%">
						<input type="text" ID="title" name="title" Height="21px"
							style="width: 200px" />

					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="12%" align="center">
						type:
					</td>
					<td width="38%">
						<select id="newstypeid" name="newstypeid">
							<option value="">
								--choose--
							</option>
							<c:forEach items="${newsTypeList}" var="newsType">
								<option value="${newsType.ID}">
									${newsType.TYPENAME}
								</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="10%" align="center">
						picture:
					</td>
					<td width="90%">
						<input type="text" ID="imgpath" name="imgpath" Height="21px"
							readonly="readonly" style="width: 200px" />
						<input type="button" value="choose picture" onclick="openDLG()" />
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="10%" align="center" >
						news content:
					</td>
					<td width="90%">
						<textarea id="content" name="content" rows="" cols=""></textarea>
					</td>
				</tr>

				<tr bgcolor="#FFFFFF">
					<td align="center">
					</td>
					<td colspan="3">
						&nbsp;&nbsp;
						<br />
						<input type="submit" value="save" />
						&nbsp;
						<a href="<%=path%>/NewsServlet?action=list" target="right">return</a>
					</td>
				</tr>
			</table>

		</form>
	</body>
</html>
<script type="text/javascript">
	window.onload = function() {
		CKEDITOR.replace("content");
		var alertNote = "${alertNote}";
		if (alertNote == "1") {
			alert("sucess!");

		} else if (alertNote == "0") {
			alert("failed,contact Admin!");
		}
	}
	function openDLG() {
		t = window
				.showModalDialog('<%=path%>/common/imageUpload.jsp', '',
						'dialogHeight:100px; dialogWidth:400px;dialogTop:250px;dialogLeft:500px;')
		//for Chrome  
		if (t == undefined) {
			t = window.returnValue;
		}
		document.getElementById("imgpath").value = t;
	}
</script>