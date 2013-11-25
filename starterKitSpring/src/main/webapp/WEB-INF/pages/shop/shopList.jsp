<%-- List screen for Shop entity      --%>
<%-- Created on 22 nov. 2013 ( Time 17:59:59 )  --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<head>
	<meta charset="utf-8">
	<title>ShopStore</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="Shop">
	
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
		var urlBase = '<c:url value="/shop" />';
		
			function changeActionAndsubmit(url) {
				document.forms[0].action = urlBase + '/' + url;
			    document.forms[0].submit();
			}
			
			function directDelete(idshop) {
				if (idshop == 0){
					goHome();
				}else{
					document.location = urlBase + '/delete/' + idshop ;
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
	          <a class="brand" href="#">ShopList</a>
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
						<th>name</th>
						<th>address1</th>
						<th>address2</th>
						<th>zipCode</th>
						<th>city</th>
						<th>countryCode</th>
						<th>phone</th>
						<th>email</th>
						<th>executive</th>
						<th><a class="btn btn-info" href='<c:url value="create"/>'>new</a></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listShops}" var="shop">
						<tr>
							<td>${shop.code}</td>
							<td>${shop.name}</td>
							<td>${shop.address1}</td>
							<td>${shop.address2}</td>
							<td>${shop.zipCode}</td>
							<td>${shop.city}</td>
							<td>${shop.countryCode}</td>
							<td>${shop.phone}</td>
							<td>${shop.email}</td>
							<td>${shop.executive}</td>
							<c:url var="editShopUrl" value="/shop/edit/${shop.code}"/>
							<td><a class="btn btn-info" href="${editShopUrl}">Edit</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<!-- /container -->

</body>
</html>