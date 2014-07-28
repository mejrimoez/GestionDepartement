package rnu.fst.gestiondedepatement.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import rnu.fst.gestiondedepatement.controller.util.JsfUtil;
import rnu.fst.gestiondedepatement.controller.util.JsfUtil.PersistAction;
import rnu.fst.gestiondedepatement.entity.Compte;
import rnu.fst.gestiondedepatement.entity.Enseignant;
import rnu.fst.gestiondedepatement.entity.Etudiant;
import rnu.fst.gestiondedepatement.session.CompteFacade;
import rnu.fst.gestiondedepatement.session.EnseignantFacade;
import rnu.fst.gestiondedepatement.session.EtudiantFacade;

@Named("compteController")
@SessionScoped
public class CompteController implements Serializable {

    @EJB
    private rnu.fst.gestiondedepatement.session.CompteFacade ejbFacade;
    @EJB
    private rnu.fst.gestiondedepatement.session.EtudiantFacade etudiantFacade;
    @EJB
    private rnu.fst.gestiondedepatement.session.EnseignantFacade enseignantFacade;
    private List<Compte> items = null;
    private Compte selected;
    private Compte current;
    private Etudiant currentEtudiant;
    private Enseignant currentEnseignant;

    public CompteController() {
        current = new Compte();
    }

    public Compte getSelected() {
        return selected;
    }

    public void setSelected(Compte selected) {
        this.selected = selected;
    }

    public CompteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CompteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public EtudiantFacade getEtudiantFacade() {
        return etudiantFacade;
    }

    public void setEtudiantFacade(EtudiantFacade etudiantFacade) {
        this.etudiantFacade = etudiantFacade;
    }

    public EnseignantFacade getEnseignantFacade() {
        return enseignantFacade;
    }

    public void setEnseignantFacade(EnseignantFacade enseignantFacade) {
        this.enseignantFacade = enseignantFacade;
    }

    public Compte getCurrent() {
        return current;
    }

    public void setCurrent(Compte current) {
        this.current = current;
    }

    public Etudiant getCurrentEtudiant() {
        return currentEtudiant;
    }

    public void setCurrentEtudiant(Etudiant currentEtudiant) {
        this.currentEtudiant = currentEtudiant;
    }

    public Enseignant getCurrentEnseignant() {
        return currentEnseignant;
    }

    public void setCurrentEnseignant(Enseignant currentEnseignant) {
        this.currentEnseignant = currentEnseignant;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private CompteFacade getFacade() {
        return ejbFacade;
    }

    public Compte prepareCreate() {
        selected = new Compte();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("CompteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("CompteUpdated"));
    }

    public String updateSelfInfos() throws IOException {
        if (current != null) {
            try {
                getFacade().edit(current);
                JsfUtil.addSuccessMessage("Compte mis à jour avec succés");
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
                JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
        return getIndex();
    }

    public String getIndex() {
        switch (current.getRole()) {
            case "etudiant":
                return "pretty:EtudiantIndex";
            case "enseignant":
                return "pretty:EnseignantIndex";
            case "administratif":
                return "pretty:AdministratifIndex";
            case "administrateur":
                return "pretty:AdministrateurIndex";    
        }
        return "";
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("CompteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Compte> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public String login() {
        Compte c = getFacade().findByCredentials(current.getCin(), current.getMotdepass());
        if (c == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
                            "Votre CIN ou votre mot de passe sont incorrects !"));
            current = new Compte();
            return null;
        } else {
            String reponse = "", role = c.getRole();
            switch (role) {
                case "etudiant":
                    reponse = "pretty:EtudiantIndex";
                    Etudiant et = getEtudiantFacade().findByCin(c.getCin());
                    if (et != null) {
                        currentEtudiant = et;
                    }
                    break;
                case "enseignant":
                    reponse = "pretty:EnseignantIndex";
                    Enseignant ens = getEnseignantFacade().findByCin(c.getCin());
                    if (ens != null) {
                        currentEnseignant = ens;
                    }
                    break;
                case "administratif":
                    reponse = "pretty:AdministratifIndex";
                    break;
                case "administrateur":
                    reponse = "pretty:AdministrateurIndex";
                    break;
            }

            // enregistrement date du derniere connection     
            c.setDateconnection(new Date());
            getFacade().edit(c);

            // chargement de la page 
            if (reponse.equals("")) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
                                "Vous n'êtes pas inscrits dans le système ! Veuiller consulter l'administration"));
                current = new Compte();
                return "pretty:Index";
            }
            current = c;
            return reponse;
        }
    }

    public String deconnect() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        // invalider la session
        ((HttpSession) ec.getSession(true)).invalidate();
        return ("pretty:Index");
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }

    }

    public Compte getCompte(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Compte> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Compte> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Compte.class)
    public static class CompteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CompteController controller = (CompteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "compteController");
            return controller.getCompte(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Compte) {
                Compte o = (Compte) object;
                return getStringKey(o.getCin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Compte.class.getName()});
                return null;
            }
        }

    }

}
