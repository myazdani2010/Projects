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

<script type="text/javascript">
function newXMLHttpRequest()
{
  var xmlreq = false;

  if( window.XMLHttpRequest ) 
	  xmlreq = new XMLHttpRequest();
  else if( window.ActiveXObject )
  {
    try
    {
      xmlreq = new ActiveXObject( "Msxml2.XMLHTTP" );
    }
    catch( e1 )
    {
      try
      {
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
      }
      catch( e2 ) {}
    }
  }

  return xmlreq;
}


function getReadyStateHandler( req, responseHandler )
{
  return function ()
  {
    if(req.readyState == 4)
    {
      if( req.status == 200 ) responseHandler( req.responseText );
      else alert("HTTP error: "+req.status);
    }
  }
}

function updateDocument( respText )
{
    document.getElementById( "checkpoint_msg" ).innerHTML = respText;
}


function clickHandler( arg )
{
  var req = newXMLHttpRequest();
  req.onreadystatechange = getReadyStateHandler( req, updateDocument );
  
  req.open("GET", "/gefp/plan/student/checkCheckpoint.html"+arg, true);
  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  req.send( null );

}
</script>

</head>
<body>

<div class="container" style="width: 98%" >

	<c:if test="${userLogin != null}">
	  <div  style="float: right; margin-top: 0px">
		<h4>Welcome ${userLogin.username}!</h4>
		<a href="/gefp/logout.html">
		  <button type="button" class="btn btn-danger btn-sm">
		    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout 
		  </button>
		</a>
	  </div>
	</c:if>

	<div class="page-header" style="margin-top: 0px">
	  <ul class="pager">
      <li class="previous"><a href="search.html"> Back </a>
      </li></ul>
    </div>

	<div class="page-header" style="margin-top: 0px">
		<h2>Student Name: ${student.firstName} ${student.lastName}</h2>
		<h2>CIN: ${student.cin}</h2>
      <h4>Department: ${student.major.name}</h4>
      <h4>Flight Plan: ${student.plan.name}</h4>
    </div>
   
	
	<div id="checkpoint_msg"></div>
    
	<table class="table table-striped table-hover " border="1">
		<tr> <td> </td>
			<c:forEach items="${plan.runways}" var="runway">
			<td><b>${runway.name }</b></td>
			</c:forEach>
		</tr>
		
		<c:forEach items="${plan.stages}" var="stage">
			<tr>
				<td><b>${stage.name}</b></td>
				<c:forEach items="${plan.runways}" var="runway">
					<td>
						<c:forEach items="${plan.cells}" var="cell">
							<c:if test="${cell.plan == plan and cell.stage == stage and cell.runway == runway}">
								<c:forEach items="${cell.checkpoints}" var="cp">
									<c:set var="check" value="" />
									<c:forEach items="${student.checkpoints}" var="userCp">
										<c:if test="${userCp.id == cp.id }"><c:set var="check" value="checked='checked'" /></c:if> 
									</c:forEach>
									<c:if test="${ cp.deleted == false}">
										<input type="checkbox" ${check} name="checkpointId" value="${cp.id}"
												onclick="clickHandler('?checkpointId=${cp.id}');"/>
										${cp.description}<br/>
									</c:if>
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