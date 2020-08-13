/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import uyennlp.model.answer.AnswerDAO;
import uyennlp.model.answer.AnswerDTO;
import uyennlp.model.subject.SubjectDTO;
import uyennlp.util.DBHelper;

/**
 *
 * @author HP
 */
public class QuestionDAO implements Serializable {

    private final boolean DEFAULT_STATUS = true;

    Connection con;
    PreparedStatement stm;
    ResultSet rs;

    public QuestionDAO() {
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

    private Map<QuestionDTO, List<AnswerDTO>> list;

    public Map<QuestionDTO, List<AnswerDTO>> getQuestionList() {
        return list;
    }

    public int countRecords(String keyword, String subjectId, String status) throws SQLException, NamingException {
        int result = 0;
        String searchValue = getSearchValues(keyword, subjectId, status);
        try {
            String sql = "SELECT COUNT(id) as NumberOfRecords "
                    + "FROM tblQuestion "
                    + "WHERE " + searchValue;
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            int count = 1;
            if (!keyword.isEmpty()) {
                stm.setString(count, "%" + keyword + "%");
                count++;
            }
            if (!subjectId.equals("All")) {
                stm.setString(count, subjectId);
                count++;
            }
            if (!status.equals("All")) {
                if (status.equals("Available")) {
                    stm.setBoolean(count, true);
                } else if (status.equals("Disable")) {
                    stm.setBoolean(count, false);
                }
                count++;
            }

            rs = stm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("NumberOfRecords");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getSearchValues(String keyword, String subjectId, String status) {
        String searchValue = "";
        if (!keyword.isEmpty()) {
            searchValue += "content LIKE ? AND ";
        }
        if (!subjectId.equals("All")) {
            searchValue += "subjectId = ? AND ";
        }
        if (!status.equals("All")) {
            searchValue += "status = ? AND ";
        }

        searchValue = searchValue.trim();
        searchValue = searchValue.substring(0, searchValue.length() - 4);
        return searchValue;
    }

    public void searchQuestion(String keyword, String subjectId, String status,
            int pageNumber, int rowsPerPage) throws SQLException, NamingException {
        try {
            String searchValue = getSearchValues(keyword, subjectId, status);
            String sql = "SELECT id, content, subjectId, status "
                    + "FROM tblQuestion "
                    + "WHERE " + searchValue
                    + " ORDER BY content ASC "
                    + "OFFSET (? - 1) * ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            int count = 1;
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            if (!keyword.isEmpty()) {
                stm.setString(count, "%" + keyword + "%");
                count++;
            }
            if (!subjectId.equals("All")) {
                stm.setString(count, subjectId);
                count++;
            }
            if (!status.equals("All")) {
                if (status.equals("Available")) {
                    stm.setBoolean(count, true);
                } else if (status.equals("Disable")) {
                    stm.setBoolean(count, false);
                }
                count++;
            }
            stm.setInt(count, pageNumber);
            count++;
            stm.setInt(count, rowsPerPage);
            count++;
            stm.setInt(count, rowsPerPage);

            rs = stm.executeQuery();

            while (rs.next()) {
                QuestionDTO questionDTO = new QuestionDTO();
                int id = rs.getInt("id");
                String content = rs.getString("content");
                String subject = rs.getString("subjectId");
                boolean isAvailable = rs.getBoolean("status");
                questionDTO.setId(id);
                questionDTO.setContent(content);
                questionDTO.setSubjectId(subject);
                questionDTO.setStatus(isAvailable);

                AnswerDAO dao = new AnswerDAO();
                dao.searchAnswerByQuestionId(id);
                List<AnswerDTO> answers = dao.getAnswerList();

                if (list == null) {
                    list = new HashMap<>();
                }
                list.put(questionDTO, answers);
            }
        } finally {
            closeConnection();
        }
    }

    public boolean deleteQuestion(String questionId) throws SQLException, NamingException {
        boolean check = false;
        int id = Integer.parseInt(questionId);

        try {
            String sql = "UPDATE tblQuestion "
                    + "SET status = ? "
                    + "WHERE id = ?";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setBoolean(1, false);
            stm.setInt(2, id);

            check = stm.executeUpdate() > 0;

        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean updateQuestion(QuestionDTO dto) throws SQLException, NamingException {
        boolean check = false;

        try {
            String sql = "UPDATE tblQuestion "
                    + "SET content = ?, subjectId = ?, lastUpdateDate = GETDATE() "
                    + "WHERE id = ?";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getContent());
            stm.setString(2, dto.getSubjectId());
            stm.setInt(3, dto.getId());

            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public boolean createQuestion(QuestionDTO dto) throws NamingException, SQLException {
        boolean check = false;
        try {
            Date date = new Date();
            Timestamp currentTime = new Timestamp(date.getTime());
            String sql = "INSERT INTO tblQuestion"
                    + "(content, createDate, lastUpdateDate, subjectId, status)"
                    + " VALUES(?,?,?,?,?)";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getContent());
            stm.setTimestamp(2, currentTime);
            stm.setTimestamp(3, currentTime);
            stm.setString(4, dto.getSubjectId());
            stm.setBoolean(5, DEFAULT_STATUS);

            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getLatestQuestionId() throws NamingException, SQLException {
        int result = -1;

        try {
            String sql = "SELECT TOP 1 id "
                    + "FROM tblQuestion "
                    + "ORDER BY id DESC";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            if (rs.next()) {
                result = rs.getInt("id");
            }
        } finally {
            closeConnection();
        }

        return result;
    }
    
    public List<QuestionDTO> getQuizQuestionList(SubjectDTO subject) throws NamingException, SQLException {
        List<QuestionDTO> qList = null;
        try {
            String sql = "SELECT TOP(?) id, content "
                    + "FROM tblQuestion "
                    + "WHERE subjectId = ? AND status = ? "
                    + "ORDER BY NEWID()";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, subject.getNoOfQuestion());
            stm.setString(2, subject.getId());
            stm.setBoolean(3, true);
            rs = stm.executeQuery();
            while (rs.next()) {
                QuestionDTO questionDTO = new QuestionDTO();
                int id = rs.getInt("id");
                String content = rs.getString("content");
                questionDTO.setId(id);
                questionDTO.setContent(content);

                if (qList == null) {
                    qList = new ArrayList<>();
                }
                qList.add(questionDTO);
            }
        } finally {
            closeConnection();
        }
        return qList;
    }

    public void getQuestionById(int id) throws SQLException, NamingException {
        try {
            String sql = "SELECT id, content "
                    + "FROM tblQuestion "
                    + "WHERE id = ? ";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);

            rs = stm.executeQuery();

            while (rs.next()) {
                QuestionDTO questionDTO = new QuestionDTO();
                String content = rs.getString("content");
                questionDTO.setId(id);
                questionDTO.setContent(content);

                AnswerDAO dao = new AnswerDAO();
                dao.searchAnswerByQuestionId(id);
                List<AnswerDTO> answers = dao.getAnswerList();
                Collections.shuffle(answers);

                if (list == null) {
                    list = new HashMap<>();
                }
                list.put(questionDTO, answers);
            }
        } finally {
            closeConnection();
        }
    }
}
