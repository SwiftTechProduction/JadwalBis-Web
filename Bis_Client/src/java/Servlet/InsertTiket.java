/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ModelTiket;

/**
 *
 * @author hero
 */
@WebServlet(name = "InsertTiket", urlPatterns = {"/InsertTiket"})
public class InsertTiket extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            
            String kode = request.getParameter("kode");
            String jalur = request.getParameter("jalur");
            String noKursi = request.getParameter("noKursi");
            String jam = request.getParameter("jam");
            String harga = request.getParameter("harga");
            
            service.ModelTiket tkt = new service.ModelTiket();
            tkt.setKode(kode);
            tkt.setJalur(jalur);
            tkt.setNoKursi(noKursi);
            tkt.setJamBerangkat(jam);
            tkt.setHarga(harga);
            
            out.println("<center>");
            if (insertTiket(tkt)) {
                out.println("<p style='color: green'>Tambah Berhasil</p>");
            } else {
                out.println("<p style='color: red'>Tambah Gagal</p>");
            }
            out.println("</center>");
            request.getRequestDispatcher("InsertTiket.jsp").include(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    private static boolean insertTiket(ModelTiket tkt) {
        service.BisService_Service service = new service.BisService_Service();
        service.BisService port = service.getBisServicePort();
        return port.insertTiket(tkt);
    }

}