<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Edit Checkpoint</title>

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
				<!-- 		<a href="/gefp/index.html"> -->
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
			<h2>Flight Plan: ${plan.name }</h2>
			<h4>Department: ${department.name}</h4>
		</div>


		<!-- Binding Example -->


		<form:form modelAttribute="checkpoint" method="post">
			<label class="control-label">Edit Checkpoint:</label>
			<%-- 		<form:input path="description" class="form-control"/></br> --%>
			<form:textarea path="description" class="form-control"
				name="description"></form:textarea>

			<script>
				CKEDITOR.replace('description');
			</script>

			<label class="control-label">Runway:</label>
			<select name="runwayId" class="form-control">

				<c:forEach items="${checkpoint.cell.plan.runways}" var="runway">
					<c:set var="selected" value=" "></c:set>

					<c:if test="${runway == checkpoint.cell.runway}">
						<c:set var="selected" value="selected='selected'"></c:set>
					</c:if>
					<option value="${runway.id}" ${selected}>${runway.name}</option>
				</c:forEach>
			</select>

			<label class="control-label">Stage:</label>
			<select name="stageId" class="form-control">
				<c:forEach items="${checkpoint.cell.plan.stages}" var="stage">
					<c:set var="selected" value=" "></c:set>
					<c:if test="${stage == checkpoint.cell.stage}">
						<c:set var="selected" value="selected='selected'"></c:set>
					</c:if>
					<option value="${stage.id}" ${selected}>${stage.name}</option>
				</c:forEach>
			</select>

			<br />
			<input type="hidden" name="planId" value="${plan.id }" />
			<button type="submit" name="save" class="btn btn-primary">Save</button>
		</form:form>

	</div>
</body>
</html>