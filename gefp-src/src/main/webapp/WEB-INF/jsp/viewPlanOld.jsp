<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Flight Plan</title>

<link	href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.2/cosmo/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="//bootswatch.com/assets/css/bootswatch.min.css"	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/gefp/resources/tableDrag/dragtable.css" />

<script src="/gefp/resources/tableDrag/jquery.dragtable.js"></script>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="http://akottr.github.io/js/jquery.chili-2.2.js"></script>


</head>
<body>
	<div class="container" style="width: 98%">

		<c:if test="${userLogin != null}">
			<div style="float: right; margin-top: 0px">
				<h4>Welcome ${userLogin.username}!</h4>
				<a href="../add/addCheckpoint.html">
					<button type="button" class="btn btn-primary btn-sm">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
						Add Checkpoint
					</button>
				</a> <a href="/gefp/logout.html">
					<button type="button" class="btn btn-danger btn-sm">
						<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
						Logout
					</button>
				</a>

			</div>
		</c:if>

		<div class="page-header" style="margin-top: 0px">
			<ul class="pager">
				<li class="previous"><a
					href="/gefp/department/plans/${department.id }.html"> Back </a></li>
			</ul>
			<h2>Department: ${department.name}</h2>
			<h4>Flight Plan: ${plan.name}</h4>
		</div>

		<div class="form-group" style="width: 40%; float: left">

			<form action="add/runway.html" method="post" class="pull-left">
				<label class="control-label">New Runway:</label>
				<div class="input-group">
					<input type="text" class="form-control" name='newRunway' /> <input
						type="hidden" name="planId" value="${plan.id }" /> <span
						class="input-group-btn">
						<button class="btn btn-primary" type="submit">Add</button>
					</span>

				</div>
			</form>


			<c:if test="${editStage!=null }">
				<form action="edit/stage.html" method="post">
					<label class="control-label">Edit Stage:</label>
					<div class="input-group">
						<input type="text" class="form-control col-lg-4" name='stageName'
							value="${ editStage.name}" /> <input type="hidden" name="planId"
							value="${plan.id }" /> <input type="hidden" name="stageId"
							value="${editStage.id }" /> <span class="input-group-btn">
							<button class="btn btn-success" type="submit">Edit</button>
						</span>
					</div>
				</form>
			</c:if>

			<c:if test="${editRunway!=null }">
				<form action="edit/runway.html" method="post">
					<label class="control-label">Edit Runway:</label>
					<div class="input-group">
						<input type="text" class="form-control col-lg-4" name='runwayName'
							value="${ editRunway.name}" /> <input type="hidden"
							name="planId" value="${plan.id }" /> <input type="hidden"
							name="runwayId" value="${editRunway.id }" /> <span
							class="input-group-btn">
							<button class="btn btn-success" type="submit">Edit</button>
						</span>
					</div>
				</form>
			</c:if>

			<c:if test="${editRunway==null and editStage==null}">
				<br />
  	* For editing Stages & Runways click on stage or runway name to display edit option.
  	</c:if>


		</div>

		<div class="form-group" style="width: 40%; float: right">
			<form action="add/stage.html" method="post">
				<label class="control-label">New Stage:</label>
				<div class="input-group">
					<input type="text" class="form-control" name='newStage' /> <input
						type="hidden" name="planId" value="${plan.id }" /> <span
						class="input-group-btn">
						<button class="btn btn-primary" type="submit">Add</button>
					</span>

				</div>
			</form>
		</div>


<div class="demo">
		<div class="demo-content" align="center">
		<table class="table table-striped table-hover" class="sar-table" id="planTable" border="1">
			<thead><tr>
				<td></td>
				<c:forEach items="${plan.runways}" var="runway">
					<td><b><a href="${plan.id}.html?editRunwayId=${runway.id}"><div class="some-handle"></div>${runway.name }</a></b></td>
				</c:forEach>
			</tr></thead>
		<tbody>
		
			<c:forEach items="${plan.stages}" var="stage">
				<tr id="${st.id}">
					<td><b><a href="${plan.id}.html?editStageId=${stage.id}">${stage.name}</a></b></td>
					<c:forEach items="${plan.runways}" var="runway">
						<td><c:forEach items="${plan.cells}" var="cell">
								<c:if test="${cell.plan == plan and cell.stage == stage and cell.runway == runway}">
								
								<table id="checkpointTable" width="100%" ><tbody >
									<c:forEach items="${cell.checkpoints}" var="cp">
										<c:if test="${ cp.deleted == false}">
										<tr id="${ch.id}"> <td width="100%">
										${cp.description} 
										[<a href="../edit/editCheckpoint.html?checkpointId=${cp.id}">Edit</a>] 
										[<a
												href="../edit/deleteCheckpoint.html?checkpointId=${cp.id}&planId=${plan.id}&cellId=${cell.id}">Delete</a>]
										<br /></td></tr>
										</c:if>
									</c:forEach>
									</tbody></table>
								</c:if>
							</c:forEach></td>
					</c:forEach>
				</tr>
			</c:forEach>
			
		</tbody></table>
</div></div>


	</div>

</body>
</html>