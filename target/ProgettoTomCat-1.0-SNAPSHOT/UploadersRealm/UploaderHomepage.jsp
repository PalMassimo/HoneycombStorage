<%-- 
    Document   : Welcome Uploaders Page
    Created on : 12-ago-2020, 21.26.20
    Author     : massi
--%>

<%@page import="javax.persistence.TypedQuery"%>
<%@page import="java.util.List"%>
<%@page import="units.progettotomcat.entites.Consumer"%>
<%@page import="units.progettotomcat.entites.Uploader"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="java.util.Set"%>
<%@page import="units.progettotomcat.entites.UploadedFile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Uploader's Homepage</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            function deleteUploadedFile(id) {
                let xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {

                        console.log(xhr.responseText);

//                        if(xhr.responseText==="fatto!"){
//                         console.log("l'elemento è stato eliminato correttamente");   
//                        } else{
//                            console.log("l'elemento NON è stato eliminato correttamente");
//                        } //location.reload();
                    }
                };
                xhr.open("DELETE", "/ProgettoTomCat/api/filemanagment/deletefile/" + id, true);
                xhr.send(null);
            }
        </script>


    </head>

    <body>
        <h1>Benvenuto/a, <%= session.getAttribute("username")%></h1>
        <p>Hai il ruolo di <%= request.getSession().getAttribute("role")%></p>
        <h1>Vuoi cambiare/aggiungere un immagine profilo? </h1>
        <form action="/ProgettoTomCat/AddLogo" enctype="multipart/form-data" method="POST">
            <label for="fileField">Immagine di logo: </label><input type="file" name="logo" id="fileField"/>
            <input type="submit" value="Modifica"/>
        </form>

        <%
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
            EntityManager em = emf.createEntityManager();

            //verifica che sia un utente loggato...
            if (request.getSession().getAttribute("username") != null) {

                Uploader uploader = em.find(Uploader.class, request.getSession().getAttribute("username"));
                //verifica che sia un uploader
                if (uploader == null) {
                    out.println("<p> Chi sei? Non sei un uploader (:</p>");
                } else {
                    //Listiamo i files da lui associati
                    List<UploadedFile> ufs = uploader.getUploadedFiles();
                    if (ufs.isEmpty()) {
                        out.println("<p>Non hai ancora caricato alcun file</p>");
                    } else {
                        out.println("<h2>Files caricati:</h2>");
                    }

                    for (UploadedFile uf : ufs) {
                        out.println("<li>" + uf.getName() + "</li>");
        %>


        <!--<a data-method="delete" href="../api/filemanagment/deletefile/ onclick<%=uf.getId()%>">elimina</a>-->

        <input type="button" value="elimina" onclick="deleteUploadedFile(<%=uf.getId()%>)">





        <%

                    }
                }
            } else {
                out.println("<h1>Non sei un uploader</h1>");
            }
            /*if(em.find(Uploader.class, request.getSession().getAttribute("username")).getUploadedFiles()!=null){
                for(Consumer c: em.find(Uploader.class, request.getSession().getAttribute("username")).getConsumers()){
                    out.println("<li>"+c.getNomecognome()+"</li>");
                }
            }*/
        %>

        <h1>Aggiungi Consumer!</h1>
        <form action="../AddConsumer" method="POST">
            <label>Username:</label><input type="text" name="username"/>
            <br/>
            <label>Email:</label><input type="text" name="email" />
            <br/>
            <label>Nome e Cognome:</label><input type="text" name="nomecognome"/>
            <br/>
            <label>Password: </label><input type="password" name="password" />
            <br/>
            <input type="submit" value="submit!"/>
        </form>

        <h1>Add File </h1>
        <form action="/ProgettoTomCat/api/filemanagment" enctype="multipart/form-data" method="POST">  
            <label for="fileField">Select a file:</label>
            <input type="file" id="fileField2" name="fileToUpload" ><br><br>
            <input type="submit">
        </form>

    </body>

</html>
