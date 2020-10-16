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
            /*
            * Prefixed by https://autoprefixer.github.io
            * PostCSS: v7.0.29,
            * Autoprefixer: v9.7.6
            * Browsers: last 4 version
            */

            html, body{
                margin: 0;
                border: 0;
                padding: 0;
            }

            body{
                width: 100vw;
                height: 100vh;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                -webkit-box-orient: horizontal;
                -webkit-box-direction: normal;
                -ms-flex-flow: row nowrap;
                flex-flow: row nowrap;
                -webkit-box-align: center;
                -ms-flex-align: center;
                align-items: center;
            }

            form {
                margin-right: auto;
                margin-left: auto;
                width: 400px;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                -webkit-box-orient: vertical;
                -webkit-box-direction: normal;
                -ms-flex-flow: column nowrap;
                flex-flow: column nowrap;
                -webkit-box-align: center;
                -ms-flex-align: center;
                align-items: center;
                -webkit-box-pack: center;
                -ms-flex-pack: center;
                justify-content: center;
                border: 3px solid black;
                border-radius: 40px;
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

            form p{
                width: 90%;
                margin: 4vh 0;
                font-size: 130%;
                text-align: center;
                color: white;
            }

            form input[type="text"],
            form input[type="password"]{
                border-radius:8px;
                height: 6vh;
                width: 80%;
                margin: 3vh auto;
            }

            form input[type="submit"] {
                margin: 15px auto;
                border: 2px solid black;
                border-radius: 10px;
                width: 60%;
                height: 9vh;
                background-color: rgba(0, 0, 0, 0.83);
                color: rgb(214, 139, 0);
                font-family: "Russo One";
                font-size: 6vh;
            }

            form input[type="submit"]:hover{
                cursor: pointer;
            }

            @media screen and (orientation: portrait) {

                html, body{
                    margin: 0;
                    border: 0;
                    padding: 0;
                }

                body{
                    width: 100vw;
                    height: 100vh;
                    display: -webkit-box;
                    display: -ms-flexbox;
                    display: flex;
                    -webkit-box-orient: horizontal;
                    -webkit-box-direction: normal;
                    -ms-flex-flow: row nowrap;
                    flex-flow: row nowrap;
                    -webkit-box-align: center;
                    -ms-flex-align: center;
                    align-items: center;
                }

                form {
                    margin-right: auto;
                    margin-left: auto;
                    height: 80%;
                    width: 70%;
                    display: -webkit-box;
                    display: -ms-flexbox;
                    display: flex;
                    -webkit-box-orient: vertical;
                    -webkit-box-direction: normal;
                    -ms-flex-flow: column nowrap;
                    flex-flow: column nowrap;
                    -webkit-box-align: center;
                    -ms-flex-align: center;
                    align-items: center;
                    -webkit-box-pack: center;
                    -ms-flex-pack: center;
                    justify-content: center;
                    border: 3px solid black;
                    border-radius: 40px;
                    background-color: rgba(0, 0, 0, 0.83);
                    color: rgb(214, 139, 0);
                    font-family: "Nunito";
                }

                form h1{
                    margin: 2vh auto;
                    font-size: 5em;
                }

                form p{
                    font-size: 2em;
                }

                form input{
                    font-size: 2em;
                }

            }

        </style>

    </head>
    <body>
        <form action="/quicklogin" method="POST">
            <h1>Login Form</h1>
            <p>Your session is expired: insert your credentials to download the file now!</p>
            <input type="text" name="username" placeholder=" enter username" />
            <input type="password" name="password" placeholder=" enter password" />
            <input type="hidden" name="id" value="${id}"/>
            <input type="hidden" name="filename" value="${filename}"/>
            <input type="submit" value="CHECK" />
        </form>
    </body>

    <script>
        let username = document.querySelector("input[name='username']");
        username.addEventListener("keyup", () => {
            username.value = username.value.toUpperCase();
        });
    </script>


</html>
