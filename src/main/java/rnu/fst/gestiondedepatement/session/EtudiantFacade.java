/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import rnu.fst.gestiondedepatement.entity.Etudiant;

/**
 *
 * @author moez
 */
@Stateless
public class EtudiantFacade extends AbstractFacade<Etudiant> {
    @PersistenceContext(unitName = "persistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtudiantFacade() {
        super(Etudiant.class);
    }
    
    public Etudiant findByCin(String cin)
    {
        Etudiant e;
        try {
            e = (Etudiant) getEntityManager().createNamedQuery("Etudiant.findByCin").getSingleResult();
            return e;
        } catch (Exception ex) {
            return null;
        }    
    }
    
}
