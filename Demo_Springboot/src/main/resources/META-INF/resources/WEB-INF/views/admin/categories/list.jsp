<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Hello, world!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-...">
</head>
<body>
    <section class="row">
        <div class="col mt-4">
            <div class="card">
                <div class="card-header">List Category</div>
                <div class="card-body">
                    <!-- Hiển thị thông báo -->
                    <c:if test="${message != null}">
                        <div class="alert alert-primary" role="alert">
                            <i>${message}</i>
                        </div>
                    </c:if>

                    <!-- Bảng danh sách Category -->
                    <table class="table table-striped table-responsive">
                        <thead class="thead-inverse">
                            <tr>
                                <th>Category ID</th>
                                <th>Category Code</th>
                                <th>Category Name</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${categories}" var="category">
                                <tr>
                                    <td scope="row">${category.categoryId}</td>
                                    <td>${category.categoryname}</td>
                                    <td>${category.categorycode}</td>
                                    <td>${category.status ? 'Hoạt động': 'Khóa'}</td>
                                    <td>
                                        <a href="/admin/categories/view/${category.categoryId}" class="btn btn-outline-info">
                                            <i class="fa fa-info"></i>
                                        </a>
                                        <a href="/admin/categories/edit/${category.categoryId}" class="btn btn-outline-warning">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a href="/admin/categories/delete/${category.categoryId}" class="btn btn-outline-danger">
                                            <i class="fa fa-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
</body>
</html>