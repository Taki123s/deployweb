<%@ page import="database.DAOPermission" %>
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
</head>
<c:url var="accountAvatar" value="${requestScope.account.avatar}" />
<c:url var="SubmitEditAccount" value="/admin/SubmitEditAccount" />
<c:url var="EditPasswordAccount" value="/admin/EditPasswordAccount"/>
<body class="sidebar-main-active right-column-fixed">
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
				<div class="row">
					<div class="col-lg-12">
						<div class="iq-card">
							<div class="iq-card-body p-0">
								<div class="iq-edit-list">
									<ul class="iq-edit-profile d-flex nav nav-pills">
										<li class="col-md-3 p-0"><a class="nav-link active"
											data-toggle="pill" href="#personal-information"> Personal
												Information </a></li>
										<li class="col-md-3 p-0"><a class="nav-link"
											data-toggle="pill" href="#chang-pwd"> Change Password </a></li>

									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-12">
						<div class="iq-edit-list-data">
							<div class="tab-content">
								<div class="tab-pane fade active show" id="personal-information"
									role="tabpanel">
									<div class="iq-card">
										<div class="iq-card-header d-flex justify-content-between">
											<div class="iq-header-title">
												<h4 class="card-title">Personal Information</h4>
											</div>
										</div>
										<div class="iq-card-body">
											<form method="post" action="${SubmitEditAccount}?idUser=${requestScope.account.id}&&typeId=${requestScope.account.type.id}" enctype="multipart/form-data">
												<div class="form-group row align-items-center">
													<div class="col-md-12">
														<div class="profile-img-edit">
															<img class="profile-pic" src="${accountAvatar}"
																alt="profile-pic">
															<div class="p-image">
																<i class="ri-pencil-line upload-button"></i> <input
																	class="file-upload" type="file" accept="image/*" name="imageUser"/>
															</div>
														</div>
													</div>
												</div>
												<div class=" row align-items-center">
													<div class="form-group col-sm-6">
														<label for="fname">FullName :</label> <input type="text"
															class="form-control" id="fname"
															value="${requestScope.account.fullName}" name="fullName" required="required">
													</div>

													<div class="form-group col-sm-6">
														<label for="email">Email :</label> <input type="text"
															class="form-control" id="email" value="${requestScope.account.email}" name="email" required="required">
													</div>
													<div class="form-group col-sm-6">
														<label >Type Account :</label>
														
														<p>${requestScope.account.getTypeAccount()}</p>
														
													</div>

													<div class="form-group col-sm-6">
														<label for="dob">Phone Number :</label> <input
															class="form-control" id="dob"
															value="${requestScope.account.phone}" name="phoneNumber" required="required">
													</div>



												</div>
												<div id="error" style="color: red">${requestScope.error}</div>
												<button type="submit" class="btn btn-primary mr-2">Submit</button>
												<button type="reset" class="btn iq-bg-danger">Cancel</button>
											</form>
										</div>
										<div class="form-group row align-items-center">
											<div class="form-group col-sm-6">
												<label>Role :</label>
												<div id="renderRoles">
													<c:forEach var="role" items="${requestScope.account.roles.values()}">
														<div style="border: 1px solid red;">
															<p style="color: green">${role.description}</p>

															<p>Permission</p>
															<div id="permissionArea">
															<c:forEach var="permission" items="${role.permissions}">
																<div id="permission-${permission.id}">
																	<p style="color: red">${permission.description}</p>
																	<label style="color: red"
																		   onclick="removePermission(${permission.id},${role.id})">Xóa </label>
																</div>

															</c:forEach>
																<label>Add Permission</label>
																	<select name="addPer" class="permissionSelect">
																		<c:forEach var="pms" items="${DAOPermission.getEnablePermission(requestScope.account.id,role.id)}">
																			<option value="${pms.id}">${pms.description}</option>
																		</c:forEach>
																	</select>
																	<button onclick="addPermission(this,${role.id})">Thêm</button>
															</div>
															<div>
																<label style="color: green"
																		onclick="removeRole(${role.id},${requestScope.account.id})">Xóa</label>
															</div>
														</div>
													</c:forEach>
												</div>
												<select id="selectRoletoAdd">
													<c:forEach var="unableRole" items="${requestScope.listUnableRole}">
														<option value="${unableRole.id}">${unableRole.description}</option>
													</c:forEach>
												</select>
												<button onclick="addRole()">Thêm</button>
											</div>
										</div>
									</div>
								</div>
								<div class="tab-pane fade" id="chang-pwd" role="tabpanel">
									<div class="iq-card">
										<div class="iq-card-header d-flex justify-content-between">
											<div class="iq-header-title">
												<h4 class="card-title">Change Password</h4>
											</div>
										</div>
										<div class="iq-card-body">
											<form>

												<div class="form-group">
													<label for="passwordConfirm">New Password:</label> <input
														type="Password" class="form-control" value="" id="passwordConfirm" required>
												</div>

												<button type="button" class="btn btn-primary mr-2" onclick="changePassword()">Submit</button>

												<button type="reset" class="btn iq-bg-danger">Cancel</button>
											</form>
										</div>
									</div>
								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Wrapper END -->
	<!-- Footer -->
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
	<script>
		function addPermission(button,idRole){
			let permissionArea = $(button).parent();
			let permissionSelect = permissionArea.find('.permissionSelect');
			let selectedPermissionId = permissionSelect.val();
			$.ajax({
				url : "AddPermission",
				type : "post",
				data : {
					idPermission : selectedPermissionId,
					idUser : ${requestScope.account.id},
					idRole:idRole,
				},
				success : function(data) {
					renderRole(data);

				},
				error : function(data) {
					console.log(data)
				}
			});
		}
		function removePermission(permission,idRole){
			$.ajax({
				url : "RemovePermission",
				type : "post",
				data : {
					idPermission : permission,
					idUser : ${requestScope.account.id},
					idRole:idRole,
				},
				success : function(data) {
					renderRole(data);

				},
				error : function(data) {
				console.log(data)
				}
			});
		}
	function changePassword(){
		Swal.fire({
			  title: 'Are you sure?',
			  text: "You won't be able to revert this!",
			  icon: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, update it!'
			}).then((result) => {
			  if (result.isConfirmed) {
				  $.ajax({
						url : "EditPasswordAccount",
						type : "get",
						data : {
							password : $("#passwordConfirm").val(),
							idUser : ${account.id},
						},
						success : function(data) {
							var getData = JSON.parse(data);
							if(getData==="true"){
								Swal.fire({
									  position: 'center',
									  icon: 'success',
									  title: 'Your work has been saved',
									  showConfirmButton: false,
									  timer: 1500
									})
							}
							if(getData==="false"){
								Swal.fire({
									  position: 'center',
									  icon: 'error',
									  title: 'Có lỗi xảy ra trong quá trình thay đổi',
									  showConfirmButton: false,
									  timer: 1500
									})
							}
							if(getData==="error"){
								Swal.fire({
									  position: 'center',
									  icon: 'error',
									  title: 'Mật khẩu phải bao gồm chữ hoa, chữ thường, ký tự đặc biệt và chữ số',
									  showConfirmButton: false,
									  timer: 1500
									})
							}
				
						},
						error : function(data) {
							
						}
					});
			  }
			})
	}
	
	
	
	function renderRole(data){
		let object = JSON.parse(data);

		let arrayListRole = Object.values(JSON.parse(object.updateRoleUser));
		let arrayEnableListRole = JSON.parse(object.newEnableListRole);
		let renderRoles="";
		let renderSelectRoletoAdd="";

		for(let i=0;i<arrayListRole.length;i++){
			let permissionList = arrayListRole[i].permissions;
			let permissionDOM="";
			for(let j=0;j<permissionList.length;j++){
				permissionDOM+=`<div id="permission-`+permissionList[j].id+`"><p>`+permissionList[j].description+`</p> <label onclick="removePermission(`+permissionList[j].id+`,`+arrayListRole[i].id+`)">Xóa</label></div>`
			}
			permissionDOM+=`	<label>Add Permission</label>
																	<select name="addPer" class="permissionSelect">`;

					let enablePermission = arrayListRole[i].enablePermission;

					for(let m =0;m<enablePermission.length;m++){
						permissionDOM+=`<option value="`+enablePermission[m].id+`">`+enablePermission[m].description+`</option>`;
					}
					permissionDOM+=`</select><button onclick="addPermission(this,`+arrayListRole[i].id+`)">Thêm</button>`;
			renderRoles+= `<div style="border: 1px solid red;"> <p>`+arrayListRole[i].description+`</p><p>Permission</p><div id="permissionArea">`+
					permissionDOM
						+
					`</div><div>
			<label onclick="removeRole(`+arrayListRole[i].id+`,${requestScope.account.id})" >Xóa</label></div>`
		}
		for(let i=0;i<arrayEnableListRole.length;i++){
			renderSelectRoletoAdd+=`<option value="`+arrayEnableListRole[i].id+`">`+arrayEnableListRole[i].description+`</option>`
		}
		$("#renderRoles").html(renderRoles);
		$("#selectRoletoAdd").html(renderSelectRoletoAdd);
		Swal.fire({
			  position: 'center',
			  icon: 'success',
			  title: 'Your work has been saved',
			  showConfirmButton: false,
			  timer: 1500
			})

	}
	function addRole(){
		Swal.fire({
			  title: 'Are you sure?',
			  text: "You won't be able to revert this!",
			  icon: 'info',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Yes, add it!'
			}).then((result) => {
			  if (result.isConfirmed) {
				ajaxAddrole();
			  }
			})
	}
	function ajaxAddrole(){
		$.ajax({
			url : "AddRoleUser",
			type : "get",
			data : {
				idRole : $("#selectRoletoAdd").val(),
				idUser : ${account.id},
			},
			success : function(data) {
				renderRole(data)
	
			},
			error : function(data) {

			}
		});
	}
	function removeRole(idRole,idUser){
		if(idRole==1){
			Swal.fire({
				  icon: 'error',
				  title: 'Lỗi',
				  text: 'Đây là quyền cơ bản, không thể xóa',

				})
		}else{
			Swal.fire({
				  title: 'Are you sure?',
				  text: "You won't be able to revert this!",
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes, delete it!'
				}).then((result) => {
				  if (result.isConfirmed) {
						ajaxRemoveRole(idRole,idUser);
				  }
				})

		}
	}
		function ajaxRemoveRole(idRole,idUser){
			$.ajax({
				url : "RemoveRoleUser",
				type : "post",
				data : {
					idRole : idRole,
					idUser : idUser,
				},
				success : function(data) {
					 renderRole(data)

				},
				error : function(data) {

				}
			});
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