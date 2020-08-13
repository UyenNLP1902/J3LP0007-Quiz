/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.answer;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class AnswerDTO implements Serializable, Comparable<AnswerDTO> {

    private int id;
    private int questionId;
    private String content;
    private boolean isCorrect;

    public AnswerDTO() {
    }

    public AnswerDTO(int id, int questionId, String content, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public int compareTo(AnswerDTO o) {
        Integer thisId = this.id;
        Integer thatId = o.id;
        return thisId.compareTo(thatId);
    }

}
