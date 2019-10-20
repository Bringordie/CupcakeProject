<%-- 
    Document   : LoginError.jsp
    Created on : 20-10-2019, 14:56:29
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
    <form action="FrontController" method="POST">
        <input type="hidden" name="cmd" value="login" />
        <b> Username or Password is incorrect. Please try again </b>
        <p>Username:      <input type="text" name="usernamelogin" required/></p>
        <p>Password:      <input type="password" name="passwordlogin" required/> </p>
        <input type="submit" value="Login" />
    </form>
    </head>
</html>
