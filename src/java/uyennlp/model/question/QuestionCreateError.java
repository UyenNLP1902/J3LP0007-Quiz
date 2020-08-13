/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uyennlp.model.question;

/**
 *
 * @author HP
 */
public class QuestionCreateError {

    private String questionIsEmpty;
    private String answerIsEmpty;
    private String noCorrectAnswer;

    public QuestionCreateError() {
    }

    public QuestionCreateError(String questionIsEmpty, String answerIsEmpty, String noCorrectAnswer) {
        this.questionIsEmpty = questionIsEmpty;
        this.answerIsEmpty = answerIsEmpty;
        this.noCorrectAnswer = noCorrectAnswer;
    }

    public String getQuestionIsEmpty() {
        return questionIsEmpty;
    }

    public void setQuestionIsEmpty(String questionIsEmpty) {
        this.questionIsEmpty = questionIsEmpty;
    }

    public String getAnswerIsEmpty() {
        return answerIsEmpty;
    }

    public void setAnswerIsEmpty(String answerIsEmpty) {
        this.answerIsEmpty = answerIsEmpty;
    }

    public String getNoCorrectAnswer() {
        return noCorrectAnswer;
    }

    public void setNoCorrectAnswer(String noCorrectAnswer) {
        this.noCorrectAnswer = noCorrectAnswer;
    }

}
