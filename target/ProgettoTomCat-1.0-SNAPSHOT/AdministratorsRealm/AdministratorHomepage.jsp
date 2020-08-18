<%-- 
    Document   : AdministratorHomepage
    Created on : 11-ago-2020, 20.46.35
    Author     : massi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrator Homepage</title>
    </head>
    <body>
        <h1>Administrator Homepage!</h1>
        <h2>You can add every type of user</h2>
        <form action="/ProgettoTomCat/AddUser" method="POST"><!--we can say to the browser to open a new tab with target="_blank"-->
            <label for="usernameField">Username:<input type="text" name="username" id="usernameField"/></label>
            <br />
            <label for="emailField">Email:<input type="text" name="email" id="emailField"/></label>
            <br />
            <label for="nomecognomeField">Nome e Cognome:<input type="text" name="nomecognome" id="nomecognomeField"/></label>
            <br/>
            <label for="passwordField" >Password:&nbsp;<input type="password" name="password" id="passwordField" /></label>
            <br />
            <label>Type of user:</label>
            <input type="radio" id="consumerField" name="user" value="consumer" checked><label for="consumerField">Consumer</label>
            <input type="radio" id="uploaderField" name="user" value="uploader"><label for="uploaderField">Uploader</label>
            <input type="radio" id="administratorField" name="user" value="administrator"><label for="administratorField">Administrator</label>
            <br />
            <input type="submit" value="subscribe" />
        </form>
    </body>
</html>
