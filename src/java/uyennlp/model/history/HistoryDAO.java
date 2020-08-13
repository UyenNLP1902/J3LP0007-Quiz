/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.history;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import uyennlp.util.DBHelper;

/**
 *
 * @author HP
 */
public class HistoryDAO {

    Connection con;
    PreparedStatement stm;
    ResultSet rs;

    public HistoryDAO() {
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

    private List<HistoryDTO> list;

    public List<HistoryDTO> showListHistory() {
        return list;
    }

    public boolean saveHistory(String email, String subjectId, double score, int numberOfCorrect) throws SQLException, NamingException {
        boolean check = false;

        try {
            Date date = new Date();
            Timestamp currentTime = new Timestamp(date.getTime());
            String sql = "INSERT INTO tblHistory"
                    + "(email,subjectId,score,dateQuiz,numberOfCorrect) "
                    + "VALUES(?,?,?,?,?)";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, subjectId);
            stm.setDouble(3, score);
            stm.setTimestamp(4, currentTime);
            stm.setInt(5, numberOfCorrect);

            check = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return check;
    }

    public void searchHistoryByStudent(HistoryDTO info,
            int currentPage, int rowsPerPage) throws NamingException, SQLException {
        try {
            String sql = "SELECT id, subjectId, score, dateQuiz "
                    + "FROM tblHistory "
                    + "WHERE email = ? AND subjectId = ? "
                    + " ORDER BY id ASC "
                    + "OFFSET (? - 1) * ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, info.getEmail());
            stm.setString(2, info.getSubjectId());
            stm.setInt(3, currentPage);
            stm.setInt(4, rowsPerPage);
            stm.setInt(5, rowsPerPage);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String subjectId = rs.getString("subjectId");
                double score = rs.getDouble("score");
                Timestamp dateQuiz = rs.getTimestamp("dateQuiz");

                HistoryDTO dto = new HistoryDTO();
                dto.setId(id);
                dto.setEmail(info.getEmail());
                dto.setSubjectId(subjectId);
                dto.setScore(score);
                dto.setDateQuiz(dateQuiz);

                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
    }

    public void searchHistoryByAdmin(String keyword, String subject,
            int currentPage, int rowsPerPage) throws NamingException, SQLException {
        try {
            String searchValue = getSearchValues(keyword, subject);
            String sql = "SELECT id, email, subjectId, score, dateQuiz, numberOfCorrect "
                    + "FROM tblHistory "
                    + "WHERE " + searchValue
                    + " ORDER BY id ASC "
                    + "OFFSET (? - 1) * ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            int count = 1;
            if (!keyword.trim().isEmpty()) {
                stm.setString(count, "%" + keyword + "%");
                count++;
            }
            if (!subject.equals("All")) {
                stm.setString(count, subject);
                count++;
            }
            stm.setInt(count, currentPage);
            count++;
            stm.setInt(count, rowsPerPage);
            count++;
            stm.setInt(count, rowsPerPage);
            rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String subjectId = rs.getString("subjectId");
                double score = rs.getDouble("score");
                Timestamp dateQuiz = rs.getTimestamp("dateQuiz");
                int numberOfCorrect = rs.getInt("numberOfCorrect");

                HistoryDTO dto = new HistoryDTO();
                dto.setId(id);
                dto.setEmail(email);
                dto.setSubjectId(subjectId);
                dto.setScore(score);
                dto.setDateQuiz(dateQuiz);
                dto.setNumberOfCorrect(numberOfCorrect);

                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
    }

    private String getSearchValues(String email, String subject) {
        String searchValue = "";
        if (!email.isEmpty()) {
            searchValue += "email LIKE ? AND ";
        }
        if (!subject.equals("All")) {
            searchValue += "subjectId = ? AND ";
        }

        searchValue = searchValue.trim();
        searchValue = searchValue.substring(0, searchValue.length() - 4);
        return searchValue;
    }

    public int countRecordsByAdmin(String keyword, String subject) throws NamingException, SQLException {
        int result = 0;
        try {
            String searchValue = getSearchValues(keyword, subject);
            String sql = "SELECT COUNT(id) as NumberOfRecords "
                    + "FROM tblHistory "
                    + "WHERE " + searchValue;
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            int count = 1;
            if (!keyword.trim().isEmpty()) {
                stm.setString(count, "%" + keyword + "%");
                count++;
            }
            if (!subject.equals("All")) {
                stm.setString(count, subject);
                count++;
            }
            rs = stm.executeQuery();

            while (rs.next()) {
                result = rs.getInt("NumberOfRecords");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int countRecordsByStudent(HistoryDTO info) throws NamingException, SQLException {
        int result = 0;
        try {
            String sql = "SELECT COUNT(id) as NumberOfRecords "
                    + "FROM tblHistory "
                    + "WHERE email = ? AND subjectId = ? ";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, info.getEmail());
            stm.setString(2, info.getSubjectId());
            rs = stm.executeQuery();

            while (rs.next()) {
                result = rs.getInt("NumberOfRecords");
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
