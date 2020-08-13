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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uyennlp.model.answer.AnswerDAO;
import uyennlp.model.answer.AnswerDTO;
import uyennlp.model.question.QuestionDAO;
import uyennlp.model.question.QuestionDTO;

/**
 *
 * @author HP
 */
public class UpdateServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";

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
        String questionId = request.getParameter("txtQuestionId");
        String subjectId = request.getParameter("txtSubjectId");
        String questionContent = request.getParameter("txtQuestionContent");

        String answerAContent = request.getParameter("content1");
        String answerBContent = request.getParameter("content2");
        String answerCContent = request.getParameter("content3");
        String answerDContent = request.getParameter("content4");
        String answerAId = request.getParameter("id1");
        String answerBId = request.getParameter("id2");
        String answerCId = request.getParameter("id3");
        String answerDId = request.getParameter("id4");
        String answerCorrect = request.getParameter("rbtnCorrect");

        String keyword = request.getParameter("txtKeyword");
        String status = request.getParameter("cboStatus");
        String subject = request.getParameter("cboSubject");
        String page = request.getParameter("txtCurrentPage");

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                if (!answerAContent.isEmpty()
                        && !answerBContent.isEmpty()
                        && !answerCContent.isEmpty()
                        && !answerDContent.isEmpty()
                        && !questionContent.isEmpty()) {

                    boolean check = false;

                    QuestionDTO dto = new QuestionDTO();
                    dto.setId(Integer.parseInt(questionId));
                    dto.setSubjectId(subjectId);
                    dto.setContent(questionContent);

                    List<AnswerDTO> answerList = new ArrayList<>();

                    AnswerDTO answerA = new AnswerDTO();
                    answerA.setId(Integer.parseInt(answerAId));
                    answerA.setContent(answerAContent);
                    answerList.add(answerA);

                    AnswerDTO answerB = new AnswerDTO();
                    answerA.setId(Integer.parseInt(answerBId));
                    answerA.setContent(answerBContent);
                    answerList.add(answerB);

                    AnswerDTO answerC = new AnswerDTO();
                    answerA.setId(Integer.parseInt(answerCId));
                    answerA.setContent(answerCContent);
                    answerList.add(answerC);

                    AnswerDTO answerD = new AnswerDTO();
                    answerA.setId(Integer.parseInt(answerDId));
                    answerA.setContent(answerDContent);
                    answerList.add(answerD);

                    QuestionDAO qdao = new QuestionDAO();
                    check = qdao.updateQuestion(dto);

                    AnswerDAO adao = new AnswerDAO();
                    adao.updateAnswers(answerList, answerCorrect);

                    if (check) {
                        url = "DispatcherController"
                                + "?btnAction=Search"
                                + "&txtKeyword=" + keyword
                                + "&cboStatus=" + status
                                + "&choSubject=" + subject
                                + "&txtCurrentPage=" + page;
                    }
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
