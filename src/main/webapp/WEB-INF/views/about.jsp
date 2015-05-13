<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%><html lang="en">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="SJSU Course Recommendation">
	
	<title>About</title>

	<link rel="shortcut icon" href="images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link  href="<c:url value="/resources/css/bootstrap.min.css"></c:url>" rel="stylesheet" />
	<link  href="<c:url value="/resources/css/font-awesome.min.css"></c:url>" rel="stylesheet" />

	<!-- Custom styles for our template -->
	<link  href="<c:url value="/resources/css/bootstrap-theme.css"  ></c:url>" media="screen" rel="stylesheet"/>
	<link  href="<c:url value="/resources/css/main.css"></c:url>" rel="stylesheet"/>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
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
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">
<br>

		<div class="row">
			<div class="col-sm-4">
				<img src="<c:url value="/resources/images/nielet.jpg"  ></c:url>" class="img-responsive .img-circle" alt="nielet" width="200" height="190">
				<p>Nielet Dmello</p>
			</div>
    		<div class="col-sm-4">
    			<img src="<c:url value="/resources/images/larkins.jpg"  ></c:url>" class="img-responsive .img-circle" alt="larkins" width="200" height="190">
    			<p>Larkins Carvalho</p>
    		</div>
    		<div class="col-sm-4">
    			<img src="<c:url value="/resources/images/viraj.jpg"  ></c:url>"class="img-responsive .img-circle" alt="viraj" width="200" height="190">
    			<p>Viraj Dudhane</p>
    		</div>
    	</div>
    	<div class="row">
    		<div class="col-sm-4">
    			<img src="<c:url value="/resources/images/rajas.jpg"  ></c:url>" class="img-responsive .img-circle" alt="rajas" width="200" height="190">
    			<p>Rajas Hegiste</p>
    		</div>
    		<div class="col-sm-4">
    			<img src="<c:url value="/resources/images/shubhi.jpg"  ></c:url>" class="img-responsive .img-circle" alt="shubhi" width="200" height="190">
    			<p>Shubhi Yede</p>
    		</div>
    		<div class="col-sm-4">
    			<img src="<c:url value="/resources/images/swati.jpg"  ></c:url>" class="img-responsive .img-circle" alt="swati" width="200" height="190">
    			<p>Swati Mittal</p>
    		</div>
		</div>
	</div>	<!-- /container -->
	

	<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">
					
					<div class="col-md-3 widget">
						<h3 class="widget-title">Contact</h3>
						<div class="widget-body">
							<p>+234 23 9873237<br>
								<a href="mailto:#">some.email@somewhere.com</a><br>
								<br>
								234 Hidden Pond Road, Ashland City, TN 37015
							</p>	
						</div>
					</div>

					<div class="col-md-3 widget">
						<h3 class="widget-title">Follow me</h3>
						<div class="widget-body">
							<p class="follow-me-icons clearfix">
								<a href=""><i class="fa fa-twitter fa-2"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a>
								<a href=""><i class="fa fa-github fa-2"></i></a>
								<a href=""><i class="fa fa-facebook fa-2"></i></a>
							</p>	
						</div>
					</div>

					
				</div> <!-- /row of widgets -->
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="row">
					
					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="simplenav">
								<a href="#">Home</a> | 
								<a href="about.html">About</a> |
								<a href="sidebar-right.html">Sidebar</a> |
								<a href="contact.html">Contact</a> |
								<b><a href="signup.html">Sign up</a></b>
							</p>
						</div>
					</div>

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">
								Copyright &copy; 2014, Your name. Designed by <a href="http://gettemplate.com/" rel="designer">gettemplate</a> 
							</p>
						</div>
					</div>

				</div> <!-- /row of widgets -->
			</div>
		</div>
	</footer>	
		




	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="<c:url value="/resources/js/headroom.min.js"></c:url>"></script>
	<script src="<c:url value="/resources/js/jQuery.headroom.min.js"></c:url>"></script>
	<script src="<c:url value="/resources/js/template.js"></c:url>"></script>
</body>
</html>