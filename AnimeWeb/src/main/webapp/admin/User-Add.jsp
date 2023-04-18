<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
	<!-- Required meta tags -->
	<meta charset="utf-8">
	<meta name="viewport"
		  content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>FinDash - Responsive Bootstrap 4 Admin Dashboard Template</title>
	<!-- Favicon -->
	<link rel="shortcut icon" href="images/favicon.ico" />
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- Typography CSS -->
	<link rel="stylesheet" href="css/typography.css">
	<!-- Style CSS -->
	<link rel="stylesheet" href="css/style.css">
	<!-- Responsive CSS -->
	<link rel="stylesheet" href="css/responsive.css">
	<c:url var="addUser" value="/admin/UserAdd" />
</head>
<body>
<!-- loader Start -->
<div id="loading">
	<div id="loading-center"></div>
</div>
<!-- loader END -->
<!-- Wrapper Start -->
<div class="wrapper">
	<!-- Sidebar  -->
	<c:import url="/admin/sidebar.jsp" />
	<!-- TOP Nav Bar -->
	<c:import url="/admin/header.jsp" />
	<!-- TOP Nav Bar END -->
	<!-- Page Content  -->
	<div id="content-page" class="content-page">
		<div class="container-fluid">
			<form method="post" action="${addUser}?type=add" enctype="multipart/form-data">
				<div class="row">
					<div class="col-lg-3">
						<div class="iq-card">
							<div class="iq-card-header d-flex justify-content-between">
								<div class="iq-header-title">
									<h4 class="card-title">Add New User</h4>
								</div>
							</div>
							<div class="iq-card-body">

								<div class="form-group">
									<div class="add-img-user profile-img-edit">
										<img class="profile-pic img-fluid" src="images/user/11.png"
											 alt="profile-pic">
										<div class="p-image">
											<a href="javascript:void();"
											   class="upload-button btn iq-bg-primary">File Upload</a> <input
												class="file-upload" type="file" accept="image/*" name="avatar">
										</div>
									</div>
									<div class="img-extension mt-3">
										<div class="d-inline-block align-items-center">
											<span>Only</span> <a href="javascript:void();">.jpg</a> <a
												href="javascript:void();">.png</a> <a
												href="javascript:void();">.jpeg</a> <span>allowed</span>
										</div>
									</div>
								</div>


							</div>
						</div>
					</div>
					<div class="col-lg-9">
						<div class="iq-card">
							<div class="iq-card-header d-flex justify-content-between">
								<div class="iq-header-title">
									<h4 class="card-title">New User Information</h4>
								</div>
							</div>
							<div class="iq-card-body">
								<div class="new-user-info">

									<div class="row">
										<div class="form-group col-md-6">
											<label for="fullName">Full Name: </label> <input type="text"
																							 class="form-control" id="fullName" name="fullName" placeholder="Full Name">
										</div>


										<div class="form-group col-md-6">
											<label for="phone">Phone Number:</label> <input type="text"
																							class="form-control" id="phone" name="phone" placeholder="Phone Number">
										</div>
										<div class="form-group col-md-6">
											<label for="email">Email:</label> <input type="email"
																					 class="form-control" id="email" name="email" placeholder="Email">
										</div>


									</div>
									<hr>
									<h5 class="mb-3">Security</h5>
									<div class="row">
										<div class="form-group col-md-12">
											<label for="uname">User Name:</label> <input type="text"
																						 class="form-control" id="uname" name="userName" placeholder="User Name">
										</div>
										<div class="form-group col-md-6">
											<label for="pass">Password:</label> <input type="password"
																					   class="form-control" id="pass" name="password" placeholder="Password">
										</div>

									</div>

									<button type="submit" class="btn btn-primary">Add New
										User</button>

								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Wrapper END -->
<!-- Footer -->
<footer class="bg-white iq-footer">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-6">
				<ul class="list-inline mb-0">
					<li class="list-inline-item"><a href="privacy-policy.html">Privacy
						Policy</a></li>
					<li class="list-inline-item"><a href="terms-of-service.html">Terms
						of Use</a></li>
				</ul>
			</div>
			<div class="col-lg-6 text-right">
				Copyright 2020 <a href="#">FinDash</a> All Rights Reserved.
			</div>
		</div>
	</div>
</footer>
<!-- Footer END -->

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Appear JavaScript -->
<script src="js/jquery.appear.js"></script>
<!-- Countdown JavaScript -->
<script src="js/countdown.min.js"></script>
<!-- Counterup JavaScript -->
<script src="js/waypoints.min.js"></script>
<script src="js/jquery.counterup.min.js"></script>
<!-- Wow JavaScript -->
<script src="js/wow.min.js"></script>
<!-- Apexcharts JavaScript -->
<script src="js/apexcharts.js"></script>
<!-- Slick JavaScript -->
<script src="js/slick.min.js"></script>
<!-- Select2 JavaScript -->
<script src="js/select2.min.js"></script>
<!-- Owl Carousel JavaScript -->
<script src="js/owl.carousel.min.js"></script>
<!-- Magnific Popup JavaScript -->
<script src="js/jquery.magnific-popup.min.js"></script>
<!-- Smooth Scrollbar JavaScript -->
<script src="js/smooth-scrollbar.js"></script>
<!-- lottie JavaScript -->
<script src="js/lottie.js"></script>
<!-- am core JavaScript -->
<script src="js/core.js"></script>
<!-- am charts JavaScript -->
<script src="js/charts.js"></script>
<!-- am animated JavaScript -->
<script src="js/animated.js"></script>
<!-- am kelly JavaScript -->
<script src="js/kelly.js"></script>
<!-- am maps JavaScript -->
<script src="js/maps.js"></script>
<!-- am worldLow JavaScript -->
<script src="js/worldLow.js"></script>
<!-- Style Customizer -->
<script src="js/style-customizer.js"></script>
<!-- Chart Custom JavaScript -->
<script src="js/chart-custom.js"></script>
<!-- Custom JavaScript -->
<script src="js/custom.js"></script>
</body>
</html>