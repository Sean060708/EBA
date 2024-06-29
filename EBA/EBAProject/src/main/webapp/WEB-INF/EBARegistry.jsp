<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>註冊</title>
</head>
<body>
	<h1>註冊頁面</h1>
    <form action="add" method="post">
        <label for="username">帳號:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="nickname">姓名:</label>
        <input type="text" id="nickname" name="nickname" required><br>

        <label for="password">密碼:</label>
        <input type="password" id="password" name="password" required><br>


        <label for="email">信箱:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="phonenumber">手機號碼:</label>
        <input type="text" id="phonenumber" name="phonenumber" required><br>

        <label for="sex">性別:</label>
        <input type="number" id="sex" name="sex" required><br>

        <label for="avatar">照片:</label>
        <input type="text" id="avatar" name="avatar" required><br>

        <button type="submit">註冊</button>
    </form>
</body>
</html>