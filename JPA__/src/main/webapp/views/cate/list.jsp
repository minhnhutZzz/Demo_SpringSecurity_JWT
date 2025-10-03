<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category List</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
    <style>
        .portlet-title {
            font-size: 1.5em;
            margin-bottom: 20px;
        }

        .table-toolbar {
            margin: 20px 0;
        }

        .portlet-body {
            margin-top: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .btn {
            margin-right: 10px;
        }

        .table {
            width: 100%;
        }

        .table th,
        .table td {
            padding: 15px;
            text-align: center;
        }
    </style>
</head>

<body>
    <div class="container mt-5">
        <h1 class="portlet-title">Category Management</h1>

        <!-- Table toolbar -->
        <div class="table-toolbar mb-4">
            <a href="${pageContext.request.contextPath}/admin-category/create" class="btn btn-success">
                <i class="fas fa-plus"></i> Add New
            </a>
        </div>

        <!-- Category Table -->
        <div class="portlet-body">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Category Code</th>
                        <th>Category Name</th>
                        <th>Image</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Loop through categories from 'categorys' attribute -->
                    <c:forEach var="category" items="${categorys}">
                        <tr>
                            <td>${category.categoryCode}</td>
                            <td>${category.categoryName}</td>
                            <td><img src="${pageContext.request.contextPath}/images/category/${category.images}" alt="Current Image" width="100"></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin-category/edit?categoryId=${category.categoryId}"
                                    class="btn btn-warning"><i class="fas fa-edit"></i> Edit</a>
                                <a href="${pageContext.request.contextPath}/admin-category/delete?categoryId=${category.categoryId}"
                                    class="btn btn-danger"
                                    onclick="return confirm('Are you sure you want to delete this category?');"><i
                                        class="fas fa-trash"></i> Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- jQuery and Bootstrap JavaScript -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
