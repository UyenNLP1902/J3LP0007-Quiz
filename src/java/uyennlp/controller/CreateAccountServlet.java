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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uyennlp.model.account.AccountCreateError;
import uyennlp.model.account.AccountDAO;
import uyennlp.model.account.AccountDTO;
import uyennlp.util.Encryption;

/**
 *
 * @author HP
 */
public class CreateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

    private final String EMAIL_VALID = "[A-Za-z0-9.+-_]{1,}"
            + "[@]{1}"
            + "[A-Za-z]{1,6}"
            + "[.]{1}"
            + "[A-Za-z]{2,4}";
    private final String ROLE_DEFAULT = "student";
    private final String STATUS_DEFAULT = "new";

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

        String email = request.getParameter("txtEmail");
        String name = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");

        AccountCreateError errors = new AccountCreateError();
        boolean foundErr = false;
        String url = ERROR_PAGE;

        try {
            if (email.trim().isEmpty() || !email.matches(EMAIL_VALID)) {
                foundErr = true;
                errors.setEmailTemplateErr("Wrong email format!");
            }

            if (name.trim().length() < 2 || name.trim().length() > 30) {
                foundErr = true;
                errors.setNameLengthErr("Name is required inputted from 2 to 30 characters");
            }

            if (password.isEmpty() || password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthErr("Password is required inputted from 6 to 20 characters");
            } else if (!confirm.equals(password)) {
                foundErr = true;
                errors.setConfirmNotMatched("Confirm must match password");
            }

            if (foundErr) {
                request.setAttribute("CREATEERRORS", errors);
            } else {
                //String ecryptedPassword = Encryption.getSHA(password);
                AccountDTO dto
                        = new AccountDTO(email, name, password, ROLE_DEFAULT, STATUS_DEFAULT);
                AccountDAO dao = new AccountDAO();
                boolean result = dao.createAccount(dto);

                if (result) {
                    url = LOGIN_PAGE;
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();            
            if (msg.contains("duplicate")) {
                errors.setEmailIsExisted("This email is already exist.");
                request.setAttribute("CREATE_ERRORS", errors);
            }
            log(msg);
        } catch (NamingException | NoSuchAlgorithmException | UnsupportedEncodingException ex) {
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
