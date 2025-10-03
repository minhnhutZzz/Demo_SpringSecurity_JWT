<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Welcome Admin</h2>
        <!-- Nội dung trang chính cho Admin -->
        <p>This is the admin dashboard.</p>
        <a href="${pageContext.request.contextPath}/admin-category" class="btn btn-primary">Manage Categories</a>
    </div>
</body>
</html>
