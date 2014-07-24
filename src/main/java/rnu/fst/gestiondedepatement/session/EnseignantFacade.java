/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rnu.fst.gestiondedepatement.entity.Enseignant;

/**
 *
 * @author moez
 */
@Stateless
public class EnseignantFacade extends AbstractFacade<Enseignant> {
    @PersistenceContext(unitName = "persistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnseignantFacade() {
        super(Enseignant.class);
    }
    
}
