/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uyennlp.model.account.AccountDAO;
import uyennlp.model.account.AccountDTO;
import uyennlp.model.subject.SubjectDAO;
import uyennlp.model.subject.SubjectDTO;

/**
 *
 * @author HP
 */
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String MAIN_PAGE = "mainPage.jsp";
    private final String ACTIVATE_PAGE = "activate.jsp";

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
        String url = INVALID_PAGE;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            AccountDTO account = new AccountDTO();
            account.setEmail(username);
            account.setPassword(password);

            AccountDAO dao = new AccountDAO();
            boolean check = dao.checkLogin(account);
            if (check) {
                HttpSession session = request.getSession();
                AccountDTO dto = dao.getUser(username);

                session.setAttribute("NAME", dto.getName());
                session.setAttribute("EMAIL", dto.getEmail());

                String accountStatus = dto.getStatus();
                if (!accountStatus.equals("activated")) {
                    url = ACTIVATE_PAGE;
                } else {
                    url = MAIN_PAGE;
                    if (dto.getRole().equals("admin")) {
                        session.setAttribute("ROLE", dto.getRole());
                    }
                    SubjectDAO subjectDAO = new SubjectDAO();
                    List<SubjectDTO> list = subjectDAO.getSubjectList();
                    if (list != null) {
                        session.setAttribute("LIST_SUBJECT", list);
                    }
                }
            }

        } catch (SQLException | NamingException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            log(ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
