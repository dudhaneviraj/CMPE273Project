<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%><html lang="en">
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="SJSU Course Recommendation">

<title>Profile Info</title>

<link rel="shortcut icon" href="images/gt_favicon.png">

<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link href="<c:url value="/resources/css/bootstrap.min.css"></c:url>"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/font-awesome.min.css"></c:url>"
	rel="stylesheet" />

<!-- Custom styles for our template -->
<link href="<c:url value="/resources/css/bootstrap-theme.css"></c:url>"
	media="screen" rel="stylesheet" />
<link href="<c:url value="/resources/css/main.css"></c:url>"
	rel="stylesheet" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
<style type="text/css">
div.container .input-group-btn {
	font-size: 14px;
}
</style>
</head>

<body>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom">
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/recommender/"><img
					src="<c:url value="/resources/images/logo2.png"></c:url>"
					alt="Course Recommender"> Course Recommender </a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="active"><a href="/recommender/">Home</a></li>
					<li><a href="/recommender/${token}/about">About</a></li>
					<li><a href="/recommender/${token}/contact">Contact</a></li>

				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<!-- /.navbar -->

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<!-- <ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li class="active">About</li>
		</ol> -->

		<div class="row">

			<!-- Article main content -->
			<article class="col-sm-8 maincontent">
				<header class="page-header">
					<h1 class="page-title">LinkedIn Profile Data</h1>
				</header>
				<h3></h3>

			</article>
			<!-- /Article -->


			<div class="col-sm-12 col-md-12">
				<table class="table table-hover">


					<tr>
						<td><img src="${picture}" class="img-circle"
							alt="Cinque Terre" width="304" height="236" /></td>
					</tr>



					<tr>
						<td>First Name</td>
						<td>${firstName}</td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td>${lastName}</td>
					</tr>
					<tr>
						<td>Headline</td>
						<td>${headline}</td>
					</tr>
					<tr>
						<td>Skills</td>
						<td>${skillSet}</td>
					</tr>
				</table>


			</div>
		</div>
		<a class="btn btn-action btn-lg" role="button"
			href="/recommender/${token}/menu" align="center">See
			Recommendations</a>

	</div>
	<!-- /container -->


	<footer id="footer" class="top-space" style="margin-top: 180px;">

		<div class="footer1">
			<div class="container">
				<div class="row">

					<div class="col-md-3 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p>
								(408) 952-9749<br> <a href="mailto:#">courserateam@gmail.com</a><br>
								<br> San Jose State University, San Jose- CA
							</p>
						</div>
					</div>

					<div class="col-md-3 widget">
						<h3 class="widget-title">Follow me</h3>
						<div class="widget-body">
							<p class="follow-me-icons clearfix">
								<a href=""><i class="fa fa-twitter fa-2"></i></a> <a href=""><i
									class="fa fa-dribbble fa-2"></i></a> <a href=""><i
									class="fa fa-github fa-2"></i></a> <a href=""><i
									class="fa fa-facebook fa-2"></i></a>
							</p>
						</div>
					</div>


				</div>
				<!-- /row of widgets -->
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="row">

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="simplenav">
								<a href="#">Home</a> | <a href="about.html">About</a> | <a
									href="contact.html">Contact</a> |
							</p>
						</div>
					</div>

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">
								Copyright &copy; 2015, SJSU Team. Designed by <a href="#"
									rel="designer">Team</a>
							</p>
						</div>
					</div>

				</div>
				<!-- /row of widgets -->
			</div>
		</div>
	</footer>





	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="<c:url value="/resources/js/headroom.min.js"></c:url>"></script>
	<script
		src="<c:url value="/resources/js/jQuery.headroom.min.js"></c:url>"></script>
	<script src="<c:url value="/resources/js/template.js"></c:url>"></script>
</body>
</html>