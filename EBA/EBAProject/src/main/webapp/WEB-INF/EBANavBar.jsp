<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
<nav
	class="navbar navbar-expand-lg navbar-light bg-white fixed-top shadow d-flex flex-column">
	<div class="container-fluid justify-content-end">
		<a href="#" class="me-5"> <i class="fas fa-envelope fa-2x "
			style="color: #FFD700"></i>
		</a> <a href="#" class="me-5"> <i class="fab fa-line fa-2x"
			style="color: green"></i>
		</a> <a href="#" class="me-5"> <i class="fab fa-facebook fa-2x"
			style="color: blue"></i>
		</a> <a href="#" class="me-2 text-decoration-none"> <i
			class="fas fa-shopping-cart fa-lg me-2" style="color: #428bca"></i> <strong
			class="p-secondary me-2" style="color: #428bca">購物車 ( ${shopCount ? shopCount : '0'}
				)</strong>
		</a>
		<i class="fas fa-solid fa-user fa-lg me-2" style="color: #428bca"></i>
		<c:choose>
			<c:when test="${users.username != null}">
				<strong class="p-secondary me-2" style="color: #428bca">${users.username}</strong>
			</c:when>
			<c:otherwise>
			<a href="EBALogin" class="me-2 text-decoration-none"> 
					<strong class="p-secondary me-2">登入</strong>
				</a>	
			</c:otherwise>
		</c:choose>
		<form>
			<div class="input-group">
				<input class="form-control" type="search" placeholder="搜尋"
					aria-label="Search">
				<div class="input-group-append">
					<button class="btn btn-outline-success" type="submit">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div>
		</form>
	</div>
	<div class="container-fluid justify-content-center">
		<ul class="navbar-nav mx-auto ">
			<li class="nav-item"><a class="nav-link me-3" href="#"> <strong>💯所有商品</strong>
			</a></li>
			<li class="nav-item"><a class="nav-link me-3" href="#"> <strong>🔥熱賣商品</strong>
			</a></li>
			<li class="nav-item"><a class="nav-link me-3" href="#"> <strong>🆕新品</strong>
			</a></li>
			<li class="nav-item"><a class="nav-link me-3" href="#"> <strong>👕男生</strong>
			</a></li>
			<li class="nav-item"><a class="nav-link me-3" href="#"> <strong>👗女生</strong>
			</a></li>
			<li class="nav-item"><a class="nav-link me-3" href="#"> <strong>💥特惠</strong>
			</a></li>
			<li class="nav-item"><a class="nav-link me-3" href="#"> <strong>✨精選</strong>
			</a></li>
		</ul>
	</div>
</nav>
<script>
	
</script>