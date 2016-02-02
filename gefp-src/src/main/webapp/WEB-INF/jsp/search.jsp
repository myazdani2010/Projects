<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"  %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Student Search</title>
<link href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.2/cosmo/bootstrap.min.css" rel="stylesheet" type="text/css" media="all" />
<link href="//bootswatch.com/assets/css/bootswatch.min.css" rel="stylesheet" type="text/css" />
<script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
<link href="<c:url value="/resources/core/main.css" />" rel="stylesheet"/>
<script type="text/javascript" src="//code.jquery.com/ui/1.11.3/jquery-ui.js"></script>

<script type="text/javascript">
$(document).ready(function() {
$(function() {
$("#w-input-search").autocomplete({
	
	source : function(request, response) {
		$.ajax({
			url : "/gefp/autoComplete.html",
			type : "POST",
			data : {
				term : request.term
			},
			dataType : "json",

			success : function(data) {
				response($.map(data, function(v, i) {
					return {
						label : v.label,
						value : v.value,
						id:v.id
					};
				}));
			}
		});					
	}, select: function(event, ui) {
		console.log(ui.item)
		
           if( ui.item )
               window.location.href = "search.html?search=" + ui.item.id;
       }
});
});
}); 
</script>

</head>
<body>


<div class="container" style="width: 70%">
      
	<c:if test="${userLogin != null}">
	  <div  style="float: right; margin-top: 0px">
		<h4>Welcome ${userLogin.username}!</h4>
<!-- 		<a href="/gefp/index.html"> -->
<a href="/gefp/plan/publishPlans.html">
		  <button type="button" class="btn btn-sm">
		    <span class="glyphicon" aria-hidden="true"></span> Published Plan 
		  </button>
		</a>
		<a href="/gefp/logout.html">
		  <button type="button" class="btn btn-danger btn-sm">
		    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout 
		  </button>
		</a>
	  </div>
	</c:if>
  
    <div class="page-header">
      <ul class="pager">
      </ul>
      <h2>GEFP Student Search</h2>
    </div>

	<div class="form-group" style="width: 55%" >
		<form action="search.html" method="post">	
	  		<label class="control-label">Search:</label>
	  		<div class="input-group">
	    		<input type="text" class="form-control"  name='search' id="w-input-search" value="${term}"/> <!-- onkeypress="clickHandler(value)" -->
<%-- 	    		<input type="hidden" name="departmentId" value="${department.id }"/> --%>
	    		<span class="input-group-btn">
	      			<button class="btn btn-primary" type="submit" >Search</button>
	    		</span>
	    		
	    			<script>
	$(document).ready(function() {

		$('#w-input-search2').autocomplete({
			//serviceUrl: '${pageContext.request.contextPath}/getTags',
			serviceUrl: '/gefp/autoComplete.html',
			paramName: "term",
			delimiter: ",",
		    transformResult: function(response) {
		    	console.log(response);
		    	//return response;
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item.cin + "  " + item.firstName + "  " + item.lastName, data: item.cin };
		            	})
		            
		        	}; 
		    	}
		    
			});
		
	});
	
	</script>
	    		
	  		</div>	
	  	</form>
	</div>
	
	<table class="table table-striped table-hover ">
		<tr><td><b>ID</b></td><td><b>CIN</b></td><td><b>First Name</b></td>
		<td><b>Last Name</b></td><td><b>User Name</b></td><td><b>Email</b></td>
		<td><b>Contact</b></td><td><b>Operation</b></td></tr>
		<c:forEach items="${students}" var="student">
			<tr>
			<td>${student.id}</td>
			<td>${student.cin}</td>
			<td>${student.firstName}</td>
			<td>${student.lastName}</td>
			<td>${student.username}</td>
			<td>${student.email}</td>
			<td>${student.contact}</td>
			<td><a href="viewPlan.html?studentId=${student.id}" class="btn btn-warning">view</a> </td>
			</tr>
		</c:forEach>
	</table>
       
</div>

</body>
</html>