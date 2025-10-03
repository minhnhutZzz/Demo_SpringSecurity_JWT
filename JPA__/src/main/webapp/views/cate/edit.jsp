<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Edit Category</h2>

        <!-- Hiển thị lỗi nếu có -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <!-- Form chỉnh sửa -->
        <form action="${pageContext.request.contextPath}/admin-category/update" method="post" enctype="multipart/form-data">
            <input type="hidden" name="categoryId" value="${category.categoryId}">
            
            <div class="mb-3">
                <label for="categoryCode" class="form-label">Category Code</label>
                <input type="text" class="form-control" id="categoryCode" name="categoryCode" 
                    value="${category.categoryCode}" required 
                    <c:if test="${not empty error}">readonly</c:if>>
            </div>

            <div class="mb-3">
                <label for="categoryName" class="form-label">Category Name</label>
                <input type="text" class="form-control" id="categoryName" name="categoryName" 
                    value="${category.categoryName}" required 
                    <c:if test="${not empty error}">readonly</c:if>>
            </div>

            <div class="mb-3">
                <label for="images" class="form-label">Category Image</label>
                <input type="file" class="form-control" id="images" name="images"
                    <c:if test="${not empty error}">disabled</c:if>>
                <br/>
                <c:if test="${not empty category.images}">
                    <img src="${pageContext.request.contextPath}/images/category/${category.images}" alt="Current Image" width="100">
                </c:if>
            </div>

            <!-- Chỉ hiển thị nút cập nhật nếu không có lỗi (tức là có quyền chỉnh sửa) -->
            <c:if test="${empty error}">
                <button type="submit" class="btn btn-primary">Update Category</button>
            </c:if>
        </form>
    </div>
</body>
</html>
