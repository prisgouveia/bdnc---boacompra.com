<%-- 
    Document   : carrinho
    Created on : 30/08/2016, 20:02:45
    Author     : Priscila Gouveia <priscilaggouveia@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="col-md-10 col-md-offset-1 conteudoCarrinho">
                <label class="h2">Meu carrinho</label>
                <table class="table">
                    <tr class="active">
                        <td><b>Produto</b></td>
                        <td><b>Quantidade</b></td>
                        <td><b>Preço Unitário</b></td>
                        <td><b>Preço Total</b></td>
                    </tr>
                    <c:forEach var="produto" items="${carrinho.produtos}">
                        <tr>
                            <td>
                                <div class="col-md-4"><img src="Imagem?idProduto=${produto.id}" height="80" width="80"> </div>
                                <div class="col-md-7"> ${produto.descricao}</div>
                            </td>
                            <td>
                                <div>
                                    <form action="AlterarQtdeProduto">
                                        <input  type="number" min="0" max="10" name="quantidade" value="${produto.qtdeVenda}">
                                        <button type="submit"> <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span></button>
                                    </form>
                                </div>
                            </td>

                            <td>${produto.valor}</td>
                            <td>${produto.valorComQuantidade}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3"><b class="h3">Total</b></td>
                        <td>${carrinho.valor}</td>
                    </tr>
                </table>
                <div class="col-md-3">
                    <a href="index.jsp" class="btn btn-info">Comprar mais produtos</a>
                </div>
                <div class="col-md-2 col-md-offset-7">
                    <a href="fecharPedido.jsp" class="btn btn-default">Finalizar pedido</a>
                </div>
            </div>
        </div>
        <footer>
            <div class="navbar navbar-fixed-bottom footer">
                <p class="navbar-text">Developed by PriscilaG e FernandaM &copy; 2016</p>
            </div>
        </footer> 
    </body>
</html>

