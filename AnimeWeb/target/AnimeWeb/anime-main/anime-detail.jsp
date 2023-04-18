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
	   value="${requestScope.movie.getFirstAvatar()}" />
<c:url var="commentMovie" value="/anime-main/CommentMovie" />
<c:url var="renderComment" value="/anime-main/RenderCommentMovie"/>
<body>
	<fmt:setLocale value="${sessionScope.LANG}" />
	<fmt:setBundle basename="app" />

	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<div id="ah_wrapper">
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
						<a href=""><fmt:message>menu.categories</fmt:message></a>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Anime Section Begin -->
	<section class="anime-details spad">
		<div class="container">
			<div class="anime__details__content">
				<div class="row">
					<div class="col-lg-3">

						<div class="anime__details__pic set-bg"
							data-setbg="${urlAvatarMovie}">
<%--							<div class="comment">--%>
<%--								<i class="fa fa-comments"></i> ${viewFilm.listComment.size()}--%>
<%--							</div>--%>
<%--							<div class="view">--%>
<%--								<i class="fa fa-eye"></i> ${viewFilm.view }--%>
<%--							</div>--%>
						</div>
					</div>
					<div class="col-lg-9">
						<div class="anime__details__text">
							<div class="anime__details__title">
								<h3>${requestScope.movie.name}</h3>

							</div>
							<div class="anime__details__rating">
<%--								<div class="rating" style="font-size: 18px; color: #b7b7b7;">--%>
<%--									${movie.getAvgScore()} <i class="fa fa-star"></i>--%>
<%--								</div>--%>
<%--								<span>${movie.voteTotal()} <fmt:message>content.votes</fmt:message></span>--%>
							</div>
							<p>${requestScope.movie.descriptionVN}</p>
							<div class="anime__details__widget">
								<div class="row">
									<div class="col-lg-6 col-md-6">
										<ul>
											<li><span><fmt:message>content.type</fmt:message></span>
												${requestScope.movie.type.description}</li>
											<li><span><fmt:message>menu.categories</fmt:message>:
											<c:forEach var="genre" items="${requestScope.movie.genres}">
												${genre.description}
											</c:forEach>
											</span>

											</li>
											<li><span>Nhà sản xuất:
											<c:forEach var="producer" items="${requestScope.movie.listProducer}">
												${producer.name}
											</c:forEach>

											</span></li>

										</ul>
									</div>
									<div class="col-lg-6 col-md-6">
										<ul>
											<li><span><fmt:message>content.duration</fmt:message></span>
												24 min/ep</li>
											<li><span><fmt:message>content.quality</fmt:message></span>
												HD</li>
											<li><span><fmt:message>content.views</fmt:message> ${requestScope.movie.views}</span>
												</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="anime__details__btn">
<%--								<c:choose>--%>

<%--									<c:when test="${checkFl==false}">--%>

<%--										<a href="follow?idMv=${viewFilm.idMovie}" class="follow-btn">--%>
<%--											<i class="fa fa-heart-o"></i> <fmt:message>button.follow</fmt:message>--%>

<%--										</a>--%>

<%--									</c:when>--%>
<%--									<c:when test="${empty checkFl}">--%>

<%--										<a href="follow?idMv=${viewFilm.idMovie}" class="follow-btn">--%>
<%--											<i class="fa fa-heart-o"></i> <fmt:message>button.follow</fmt:message>--%>

<%--										</a>--%>

<%--									</c:when>--%>
<%--									<c:when test="${checkFl==true}">--%>
<%--										<a href="follow?idMv=${viewFilm.idMovie}" class="follow-btn">--%>
<%--											<i class="fa fa-heart"></i> <fmt:message>button.follow</fmt:message>--%>

<%--										</a>--%>
<%--									</c:when>--%>



<%--								</c:choose>--%>

									<c:if test="${!requestScope.movie.isFree()}">
										<button type="submit" style="color: white" id="buyMovie">Mua ngay ${requestScope.movie.getRenderPrice()} đ</button>
									</c:if>

								<a href="anime_details?idf=${requestScope.viewFilm.idMovie}"
									class="watch-btn"><span><fmt:message>button.watch</fmt:message></span>
									<i class="fa fa-angle-right"></i></a>
								<button id="rateBtn">
									<fmt:message>button.rate</fmt:message>
								</button>
								<div>
									<form action="rateMovie?id=${requestScope.movie.id}" method="post"
										id="formVote">
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<p class="fa fa-star rateStar"></p>
										<c:choose>

											<c:when test="${empty sessionScope.user}">
												<input type="number" id="scoreMovie" value="1"
													name="scoreMovie" style="display: none;">
											</c:when>
											<c:when test="${not empty sessionScope.user}">
<%--												<input type="number" id="scoreMovie"--%>
<%--													value="${user.getmyRate(movie.id)}"--%>
<%--													name="scoreMovie" style="display: none;">--%>
											</c:when>
										</c:choose>
										<input id="vote" type="submit"
											value=<fmt:message>content.vote</fmt:message> />
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8 col-md-8">
					<div class="anime__details__review">
						<div class="section-title">
							<h5>
								<fmt:message>content.reviews</fmt:message>
							</h5>
						</div>



						<div id="commentBase">
							<c:forEach var="comment" items="${requestScope.movie.listComment}">
								<div class="anime__review__item">
									<div class="anime__review__item__pic">
										<c:url var="avt"
											   value="${comment.accountComment.avatar}" />
										<img src="${avt}" alt="">
									</div>
									<div class="anime__review__item__text">
										<h6>
												${comment.accountComment.fullName} - <span>${comment.diffTime}</span>
										</h6>
										<p>${comment.content}</p>
									</div>
									<div class="replyBase">
										<button
												value="${comment.id}"
												class="setValue" onclick="showForm(${comment.id},this,${comment.accountComment.id})">Reply</button>
										<div class="hiddenForm" style="display: none">

										</div>
									</div>

											<div id="commentBase1${comment.id}">


											</div>
									<c:if test="${comment.availableReply}">
										<button id="moreComment${comment.id}" onclick="showMore(this,`root1`)" value="0">Xem thêm</button>

									</c:if>
								</div>
							</c:forEach>
						</div>
						<c:if test="${availableToRender>0}">
							<button id="moreComment" onclick="showMore(this,`root0`)" value="${requestScope.rendered}">Xem thêm 5 bình luận tiếp theo, còn ${availableToRender} khả dụng</button>
						</c:if>
					</div>






					<div class="anime__details__form">
						<div class="section-title">
							<h5>
								<fmt:message>conten.comment</fmt:message>
							</h5>
						</div>

						<form action="${commentMovie}" method="post">
							<input type="number" value="${requestScope.movie.id}" hidden name="idMovie">
							<textarea placeholder="Your Comment" name="message"></textarea>
							<button type="submit" value="${requestScope.movie.id}" name="saveID">
								<i class="fa fa-location-arrow"></i>
								<fmt:message>content.review</fmt:message>
							</button>
						</form>
					</div>





				</div>
				<c:import url="/anime-main/topview.jsp" />

			</div>
		</div>
	</section>
	<!-- Anime Section End -->

	<!-- Footer Section Begin -->
	<c:import url="/anime-main/footer.jsp" />
	</div>
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
	<script>
		function showForm(idComment,button,userReply){




					const form = button.closest(".replyBase").querySelector(".hiddenForm");
					let innerForm ;
					// Hiển thị hoặc ẩn form tùy thuộc vào trạng thái hiện tại của form
					if (form.style.display === "none") {
						form.style.display = "block";
						innerForm = `<form
										action="${commentMovie}?idParent=`+idComment+`&idUserReply=`+userReply+`&idMovie=${requestScope.movie.id}"
										 method="post" class="formComment">
										<textarea placeholder="Your Comment" name="message"
											style="display: block;"></textarea>
										<button type="submit" value="" name="" class="site-btn">
											<i class="fa fa-location-arrow"></i> Submit
										</button>
									</form>`
						form.innerHTML= innerForm;
					} else {
						form.style.display = "none";
					}


		}
	</script>
	<script>
		function showMore(button,type){
			let rendered = button.value;
			let parentId = button.id.substring("moreComment".length);
			$.ajax({
				url : "${renderComment}",
				type : "post",
				data : {
					rendered: rendered,
					idMovie:${requestScope.movie.id},
					type:type,
					parentId:parentId,
				},
				success : function(data) {
					renderComment(data,button.id,type,parentId);

				},
				error : function(data) {
					console.log("vl")
				}
			});
		}
		function renderComment(json,namebutton,type,parentId){
			let data = JSON.parse(json);
			let commentList = JSON.parse(data.listComment);
			let rendered = JSON.parse(data.rendered);
			let enableRender = JSON.parse(data.enableRender);
			$("#"+namebutton).val(rendered);

			let reviewItemHTML = `
`;
			if(type=="root0"){
				let moreButton;
				let replyBase;
				for(let i=0;i<commentList.length;i++){
					moreButton=commentList[i].availableReply?`<button id="moreComment`+commentList[i].id+`" onclick="showMore(this,\`root1\`)" value="0">Xem thêm</button>`:``;
					replyBase=`	<div class="replyBase">
										<button
												value="`+commentList[i].id+`"
												class="setValue" onclick="showForm(`+commentList[i].id+`,this,`+commentList[i].accountComment.id+`)">Reply</button>
										<div class="hiddenForm" style="display: none">

										</div>
									</div>`;
					reviewItemHTML+=`
<div class="anime__review__item">
        <div class="anime__review__item__pic">

            <img src="`+commentList[i].accountComment.avatar+`" alt="">
        </div>
        <div class="anime__review__item__text">
            <h6>`+commentList[i].accountComment.fullName+`
                - <span>`+commentList[i].diffTime+`</span>
            </h6>
            <p>`+commentList[i].content+`</p>
        </div>`+replyBase+`<div id="commentBase1`+commentList[i].id+`">


											</div>`+ moreButton+`
  </div>`
				}
				$("#commentBase").append(reviewItemHTML);

			}

			if (type == "root1" || type == "root2") {
				let moreButton;
				let replyBase;
				let parent;
				for (let i = 0; i < commentList.length; i++) {
					if(type=="root2"){
						parent = commentList[i].parentId;
					}else{
						parent=commentList[i].id;
					}
					replyBase = `	<div class="replyBase">
										<button
												value="` + commentList[i].id + `"
												class="setValue" onclick="showForm(` + parent+ `,this,` + commentList[i].accountComment.id + `)">Reply</button>
										<div class="hiddenForm" style="display: none">

										</div>
									</div>`;
					moreButton = commentList[i].availableReply ? `<button id="moreComment` + commentList[i].id + `" onclick="showMore(this,\`root2\`)" value="0">Xem thêm</button>` : ``;
					reviewItemHTML += `
		<div
			class="blog__details__comment__item blog__details__comment__item--reply">
													<div class="blog__details__comment__item__pic">
														<img
																src="` + commentList[i].accountComment.avatar + `"
																style="width: 50px; height: 50px; border-radius: 50%;"
																alt="">
													</div>
													<div class="blog__details__comment__item__text">
														<div class="anime__review__item__text">
															<h6>` + commentList[i].accountComment.fullName + `
																	reply to` + commentList[i].accountReply.fullName + `
																		 - <span>` + commentList[i].diffTime + `</span>
															</h6>
															<p>` + commentList[i].content + `</p></div>` +replyBase+ `<div id="commentBase2` + commentList[i].id + `">


											</div>` + moreButton + `
														</div>
													</div>
		</div>

					`
				}

				if (type == "root1") {

					$("#commentBase1" + parentId).append(reviewItemHTML);
				} else {
					$("#commentBase2" + parentId).append(reviewItemHTML);
				}
			}
			if(enableRender==0){
				$("#"+namebutton).css("display","none");
			}else{
				let valueCmt = enableRender>=5?5:enableRender;
				$("#"+namebutton).html("Xem thêm"+valueCmt+" bình luận tiếp theo, còn "+ enableRender+" bình luận khả dụng");
				$("#"+namebutton).val(rendered);
			}

		}

	</script>
	<script>
		$("#rateBtn").on("click", function() {
			$("#formVote").toggleClass("show");
		});

		$(".rateStar").on("click", function() {
			// Xóa màu trắng tất cả các nút đánh giá
			$(".rateStar").css("color", "white");

			// Thêm màu vàng cho các nút đánh giá đã được nhấn
			$(this).prevAll().addBack().css("color", "yellow");

			// Thiết lập giá trị đánh giá cho phần tử input "scoreMovie"
			$("#scoreMovie").val($(this).index() + 1);
		});

		$(".rateStar").on("mouseover", function() {
			// Xóa màu trắng tất cả các nút đánh giá
			$(".rateStar").css("color", "white");

			// Thêm màu vàng cho các nút đánh giá được trỏ chuột qua
			$(this).prevAll().addBack().css("color", "yellow");
		});

		$(".rateStar").on("mouseleave", function() {
			// Xóa màu trắng tất cả các nút đánh giá
			$(".rateStar").css("color", "white");

			// Thêm màu vàng cho các nút đánh giá đã được nhấn
			$(".rateStar").slice(0, $("#scoreMovie").val()).css("color", "yellow");
		});
	</script>
	<script>
		let arrbutton = document.getElementsByClassName("rateStar");
		let value = document.getElementById("scoreMovie").value;
		for (let j = 0; j < value; j++) {
			arrbutton[j].style.color = 'yellow';
		}
	</script>

</body>

</html>