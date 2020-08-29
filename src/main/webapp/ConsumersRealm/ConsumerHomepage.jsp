<%-- 
    Document   : ConsumersHomepage
    Created on : 12-ago-2020, 11.10.33
    Author     : massi
--%>

<%@page import="javax.persistence.TypedQuery"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="units.progettotomcat.entites.UploadedFile"%>
<%@page import="units.progettotomcat.entites.Consumer"%>
<%@page import="units.progettotomcat.entites.Uploader"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <h1>Welcome, <%=(String) (request.getSession().getAttribute("username"))%></h1>

        <h2>Segue la lista degli uploaders affiliati</h2>
        <%
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("progettotomcatPU");
            EntityManager em = emf.createEntityManager();

            //uploaders affiliati
            TypedQuery<String> uQuery = em.createQuery("SELECT u.username FROM Consumer c INNER JOIN c.uploaders u WHERE c.username=:currentconsumer", String.class);
            uQuery.setParameter("currentconsumer", (String) (request.getSession().getAttribute("username")));

            List<String> uploadersAffiliate = uQuery.getResultList();

            for (String ua : uploadersAffiliate) {
                out.println("<li>" + ua + "</li>");
                %> <img src="/ProgettoTomCat/api/logomanagment/getlogo/<%=ua%>" alt="logo_uploader"/>       <%
            }

        %>

        <h2>Segue la lista i files caricati per te</h2>
        <ul>
            <%                
                for (String ua : uploadersAffiliate) {

                    out.println("<h3>Uploader " + ua + "</h3>");

                    TypedQuery<UploadedFile> filesUploaded = em.createQuery("SELECT uf FROM UploadedFile uf INNER JOIN uf.uploader WHERE uf.uploader.username=:currentuploader", UploadedFile.class);
                    filesUploaded.setParameter("currentuploader", ua);
                    
                    for(UploadedFile uploadedFile: filesUploaded.getResultList()){
                        out.println("<li>File caricato: <a href=http://localhost:8080/ProgettoTomCat/api/filemanagment/getfile/"+uploadedFile.getId()+"/"+uploadedFile.getName()+">"+uploadedFile.getName()+"</a>"+"</li>");
                    }

                }
//
//                TypedQuery<String> qf = em.createQuery("SELECT uf.name FROM UploadedFile uf INNER JOIN uf.uploader ufu WHERE uf.uploader= ANY("
//                        + "SELECT u FROM Consumer c INNER JOIN c.uploaders u WHERE c.username =:currentconsumer)", String.class);
//                qf.setParameter("currentconsumer", (String) (request.getSession().getAttribute("username")));
//
//                for (String u : qf.getResultList()) {
//                    out.println("<li>" + u + "</li>");
//                }
            %>
        </ul>
    </body>
</html>
