<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Danh sách tác giả</title>
</head>
<body>
    <h1>Danh sách tác giả</h1>
    <a href="${pageContext.request.contextPath}/author?action=create">Tạo tác giả mới</a>
    <br>
    <br>

    <table border="1">
        <thead>
            <tr>
                <th>Author ID</th>
                <th>Author Name</th>
                <th>Date of Birth</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.authorId}</td>
                    <td>${author.authorName}</td>
                    <td>${author.dateOfBirth}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/author?action=update&authorId=${author.authorId}">Cập nhật</a> |
                        <a href="${pageContext.request.contextPath}/author?action=delete&authorId=${author.authorId}">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Phân trang -->
    <div>
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/authorList?page=1">Đầu</a>
            <a href="${pageContext.request.contextPath}/authorList?page=${currentPage - 1}">Trước</a>
        </c:if>

        <span>${currentPage} / ${totalPages}</span>

        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/authorList?page=${currentPage + 1}">Sau</a>
            <a href="${pageContext.request.contextPath}/authorList?page=${totalPages}">Cuối</a>
        </c:if>
    </div>
</body>
</html>
