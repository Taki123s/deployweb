<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="shortcut icon" href="images/favicon.ico"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Typography CSS -->
    <link rel="stylesheet" href="css/typography.css">
    <!-- Style CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Full calendar -->
    <link href='fullcalendar/core/main.css' rel='stylesheet'/>
    <link href='fullcalendar/daygrid/main.css' rel='stylesheet'/>
    <link href='fullcalendar/timegrid/main.css' rel='stylesheet'/>
    <link href='fullcalendar/list/main.css' rel='stylesheet'/>

    <link rel="stylesheet" href="css/flatpickr.min.css">
    <c:url var="MovieList" value="/admin/MovieList"/>
    <c:url var="chapterSetting" value="/admin/ChapterSetting"/>
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
    <c:import url="/admin/sidebar.jsp"/>
    <!-- TOP Nav Bar -->
    <c:import url="/admin/header.jsp"/>
    <div id="content-page" class="content-page">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                    <div class="iq-card">
                        <div class="iq-card-header d-flex justify-content-between">
                            <div class="iq-header-title">
                                <h4 class="card-title">Bootstrap Datatables</h4>
                            </div>
                        </div>
                        <div class="iq-card-body">
                            <p>Images in Bootstrap are made responsive with <code>.img-fluid</code>. <code>max-width:
                                100%;</code> and <code>height: auto;</code> are applied to the image so that it scales
                                with the parent element.</p>
                            <div class="table-responsive">
                                <table id="datatable" class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>Stt</th>
                                        <th>Id</th>
                                        <th>Name</th>
                                        <th>currentEpisode</th>
                                        <th>totalEpisode</th>
                                        <th>Type</th>
                                        <th>Original Price</th>
                                        <th>Render Price</th>
                                        <th>Create At</th>
                                        <th>Option</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="stt" value="${requestScope.index*9}"/>
                                    <c:forEach var="movie" items="${requestScope.listMovie}">
                                        <c:set var="stt" value="${stt + 1}"/>
                                        <tr>
                                            <td>${stt}</td>
                                            <td>${movie.id}</td>
                                            <td>${movie.name}</td>
                                            <td>${movie.currentEpisode}</td>
                                            <td>${movie.totalEpisode}</td>
                                            <td>${movie.type.description}</td>
                                            <td>${movie.price}</td>
                                            <td>${movie.getRenderPrice()}</td>
                                            <td>${movie.createAt}</td>
                                            <td>
                                                <a class="iq-icons-list" href="${chapterSetting}?idMovie=${movie.id}"><i class="ion-upload"></i>
                                                </a>

                                                <a class="iq-bg-primary" data-toggle="tooltip"
                                                        data-placement="top" title=""
                                                        data-original-title="Edit" type="submit">
                                                    <i class="ri-pencil-line"></i>
                                                </a>
                                                <a class="iq-bg-primary" data-toggle="tooltip"
                                                        data-placement="top" title=""
                                                        data-original-title="Delete" type="button"
                                                        onclick="">
                                                    <i class="ri-delete-bin-line"></i>
                                                </a>
                                            </td>
                                        </tr>

                                    </c:forEach>

                                    </tbody>

                                </table>
                                <div class="col-md-6">
                                    <nav aria-label="Page navigation example">
                                        <ul class="pagination justify-content-end mb-0">
                                            <li class="page-item disabled"><a class="page-link"
                                                                              href="" tabindex="-1"
                                                                              aria-disabled="true">Previous</a></li>
                                            <c:forEach var="i" begin="1" end="${requestScope.totalMovie}">
                                                <c:if test="${i==requestScope.index+1}">
                                                    <li class="page-item active">
                                                </c:if>
                                                <c:if test="${i!=requestScope.index+1}">
                                                    <li class="page-item">
                                                </c:if>
                                                <a class="page-link" href="${MovieList}?index=${i-1}">${i}</a>
                                                </li>
                                            </c:forEach>

                                            <li class="page-item"><a class="page-link"
                                                                     href="">Next</a></li>
                                        </ul>
                                    </nav>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="iq-footer">
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
    <!-- Raphael-min JavaScript -->
    <script src="js/raphael-min.js"></script>
    <!-- Morris JavaScript -->
    <script src="js/morris.js"></script>
    <!-- Morris min JavaScript -->
    <script src="js/morris.min.js"></script>
    <!-- Flatpicker Js -->
    <script src="js/flatpickr.js"></script>
    <!-- Style Customizer -->
    <script src="js/style-customizer.js"></script>
    <!-- Chart Custom JavaScript -->
    <script src="js/chart-custom.js"></script>
    <!-- Custom JavaScript -->
    <script src="js/custom.js"></script>
</body>
</html>