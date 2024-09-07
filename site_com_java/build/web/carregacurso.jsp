<%-- 
    Document   : carregacurso
    Created on : 30 de nov. de 2023, 09:58:28
    Author     : thais
--%>

<%@page import="model.Curso"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <%
          Curso curso = (Curso) request.getAttribute("curso");
        %> 
        <form method="post" action="ControleCursos?flag=alteracao">
            <p>
                <label for="c">Código:*</label>
                <input value="<%= curso.getCodigo() %>" id="c" type="text" name="codigo" size="5" maxlength="5" readonly>
            </p>
            <p>
                <label for="n">Nome:*</label>
                <input value="<%= curso.getNome() %>"  id="n" type="text" name="nome" size="50" maxlength="50" required>
            </p>
            <p>
                <label for="d">Duração:</label>
                <input value="<%= curso.getDuracao() %>"  id="d" type="text" name="duracao" size="15" maxlength="15">
            </p> 
            <p>
                <input type="submit" value="Salvar Alterações">
            </p>
        </form>

    </body>
</html>
