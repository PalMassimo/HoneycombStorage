<%-- 
    Document   : index
    Created on : 11-ago-2020, 20.14.56
    Author     : massi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Hello World un cazzo</h1>
        
        
        <p>Il tuo ruolo è <%= request.getSession().getAttribute("role")  %></p>
        <% if(request.getSession().getAttribute("role")==null) out.println("<p>non hai alcun ruolo</p>");%>
        
        <h1>Vai alla pagina di login</h1>
        <a href="login.html">vai</a>
        
        <form action="Logout" method="POST">
            <input type="submit" value="Logout"/>
        </form>
    </body>
</html>
