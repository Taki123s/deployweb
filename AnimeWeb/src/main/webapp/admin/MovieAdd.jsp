<%@ page contentType="text/html; charset=UTF-8"
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
  <c:url var="submitAddMovie" value="/admin/SubmitAddMovie"/>
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
      <form method="post" action="${submitAddMovie}?type=add" enctype="multipart/form-data">
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

                    <div class="p-image">
                      <img class="profile-pic img-fluid" style="width: 50px!important;height: 50px!important;" src="images/user/11.png"
                           alt="profile-pic">
                      <label
                         class="upload-button btn iq-bg-primary">File Upload <input
                            class="file-upload" type="file" accept="image/*" name="avatar1"></label>
                    </div>
                    <div class="p-image">
                      <img class="profile-pic img-fluid" style="width: 50px!important;height: 50px!important;" src="images/user/11.png"
                           alt="profile-pic">
                      <label
                         class="upload-button btn iq-bg-primary">File Upload <input
                            class="file-upload" type="file" accept="image/*" name="avatar2"></label>
                    </div>
                    <div class="p-image">
                      <img class="profile-pic img-fluid" style="width: 50px!important;height: 50px!important;" src="images/user/11.png"
                           alt="profile-pic">
                      <label
                         class="upload-button btn iq-bg-primary">File Upload <input
                            class="file-upload" type="file" accept="image/*" name="avatar3"></label>
                    </div>
                    <div class="p-image">
                      <img class="profile-pic img-fluid" style="width: 50px!important;height: 50px!important;" src="images/user/11.png"
                           alt="profile-pic">
                      <label
                         class="upload-button btn iq-bg-primary">File Upload <input
                            class="file-upload" type="file" accept="image/*" name="avatar4"></label>
                    </div>
                    <div class="p-image">
                      <img class="profile-pic img-fluid" style="width: 50px!important;height: 50px!important;" src="images/user/11.png"
                           alt="profile-pic">
                      <label
                         class="upload-button btn iq-bg-primary">File Upload <input
                            class="file-upload" type="file" accept="image/*" name="avatar5"></label>
                    </div>
                  </div>
                  <div class="img-extension mt-3">
                    <div class="d-inline-block align-items-center">
                      <span>Only</span> <label href="javascript:void();">.jpg</label> <label
                         >.png</label> <label>.jpeg</label> <span>allowed</span>
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
                      <label for="name">Name: </label> <input type="text"
                                                              class="form-control" id="name" name="name" placeholder="Name">
                    </div>


                    <div class="form-group col-md-6">
                      <label for="totalEpisode">Total Episode:</label> <input type="number"
                                                                              class="form-control" id="totalEpisode" name="totalEpisode" placeholder="Total Episode">
                    </div>
                    <div class="form-group col-md-6">
                      <label for="descriptionVN">Description VN:</label> <input type="text"
                                                                                class="form-control" id="descriptionVN" name="descriptionVN" placeholder="Description VN">
                    </div>
                    <div class="form-group col-md-6">
                      <label for="descriptionEN">Description EN:</label> <input type="text"
                                                                                class="form-control" id="descriptionEN" name="descriptionEN" placeholder="Description EN">
                    </div>
                    <div class="form-group col-md-6">
                      <label for="price">Price:</label> <input type="number"
                                                               class="form-control" id="price" name="price" placeholder="Price">
                    </div>
                  </div>
                  <hr>
                  <h5 class="mb-3">Extendsion</h5>
                  <div class="row">
                    <div class="form-group col-md-12">
                      <div id="pickedGenre">

                      </div>
                      <div id="GenresRender">
                        <label>Chọn thể loại
                          <select name="genrePicker"  id="genrePicker">
                            <c:forEach var="genre" items="${requestScope.listGenre}">
                              <option value="${genre.id}">${genre.description}</option>
                            </c:forEach>
                          </select>
                        </label>
                        <button type="button" onclick="chooseGenre()">Add</button>
                      </div>
                    </div>
                    <div class="form-group col-md-6">

                      <div id="typeRender">
                        <label>Chọn loại phim
                          <select name="typePicker"  id="typePicker">
                            <c:forEach var="type" items="${requestScope.listType}">
                              <option value="${type.id}">${type.description}</option>
                            </c:forEach>
                          </select>
                        </label>

                      </div>
                    </div>
                  </div>

                  <button type="submit" class="btn btn-primary">Add New
                    Movie</button>

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
<script>
    function chooseGenre(){
      let pickedValue = $("#genrePicker").val();
      let pickedText =$('#genrePicker option[value="' + $("#genrePicker").val() + '"]').text();
       $("#pickedGenre").append(
               `<div><label style="border: 1px solid red" onclick="reverseSelect(this)">
`+pickedText+`<input style="display: none;" type="number" name="genreValue" value="`+pickedValue+`">`+`
</label></div>`
       );
      $('#genrePicker option').filter(function() {
        return this.value === pickedValue;
      }).remove();
      if($('#genrePicker option').length===0){
        $('#GenresRender').css('display','none');
      }

    }
    function reverseSelect(button){
      if($("#GenresRender").is(":hidden")){
        $('#GenresRender').css('display','block');
      }
      $('#genrePicker').append(
              ` <option value="`+$(button).val()+`">`+$(button).text()+`</option>
              `
      )
      $($(button).closest("div")).remove();
    }
</script>
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