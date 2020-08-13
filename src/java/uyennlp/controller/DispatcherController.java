/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP
 */
public class DispatcherController extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String CREATE_ACCOUNT_SERVLET = "CreateAccountServlet";
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String UPDATE_SERVLET = "UpdateServlet";
    private final String CREATE_QUESTION_SERVLET = "CreateQuestionServlet";
    private final String PREPARE_QUIZ_SERVLET = "PrepareQuizServlet";
    private final String TAKE_QUIZ_SERVLET = "TakeQuizServlet";
    private final String VIEW_QUIZ_HISTORY_SERVLET = "ViewQuizHistoryServlet";
    private final String SUBMIT_QUIZ_SERVLET = "SubmitQuizServlet";
    private final String VIEW_HISTORY_SERVLET = "ViewHistoryServlet";

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

        String button = request.getParameter("btnAction");
        String url = LOGIN_PAGE;
        try {
            if (button == null) {

            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;

            } else if (button.equals("Logout")) {
                url = LOGOUT_SERVLET;

            } else if (button.equals("Create Account")) {
                url = CREATE_ACCOUNT_SERVLET;

            } else if (button.equals("Search")) {
                url = SEARCH_SERVLET;

            } else if (button.equals("Delete")) {
                url = DELETE_SERVLET;

            } else if (button.equals("Update")) {
                url = UPDATE_SERVLET;

            } else if (button.equals("Create")) {
                url = CREATE_QUESTION_SERVLET;

            } else if (button.equals("Take A Quiz")) {
                url = PREPARE_QUIZ_SERVLET;

            } else if (button.equals("Start")) {
                url = TAKE_QUIZ_SERVLET;

            } else if (button.equals("Finish Attempt")) {
                url = SUBMIT_QUIZ_SERVLET;
                
            } else if (button.equals("View History")) {
                url = VIEW_HISTORY_SERVLET;
                //for Student
            } else if (button.equals("View Quiz History")) {
                url = VIEW_QUIZ_HISTORY_SERVLET;
                //for Admin
            }
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
