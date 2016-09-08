<%-- 
    Document   : fecharPedido
    Created on : 30/08/2016, 20:01:38
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

        <div class="container conteudo" >
            <div class="col-md-6 endereco">
                <h2>Revisão</h2><br>

                <h3>Endereço de entrega</h3><br>
                <h4>${cliente.nome}</h4>
                <c:choose> 
                    <c:when test="${cliente.endereco.rua != null}">
                        <h5>Rua: ${cliente.endereco.rua}</h5> 
                        <h5>Número: ${cliente.endereco.numero}</h5> 
                        <h5>Número: ${cliente.endereco.bairro}</h5> 
                        <h5>Cidade: ${cliente.endereco.cidade}</h5> 
                    </c:when>
                    <c:when test="${cliente.endereco.rua == null}">
                        <a href="#modalEndereco" type="button" data-toggle="modal" data-target="#modalEndereco" class="btn btn-default">Definir endereço para a entrega</a>
                    </c:when>
                </c:choose>

                <h3>Sua compra</h3><br>

                <table class="table">
                    <tr class="active">
                        <td><b>Produto</b></td>
                        <td><b>Descrição</b></td>
                        <td><b>Quantidade</b></td>
                        <td><b>Preço Total</b></td>
                    </tr>
                    <c:forEach var="produto" items="${carrinho.produtos}">
                        <tr>
                            <td>
                                <div class="col-md-4"><img src="Imagem?idProduto=${produto.id}" height="80" width="80"> </div>
                            </td>
                            <td><div class="col-md-5"> ${produto.descricao}</div></td>
                            <td><div class="col-md-3"> ${produto.qtdeVenda}</div></td>
                            <td>${produto.valorComQuantidade}</td>
                        </tr>
                    </c:forEach>
                </table>

                <h3>Total: R$ ${carrinho.valor}</h3><br>


            </div>

            <div class="col-md-4 pagamento">
                <h3>Dados do pagamento</h3><br><br>
                <form class="form-horizontal" action="RealizaCompra" method="POST">
                    <label for="empresa">Prestadora do cartão</label>
                    <select name="empresa" class="form-control input-sm">
                        <option value="visa">Visa</option>
                        <option value="master">Master-Card</option>
                        <option value="hiper">Hiper-Card</option>
                    </select><br>
                    <label for="nome">Nome do titular do cartão</label>
                    <div class="form-group" id="nome">
                        <div class="col-md-12">
                            <input type="text" name="titular" class="form-control input-sm" id="inputEmail" placeholder="Nome">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">
                            <label for="numero">Número do cartão</label>
                            <input type="text" name="numero" class="form-control input-sm" id="numero" placeholder="Número do cartão">
                        </div>
                        <div class="col-md-6">
                            <label for="codigo">Código de segurança</label>
                            <input type="password" name="codigo" class="form-control input-sm" id="codigo" placeholder="Código de segurança">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6">
                            <button type="submit" class="btn btn-default ">Finalizar compra</button>
                        </div>
                    </div>
                </form>
            </div>        
        </div>
        

    </body>
</html>
