<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>

	<meta charset="utf-8">

	<title>Elise Collecte</title>
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="keywords" content="">
	<meta name="description" content="">

	<!-- stylesheets css -->
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/animate.min.css"/>">

  	<link rel="stylesheet" href="<c:url value="/resources/css/et-line-font.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">

  	<link rel="stylesheet" href="<c:url value="/resources/css/vegas.min.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">

	<link href='https://fonts.googleapis.com/css?family=Rajdhani:400,500,700' rel='stylesheet' type='text/css'>

</head>
<body>

<!-- preloader section -->
<section class="preloader">
	<div class="sk-circle">
       <div class="sk-circle1 sk-child"></div>
       <div class="sk-circle2 sk-child"></div>
       <div class="sk-circle3 sk-child"></div>
       <div class="sk-circle4 sk-child"></div>
       <div class="sk-circle5 sk-child"></div>
       <div class="sk-circle6 sk-child"></div>
      <div class="sk-circle7 sk-child"></div>
       <div class="sk-circle8 sk-child"></div>
       <div class="sk-circle9 sk-child"></div>
       <div class="sk-circle10 sk-child"></div>
       <div class="sk-circle11 sk-child"></div>
       <div class="sk-circle12 sk-child"></div>
     </div>
</section>
<c:url var="typeCol" value="/collecte/type" ></c:url>
<c:if test="${not empty successMessage}">
				<div class="alert alert-success" role="alert">
					${successMessage}
				</div>
</c:if>

<!-- home section -->
<section id="home">
	<div class="container">
		<div class="row">
		<form action="${typeCol}" method="get">
			<div class="col-md-offset-2 col-md-8 col-sm-12">
				<div class="home-thumb">
					<h1 class="wow fadeInUp" data-wow-delay="0.4s">Je déclenche la demande de colletce</h1>
          			<h3 class="wow fadeInUp" data-wow-delay="0.6s">Elise est fière de faire équipe avec vous!</h3>
          			<a href="${typeCol}?argName=goblet" class="btn btn-lg btn-default smoothScroll wow fadeInUp hidden-xs" data-toggle="modal" data-target="#myModal" data-wow-delay="0.8s">Golblet</a>
        			<a href="${typeCol}?argName=carton" data-toggle="modal" data-target="#myModal" class="btn btn-lg btn-default smoothScroll wow fadeInUp hidden-xs" data-wow-delay="1.0s">Carton</a>
				</div>
			</div>
		</form>
		</div>
		
	</div>		
</section>

<c:url var="addActionCollecte" value="/collecte/add" ></c:url>


<!-- Modal -->
<form action="${addActionCollecte}" method="post" modelAttribute="collecteForm">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Êtes vous sur de confirmer la collecte</h5>
      </div>
      <div class="modal-body">
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Non</button>
        <button type="submit" class="btn btn-primary">Oui</button> 
        
       </div>
     
    </div>
  </div>
</div>

</form>


<!-- javscript js -->
<script src="<c:url value="/resources/js/jquery.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

<script src="<c:url value="/resources/js/vegas.min.js"/>"></script>

<script src="<c:url value="/resources/js/wow.min.js"/>"></script>
<script src="<c:url value="/resources/js/smoothscroll.js"/>"></script>
<script src="<c:url value="/resources/js/custom.js"/>"></script>

</body>
</html>