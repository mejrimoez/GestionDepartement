/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rnu.fst.gestiondedepatement.entity.Anneeuniversitaire;

/**
 *
 * @author moez
 */
@Stateless
public class AnneeuniversitaireFacade extends AbstractFacade<Anneeuniversitaire> {
    @PersistenceContext(unitName = "persistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnneeuniversitaireFacade() {
        super(Anneeuniversitaire.class);
    }
    
}
