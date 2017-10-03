/*
 * Created by Abhiroop Singh on 2017.10.03  * 
 * Copyright © 2017 Abhiroop Singh. All rights reserved. * 
 */
package com.interact.Session;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Abhi
 */
@Named("studentViewController")
@SessionScoped
public class StudentViewController implements Serializable {

    @Inject
    private QuestionAnswersController questionAnswersController;
    @Inject
    private SessionsController sessionsController;

    private List<QuestionAnswers> sessionQuestions = null;
    private int questionNum = 0;

    public StudentViewController() {
    }

    public String nextIsClicked() {
        questionNum++;

        if (questionNum >= sessionQuestions.size()) {
            sessionQuestions = null;
            sessionsController.setJoinKey(null);
            return "index?faces-redirect=true";
        } else {
            return "JoinSession?faces-redirect=true";
        }

    }

    public String previousIsClicked() {
        if (questionNum != 0) {
            questionNum--;
        }

        return "JoinSession?faces-redirect=true";

    }

    public String getQuestion() {
        if (sessionQuestions == null) {
            sessionQuestions = questionAnswersController.getSessionItems();
            questionNum = 0;
        }
        System.out.println("session questions: " + sessionQuestions);

        String question = sessionQuestions.get(questionNum).getQuestion();

        return question;

    }

}
