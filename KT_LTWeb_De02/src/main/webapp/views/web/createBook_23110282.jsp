<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Tạo sách mới</title>
</head>
<body>
    <h1>Tạo sách mới</h1>
    <form action="${pageContext.request.contextPath}/book" method="post">
        <input type="hidden" name="action" value="create"/>
        <label for="title">Title:</label><br/>
        <input type="text" name="title" required/><br/>
        <label for="isbn">ISBN:</label><br/>
        <input type="text" name="isbn" required/><br/>
        <label for="publisher">Publisher:</label><br/>
        <input type="text" name="publisher" required/><br/>
        <label for="price">Price:</label><br/>
        <input type="number" name="price" step="0.01" required/><br/>
        <label for="description">Description:</label><br/>
        <textarea name="description" required></textarea><br/>
        <label for="publishDate">Publish Date:</label><br/>
        <input type="date" name="publishDate" required/><br/>
        <label for="coverImage">Cover Image:</label><br/>
        <input type="text" name="coverImage" required/><br/>
        <label for="quantity">Quantity:</label><br/>
        <input type="number" name="quantity" required/><br/>
        <input type="submit" value="Tạo sách"/>
    </form>
</body>
</html>
	