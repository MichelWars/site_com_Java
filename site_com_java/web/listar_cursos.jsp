<%-- 
    Document   : listar_cursos
    Created on : 16 de nov. de 2023, 09:45:49
    Author     : thais
--%>

<%@page import="model.Curso"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exibe os Cursos cadastrados</title>
        
        <style>
            *{
                margin: 0 auto
                    
            }
            
            table{
            
                margin-top: 50px;
                width: 80%
            }
            
            th{
                background-color: black;
                color: white
            }
            
            td,th{
                padding: 5px 10px;
                text-align: center;
                border: solid #ccffff
            }
        </style>
        
        
    </head>
    <body>
        <%
            List<Curso> listaCursos = (List<Curso>) request.getAttribute("listaCursos");
        %>
        <table>
            <tr>
                <th>Código</th>
                <th>Nome</th>
                <th>Duração</th>
                <th>Alteração</th>
                <th>Exclusão</th>
            </tr>

            <%
                for (Curso curso : listaCursos) {
            %>

            <tr>
                <td><%= curso.getCodigo()%></td>
                <td><%= curso.getNome()%></td>
                <td><%= curso.getDuracao()%></td>
                <td> <a href="ControleCursos?flag=buscarCurso&codigo=<%= curso.getCodigo() %>"> Alterar </a> </td>
                <td> <a href="ControleCursos?flag=excluir&codigo=<%= curso.getCodigo() %>"> Excluir </a> </td>
            </tr>
            <%
                }
            %>
        </table>

    </body>
</html>
