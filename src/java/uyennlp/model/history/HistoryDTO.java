/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.history;

import java.sql.Timestamp;

/**
 *
 * @author HP
 */
public class HistoryDTO {

    private int id;
    private String email;
    private String subjectId;
    private double score;
    private Timestamp dateQuiz;
    private int numberOfCorrect;

    public HistoryDTO() {
    }

    public HistoryDTO(int id, String email, String subjectId, double score, Timestamp dateQuiz, int numberOfCorrect) {
        this.id = id;
        this.email = email;
        this.subjectId = subjectId;
        this.score = score;
        this.dateQuiz = dateQuiz;
        this.numberOfCorrect = numberOfCorrect;
    }

    public int getNumberOfCorrect() {
        return numberOfCorrect;
    }

    public void setNumberOfCorrect(int numberOfCorrect) {
        this.numberOfCorrect = numberOfCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Timestamp getDateQuiz() {
        return dateQuiz;
    }

    public void setDateQuiz(Timestamp dateQuiz) {
        this.dateQuiz = dateQuiz;
    }

}
