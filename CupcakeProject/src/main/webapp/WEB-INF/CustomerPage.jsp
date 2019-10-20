<%-- 
    Document   : CustomerPage
    Created on : Oct 17, 2019, 1:57:15 PM
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CustomerPage Page</title>
    </head>
    <body>
    <h1>Hello Customer</h1>
    
    <form action="FrontController" >
                <input type="hidden" name="cmd" value="goToProducts" />
                <input type="submit" value="See all products available" align="right"/>
            </form>
    
    <form action="FrontController" method="POST">
        <input type="hidden" name="cmd" value="addBalance" />
        <p>Add balance:</p>
        <input type="number" step="0.01" name="AddBalance" value="" /> 
        <input type="submit" value="AddBalance" />
    </form>
    
    <body>
</html>
