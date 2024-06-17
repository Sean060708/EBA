<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>NINI童裝 - 登入</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
        <div class="card shadow-sm" style="width: 400px;">
            <div class="card-body">
                <h5 class="card-title text-center mb-4">Login</h5>
                <form action="test" method="post">
                    <div class="form-group">
                        <label for="username">帳號</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="輸入您的帳號" required>
                    </div>
                    <div class="form-group">
                        <label for="password">密碼</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="輸入您的密碼" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Sign in</button>
                </form>
                <div class="d-flex justify-content-between mt-3 small">
                    <a href="#">Forgot your password?</a>
                    <a href="#">Create Account</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>