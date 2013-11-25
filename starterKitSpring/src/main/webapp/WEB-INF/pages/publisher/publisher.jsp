<%-- Detail screen for Publisher entity      --%>
<%-- Created on 22 nov. 2013 ( Time 17:59:38 )  --%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>PublisherStore</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="Publisher">
	
		<!-- Le style -->
		
		<link href="<c:url value="/css/bootstrap-responsive.min.css" />" rel="stylesheet"  type="text/css" />   
		<link href="<c:url value="/css/styles.css" />" rel="stylesheet"  type="text/css" />  
		<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet"  type="text/css" />  
		
		<script type="text/javascript" src="/js/validation.js"></script>
		<script type="text/javascript" src="/js/jquery.js"></script>
		<script type="text/javascript" src="/js/bootstrap.js"></script>
	
		<style>
			body {
				padding-top: 60px;
				/* 60px to make the container go all the way to the bottom of the topbar */
			}
		</style>
	
		<script type="text/javascript">
			var urlBaseWelcome = '<s:url value="/" />';
			var urlBase = '<s:url value="/publisherForm" />';
			function changeActionAndsubmit(url) {
				document.forms[0].action = urlBase + '/' + url;
			    document.forms[0].submit();
			}
			
			function directDelete(idpublisher) {
				if (idpublisher = 0){
					goHome();
				}else{
					document.location = urlBase + '/delete/' + idpublisher ;
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
	          <a class="brand" href="#">PublisherList</a>
	          <input type="button" class="btn btn-info" value="All" 	 onclick="directGet('list')" />
	          <input type="button" class="btn btn-info" value="Back to Welcome Page" 	 onclick="goHome()" />
	        </div>
	      </div>
	    </div>
	
	    <div class="container">
	      
			<form:form modelAttribute="publisherForm" cssClass="well" method="post" action="/starterKitSpring/publisherform/save">  
				<table>
					<thead>
					<tr>
						<th align="left">code</th>
						<td><form:input path="code"  /></td>
					</tr>
					<tr>
						<th align="left">countryCode</th>
						<td><form:input path="countryCode"  /></td>
					</tr>
					<tr>
						<th align="left">name</th>
						<td><form:input path="name"  /></td>
					</tr>
					<tr>
						<th align="left">email</th>
						<td><form:input path="email"  /></td>
					</tr>
					<tr>
						<th align="left">contact</th>
						<td><form:input path="contact"  /></td>
					</tr>
					<tr>
						<th align="left">city</th>
						<td><form:input path="city"  /></td>
					</tr>
					<tr>
						<th align="left">zipCode</th>
						<td><form:input path="zipCode"  /></td>
					</tr>
					<tr>
						<th align="left">phone</th>
						<td><form:input path="phone"  /></td>
					</tr>
					<tr>
						<td colspan="2">
							<form:errors/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						 	<input type="submit" class="btn btn-info" value="Save"   onclick="changeActionAndsubmit('save')"   />
							<s:url var="deletePublisherUrl" value="/publisherForm/delete/${current.code}"/>
							<a class="btn btn-info" href="${deletePublisherUrl}">Delete</a>
							<input type="button" class="btn btn-info" value="Clear"  onclick="directGet('clear')"  />
							<input type="button" class="btn btn-info" value="Search" onclick="changeActionAndsubmit('search')" />
							<input type="button" class="btn btn-info" value="All" 	 onclick="directGet('list')" />
						</td>
					</tr>
				</table>
			</form:form>	
	    </div> <!-- /container -->
 	</body>
</html>
