<%-- 
    Document   : Products
    Created on : Oct 17, 2019, 1:57:54 PM
    Author     : 
--%>

<%@page import="logic.CupCake"%>
<%@page import="logic.LineItems"%>
<%@page import="logic.User"%>
<%@page import="logic.Topping"%>
<%@page import="java.util.ArrayList"%>
<%@page import="logic.Bottom"%>
<%@page import="logic.Shoppingcart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products Page</title>
    </head>

    <body>
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
                    <% 
                        ArrayList<Bottom> bottom = (ArrayList<Bottom>) session.getAttribute("bottoms");
                    //Unsure if needed ->
                    session.setAttribute("ubottom", bottom);
                        for (Bottom bottoms : bottom) {%>                    
                    <tr bgcolor = "#949494">
                        <td><%=bottoms.getName()%></td>
                        <td><%=bottoms.getPrice()%></td>
                        <td> <input type="radio" name="idbottom" value="<%=bottoms.getId()%>" /></td>

                    </tr>
                    <% } //end loop %>
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
                    <% 
                        ArrayList<Topping> topping = (ArrayList<Topping>) session.getAttribute("toppings"); 
                        //Unsure if needed ->
                    session.setAttribute("utopping", topping);
                        for (Topping toppings : topping) {%>                    
                    <tr bgcolor = "#949494">
                        <td><%=toppings.getName()%></td>
                        <td><%=toppings.getPrice()%></td>
                        <td> <input type="radio" name="idtopping" value="<%=toppings.getId()%>" /></td>                          
                    </tr>
                    <% } //end loop %>
                </tbody>
            </table>


            <input type="hidden" name="cmd" value="payment" />
            <input type="submit" value="Process order" />
        </form>
    </body>

</html>
