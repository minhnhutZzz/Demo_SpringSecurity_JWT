<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Chi Tiết Sách</title>
</head>
<body>
	<h1>${book.title}</h1>
	<img src="${pageContext.request.contextPath}/images/${book.coverImage}"
		alt="Cover Image" width="100" height="150" />
	<p>
		<strong>ISBN:</strong> ${book.isbn}
	</p>
	<p>
		<strong>Publisher:</strong> ${book.publisher}
	</p>
	<p>
		<strong>Publish Date:</strong> ${book.publishDate}
	</p>
	<p>
		<strong>Quantity:</strong> ${book.quantity}
	</p>
	<p>
		<strong>Review(10)</strong> 
	</p>
	<h3>Reviews:</h3>
	<c:forEach var="rating" items="${ratings}">
		<p>
			<strong>${rating.user.email}:</strong> ${rating.reviewText} <i>(${rating.rating} stars)</i>
		</p>
	</c:forEach>

	<h3>Form Thêm Review</h3>
	<form action="${pageContext.request.contextPath}/submitReview"
		method="post">
		<input type="hidden" name="bookid" value="${book.bookid}" /> <label
			for="review_text">Your Review:</label><br />
		<textarea name="review_text" rows="5" cols="40" required></textarea>
		<br /> <label for="rating">Rating:</label> <input type="number"
			name="rating" min="1" max="5" required /><br /> <input type="submit"
			value="Submit" />
	</form>
</body>
</html>
