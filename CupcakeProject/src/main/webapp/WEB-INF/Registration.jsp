<%-- 
    Document   : Registration
    Created on : Oct 17, 2019, 1:57:22 PM
    Author     : Bringordie - Frederik Braagaard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
    <form action="FrontController" method="POST">
        <input type="hidden" name="cmd" value="registration" />
        <p>Username:      <input type="text" name="username" required/></p>
        <p>Password:      <input type="password" name="password" required/> </p>
        <p>Name:          <input type="text" name="name" required/> </p>
        <p>Email:         <input type="text" name="email" required/> </p>
        <input type="submit" value="Register" />
    </form>

    </head>
</html>
