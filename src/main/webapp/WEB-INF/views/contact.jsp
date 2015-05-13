
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%><html lang="en">
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="SJSU Coursera Course Recommendation">

<title>Contact us</title>

<link rel="shortcut icon" href="images/gt_favicon.png">

<link rel="stylesheet" media="screen"
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
<link href="<c:url value="/resources/css/bootstrap.min.css"></c:url>"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/font-awesome.min.css"></c:url>"
	rel="stylesheet" />

<!-- Custom styles for our template -->
<link
	href="<c:url value="/resources/css/bootstrap-theme.css"  ></c:url>"
	media="screen" rel="stylesheet" />
<link href="<c:url value="/resources/css/main.css"></c:url>"
	rel="stylesheet" />

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
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

		<ol class="breadcrumb">
			<li><a href="index.html">Home</a></li>
			<li class="active">Contact Us</li>
		</ol>

		<div class="row">

			<!-- Article main content -->
			<article class="col-sm-9 maincontent">
				<header class="page-header">
					<h1 class="page-title">Contact us</h1>
				</header>

				<p>We’d love to hear from you.</p>
				<br>
				<h4>Address</h4>
				<address>San Jose State University, San Jose, CA- 95112,
					USA</address>
				<h4>Email</h4>
				<address>cmpe273sjsu@gmail.com</address>
				<h4>Phone:</h4>
				<address>(408) 952-9749</address>

			</article>
			<!-- /Article -->
		</div>
	</div>
	<!-- /container -->

	<section class="container-full top-space">
		<!-- <div id="map"></div> -->
		<iframe
			src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3172.309237802998!2d-121.881072!3d37.335187!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x808fccb864de43d5%3A0x397ffe721937340e!2sSan+Jos%C3%A9+State+University!5e0!3m2!1sen!2sus!4v1431206693979"
			width="100%" height="250" frameborder="0" style="border: 0"></iframe>
	</section>

	<footer id="footer">

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
								<a href="#">Home</a> | <a href="about.jsp">About</a> | <a
									href="contact.jsp">Contact</a> |
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

	<!-- Google Maps -->




</body>
</html>