<%-- 
    Document   : Login
    Created on : Oct 17, 2019, 1:57:44 PM
    Author     : Bringordie - Frederik Braagaard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    <form action="FrontController" method="POST">
        <input type="hidden" name="cmd" value="login" />
        <p>Username:      <input type="text" name="usernamelogin" required/></p>
        <p>Password:      <input type="password" name="passwordlogin" required/> </p>
        <input type="submit" value="Login" />
    </form>
    </head>
</html>
