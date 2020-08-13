/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uyennlp.model.answer.AnswerDAO;
import uyennlp.model.history.HistoryDAO;
import uyennlp.model.subject.SubjectDTO;

/**
 *
 * @author HP
 */
public class SubmitQuizServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String SHOW_RESULT_PAGE = "showResult.jsp";

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
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String email = (String) session.getAttribute("EMAIL");

                Map<Integer, Integer> answerSheet
                        = (Map<Integer, Integer>) session.getAttribute("ANSWER_SHEET");
                List<Integer> listOfAnswers = new ArrayList<>(answerSheet.values());
                SubjectDTO subject = (SubjectDTO) session.getAttribute("SUBJECT");

                AnswerDAO dao = new AnswerDAO();
                dao.submitAnswers(listOfAnswers, subject.getNoOfQuestion());
                int noOfCorrect = dao.getNumberOfCorrect();
                double score = dao.getNumberOfCorrect();

                HistoryDAO hdao = new HistoryDAO();
                boolean check = hdao.saveHistory(email, subject.getId(), score, noOfCorrect);
                
                if (check) {
                    request.setAttribute("SUBJECT", subject);
                    request.setAttribute("NO_OF_CORRECT", noOfCorrect);
                    request.setAttribute("SCORE", score);

                    //invalid attribute
                    session.removeAttribute("QUESTION_LIST");
                    session.removeAttribute("NoOfQuestion");
                    session.removeAttribute("SUBJECT");
                    session.removeAttribute("ANSWER_SHEET");
                    session.removeAttribute("TIME");

                    url = SHOW_RESULT_PAGE;
                }
            }
        } catch (SQLException | NamingException ex) {
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
