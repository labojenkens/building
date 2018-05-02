<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<meta charset="utf-8">

<title>Elise Collecte</title>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="">
<meta name="description" content="">

<!-- stylesheets css -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css"/>">

<style type="text/css">
	.row{
		    margin-top:150px;
		    padding: 0 10px;
		}
		.clickable{
		    cursor: pointer;   
		}

		.panel-heading div {
			margin-top: -18px;
			font-size: 15px;
		}
		.panel-heading div span{
			margin-left:5px;
		}
		.panel-body{
			display: none;
		}
</style>


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
<c:url var="collecteObject" value="/listeCollectes" ></c:url>
	<!-- home section -->
	<section id="home">
		<div class="container">
	 
			<div class="container">
				<div class="row">
				<div class="col-md-2">
				</div>
					<div class="col-md-8">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h3 class="panel-title">Listes des demandes de collecte</h3>
							</div>
							<div class="panel-body">
								<input type="text" class="form-control" id="dev-table-filter"
									data-action="filter" data-filters="#dev-table"
									placeholder="Filter Developers" />
							</div>
							<c:if test="${!empty listCollect}">
							<table class="table table-hover" id="dev-table">
								<thead>
									<tr>
										<th>id</th>
										<th>Nom de la société</th>
										<th>Type de la collecte</th>
										<th>Date de demande</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${listCollect}" var="collecte">
									<tr>
									<c:if test="${empty collecte.dateCollecte}">
										<td>${collecte.id}</td>
										<td>${collecte.name}</td>
										<td>${collecte.typeCollecte}</td>
										<td>${collecte.dateDemande}</td>
										<td>
										<c:url var="validateActionCollecte" value="/collecte/validate/${collecte.id}" ></c:url>
										<form action="${validateActionCollecte}" method="post" modelAttribute="validateForm">
										<button type="submit" class="btn btn-warning btn-filter" data-target="pendiente">Collecter</button>
										</form>
										</td>
										</c:if>
										
										<tr>
										</c:forEach>
								</tbody>
							</table>
							</c:if>
						</div>
					</div>
					<div class="col-md-2">
				</div>
				</div>
			</div>

		</div>
	</section>


	<!-- javscript js -->
	<script src="<c:url value="/resources/js/jquery.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>



</body>
</html>