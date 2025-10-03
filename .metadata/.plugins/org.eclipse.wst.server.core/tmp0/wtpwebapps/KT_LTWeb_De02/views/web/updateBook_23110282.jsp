<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Cập nhật sách</title>
</head>
<body>
    <h1>Cập nhật sách</h1>
    <form action="${pageContext.request.contextPath}/book" method="post">
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="bookid" value="${book.bookid}"/>
        
        <label for="title">Title:</label><br/>
        <input type="text" name="title" value="${book.title}" required/><br/>
        <label for="isbn">ISBN:</label><br/>
        <input type="text" name="isbn" value="${book.isbn}" required/><br/>
        <label for="publisher">Publisher:</label><br/>
        <input type="text" name="publisher" value="${book.publisher}" required/><br/>
        <label for="price">Price:</label><br/>
        <input type="number" name="price" value="${book.price}" step="0.01" required/><br/>
        <label for="description">Description:</label><br/>
        <textarea name="description" required>${book.description}</textarea><br/>
        <label for="publishDate">Publish Date:</label><br/>
        <input type="date" name="publishDate" value="${book.publishDate}" required/><br/>
        <label for="coverImage">Cover Image:</label><br/>
        <input type="text" name="coverImage" value="${book.coverImage}" required/><br/>
        <label for="quantity">Quantity:</label><br/>
        <input type="number" name="quantity" value="${book.quantity}" required/><br/>
        <input type="submit" value="Cập nhật sách"/>
    </form>
</body>
</html>
