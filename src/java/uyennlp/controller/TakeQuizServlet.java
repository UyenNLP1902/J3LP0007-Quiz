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
import uyennlp.model.answer.AnswerDAO;
import uyennlp.model.answer.AnswerDTO;
import uyennlp.model.question.QuestionDAO;
import uyennlp.model.question.QuestionDTO;
import uyennlp.model.subject.SubjectDAO;
import uyennlp.model.subject.SubjectDTO;

/**
 *
 * @author HP
 */
public class TakeQuizServlet extends HttpServlet {

    private final String ERROR_PAGE = "error.html";
    private final String SAVE_ANSWER_SERVLET = "SaveAnswerServlet";

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
        String subjectId = request.getParameter("txtSubjectId");
        String questionNo = request.getParameter("index");
        int index;
        try {
            SubjectDAO subjectDAO = new SubjectDAO();
            QuestionDAO questionDAO = new QuestionDAO();
            AnswerDAO answerDAO = new AnswerDAO();

            HttpSession session = request.getSession(false);
            if (session != null) {
                //Get Question List
                List<QuestionDTO> questionList;
                questionList = (List<QuestionDTO>) session.getAttribute("QUESTION_LIST");
                if (questionList == null) {
                    SubjectDTO subjectDTO = subjectDAO.getSubjectInfo(subjectId);
                    questionList = questionDAO.getQuizQuestionList(subjectDTO);
                    session.setAttribute("QUESTION_LIST", questionList);
                    if (subjectDTO.getNoOfQuestion() > questionList.size()) {
                        session.setAttribute("NoOfQuestion", questionList.size());
                    } else {
                        session.setAttribute("NoOfQuestion", subjectDTO.getNoOfQuestion());
                    }
                    session.setAttribute("SUBJECT", subjectDTO);
                }//end if questionList exist in session Scope

                //load Question
                if (questionNo == null) {
                    index = 1;
                } else {
                    index = Integer.parseInt(questionNo);
                }
                QuestionDTO question
                        = questionList.get(index - 1);
                answerDAO.searchAnswerByQuestionId(question.getId());
                List<AnswerDTO> answers = answerDAO.getAnswerList();
                request.setAttribute("QUESTION", question);
                request.setAttribute("ANSWERS", answers);
                request.setAttribute("INDEX", index);

                url = SAVE_ANSWER_SERVLET;
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
