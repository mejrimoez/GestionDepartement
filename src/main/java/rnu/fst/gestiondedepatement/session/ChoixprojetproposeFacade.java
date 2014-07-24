/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rnu.fst.gestiondedepatement.entity.Choixprojetpropose;

/**
 *
 * @author moez
 */
@Stateless
public class ChoixprojetproposeFacade extends AbstractFacade<Choixprojetpropose> {
    @PersistenceContext(unitName = "persistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChoixprojetproposeFacade() {
        super(Choixprojetpropose.class);
    }
    
}
