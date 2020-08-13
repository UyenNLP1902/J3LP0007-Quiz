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
import uyennlp.model.answer.AnswerDAO;
import uyennlp.model.answer.AnswerDTO;
import uyennlp.model.question.QuestionCreateError;
import uyennlp.model.question.QuestionDAO;
import uyennlp.model.question.QuestionDTO;

/**
 *
 * @author HP
 */
public class CreateQuestionServlet extends HttpServlet {

    private final String ERROR_PAGE = "createQuestion.jsp";
    private final String SUCCESS_PAGE = "createSuccessfully.html";

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

        String correct = request.getParameter("btnCorrect");
        String subjectId = request.getParameter("cboSubject");
        String question = request.getParameter("txtQuestion");
        String answerA = request.getParameter("txtAnswerA");
        String answerB = request.getParameter("txtAnswerB");
        String answerC = request.getParameter("txtAnswerC");
        String answerD = request.getParameter("txtAnswerD");

        QuestionCreateError errors = new QuestionCreateError();
        boolean foundErr = false;
        try {

            if (question.trim().isEmpty()) {
                foundErr = true;
                errors.setQuestionIsEmpty("Question is required!");
            }
            if (answerA.trim().isEmpty()
                    || answerB.trim().isEmpty()
                    || answerC.trim().isEmpty()
                    || answerD.trim().isEmpty()) {
                foundErr = true;
                errors.setAnswerIsEmpty("All answer fields cannot be blank!");
            }
            if (correct == null) {
                foundErr = true;
                errors.setNoCorrectAnswer("Please choose a correct answer!");
            }
            if (foundErr) {
                request.setAttribute("CREATE_ERRORS", errors);
            } else {
                QuestionDTO qdto = new QuestionDTO();
                qdto.setContent(question);
                qdto.setSubjectId(subjectId);

                QuestionDAO qdao = new QuestionDAO();
                boolean check = qdao.createQuestion(qdto);

                if (check) {
                    int questionId = qdao.getLatestQuestionId();

                    AnswerDTO dtoA = new AnswerDTO();
                    dtoA.setContent(answerA);
                    if (correct.equals("A")) {
                        dtoA.setIsCorrect(true);
                    } else {
                        dtoA.setIsCorrect(false);
                    }

                    AnswerDTO dtoB = new AnswerDTO();
                    dtoB.setContent(answerB);
                    if (correct.equals("B")) {
                        dtoB.setIsCorrect(true);
                    } else {
                        dtoB.setIsCorrect(false);
                    }

                    AnswerDTO dtoC = new AnswerDTO();
                    dtoC.setContent(answerC);
                    if (correct.equals("C")) {
                        dtoC.setIsCorrect(true);
                    } else {
                        dtoC.setIsCorrect(false);
                    }

                    AnswerDTO dtoD = new AnswerDTO();
                    dtoD.setContent(answerD);
                    if (correct.equals("D")) {
                        dtoD.setIsCorrect(true);
                    } else {
                        dtoD.setIsCorrect(false);
                    }

                    List<AnswerDTO> list = new ArrayList<>();
                    list.add(dtoA);
                    list.add(dtoB);
                    list.add(dtoC);
                    list.add(dtoD);

                    AnswerDAO adao = new AnswerDAO();
                    adao.addAnswers(questionId, list);
                    url = SUCCESS_PAGE;
                }
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
