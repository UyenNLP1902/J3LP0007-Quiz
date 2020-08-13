/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.account;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class AccountCreateError implements Serializable{

    private String emailTemplateErr;
    private String nameLengthErr;
    private String passwordLengthErr;
    private String confirmNotMatched;
    private String emailIsExisted;

    public AccountCreateError() {
    }

    public String getEmailTemplateErr() {
        return emailTemplateErr;
    }

    public void setEmailTemplateErr(String emailTemplateErr) {
        this.emailTemplateErr = emailTemplateErr;
    }

    public String getNameLengthErr() {
        return nameLengthErr;
    }

    public void setNameLengthErr(String nameLengthErr) {
        this.nameLengthErr = nameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

}
