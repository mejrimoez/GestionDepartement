package rnu.fst.gestiondedepatement.controller;

import rnu.fst.gestiondedepatement.entity.Choixenseignant;
import rnu.fst.gestiondedepatement.controller.util.JsfUtil;
import rnu.fst.gestiondedepatement.controller.util.JsfUtil.PersistAction;
import rnu.fst.gestiondedepatement.session.ChoixenseignantFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("choixenseignantController")
@SessionScoped
public class ChoixenseignantController implements Serializable {

    @EJB
    private rnu.fst.gestiondedepatement.session.ChoixenseignantFacade ejbFacade;
    private List<Choixenseignant> items = null;
    private Choixenseignant selected;

    public ChoixenseignantController() {
    }

    public Choixenseignant getSelected() {
        return selected;
    }

    public void setSelected(Choixenseignant selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getChoixenseignantPK().setCin(selected.getEnseignant().getCin());
        selected.getChoixenseignantPK().setId(selected.getEtudiant().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setChoixenseignantPK(new rnu.fst.gestiondedepatement.entity.ChoixenseignantPK());
    }

    private ChoixenseignantFacade getFacade() {
        return ejbFacade;
    }

    public Choixenseignant prepareCreate() {
        selected = new Choixenseignant();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ChoixenseignantCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ChoixenseignantUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ChoixenseignantDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Choixenseignant> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
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

    public Choixenseignant getChoixenseignant(rnu.fst.gestiondedepatement.entity.ChoixenseignantPK id) {
        return getFacade().find(id);
    }

    public List<Choixenseignant> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Choixenseignant> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Choixenseignant.class)
    public static class ChoixenseignantControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ChoixenseignantController controller = (ChoixenseignantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "choixenseignantController");
            return controller.getChoixenseignant(getKey(value));
        }

        rnu.fst.gestiondedepatement.entity.ChoixenseignantPK getKey(String value) {
            rnu.fst.gestiondedepatement.entity.ChoixenseignantPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new rnu.fst.gestiondedepatement.entity.ChoixenseignantPK();
            key.setId(Integer.parseInt(values[0]));
            key.setCin(values[1]);
            return key;
        }

        String getStringKey(rnu.fst.gestiondedepatement.entity.ChoixenseignantPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getId());
            sb.append(SEPARATOR);
            sb.append(value.getCin());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Choixenseignant) {
                Choixenseignant o = (Choixenseignant) object;
                return getStringKey(o.getChoixenseignantPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Choixenseignant.class.getName()});
                return null;
            }
        }

    }

}
