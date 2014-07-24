package rnu.fst.gestiondedepatement.controller;

import rnu.fst.gestiondedepatement.entity.Compte;
import rnu.fst.gestiondedepatement.controller.util.JsfUtil;
import rnu.fst.gestiondedepatement.controller.util.JsfUtil.PersistAction;
import rnu.fst.gestiondedepatement.session.CompteFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.servlet.http.HttpSession;

@Named("compteController")
@SessionScoped
public class CompteController implements Serializable {

    @EJB
    private rnu.fst.gestiondedepatement.session.CompteFacade ejbFacade;
    private List<Compte> items = null;
    private Compte selected;
    private Compte current;

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

    public Compte getCurrent() {
        return current;
    }

    public void setCurrent(Compte current) {
        this.current = current;
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
                            "Vérifier votre CIN et votre mot de passe ! "));
            return null;
        } else {
            switch (c.getRole()) {
                case "etudiant":
                    return "pretty:EtudiantIndex";
                case "enseignant":
                    return "pretty:EnseignantIndex";
                case "administratif":
                    return "pretty:AdministratifIndex";
                case "administrateur":
                    return "pretty:AdministrateurIndex";
                default:
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur",
                                    "Vérifier votre CIN et votre mot de passe ! "));
                    return null;
            }
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
