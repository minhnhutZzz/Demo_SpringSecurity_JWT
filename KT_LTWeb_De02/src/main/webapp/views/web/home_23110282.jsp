<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Trang Chủ</title>
</head>
<body>
    <h1>Chào mừng bạn đến với Trang Chủ</h1>

    <h3>Thông tin người dùng:</h3>
    <p>Full Name: ${user.fullname}</p>
    <p>Email: ${user.email}</p>
    <!-- Hiển thị thông tin người dùng -->

    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</body>
</html>
