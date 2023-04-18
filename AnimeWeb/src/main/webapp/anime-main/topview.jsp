<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>

<c:url var="urlAvatar"
	value="${request.rervletContext.realPath}/anime-main/storage/avatarUser/${sessionScope.user.avatar}" />
<div class="col-lg-4 col-md-6 col-sm-8">
	<div class="product__sidebar">
		<fmt:setLocale value="${sessionScope.LANG}" />
		<fmt:setBundle basename="app" />
		<div class="product__sidebar__view">
			<div class="section-title">
				<h5>
					<fmt:message>content.topview</fmt:message>
				</h5>
			</div>

			<div class="filter__gallery">




				<c:forEach var="mv" items="${listMovie.getTop5Movie()}">
					<div class="product__sidebar__view__item set-bg mix day years"
						<c:url var="urlAvatarMovie"
											value="/anime-main/storage/avatarMovie/${mv.avatar}" />
						data-setbg="${urlAvatarMovie}"
						style="background-position: center; background-size: cover;">
						<div class="ep">${mv.currentEpisode}/${mv.totalEpisodes}</div>
						<div class="rate">
							${mv.getAvgScore()} <i class='fa fa-star' style='color: #f3da35'></i>
						</div>
						<div class="view" style='bottom: 10px; right: 10px; top: unset;'>
							<i class="fa fa-eye"></i>${mv.view}
						</div>

						<h5>
							<a href="anime_details?idMv=${mv.idMovie}">${mv.nameMovie}</a>
						</h5>
					</div>
				</c:forEach>





			</div>
		</div>

	</div>
</div>
