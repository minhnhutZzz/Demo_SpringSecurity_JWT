<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Danh sách sách</title>
</head>
<body>
	<h1>Danh sách sách</h1>
	<a href="${pageContext.request.contextPath}/book?action=create">Tạo
		sách mới</a>
	<br>
	<br>

	<table border="1">
		<thead>
			<tr>
				<th>Cover Image</th>
				<th>Title</th>
				<th>ISBN</th>
				<th>Publisher</th>
				<th>Publish Date</th>
				<th>Quantity</th>
				<th>Review</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${books}">
				<tr>
					<td><img
						src="${pageContext.request.contextPath}/images/${book.coverImage}"
						alt="Cover Image" width="100" height="150" /></td>
					<td><a
						href="${pageContext.request.contextPath}/bookDetail?bookid=${book.bookid}">${book.title}</a></td>
					<td>${book.isbn}</td>
					<td>${book.publisher}</td>
					<td>${book.publishDate}</td>
					<td>${book.quantity}</td>
					<th>Review(10)</th>
					<td><a
						href="${pageContext.request.contextPath}/book?action=update&bookid=${book.bookid}">Cập
							nhật</a> | <a
						href="${pageContext.request.contextPath}/book?action=delete&bookid=${book.bookid}">Xóa</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- Phân trang -->
	<div>
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/bookList?page=1">Đầu</a>
			<a
				href="${pageContext.request.contextPath}/bookList?page=${currentPage - 1}">Trước</a>
		</c:if>

		<span>${currentPage} / ${totalPages}</span>

		<c:if test="${currentPage < totalPages}">
			<a
				href="${pageContext.request.contextPath}/bookList?page=${currentPage + 1}">Sau</a>
			<a
				href="${pageContext.request.contextPath}/bookList?page=${totalPages}">Cuối</a>
		</c:if>
	</div>
</body>
</html>
