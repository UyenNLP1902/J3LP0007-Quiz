/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.subject;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class SubjectDTO implements Serializable{

    private String id;
    private String name;
    private int noOfQuestion;
    private int timer;

    public SubjectDTO() {
    }

    public SubjectDTO(String id, String name, int noOfQuestion, int timer) {
        this.id = id;
        this.name = name;
        this.noOfQuestion = noOfQuestion;
        this.timer = timer;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(int noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

}
