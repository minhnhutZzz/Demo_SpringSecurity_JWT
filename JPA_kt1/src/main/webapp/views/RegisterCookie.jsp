<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <form action="${pageContext.request.contextPath}/registerCookie" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required /><br/>

        <label for="fullname">Full Name:</label>
        <input type="text" id="fullname" name="fullname" required /><br/>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required /><br/>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required /><br/>

        <input type="submit" value="Register" />
    </form>
</body>
</html>
