/*
 * Created by Abhiroop Singh on 2017.09.28  * 
 * Copyright © 2017 Abhiroop Singh. All rights reserved. * 
 */
package com.interact.Session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abhi
 */
@Stateless
public class SessionsFacade extends AbstractFacade<Sessions> {

    @PersistenceContext(unitName = "InteractPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SessionsFacade() {
        super(Sessions.class);
    }

    public List<Sessions> sessionQuery(String searchString) {
        // Place the % wildcard before and after the search string to search for it anywhere in the company name 
        searchString = "%" + searchString + "%";
        // Conduct the search in a case-insensitive manner and return the results in a list.
        return getEntityManager().createQuery(
                "SELECT s FROM Sessions s WHERE s.id = :searchString").
                setParameter("searchString", searchString).getResultList();
    }

}
