<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login Form</title>

<link href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.2/cosmo/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="//bootswatch.com/assets/css/bootswatch.min.css" rel="stylesheet" type="text/css" />

</head>
<body>

<c:if test="${not empty sessionScope.SPRING_SECURITY_SAVED_REQUEST_KEY}">
<p class="error">Please log in to access the page you requested.</p>
</c:if>

<div class="container" style="width: 75%" >

	<c:if test="${userLogin != null}">
	  <div  style="float: right; margin-top: 0px">
		<h4>Welcome ${userLogin.username}!</h4>
<!-- 		<a href="/gefp/index.html"> -->
			<a href="/gefp/logout.html">
		  <button type="button" class="btn btn-danger btn-sm">
		    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout 
		  </button>
		</a>
	  </div>
	</c:if>

<c:if test="${userLogin == null}">

	<form action="<c:url value='/j_spring_security_check' />" method="post">
	  <label class="control-label">Login:</label>
	  <table class="table table-striped table-hover " style="width: 300px">
		<tr> <td>Username:</td> <td> <input type="text" name="j_username" /> </td> </tr>
		<tr> <td>Password:</td> <td> <input type="password" name="j_password" /> </td> </tr>
		<tr><td colspan="2">
      	<input type="checkbox" name="_spring_security_remember_me" />
      		Remember me on this computer.
    	</td>
    	</tr>
		<tr><td colspan="2"><button type="submit" name="login" class="btn btn-success btn-sm"/>Login</td></tr>
	  </table>
	</form>

</c:if>

    <div class="page-header">
    <ul class="pager">
    	<h2>Departments</h2>
    </div>

	<table class="table table-striped table-hover ">
		<tr><td><b>ID</b></td><td><b>Department Name</b></td></tr>
		<c:forEach items="${departments}" var="dep">
			<tr>
				<td>${dep.id}</td>
				<td>${dep.name}</td>
			</tr>
		</c:forEach>
	</table>
</div>


</body>
</html>