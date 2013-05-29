<%-- List screen for Author entity      --%>
<%-- Created on 22 mai 2013 ( Time 15:26:18 )  --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="utf-8">
	<title>AuthorStore</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Author">
	<meta name="author" content="pbq">
	
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
		var urlBaseWelcome = '<s:url value="/author/search" />';
		var urlBase = '<s:url value="/authorform" />';

		function changeActionAndsubmit(url) {
			document.authorForm.action = urlBase + '/' + url;
			document.authorForm.submit();
		}

		function directDelete(idauthor) {
			document.location = urlBase + '/delete/' + idauthor;
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
          <a class="brand" href="#">Author</a>
          <input type="button" class="btn btn-info" value="Back to Welcome Page" onclick="goHome()" />
        </div>
      </div>
    </div>

    <div class="container">
      	<form:form modelAttribute="authorForm" cssClass="well" method="post" action="/starterKitSpring/authorform/save">
				<input type="text" name="id" value="${authloaded.id}" />
				<input type="text" name="firstName" value="${authloaded.firstName}"/>
				<input type="text" name="lastName" value="${authloaded.lastName}"/>
			<tr>
			<td colspan="2">
				<form:errors/>
			</td>
			</tr>
			<tr>
			<td colspan="2">
					<input type="submit" value="Save" />
			 		<input type="button" class="btn btn-info" value="Delete" onclick="directDelete('${authloaded.id}')" />
				 	<input type="button" class="btn btn-info" value="Search" onclick="changeActionAndsubmit('search')" />
				 	<input type="button" class="btn btn-info" value="All" 	 onclick="directGet('search')" />
			</td>
			</tr>
		</form:form>	
    </div> <!-- /container -->
  </body>
</html>
