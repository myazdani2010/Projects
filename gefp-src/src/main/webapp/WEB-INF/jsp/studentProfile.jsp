<!-- AngularJS Validation -->

<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix='fn' uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" ng-app>
<head>
<meta content="UTF-8" />
<title>Profile</title>

<link
	href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.2/cosmo/bootstrap.min.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="//bootswatch.com/assets/css/bootswatch.min.css"
	rel="stylesheet" type="text/css" />
<script
	src='//ajax.googleapis.com/ajax/libs/angularjs/1.3.13/angular.min.js'></script>

<script type="text/javascript">
	$scope.passwdRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[^\da-zA-Z]).{8,}$/;
	$scope.$watch('data.new_passwd', function() {
		$scope.passwdConfRegex = new RegExp(Regex
				.escape($scope.data.new_passwd));
	});
</script>


<style>
input.ng-invalid.ng-dirty {
	border: 1px solid red;
}
</style>



</head>
<body>


	<div class="container" style="width: 65%">

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


		<div class="page-header" style="margin-top: 0px">
			<ul class="pager">
				<li class="previous"><a href="/gefp/user/view/flightPlan.html">
						Back </a></li>
			</ul>
			<h2>Department: ${department.name}</h2>
			<h4>Flight Plan: ${plan.name}</h4>
		</div>


		<!--*** Start: Form ***-->
		<form name="frm" class="form-horizontal"
			style="background-color: #FFFCF6" action="profile.html" method="post">
			<fieldset>
				<legend>Profile</legend>
				<div class="form-group">
					<label class="col-lg-2 control-label">User Name:</label>
					<div class="col-lg-3">
						<input type="text" placeholder="User Name" class="form-control"
							disabled="true" value="${userLogin.username}" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 control-label">First Name:</label>
					<div class="col-lg-3"
						ng-init="user.firstName='${userLogin.firstName}'">
						<input type="text" placeholder="First Name" class="form-control"
							name="firstName" ng-model="user.firstName"
							value="${userLogin.firstName}" required />
					</div>

					<label class="col-lg-2 control-label">Last Name:</label>
					<div class="col-lg-3"
						ng-init="user.lastName='${userLogin.lastName}'">
						<input type="text" placeholder="Last Name" class="form-control"
							name="lastName" ng-model="user.lastName"
							value="${userLogin.lastName}" required />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 control-label">Password:</label>
					<div class="col-lg-3">
						<input type="password" name="password1" class="form-control"
							placeholder="Password" ng-model="user.password1" ng-minlength="4" />

						<span
							ng-show="frm.password1.$dirty && frm.password1.$error.minlength">Too
							short!</span> <span style="color: red">${passError}</span>
					</div>

					<label class="col-lg-2 control-label">Re-Password:</label>
					<div class="col-lg-3">

						<input type="password" placeholder="Password" class="form-control"
							name="password2" ng-model="user.password2" ng-minlength="4" />
					</div>
				</div>

				<div class="form-group">
					<label class="col-lg-2 control-label">Email:</label>
					<div class="col-lg-3" ng-init="user.email = '${userLogin.email}'">
						<input type="email" name="email" class="form-control"
							placeholder="Email" ng-model="user.email"
							value="${userLogin.email }" /> <span
							ng-show="frm.email.$error.email">Not an email!</span>
					</div>

					<label class="col-lg-2 control-label">Contact:</label>
					<div class="col-lg-3"
						ng-init="user.contact='${userLogin.contact }'">
						<input name="contact" placeholder="Contact" ng-model="user.contact" class="form-control" value="${userLogin.contact}" />
					</div>
				</div>


				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<button type="reset" class="btn btn-default">Cancel</button>
						<button type="submit" class="btn btn-primary">Submit</button>
					</div>
				</div>
			</fieldset>
		</form>
		<!--*** End: Form ***-->

	</div>

</body>
</html>