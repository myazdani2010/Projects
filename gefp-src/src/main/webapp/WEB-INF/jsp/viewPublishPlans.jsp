<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"  %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Flight Plan</title>

<link href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.2/cosmo/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="//bootswatch.com/assets/css/bootswatch.min.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="container" style="width: 98%" >

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
	


	<div class="page-header" style="margin-top: 0px">
	<ul class="pager">
      <li class="previous"><a href="/gefp/plan/publishPlans.html" > Back </a></li></ul>
      <h2>Department: ${publishPlan.department.name}</h2><h4>Flight Plan: ${publishPlan.name}</h4>
    </div>
    
    
    
    
    <div class="form-group" style="width: 25%; float: left" >
	
	</div>
	
	

	<table class="table table-striped table-hover " border="1">
		<tr> <td> </td>
			<c:forEach items="${publishPlan.runways}" var="runway">
			<td><b>${runway.name }</b></td>
			</c:forEach>
		</tr>
		
		<c:forEach items="${publishPlan.stages}" var="stage">
			<tr>
				<td><b>${stage.name}</b></td>
				<c:forEach items="${publishPlan.runways}" var="runway">
					<td>
						<c:forEach items="${publishPlan.cells}" var="cell">
							<c:if test="${cell.plan == publishPlan and cell.stage == stage and cell.runway == runway}">
								<c:forEach items="${cell.checkpoints}" var="cp">
									${cp.description} <br/>
								</c:forEach>	
							</c:if>
						</c:forEach>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>	
	</table>

</div>

</body>
</html>