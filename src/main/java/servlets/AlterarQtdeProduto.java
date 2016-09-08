/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidades.Carrinho;
import entidades.Produto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LojaService;

/**
 *
 * @author NandaPC
 */
@WebServlet(name = "AlterarQtdeProduto", urlPatterns = {"/AlterarQtdeProduto"})
public class AlterarQtdeProduto extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        try {

            Carrinho carrinhoDeCompras;
            String idSessao = request.getSession().getId();
            LojaService lojaService = new LojaService();

            if (lojaService.buscarIdSessao().equals(idSessao)) {
                carrinhoDeCompras = lojaService.buscarCarrinhoDeCompras();
                if (carrinhoDeCompras == null) {
                    carrinhoDeCompras = new Carrinho();
                }
            } else {
                carrinhoDeCompras = new Carrinho();
            }

            Produto produto = lojaService.buscarProduto(Long.parseLong(request.getParameter("idProduto")));
            carrinhoDeCompras.getProdutos().remove(produto);
            int qtd = Integer.parseInt(request.getParameter("quantidade"));
            if (qtd == 0) {
                carrinhoDeCompras = lojaService.removerProdutoCarrinho(carrinhoDeCompras, produto);
            } else {
                produto.setQtdeVenda(qtd);
                carrinhoDeCompras.addProduto(produto);
            }
            
            lojaService.salvarCarrinhoDeCompras(carrinhoDeCompras);

            request.getSession().setAttribute("carrinho", carrinhoDeCompras);
            RequestDispatcher dispatcher = request.getRequestDispatcher("carrinho.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
