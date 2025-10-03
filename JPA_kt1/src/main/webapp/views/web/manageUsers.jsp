<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Quản Lý Người Dùng</title>
</head>
<body>
    <h2>Quản Lý Người Dùng</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Họ Tên</th>
            <th>Email</th>
            <th>Quyền</th>
            <th>Chức Năng</th>
        </tr>
        <!-- Lặp qua danh sách người dùng -->
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.fullname}</td>
                <td>${user.email}</td>
                <td>${user.isAdmin ? "Admin" : "User"}</td>
                <td>
                    <a href="editUser?id=${user.id}">Sửa</a> | 
                    <a href="deleteUser?id=${user.id}">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="addUser">Thêm Người Dùng Mới</a>
</body>
</html>
