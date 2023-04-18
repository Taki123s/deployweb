<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>
<html>
<head>


    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Anime|Check-Out</title>
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
    <%--    <link rel="stylesheet" href="css/nice-select.css" type="text/css">--%>
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/ds/style.css"/>
    <script src="https://kit.fontawesome.com/9847adceef.js"></script>
    <fmt:setLocale value="${sessionScope.LANG}"/>
    <fmt:setBundle basename="app"/>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.4/dist/sweetalert2.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<body>
<fmt:setLocale value="${sessionScope.LANG}"/>
<c:url var="profileServlet" value="/anime-main/profile.jsp"/>
<c:url var="MovieDetail" value="/anime-main/MovieDetail"/>
<c:url var="Index" value="/anime-main/Index"/>
<div id="ah_wrapper">
    <header class="checkout__detail">

        <h1>Hóa đơn bán hàng</h1>
        <div>(Hóa đơn điện tử)</div>
        <div>Ngày ${day}, Tháng ${month}, Năm ${year}</div>
        <br>
        <div class="information">
            <h2>Anime Web</h2><br>
            <div>Mã số thuế: 02343253</div>
            <div>Mã hóa đơn: ${number}</div>
            <div>Họ tên khách hàng: ${user.fullName}</div>
        </div>

    </header>
    <section class="product spad">
        <div class="container">

            <div class="row">
                <div class="col-lg-8">
                    <div class="trending__product">
                        <div class="row">
                            <div class="col-lg-8 col-md-8 col-sm-8">
                                <div class="section-title">

                                    <h4>
                                        Check Out
                                    </h4>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <table>
                                <tr>
                                    <th>Avatar</th>
                                    <th>Name</th>
                                    <th>Movie genre</th>
                                    <th>Producer</th>
                                    <th>Price</th>
                                </tr>
                                <c:forEach var="movie" items="${sessionScope.order.getSelectedMovies()}">
                                    <tr>
                                        <td><img src=${movie.getFirstAvatar()} alt=${movie.getFirstAvatar()} width="100"
                                                 height="200"></td>
                                        <td>${movie.name}</td>
                                        <td>

                                            <c:forEach var="genre" items="${movie.genres}">
                                                <div><fmt:message>${genre.description}</fmt:message></div>
                                            </c:forEach>

                                        </td>
                                        <td>
                                            <c:forEach var="producer" items="${movie.listProducer}">
                                                <div>${producer.name}</div>
                                            </c:forEach>
                                        </td>
                                        <td>${movie.price}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="payment">
                    <div class="bill">
                        <div>Total : ${order.selectedMovies.size()}</div>
                        <div class="name_film_buy">
                            <c:forEach var="moive" items="${order.getSelectedMovies()}">
                                <div>
                                    Name: ${moive.name}
                                    Price: ${movie.getRenderPrice()}đ
                                </div>
                            </c:forEach>
                        </div>
                        <div class="price_total">
                            <div>
                                Total Price: ${sessionScope.order.totalPrice}đ
                            </div>
                        </div>
                    </div>
                    <a href="/anime-main/checkout?action=checkout">
                        <button>Thanh Toán</button>
                    </a>
                    <a href="/anime-main/checkout?action=back">
                        <button id="buyMovie">Quay lại</button>
                    </a>
                </div>
                <div class="col-md-6">


                </div>
            </div>
        </div>
    </section>
    <c:if test="${empty sessionScope.done}">
    <c:choose>
        <c:when test="${success}">
            <script>
                Swal.fire({
                    title: 'Thanh toán thành công',
                    icon: 'success',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    window.location.href = "/anime-main/wishList";
                });
            </script>
        </c:when>
        <c:otherwise>
            <script>
                Swal.fire({
                    title: 'Thanh toán thất bại',
                    icon: 'error',
                    confirmButtonText: 'OK'
                }).then((result) => {
                    window.location.href = "/anime-main/checkout";
                });
            </script>
        </c:otherwise>
    </c:choose>
    </c:if>
    <c:import url="/anime-main/footer.jsp"/>
</div>
<script>

</script>
</body>

</html>
