<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Trang Quản Trị</title>
</head>
<body>
    <h1>Chào mừng đến với Trang Quản Trị</h1>

    <h3>Thông tin người dùng:</h3>
    <p>Full Name: ${user.fullname}</p>
    <p>Email: ${user.email}</p>
    <!-- Bạn có thể hiển thị các thông tin khác ở đây -->

    <a href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
</body>
</html>
