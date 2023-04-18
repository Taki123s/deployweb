<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Anime Template">
<meta name="keywords" content="Anime, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<title>Anime</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Oswald:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:wght@300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/plyr.css" type="text/css">
<link rel="stylesheet" href="css/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
<link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
<link rel="stylesheet" type="text/css" href="css/ds/style.css" />
<script src="https://kit.fontawesome.com/9847adceef.js"></script>
</head>

<c:url var="urlAvatarMovie"
	   value="${requestScope.movie.getFirstAvatar()}"/>
<c:url var="watchingMovie" value="/anime-main/WatchingMovie"/>
<body>
	<fmt:setLocale value="${sessionScope.LANG}" />
	<fmt:setBundle basename="app" />


	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header Section Begin -->
	<c:import url="/anime-main/header.jsp" />
	<!-- Header End -->

	<!-- Breadcrumb Begin -->
	<div class="breadcrumb-option">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="breadcrumb__links">
						<a href="index.jsp"><i class="fa fa-home"></i> <fmt:message>menu.hompage</fmt:message></a>
						<span>${sessionScope.watchingMovie.name}</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Anime Section Begin -->
	<section class="anime-details spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="anime__video__player">
						<video id="player" controls data-poster="${urlAvatarMovie}">
							<source src="${requestScope.watchChapter.name}" type="video/mp4">
							<!-- Captions are optional -->

						</video>
						<input type="text" id="openingValue" value="${requestScope.watchChapter.opening}"
							hidden>
					</div>
					<div class="anime__details__episodes">
						<div class="section-title">
							<h5>
								<fmt:message>button.episodes</fmt:message>
							</h5>
						</div>
						<c:forEach var="chap" items="${sessionScope.watchingMovie.listChapter}">
							<c:if test="${chap.index==requestScope.chapter}">

								<a href="${watchingMovie}?chapter=${chap.index}" style="color: #000000!important;
	background: #ffffff!important;">Ep ${chap.index}</a>
							</c:if>
							<c:if test="${chap.index!=requestScope.chapter}">

								<a href="${watchingMovie}?chapter=${chap.index}">Ep ${chap.index}</a>
							</c:if>
						</c:forEach>

					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8">
					<div class="anime__details__review">
						<div class="section-title">
							<h5>
								<fmt:message>content.reviews</fmt:message>
							</h5>
						</div>




					</div>

				</div>
			</div>
		</div>
	</section>
	<!-- Anime Section End -->

	<!-- Footer Section Begin -->
	<c:import url="/anime-main/footer.jsp" />
	<!-- Footer Section End -->

	<!-- Search model Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">
				<i class="icon_close"></i>
			</div>
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
			</form>
		</div>
	</div>
	<!-- Search model end -->

	<!-- Js Plugins -->
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/player.js"></script>
	<script src="js/jquery.nice-select.min.js"></script>
	<script src="js/mixitup.min.js"></script>
	<script src="js/jquery.slicknav.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	<script type="text/javascript">
		function skip() {
			let vd = document.getElementById("player");
			vd.currentTime = $("#openingValue").val();
		}
	</script>
	<script type="text/javascript">
		window.onload = function() {
			$(".plyr__controls")
					.append(
							"<i id=\"\skipOpening\"\ class=\"\plyr__controls__item plyr__volume fa fa-fast-forward\"\ onclick=\"\skip()\"\ style=\"\left:110px;bottom: 14px;\"\ aria-hidden=\"\true\"\></i>")
		};
	</script>

</body>

</html>