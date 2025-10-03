<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${category.isEdit ? 'Edit Category' : 'Add New Category'}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
    <section class="row mt-4">
        <div class="col-6 offset-3">
            <div class="card">
                <div class="card-header">
                    <h2>${category.isEdit ? 'Edit Category' : 'Add New Category'}</h2>
                </div>
                <div class="card-body">
                    <form action="<c:url value='/admin/categories/saveOrUpdate' />" method="POST">
                        <div class="mb-3">
                            <label for="categoryId" class="form-label">Category ID:</label>
                            <input type="hidden" value="${category.isEdit}" name="isEdit"/>
                            <input type="text" readonly="readonly" class="form-control" value="${category.categoryId}" id="categoryId" name="categoryId" aria-describedby="categoryId" placeholder="Category Id">
                        </div>
                        <div class="mb-3">
                            <label for="categoryname" class="form-label">Category Name:</label>
                            <input type="text" class="form-control" value="${category.categoryname}" id="categoryname" name="categoryname" aria-describedby="categoryname" placeholder="Category Name">
                        </div>
                        <div class="mb-3">
                            <label for="categorycode" class="form-label">Category Code:</label>
                            <input type="text" class="form-control" value="${category.categorycode}" id="categorycode" name="categorycode" aria-describedby="categorycode" placeholder="Category Code">
                        </div>
                        <div class="mb-3">
                            <label for="status" class="form-label">Status:</label>
                            <select class="form-select" name="status" id="status">
                                <option ${category.status==false ? 'selected' : ''} value="false" >Khóa</option>
                                 <option ${category.status==true ? 'selected' : ''} value="true" >Hoạt động</option>
                            </select>
                        </div>
                        <div class="card-footer text-muted">
                            <a href="<c:url value='/admin/categories/add' />" class="btn btn-secondary">
                                <i class="fas fa-new"></i> New
                            </a>
                            <a href="<c:url value='/admin/categories' />" class="btn btn-success">
                                <i class="fas fa-bars"></i> List Categories
                            </a>
                            <button class="btn btn-primary" type="submit">
                                <i class="fas fa-save"></i> 
                                <span>${category.isEdit ? 'Update' : 'Save'}</span>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>