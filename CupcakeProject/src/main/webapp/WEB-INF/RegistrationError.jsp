<%-- 
    Document   : RegistrationError
    Created on : 20-10-2019, 14:57:00
    Author     : Bringordie - Frederik Braagaard
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error registrating</title>
    <form action="FrontController" method="POST">
        <input type="hidden" name="cmd" value="registration" />
        <b> Username is already taken, please choose a different one </b>
        <p>Username:      <input type="text" name="username" required/></p>
        <p>Password:      <input type="password" name="password" required/> </p>
        <p>Name:          <input type="text" name="name" required/> </p>
        <p>Email:         <input type="text" name="email" required/> </p>
        <input type="submit" value="Register" />
    </form>

    </head>
</html>
