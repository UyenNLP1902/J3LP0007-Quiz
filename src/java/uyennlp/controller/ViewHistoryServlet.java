/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uyennlp.model.history.HistoryDAO;
import uyennlp.model.history.HistoryDTO;

/**
 *
 * @author HP
 */
public class ViewHistoryServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String MAIN_PAGE = "mainPage.jsp";
    private final int ROWS_PER_PAGE = 5;

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
        PrintWriter out = response.getWriter();

        String url = ERROR_PAGE;
        String subjectId = request.getParameter("cboSubject");
        String page = request.getParameter("txtCurrentPage");
        int currentPage = Integer.parseInt(page);
        try {
            HttpSession session = request.getSession(false);

            if (session != null) {
                HistoryDAO dao = new HistoryDAO();
                HistoryDTO dto = new HistoryDTO();
                dto.setSubjectId(subjectId);
                String email = (String) session.getAttribute("EMAIL");
                dto.setEmail(email);

                dao.searchHistoryByStudent(dto, currentPage, ROWS_PER_PAGE);

                int noOfRecords = dao.countRecordsByStudent(dto);
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / ROWS_PER_PAGE);
                request.setAttribute("noOfPages", noOfPages);

                List<HistoryDTO> list = dao.showListHistory();
                request.setAttribute("VIEW_RESULT", list);
                request.setAttribute("currentPage", page);

                url = MAIN_PAGE;
            }
        } catch (NamingException | SQLException ex) {
            log(ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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

}
