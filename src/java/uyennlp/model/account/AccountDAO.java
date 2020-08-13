/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.account;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import uyennlp.util.DBHelper;

/**
 *
 * @author HP
 */
public class AccountDAO implements Serializable{

    Connection con;
    PreparedStatement stm;
    ResultSet rs;

    public AccountDAO() {
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

    public boolean checkLogin(AccountDTO dto) throws SQLException, NamingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        boolean result = false;
        try {
            String sql = "SELECT email "
                    + "FROM tblAccount "
                    + "WHERE email = ? AND password = ?";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getEmail());
            stm.setString(2, dto.getPassword());
            rs = stm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public AccountDTO getUser(String username) throws SQLException, NamingException {
        AccountDTO dto = null;
        try {
            String sql = "SELECT name, role, status "
                    + "FROM tblAccount "
                    + "WHERE email = ?";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                dto = new AccountDTO();
                String name = rs.getString("name");
                String role = rs.getString("role");
                String status = rs.getString("status");
                dto.setEmail(username);
                dto.setName(name);
                dto.setRole(role);
                dto.setStatus(status);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

     public boolean createAccount(AccountDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "INSERT INTO tblAccount"
                    + "(email, name, password, role, status) "
                    + "VALUES (?,?,?,?,?)";
            con = DBHelper.makeConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getEmail());
            stm.setString(2, dto.getName());
            stm.setString(3, dto.getPassword());
            stm.setString(4, dto.getRole());
            stm.setString(5, dto.getStatus());

            result = stm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

}
