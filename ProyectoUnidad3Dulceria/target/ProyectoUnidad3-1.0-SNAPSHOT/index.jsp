<%-- 
    Document   : index.jsp
    Created on : 25 oct 2026, 6:51:21 p.m.
    Author     : karel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dulceria y Papeleria Córdova</title>
    </head>
    <body>
        
        <h1>Dulceria y Papeleria Córdova</h1>
        
         <c:choose>
            <c:when test="${not empty lista}">
                
                <table border="1">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Foto</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="m" items="${lista}">
                            <tr>
                                <td>${m.id}</td>
                                <td>${m.name}</td>
                                <td>${m.precio}</td>
                                <td>
                                    <img src="${m.foto}" alt="${m.name}" width="80" height="80" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                
            </c:when>
            <c:otherwise>
                <p>No hay Dulces</p>
            </c:otherwise>
        </c:choose>
                
                <h2>Agregar Dulces</h2>
                <form method="Post" action="SvDulceria">
                    <label>Nombre:
                        <input type="text" name="name" id="name" />
                    </label>
                    
                    <label>Precio
                        <input type="number" name="price" id="price" />
                    </label>
                    
                    <label for="foto">URL de la foto:</label>
                    <input type="text" id="foto" name="foto" required><br><br>
                    
                    <button type="submit">Enviar</button>
                    
                </form>
    </body>
</html>
