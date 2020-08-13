/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.subject;

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
public class SubjectDAO implements Serializable {

    Connection con;
    PreparedStatement stm;
    ResultSet rs;

    public SubjectDAO() {
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

    private List<SubjectDTO> subjectList;

    public List<SubjectDTO> getSubjectList() throws SQLException, NamingException {
        try {
            String sql = "SELECT id, name, noOfQuestion "
                    + "FROM tblSubject";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                int noOfQuestion = rs.getInt("noOfQuestion");
                SubjectDTO dto = new SubjectDTO();
                dto.setId(id);
                dto.setName(name);
                dto.setNoOfQuestion(noOfQuestion);

                if (this.subjectList == null) {
                    this.subjectList = new ArrayList<>();
                }
                this.subjectList.add(dto);
            }
        } finally {
            closeConnection();
        }
        return subjectList;
    }

    public SubjectDTO getSubjectInfo(String subjectId) throws NamingException, SQLException {
        SubjectDTO dto = null;
        try {
            String sql = "SELECT name, noOfQuestion, timer "
                    + "FROM tblSubject "
                    + "WHERE id = ?";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, subjectId);
            rs = stm.executeQuery();

            if (rs.next()) {
                dto = new SubjectDTO();
                String name = rs.getString("name");
                int noOfQuestion = rs.getInt("noOfQuestion");
                int timer = rs.getInt("timer");
                dto.setId(subjectId);
                dto.setName(name);
                dto.setNoOfQuestion(noOfQuestion);
                dto.setTimer(timer);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
