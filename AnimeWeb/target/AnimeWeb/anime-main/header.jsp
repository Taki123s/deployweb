<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:useBean id="now" class="java.util.Date" scope="request"/>
<c:url var="urlAvatar" value="${user.avatar}"/>
<c:url var="listFollow" value="/anime-main/ListFollow"/>
<c:url var="movieDetail" value="/anime-main/MovieDetail"/>
<c:url var="listPurchased" value="/anime-main/ListPurchased"/>
<fmt:setBundle basename="app"/>
<header class="header">
    <fmt:setLocale value="${sessionScope.LANG}"/>
    <fmt:setBundle basename="app"/>
    <div class="container">
        <div class="row">
            <div class="col-lg-2">
                <div class="header__logo">
                    <a href="index.jsp"> <img src="img/logo.png" alt="">
                    </a>
                </div>
            </div>
            <div class="col-lg-7">
                <div class="header__right">

                    <form class="searchTag" action="search" method="post">
                        <div class="searchInput">
                            <input id="search-input" placeholder="Search here....."
                                   oninput="searchByName(this)" name="searchBox" type="text">

                        </div>
                    </form>
                    <div class="iconSearch">
                        <i class="fas fa-search"></i>
                        <table id="renderSearch"></table>


                    </div>

                </div>

            </div>
            <div class="col-lg-3">
                <c:if test="${not empty sessionScope.user}">
                    <div class="vallet">
                        <a href="#"><i class="fas fa-wallet fa-lg" style="color: #ffffff;"></i></a> &nbsp; 2.000.000đ
                    </div>
                </c:if>
                <div class="header__right2">
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <c:if test="${sessionScope.user.isManager()==false}">
                                <div>
                                    <img alt="" src="${urlAvatar}" id="avtUser1" onclick="vision1()">
                                    <ul class="profile" id="profile1">

                                        <li><a href="profile.jsp">
                                            <button class="">
                                                <fmt:message>header.account</fmt:message>
                                            </button>
                                        </a></li>
                                        <li><a href="${listFollow}"><fmt:message>header.follow</fmt:message></a>
                                        <li><a href="${listPurchased}">Phim đã mua</a></li>
                                        <li><a href="logOut">
                                            <button
                                                    class="fas fa-sign-out-alt"></button>
                                        </a></li>
                                    </ul>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.user.isManager()==true}">
                                <div>
                                    <fmt:setLocale value="${sessionScope.LANG}"/>
                                    <fmt:setBundle basename="app"/>
                                    <img alt="" src="${urlAvatar}" id="avtUser" onclick="vision()">
                                    <ul class="profile" id="profile">

                                        <li><a href="profile.jsp">
                                            <button class="">
                                                <fmt:message>header.account</fmt:message>
                                            </button>
                                        </a></li>
                                        <li><a href="${listFollow}"><fmt:message>header.follow</fmt:message></a>
                                        <li><a href="${listPurchased}">Phim đã mua</a></li>
                                        <c:url var="adm" value="/admin/UserList"/>
                                        <li><a href="${adm}">
                                            <button class="fa fa-cog"></button>
                                        </a></li>
                                        <li><a href="logOut">
                                            <button
                                                    class="fas fa-sign-out-alt"></button>
                                        </a></li>

                                    </ul>
                                </div>
                            </c:if>

                        </c:when>
                        <c:when test="${empty sessionScope.user}">
                            <a href="login.jsp"><fmt:message>menu.login</fmt:message> </a>
                            <font color="#e53637"> / </font>
                            <a href="signup.jsp"><fmt:message>menu.signup</fmt:message></a>

                        </c:when>
                    </c:choose>
                    <input type="text" id="idSession" value="${pageContext.session.id}"
                           style="display: none;">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <fmt:setLocale value="${sessionScope.LANG}"/>
                <fmt:setBundle basename="app"/>
                <div class="header__nav">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li class="active"><a href="index.jsp"><fmt:message>menu.hompage</fmt:message></a></li>
                            <li><a href="#"><fmt:message>menu.categories</fmt:message><span
                                    class="arrow_carrot-down"></span></a>
                                <div class="dropdown">
                                    <ul>
                                        <%--										<c:forEach var="genre" items="${listMovie.listGenre}">--%>
                                        <%--											<li><a href="genre?genre=${genre}"><fmt:message>${genre}</fmt:message>--%>
                                        <%--											</a></li>--%>
                                        <%--										</c:forEach>--%>
                                    </ul>
                                </div>
                            </li>

                            <li><a href="blog.jsp"><fmt:message>menu.ourblog</fmt:message></a></li>
                            <li><a
                                    href="https://www.facebook.com/profile.php?id=100012214729084"><fmt:message>menu.contracts</fmt:message></a>
                            </li>
                            <li><a href="#"><fmt:message>content.langue</fmt:message></a>
                                <div class="dropdown2">
                                    <c:set var="query" value="${pageContext.request.queryString}"/>

                                    <ul>
                                        <c:if test="${param.lang== null}">
                                            <li style="color: black;"><a
                                                    href="?${query}&&lang=vi_VN"><fmt:message>content.vn</fmt:message></a>
                                            </li>
                                            <li style="color: black;"><a
                                                    href="?${query}&&lang=en_US"><fmt:message>content.en</fmt:message></a>
                                            </li>
                                        </c:if>
                                        <c:if test="${param.lang!= null}">

                                            <li style="color: black;"><a
                                                    href="?${fn:substring(query, 0, query.length()-12)}&&lang=vi_VN"><fmt:message>content.vn</fmt:message></a>
                                            </li>
                                            <li style="color: black;"><a
                                                    href="?${fn:substring(query, 0, query.length()-12)}&&lang=en_US"><fmt:message>content.en</fmt:message></a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </div>
                            </li>
                            <li>
                                <a href="#">Wish list <i class="fas fa-film"></i></a>
                            </li>
                            <li>
                                <a href="#">Recharge <i class="fas fa-credit-card" style="color: #fafafa;"></i></a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <div id="mobile-menu-wrap"></div>
    </div>
</header>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/player.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>
<script>
    function searchByName(input) {
        var value = input.value;
        var ssid = document.getElementById("idSession").value;
        $.ajax({
            url: "search",
            type: "get",
            data: {
                searchBox: value,
                id: ssid
            },
            success: function (data) {
                var target = document.getElementById("renderSearch");
                target.innerHTML = data;
            },
            error: function (data) {

            }
        });

    }
</script>
<script>
    function vision() {
        var isVisible = document.getElementById("profile").style.display;
        if (isVisible == "") {
            document.getElementById("profile").style.display = 'block';
        } else if (isVisible == "block") {
            document.getElementById("profile").style.display = 'none';
        } else {
            document.getElementById("profile").style.display = 'block';
        }
    }
</script>
<script>
    function vision1() {
        var isVisible = document.getElementById("profile1").style.display;
        if (isVisible == "") {
            document.getElementById("profile1").style.display = 'block';
        } else if (isVisible == "block") {
            document.getElementById("profile1").style.display = 'none';
        } else {
            document.getElementById("profile1").style.display = 'block';
        }
    }
</script>
<script>
    $(function () {
        $('#nav ul li a').each(function () {
            var path = window.location.href;
            var current = path.substring(path.lastIndexOf('/') + 1);
            var url = $(this).attr('href');
            if (url == current) {
                $(this).addClass('active');
            }
            ;
        });
    });
</script>