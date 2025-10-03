<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
        }
        .login-container {
            width: 350px;
            margin: 100px auto;
            padding: 25px 30px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"], 
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background: #45a049;
        }
        .register-link {
            display: block;
            margin-top: 15px;
            text-align: center;
        }
        .register-link a {
            text-decoration: none;
            color: #007BFF;
            font-weight: bold;
        }
        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>Login Session</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required/>

            <label for="passwd">Password:</label>
            <input type="password" id="passwd" name="passwd" required/>

            <input type="submit" value="Login"/>
        </form>
        <div class="register-link">
            <p>Chưa có tài khoản? 
               <a href="${pageContext.request.contextPath}/views/RegisterSession_23110282.jsp">
                   Đăng ký ngay
               </a>
            </p>
        </div>
    </div>
</body>
</html>
