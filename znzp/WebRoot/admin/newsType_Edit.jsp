<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title></title>
		<base target="_self">
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css" />

	</head>
	<body leftmargin="8" topmargin='8' onload="noteAlert()">
		<form id="form" action="NewsTypeServlet?action=editSave" method="post">
		<input type="hidden" name="id" id="id" value='${newsType.id}'/>
			<table width="98%" border="0" align="center" cellpadding="0"
				cellspacing="0" style="margin-bottom: 8px">
				<tr>
					<td>
						<div style='float: left'>
							<img height="16" src="<%=path%>/images/add.png" width="16" />
							&nbsp;
					
						</div>
					</td>
				</tr>
				<tr>
					<td height="1" background="<%=path%>/images/sp_bg.gif" style='padding: 0px'>
					</td>
				</tr>
			</table>
			<table width="98%" align="center" border="0" cellpadding="4"
				cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom: 8px">
				<tr>
					<td colspan="4" background="<%=path%>/images/wbg.gif" class='title'>
						<div style='float: left'>
							<span>required<font color="red">(*)</font> </span>
						</div>
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td width="12%" align="center">
						type name:
					</td>
					<td width="38%">						 
		              <input type="text" ID="typename" name="typename"  value='${newsType.typename}'  Height="21px" Width="196px" />				
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
						<a href="<%=path%>/NewsTypeServlet?action=list" target="right">return</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
<script>
	            
	function noteAlert() {
		var alertNote = "${alertNote}";
		if (alertNote == "1") {
			alert("success!");

		} else if (alertNote == "0") {
			alert("failed， please contact admin!");
		}
	}
</script>
