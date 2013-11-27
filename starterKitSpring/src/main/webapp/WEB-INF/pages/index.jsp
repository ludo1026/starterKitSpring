<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>

<!-- taglib prefix="s" uri="/struts-tags" -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Liste des entit&#233;s g&#233;r&#233;es</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="BookStore">
<meta name="author" content="asic">
<base href="<%= request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/"%>">

<!-- Le styles -->
<link href='<s:url value='/css/bootstrap.min.css' var='url'/>' rel="stylesheet" />
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<link href="<s:url value='/css/bootstrap-responsive.min.css' var='url'/>" rel="stylesheet" />
<link href="<s:url value='/css/styles.css' var='url'/>" rel="stylesheet" />
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
          <a class="brand" href="#">Liste des entit&#233;s g&#233;r&#233;es</a>
        </div>
      </div>
    </div>

    <div class="container">
      
		<ul>
            <li><a href="country">Country</a></li>
	    </ul>

    </div> <!-- /container -->

    <script src="<s:url value='/js/jquery.js' var='url'/>"></script>
    <script src="<s:url value='/js/bootstrap.min.js' var='url'/>"></script>
	<script src="<s:url value='/js/validation.js' var='url'/>"></script>
	
</body>
</html>