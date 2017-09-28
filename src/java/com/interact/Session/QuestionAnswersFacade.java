/*
 * Created by Abhiroop Singh on 2017.09.28  * 
 * Copyright © 2017 Abhiroop Singh. All rights reserved. * 
 */
package com.interact.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abhi
 */
@Stateless
public class QuestionAnswersFacade extends AbstractFacade<QuestionAnswers> {

    @PersistenceContext(unitName = "InteractPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionAnswersFacade() {
        super(QuestionAnswers.class);
    }
    
}
