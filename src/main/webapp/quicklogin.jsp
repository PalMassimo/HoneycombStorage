<%-- 
    Document   : QuickLogin
    Created on : 28-set-2020, 17.01.11
    Author     : massi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Russo One" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Archivo Black" />
        <title>Quick Login</title>

        <style>

            html, body{
                margin: 0;
                border: 0;
                padding: 0;
            }

            body{
                width: 100vw;
                height: 100vh;
                display:flex;
                flex-flow: row nowrap;
                align-items: center;
            }

            form {
                margin-right: auto;
                margin-left: auto;
                width: 450px;
                display: flex;
                flex-flow: column nowrap;
                align-items: center;
                justify-content: center;
                border: 3px solid black;
                border-radius: 10px;
                background-color: rgba(0, 0, 0, 0.83);
                color: rgb(214, 139, 0);
                font-family: "Nunito";
            }
            form h1{
                text-align: center;
                font-size: 2.8em;
                margin: 20px 0;
                font-family: "Archivo Black";
            }

            p{
                width: 90%;
                margin: 10px 0;
                font-size: 130%;
                text-align: center;
            }

            form label{
                margin: 20px auto;
                width: 85%;
                text-align: center;
            }

            form label input{
                border-radius: 8px;
                height: 30px;
                width: 70%;
            }

            form input[type="submit"] {
                margin: 15px auto;
                border: 2px solid black;
                border-radius: 10px;
                width: 60%;
                height: 70px;
                background-color: rgba(0, 0, 0, 0.83);
                color: rgb(214, 139, 0);
                font-family: "Russo One";
                font-size: 200%;
            }

        </style>

    </head>
    <body>
        <form action="login" method="GET">
            <h1>LITTLE CHECK</h1>
            <p>Your session is expired: insert your credentials to download the file!</p>
            <label for="usernameField"> Username:
                <input type="text" name="username" id="usernameField"/>
            </label>
            <label for="passwordField"> Password:
                <input type="password" name="password" id="passwordField"/>
            </label>
            <input type="hidden" name="id" value="<%=(String) request.getAttribute("id")%>"/>
            <input type="hidden" name="filename" value="<%=(String) request.getAttribute("filename")%>"/>
            <input type="submit" value="CHECK"/>
        </form>
    </body>
</html>
