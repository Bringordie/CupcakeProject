<%--
    Document   : Products
    Created on : Oct 17, 2019, 1:57:54 PM
    Author     : Bringordie - Frederik Braagaard
--%>
 
<%@page import="logic.CupCake"%>
<%@page import="logic.LineItems"%>
<%@page import="logic.User"%>
<%@page import="logic.Topping"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.Bottom"%>
<%@page import="logic.Shoppingcart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products Page</title>
    </head>
 
    <body>
        <p> Your balance <% out.println(String.format("%.0f", session.getAttribute("usersBalance"))+ " $"); %></p>
        <h1 align="center">Bottoms:</h1>            
        <form action="FrontController" method="POST" align="center">
            <table width = "50%" border = "1" align = "center">
                <thead>
                    <tr>
                        <td>Name</td>
                        <td>Price</td>
                        <td>Select item</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items ="${bottoms}" var ="bottom">
                    <tr bgcolor = "#949494">
                        <td> <c:out value = "${bottom.getName()}"/> </td>
                        <td> <c:out value = "${bottom.getPrice()}"/> </td>
                        <td> <input type="radio" name="idbottom" value="${bottom.getId()}" /></td>
                        </c:forEach>
                    </tr>
                </tbody>
            </table>  
        <h1 align="center">Toppings:</h1>
            <table width = "50%" border = "1" align = "center">
                <thead>
                    <tr>
                        <td>Name</td>
                        <td>Price</td>
                        <td>Select item</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items ="${toppings}" var ="topping">
                    <tr bgcolor = "#949494">
                        <td> <c:out value = "${topping.getName()}"/> </td>
                        <td> <c:out value = "${topping.getPrice()}"/> </td>
                        <td> <input type="radio" name="idtopping" value="${topping.getId()}" /></td>
                        </c:forEach>                        
                    </tr>
                </tbody>
            </table>
                <p>Quantity:      <input type="number" name="quantity" value="1" /></p>
            <input type="hidden" name="cmd" value="payment" />
            <input type="submit" value="Add cupcake" align="right" />
            <form>
        <form action="FrontController" >
            <input type="hidden" name="cmd" value="processorder" />
            <input type="submit" value="Process order" align="right"/>
        </form>   
    </body>
</html>