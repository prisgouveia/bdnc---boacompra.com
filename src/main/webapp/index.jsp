<%-- 
    Document   : index
    Created on : 30/08/2016, 20:23:57
    Author     : Priscila Gouveia <priscilaggouveia@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BoaCompra.com</title>
        <link rel="shortcut icon" href="resources/img/carrinho.png" >
        <link  href="resources/css/style_cadastro.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
        <link  href="resources/css/style_cadastro.css" rel="stylesheet" type="text/css">
        <script src="resources/js/jquery.js" type="text/javascript"></script>
        <script src="resources/js/bootstrap.min.js" type="text/javascript"></script>    
        <meta name="viewport" content="width=device-width, minimum=scale= 1.0, initial=scale=1, user-scalable=yes"/> 
    </head>
    <body>        
        <header>
            <c:choose>
                <c:when test="${cliente == null}">
                    <%@include file="navbar.jsp" %>
                </c:when>
                <c:when test="${cliente != null}">
                    <%@include file="navbarLogado.jsp" %>
                </c:when>
            </c:choose>
        </header>

        <div class="container conteudo">
            <div class="jumbotron banner">
                <h1 class="text-center">SALDÃO até 80% de desconto</h1>
            </div>

            <div class="col-md-3 menu">
                <ul class="listaMenu">
                    <li class="opcaoMenu"><a class="linksMenu" href="#">Celulares e Telefonia Fixa</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="opcaoMenu"><a class="linksMenu" href="#">Eletrodomésticos</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="opcaoMenu"><a class="linksMenu" href="#">Games</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="opcaoMenu"><a class="linksMenu" href="#">Informática e Tablets</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="opcaoMenu"><a class="linksMenu" href="#">Livros</a></li>
                    <li role="separator" class="divider"></li>
                    <li class="opcaoMenu"><a class="linksMenu" href="#">TVs, Áudio e Home Theater</a></li>
                </ul>
            </div>

            <div class="col-md-9">
                <c:forEach var="produto" items="${produtos}">
                    <div class="col-sm-6 col-md-4">
                        <a href="ProdutoPerfil?idProduto=${produto.id}">
                            <div class="thumbnail">
                                <img src="Imagem?idProduto=${produto.id}" alt="...">
                                <div class="caption">
                                    <p>${produto.descricao}</p>
                                    <h3 class="valorProduto">R$ ${produto.valor}</h3>
                                </div>
                            </div>
                        </a>
                    </div>
                </c:forEach>

            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="resources/js/bootstrap.js"></script>
    </body>
</html>

