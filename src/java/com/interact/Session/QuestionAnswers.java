/*
 * Created by Abhiroop Singh on 2017.09.28  * 
 * Copyright © 2017 Abhiroop Singh. All rights reserved. * 
 */
package com.interact.Session;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abhi
 */
@Entity
@Table(name = "QuestionAnswers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionAnswers.findAll", query = "SELECT q FROM QuestionAnswers q")
    , @NamedQuery(name = "QuestionAnswers.findById", query = "SELECT q FROM QuestionAnswers q WHERE q.id = :id")
    , @NamedQuery(name = "QuestionAnswers.findByQuestion", query = "SELECT q FROM QuestionAnswers q WHERE q.question = :question")
    , @NamedQuery(name = "QuestionAnswers.findByQuestionType", query = "SELECT q FROM QuestionAnswers q WHERE q.questionType = :questionType")
    , @NamedQuery(name = "QuestionAnswers.findByAnswer", query = "SELECT q FROM QuestionAnswers q WHERE q.answer = :answer")
    , @NamedQuery(name = "QuestionAnswers.findByAnswerChoices", query = "SELECT q FROM QuestionAnswers q WHERE q.answerChoices = :answerChoices")})
public class QuestionAnswers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3000)
    @Column(name = "question")
    private String question;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3000)
    @Column(name = "questionType")
    private String questionType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3000)
    @Column(name = "answer")
    private String answer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3000)
    @Column(name = "answerChoices")
    private String answerChoices;
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    @ManyToOne
    private Sessions sessionId;

    public QuestionAnswers() {
    }

    public QuestionAnswers(Integer id) {
        this.id = id;
    }

    public QuestionAnswers(Integer id, String question, String questionType, String answer, String answerChoices) {
        this.id = id;
        this.question = question;
        this.questionType = questionType;
        this.answer = answer;
        this.answerChoices = answerChoices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerChoices() {
        return answerChoices;
    }

    public void setAnswerChoices(String answerChoices) {
        this.answerChoices = answerChoices;
    }

    public Sessions getSessionId() {
        return sessionId;
    }

    public void setSessionId(Sessions sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionAnswers)) {
            return false;
        }
        QuestionAnswers other = (QuestionAnswers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.interact.Session.QuestionAnswers[ id=" + id + " ]";
    }
    
}
