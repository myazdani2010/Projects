<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script>
<script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
<link href="<c:url value="/resources/core/main.css" />" rel="stylesheet">

</head>
<body>
	<h2>Spring MVC + jQuery + Autocomplete example</h2>

	<div>
		<input type="text"  id="w-input-search" value="">
		<span>
			<button id="w-button-search" type="button">Search</button>
		</span>
	</div>
	
	<script>
	$(document).ready(function() {

		$('#w-input-search').autocomplete({
			//serviceUrl: '${pageContext.request.contextPath}/getTags',
			serviceUrl: '/gefp/getTags.html',
			paramName: "tagName",
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
	
</body>
</html>