<%-- List screen for Author entity      --%>
<%-- Created on 20 nov. 2013 ( Time 16:12:07 )  --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="utf-8">
	<title>AuthorStore</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="Author">
	
		<!-- Le style -->
		
		<link href="<c:url value="/static/css/bootstrap-responsive.min.css" />" rel="stylesheet"  type="text/css" />   
		<link href="<c:url value="/static/css/styles.css" />" rel="stylesheet"  type="text/css" />  
		<link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet"  type="text/css" />  
		
		<script type="text/javascript" src="/static/javascript/validation.js"></script>
		<script type="text/javascript" src="/static/javascript/jquery.js"></script>
		<script type="text/javascript" src="/static/javascript/bootstrap.js"></script>

		<style>
			body {
				padding-top: 60px;
				/* 60px to make the container go all the way to the bottom of the topbar */
			}
		</style>
		
		<script type="text/javascript">
		var urlBaseWelcome = '<c:url value="/" />';
		var urlBase = '<c:url value="/author" />';
		
			function changeActionAndsubmit(url) {
				document.forms[0].action = urlBase + '/' + url;
			    document.forms[0].submit();
			}
			
			function directDelete(idauthor) {
				if (idauthor == 0){
					goHome();
				}else{
					document.location = urlBase + '/delete/' + idauthor ;
				}
			}
			
			function directGet(url) {
				document.location = urlBase + '/' + url;
			}
			
			function goHome() {
				document.location = urlBaseWelcome;
			}
		</script>
</head>

<body>
	    <div class="navbar navbar-fixed-top">
	      <div class="navbar-inner">
	        <div class="container">
	          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </a>
	          <a class="brand" href="#">AuthorList</a>
	          <input type="button" class="btn btn-info" value="All" 	 onclick="directGet('list')" />
	          <input type="button" class="btn btn-info" value="Back to Welcome Page" 	 onclick="goHome()" />
	        </div>
	      </div>
	    </div>

	<div class="container">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>id</th>
						<th>firstName</th>
						<th>lastName</th>
						<th><a class="btn btn-info" href='<c:url value="create"/>'>new</a></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listAuthors}" var="author" varStatus="status">
						<tr>
							<td align="center">${status.count}</td>
							<td>${author.id}</td>
							<td>${author.firstName}</td>
							<td>${author.lastName}</td>
							<c:url var="editAuthorUrl" value="/author/edit/${author.id}"/>
							<td><a class="btn btn-info" href="${editAuthorUrl}">Edit</a></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<!-- /container -->

</body>
</html>