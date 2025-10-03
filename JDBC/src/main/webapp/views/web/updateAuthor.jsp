<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Cập nhật tác giả</title>
</head>
<body>
    <h1>Cập nhật tác giả</h1>
    <form action="${pageContext.request.contextPath}/author" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="authorId" value="${author.authorId}"/>
        
        <label for="authorName">Tên tác giả:</label><br/>
        <input type="text" name="authorName" value="${author.authorName}" required/><br/>

        <label for="dateOfBirth">Ngày sinh:</label><br/>
        <input type="date" name="dateOfBirth" value="${author.dateOfBirth}" required/><br/>

        <input type="submit" value="Cập nhật tác giả"/>
    </form>
</body>
</html>
