<%-- Detail screen for Customer entity      --%>
<%-- Created on 22 nov. 2013 ( Time 17:59:35 )  --%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
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
			var urlBaseWelcome = '<s:url value="/" />';
			var urlBase = '<s:url value="/customerForm" />';
			function changeActionAndsubmit(url) {
				document.forms[0].action = urlBase + '/' + url;
			    document.forms[0].submit();
			}
			
			function directDelete(idcustomer) {
				if (idcustomer = 0){
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
	      
			<form:form modelAttribute="customerForm" cssClass="well" method="post" action="/starterKitSpring/customerform/save">  
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
						<th align="left">firstName</th>
						<td><form:input path="firstName"  /></td>
					</tr>
					<tr>
						<th align="left">lastName</th>
						<td><form:input path="lastName"  /></td>
					</tr>
					<tr>
						<th align="left">login</th>
						<td><form:input path="login"  /></td>
					</tr>
					<tr>
						<th align="left">password</th>
						<td><form:input path="password"  /></td>
					</tr>
					<tr>
						<th align="left">age</th>
						<td><form:input path="age"  /></td>
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
						<th align="left">reviewer</th>
						<td><form:input path="reviewer"  /></td>
					</tr>
					<tr>
						<td colspan="2">
							<form:errors/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						 	<input type="submit" class="btn btn-info" value="Save"   onclick="changeActionAndsubmit('save')"   />
							<s:url var="deleteCustomerUrl" value="/customerForm/delete/${current.code}"/>
							<a class="btn btn-info" href="${deleteCustomerUrl}">Delete</a>
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
