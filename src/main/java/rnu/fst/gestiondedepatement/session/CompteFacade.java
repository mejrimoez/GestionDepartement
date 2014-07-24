/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rnu.fst.gestiondedepatement.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import rnu.fst.gestiondedepatement.entity.Compte;

/**
 *
 * @author moez
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {
    @PersistenceContext(unitName = "persistanceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }
    
    public Compte findByCredentials(String cin,String passe)
    {
        try{
        Query q = getEntityManager().createNamedQuery("Compte.findByCredentials");
        q.setParameter("motdepass", passe);
        q.setParameter("cin", cin);
        Compte c = (Compte) q.getSingleResult();
        return c;
        }catch(Exception e){
            return null;
        }
    }
}
