<%-- 
    Document   : detalheProduto
    Created on : 30/08/2016, 16:51:01
    Author     : Priscila Gouveia <priscilaggouveia@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
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

        <div class="container" >
            <div class="col-md-9 detalhesproduto">
                <div class="col-md-7">
                    <img src="Imagem?idProduto=${produto.id}" height="350" width="350">                    
                </div>
                <div class="col-md-5 detalhesProduto">
                    <h2>${produto.descricao}</h2>
                    <b>R$ ${produto.valor}</b><br>
                    <a href="AddProdutoCarrinho?idProduto=${produto.id}" class="btn btn-default addCarrinho">Adicionar ao carrinho</a>

                </div>
            </div>

            <div class="col-md-9 sugestao">
                <h4>Quem comprou esse produto, também comprou</h4><br><br>
                <c:forEach var="p" items="${produtosSugeridos}">
                    <div class="col-sm-6 col-md-3">
                        <a href="">
                            <div class="thumbnail">
                                <img src="Imagem?idProduto=${p.id}" alt="...">
                                <div class="caption">
                                    <p>${p.descricao}</p>
                                    <h3>R$ ${p.valor}</h3>
                                </div>
                            </div>
                        </a>
                    </div>                    
                </c:forEach>
            </div>
        </div>
        <footer>
            <div class="navbar navbar-fixed-bottom footer">
                <p class="navbar-text">Developed by PriscilaG e FernandaM &copy; 2016</p>
            </div>
        </footer> 
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="resources/js/bootstrap.js"></script>
    </body>
</html>

