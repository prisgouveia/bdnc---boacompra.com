/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidades.Carrinho;
import entidades.Produto;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "AddProdutoCarrinho", urlPatterns = {"/AddProdutoCarrinho"})
public class AddProdutoCarrinho extends HttpServlet {

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

            Carrinho carrinhoDeCompras = null;
            String idSessao = request.getSession().getId();
            LojaService lojaService = new LojaService();

            String idSalvo = lojaService.buscarIdSessao();
            if (idSalvo != null && idSalvo.equals(idSessao)) {
                carrinhoDeCompras = lojaService.buscarCarrinhoDeCompras();
            }

            if (carrinhoDeCompras == null) {
                carrinhoDeCompras = new Carrinho();
            }

            List<Produto> produtos = carrinhoDeCompras.getProdutos();
            Long idProduto = Long.parseLong(request.getParameter("idProduto"));
            Produto produto = null;
            for (Produto p : produtos) {
                if (p.getId() == idProduto) {
                    p.setQtdeVenda(p.getQtdeVenda() + 1);
                    produto = p;
                    break;
                }

            }
            if (produto == null) {
                produto = lojaService.buscarProduto(idProduto);
                produto.setQtdeVenda(1);
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
