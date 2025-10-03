<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Tạo tác giả mới</title>
</head>
<body>
    <h1>Tạo tác giả mới</h1>
    <form action="${pageContext.request.contextPath}/author" method="post">
        <input type="hidden" name="action" value="create"/>
        
        <label for="authorName">Tên tác giả:</label><br/>
        <input type="text" name="authorName" required/><br/>

        <label for="dateOfBirth">Ngày sinh:</label><br/>
        <input type="date" name="dateOfBirth" required/><br/>

        <input type="submit" value="Tạo tác giả"/>
    </form>
</body>
</html>
