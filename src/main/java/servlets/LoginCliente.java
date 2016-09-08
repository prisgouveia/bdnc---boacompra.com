/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entidades.Cliente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ClienteService;
import service.LojaService;

/**
 *
 * @author NandaPC
 */
@WebServlet(name = "LoginCliente", urlPatterns = {"/LoginCliente"})
public class LoginCliente extends HttpServlet {

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
        RequestDispatcher dispather;
        try {

            ClienteService clienteService = new ClienteService();
            LojaService lojaService = new LojaService();
            Cliente cliente = clienteService.logar(request.getParameter("email"), request.getParameter("senha"));
            
            if (cliente != null) {
                request.getSession().setAttribute("cliente", cliente);
                lojaService.salvarIdSessao(request.getSession().getId());

                dispather = request.getRequestDispatcher("index.jsp");

                System.out.println("LOGADO");
                
            }else{
            dispather = request.getRequestDispatcher("login.html");
            }
            dispather.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
