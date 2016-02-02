<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"  %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Dep ${department.id} plan: ${plan.id}</title>

<link href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.2/cosmo/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="//bootswatch.com/assets/css/bootswatch.min.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="container" style="width: 70%">
      
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
      
      
    <div class="page-header">
      <ul class="pager">
      <li class="previous"><a href="/gefp/departments.html" > Back </a></li></ul>
      <h2>Flight Plans</h2>
      <h4>Department: ${department.name}</h4>
    </div>

	<div class="form-group" style="width: 55%" >
		<form action="changePlan.html" method="get">
			<label class="control-label">Designate Plan: ( Date: ${department.currentPlan.publishedDate})</label>
			<div class="input-group">
			<select name="planId" class="form-control">
				<c:forEach items="${department.plans}" var="plan">
				<c:set var="selected" value=""/>
					<c:if test="${plan == department.currentPlan}"> <c:set var="selected" value="selected='selected'"/></c:if>		
			  		<option value="${plan.id}" ${selected}> ${plan.name} </option>
				</c:forEach>
			</select>
			<input type="hidden" name="departmentId" value="${department.id }"/>
			<span class="input-group-btn">
		      	<button class="btn btn-primary" type="submit" >Publish</button>
		    </span>
		    </div>
		</form>
	</div>
	
		<div class="form-group" style="width: 55%" >

		<form action="add.html" method="post">	
	  		<label class="control-label">New Plan:</label>
	  		<div class="input-group">
	    		<input type="text" class="form-control" name='newPlan'/>
	    		<input type="hidden" name="departmentId" value="${department.id }"/>
	    		<span class="input-group-btn">
	      			<button class="btn btn-primary" type="submit" >Add</button>
	    		</span>
	    
	  		</div>	
	  	</form>
	
	</div>
	
	
	<table class="table table-striped table-hover ">
		<tr><td><b>ID</b></td><td><b>Department Name</b></td><td><b>Operation</b></td></tr>
		<c:forEach items="${department.plans}" var="plan">
			<c:set var="selectedRow" value=""/>
			<c:if test="${plan == department.currentPlan}"> <c:set var="selectedRow" value="class='success'"/></c:if>
			<tr ${selectedRow}>
			<td >${plan.id}</td>
			<td >${plan.name}</td>
			<td>
				<a href="view/${plan.id}.html" class="btn btn-warning">view</a> 
				<c:if test="${plan == department.currentPlan}"><b>Designated Plan</b></c:if>
			</td>
			</tr>
		</c:forEach>
		</table>
       
</div>

</body>
</html>