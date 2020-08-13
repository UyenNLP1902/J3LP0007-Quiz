/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.answer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import uyennlp.util.DBHelper;

/**
 *
 * @author HP
 */
public class AnswerDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public AnswerDAO() {
    }

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    private List<AnswerDTO> list;

    public List<AnswerDTO> getAnswerList() {
        return list;
    }

    public void searchAnswerByQuestionId(int questionId) throws NamingException, SQLException {
        try {
            String sql = "SELECT id, questionId, content, isCorrect "
                    + "FROM tblAnswer "
                    + "WHERE questionId = ?";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, questionId);
            rs = stm.executeQuery();

            while (rs.next()) {
                AnswerDTO dto = new AnswerDTO();
                int id = rs.getInt("id");
                String content = rs.getString("content");
                boolean isCorrect = rs.getBoolean("isCorrect");
                dto.setId(id);
                dto.setQuestionId(questionId);
                dto.setContent(content);
                dto.setIsCorrect(isCorrect);

                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
    }

    public void updateAnswers(List<AnswerDTO> list, String correctAnswer) throws SQLException, NamingException {
        for (AnswerDTO a : list) {
            updateAnswer(a, Integer.parseInt(correctAnswer));
        }
    }

    private boolean updateAnswer(AnswerDTO dto, int correctAnswer) throws SQLException, NamingException {
        boolean check = false;

        try {
            boolean correct = false;
            if (dto.getId() == correctAnswer) {
                correct = true;
            }

            String sql = "UPDATE tblAnswer "
                    + "SET content = ?, isCorrect = ? "
                    + "WHERE id = ?";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getContent());
            stm.setBoolean(2, correct);
            stm.setInt(3, dto.getId());

            check = stm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return check;
    }

    public void addAnswers(int questionId, List<AnswerDTO> list) throws NamingException, SQLException {
        for (AnswerDTO a : list) {
            addAnswer(questionId, a);
        }
    }

    private boolean addAnswer(int questionId, AnswerDTO dto) throws NamingException, SQLException {
        boolean check = false;

        try {
            String sql = "INSERT INTO tblAnswer"
                    + "(questionId, content, isCorrect) "
                    + "VALUES (?,?,?)";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, questionId);
            stm.setString(2, dto.getContent());
            stm.setBoolean(3, dto.isIsCorrect());

            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    private int numberOfCorrect;
    private double score;

    public int getNumberOfCorrect() {
        return numberOfCorrect;
    }

    public double getScore() {
        return score;
    }

    private boolean checkCorrectAnswers(int id) throws SQLException, NamingException {
        boolean check = false;

        try {
            String sql = "Select id, content "
                    + "FROM tblAnswer "
                    + "WHERE id = ? AND isCorrect = ?";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setBoolean(2, true);
            rs = stm.executeQuery();

            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }

        return check;
    }

    public void submitAnswers(List<Integer> listOfAnswers, int totalQuestions) throws SQLException, NamingException {
        score = 0;
        numberOfCorrect = 0;
        for (Integer answerId : listOfAnswers) {
            boolean check = checkCorrectAnswers(answerId);
            if (check) {
                numberOfCorrect++;
            }
        }
        score = (double)numberOfCorrect / (double)totalQuestions;
    }

}
