<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Thêm Người Dùng</title>
</head>
<body>
    <h2>Thêm Người Dùng</h2>
    <form method="post" action="saveUser">
        Họ Tên: <input type="text" name="fullname" required/><br/>
        Email: <input type="email" name="email" required/><br/>
        Mật Khẩu: <input type="password" name="password" required/><br/>
        Quyền: 
        <select name="isAdmin">
            <option value="false">User</option>
            <option value="true">Admin</option>
        </select><br/>
        <input type="submit" value="Lưu"/>
    </form>
</body>
</html>
