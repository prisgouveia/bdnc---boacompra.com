/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidades.Carrinho;
import entidades.Cliente;
import entidades.Compra;
import entidades.Pagamento;
import java.io.IOException;
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
@WebServlet(name = "RealizaCompra", urlPatterns = {"/RealizaCompra"})
public class RealizaCompra extends HttpServlet {

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

            Carrinho carrinho = null;
            String idSessao = request.getSession().getId();
            LojaService lojaService = new LojaService();

            if (lojaService.buscarIdSessao().equals(idSessao)) {
                carrinho = lojaService.buscarCarrinhoDeCompras();
            }

            if (carrinho != null) {
                Compra compra = new Compra();
                compra.setCarrinho(carrinho);
                compra.setCliente((Cliente)request.getSession().getAttribute("cliente"));
                compra.setEnderecoParaEntrega(compra.getCliente().getEndereco());
                Pagamento pagamento = new Pagamento();
                pagamento.setEmpresa(request.getParameter("empresa"));
                pagamento.setTitular(request.getParameter("titular"));
                pagamento.setNumero(Integer.parseInt(request.getParameter("numero")));
                pagamento.setCodSeguranca(request.getParameter("codigo"));
                compra.setPagamento(pagamento);
                lojaService.salvarCompra(compra);
//                lojaService.limparCarrinho(carrinho);
                request.getSession().removeAttribute("carrinho");
                response.sendRedirect("");
            }
            
            response.sendRedirect("");

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
