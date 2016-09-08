/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

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
@WebServlet(urlPatterns = {"/inicio"})
public class Inicio extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LojaService lojaService = new LojaService();
            req.getSession().setAttribute("produtos", lojaService.listarProdutos());
            resp.sendRedirect("index.jsp");
//                    eRequestDispatcher dispather = req.getRequestDispatcher("index.jsp");
//            dispather.forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
}
