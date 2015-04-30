<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%><html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Linkedin course Recommender</title>




<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

	
	<link href="<c:url value="/resources/static/css/bootstrap.min.css"></c:url>"   rel="stylesheet"/>
    <!-- Bootstrap Core CSS -->
	
	
    <!-- Custom CSS -->
    <link href="<c:url value="/resources/static/css/sb-admin.css"></c:url>"   rel="stylesheet"/>

    <!-- Morris Charts CSS -->
    <link href="<c:url value="/resources/static/css/plugins/morris.css"></c:url>"   rel="stylesheet"/>

    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/static/font-awesome/css/font-awesome.min.css"></c:url>"   rel="stylesheet" type="text/css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>


<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/recommender/">Linkedin Course Recommender</a>
            </div>
            <!-- Top Menu Items -->
              <ul class="nav navbar-right top-nav">
              <li class="button">
              <br/>	
              <form action="https://www.linkedin.com/uas/oauth2/authorization" method="get">
		 		<input type="hidden" name="response_type" value="code"/> 
 				<input type="hidden" name="client_id" value="75gfml6z2nghgn"/> 	
			 	<input type="hidden" name="redirect_uri" value="http://localhost:8080/recommender/callback"/>
		 		<input type="hidden" name="state" value="DCEeFWf45A53sd"/>
				 
				 <input type="image" src="<c:url value="/resources/static/img/Sign-In.png"></c:url>" border="0" width=200 heigth=100 alt="Submit" />
					 
					</form>
             
              </li>
              </ul>>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="index.html"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                    <li>
                        <a href="charts.html"><i class="fa fa-fw fa-bar-chart-o"></i> Charts</a>
                    </li>
                    <li>
                        <a href="tables.html"><i class="fa fa-fw fa-table"></i> Tables</a>
                    </li>
                    <li>
                        <a href="forms.html"><i class="fa fa-fw fa-edit"></i> Forms</a>
                    </li>
                    <li>
                        <a href="bootstrap-elements.html"><i class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a>
                    </li>
                    <li>
                        <a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>
                    </li>
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="#">Dropdown Item</a>
                            </li>
                            <li>
                                <a href="#">Dropdown Item</a>
                            </li>
                        </ul>
                    </li>
                    <li class="active">
                        <a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Blank Page</a>
                    </li>
                    <li>
                        <a href="index-rtl.html"><i class="fa fa-fw fa-dashboard"></i> RTL Dashboard</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Course Recommender Home
                            <small>With Profile Data From @Linkedin</small>
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-file"></i> Blank Page
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>

    <!-- jQuery -->
    <script src="<c:url value="/resources/static/js/jquery.js"></c:url>"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/static/js/bootstrap.min.js"></c:url>"  >      </script>

    <!-- Morris Charts JavaScript -->
    <script src="<c:url value="/resources/static/js/plugins/morris/raphael.min.js"></c:url>" >   </script>
    <script src="<c:url value="/resources/static/js/plugins/morris/morris.min.js"></c:url>">	</script>
    <script src="<c:url value="/resources/static/js/plugins/morris/morris-data.js"></c:url>"></script>

</body>

</html>
