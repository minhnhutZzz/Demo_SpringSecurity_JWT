<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Add New Category</h2>

        <!-- Form to add category -->
        <form action="${pageContext.request.contextPath}/admin-category/create" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="categoryCode" class="form-label">Category Code</label>
                <input type="text" class="form-control" id="categoryCode" name="categoryCode" required>
            </div>
            <div class="mb-3">
                <label for="categoryName" class="form-label">Category Name</label>
                <input type="text" class="form-control" id="categoryName" name="categoryName" required>
            </div>
            <div class="mb-3">
                <label for="images" class="form-label">Category Image</label>
                <input type="file" class="form-control" id="images" name="images">
            </div>
            <button type="submit" class="btn btn-primary">Add Category</button>
        </form>
    </div>
</body>
</html>
