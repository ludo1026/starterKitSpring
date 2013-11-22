<%-- List screen for Customer entity      --%>
<%-- Created on 22 nov. 2013 ( Time 16:55:25 )  --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="utf-8">
	<title>CustomerStore</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="Customer">
	
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
		var urlBaseWelcome = '<c:url value="/" />';
		var urlBase = '<c:url value="/customer" />';
		
			function changeActionAndsubmit(url) {
				document.forms[0].action = urlBase + '/' + url;
			    document.forms[0].submit();
			}
			
			function directDelete(idcustomer) {
				if (idcustomer == 0){
					goHome();
				}else{
					document.location = urlBase + '/delete/' + idcustomer ;
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
	          <a class="brand" href="#">CustomerList</a>
	          <input type="button" class="btn btn-info" value="All" 	 onclick="directGet('list')" />
	          <input type="button" class="btn btn-info" value="Back to Welcome Page" 	 onclick="goHome()" />
	        </div>
	      </div>
	    </div>

	<div class="container">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>code</th>
						<th>countryCode</th>
						<th>firstName</th>
						<th>lastName</th>
						<th>login</th>
						<th>password</th>
						<th>age</th>
						<th>city</th>
						<th>zipCode</th>
						<th>phone</th>
						<th>reviewer</th>
						<th><a class="btn btn-info" href='<c:url value="create"/>'>new</a></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listCustomers}" var="customer">
						<tr>
							<td>${customer.code}</td>
							<td>${customer.countryCode}</td>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
							<td>${customer.login}</td>
							<td>${customer.password}</td>
							<td>${customer.age}</td>
							<td>${customer.city}</td>
							<td>${customer.zipCode}</td>
							<td>${customer.phone}</td>
							<td>${customer.reviewer}</td>
							<c:url var="editCustomerUrl" value="/customer/edit/${customer.code}"/>
							<td><a class="btn btn-info" href="${editCustomerUrl}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<!-- /container -->

</body>
</html>