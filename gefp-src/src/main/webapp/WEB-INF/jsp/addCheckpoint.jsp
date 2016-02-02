<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Add Checkpoint</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.2/cosmo/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="//bootswatch.com/assets/css/bootswatch.min.css"
	rel="stylesheet" type="text/css" />
<script src="/gefp/resources/core/ckeditor/ckeditor.js"></script>

</head>
<body>

	<div class="container" style="width: 60%">
		<c:if test="${userLogin != null}">
			<div style="float: right; margin-top: 0px">
				<h4>Welcome ${userLogin.username}!</h4>
				<a href="/gefp/logout.html">
					<button type="button" class="btn btn-danger btn-sm">
						<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
						Logout
					</button>
				</a>
			</div>
		</c:if>


		<div class="page-header">
			<ul class="pager">
				<li class="previous"><a
					href="/gefp/department/plans/view/${plan.id}.html"> Back </a></li>
			</ul>
			<h3>Flight Plan: ${plan.name }</h3>
			<h4>Department: ${department.name}</h4>
		</div>

		<center><h2>Add Checkpoint</h2></center>
		<form action="../add/addCheckpoint.html" method="post">


			<label class="control-label">Runway:</label>
			<div class="input-group">
				<select name="runwayId" class="form-control">
					<c:forEach items="${plan.runways}" var="runway">
						<option value="${runway.id}">${runway.name}</option>
					</c:forEach>
				</select>


			</div>

			<label class="control-label">Stage:</label>
			<div class="input-group">
				<select name="stageId" class="form-control">
					<c:forEach items="${plan.stages}" var="stage">
						<option value="${stage.id}">${stage.name}</option>
					</c:forEach>
				</select>

			</div>
			<label class="control-label">New Checkpoint:</label>
			<textarea class="form-control" name="newCheckpoint"></textarea>
			<script>
				CKEDITOR.replace('newCheckpoint');
			</script>
			<div class="input-group" style="margin-top: 15px; margin-bottom: 30px">
				<input type="hidden" name="planId" value="${plan.id }" /> 
				<input type="hidden" name="departmentId" value="${department.id }" /> 
					<span class="input-group-btn">
					<button class="btn btn-primary" type="submit">Add</button>
				</span>

			</div>
		</form>

	</div>
</body>
</html>