<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NINI童裝 - 登入</title>
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
<style>
    body {
        background-color: #f8f9fa;
    }
    .card {
        border: none;
        border-radius: 1rem;
    }
    .btn-primary {
        background-color: #007bff;
        border-color: #007bff;
    }
    .btn-primary:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }
</style>
</head>
<body>
	 <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card shadow-sm p-4" style="width: 400px;">
      <div class="card-body">
        <h3 class="card-title text-center mb-4">NINI童裝登入</h3>
        <form action="/login" method="post">
          <div class="mb-3">
            <label for="username" class="form-label">帳號</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="輸入您的帳號" required>
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">密碼</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="輸入您的密碼" required>
          </div>
          <div class="d-grid">
            <button type="submit" class="btn btn-primary btn-block mt-3">登入</button>
          </div>
        </form>
        <div class="d-flex justify-content-between mt-3">
          <a href="#" class="text-muted fs-5">忘記密碼?</a>
          <a href="EBARegistry" class="text-muted fs-5">新建帳號</a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
