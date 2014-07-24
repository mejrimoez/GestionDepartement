package rnu.fst.gestiondedepatement.controller;

import rnu.fst.gestiondedepatement.entity.Choixprojetpropose;
import rnu.fst.gestiondedepatement.controller.util.JsfUtil;
import rnu.fst.gestiondedepatement.controller.util.JsfUtil.PersistAction;
import rnu.fst.gestiondedepatement.session.ChoixprojetproposeFacade;

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

@Named("choixprojetproposeController")
@SessionScoped
public class ChoixprojetproposeController implements Serializable {

    @EJB
    private rnu.fst.gestiondedepatement.session.ChoixprojetproposeFacade ejbFacade;
    private List<Choixprojetpropose> items = null;
    private Choixprojetpropose selected;

    public ChoixprojetproposeController() {
    }

    public Choixprojetpropose getSelected() {
        return selected;
    }

    public void setSelected(Choixprojetpropose selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getChoixprojetproposePK().setId(selected.getProposeprojet().getId());
        selected.getChoixprojetproposePK().setEtuid(selected.getEtudiant().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setChoixprojetproposePK(new rnu.fst.gestiondedepatement.entity.ChoixprojetproposePK());
    }

    private ChoixprojetproposeFacade getFacade() {
        return ejbFacade;
    }

    public Choixprojetpropose prepareCreate() {
        selected = new Choixprojetpropose();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ChoixprojetproposeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ChoixprojetproposeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ChoixprojetproposeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Choixprojetpropose> getItems() {
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

    public Choixprojetpropose getChoixprojetpropose(rnu.fst.gestiondedepatement.entity.ChoixprojetproposePK id) {
        return getFacade().find(id);
    }

    public List<Choixprojetpropose> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Choixprojetpropose> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Choixprojetpropose.class)
    public static class ChoixprojetproposeControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ChoixprojetproposeController controller = (ChoixprojetproposeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "choixprojetproposeController");
            return controller.getChoixprojetpropose(getKey(value));
        }

        rnu.fst.gestiondedepatement.entity.ChoixprojetproposePK getKey(String value) {
            rnu.fst.gestiondedepatement.entity.ChoixprojetproposePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new rnu.fst.gestiondedepatement.entity.ChoixprojetproposePK();
            key.setEtuid(Integer.parseInt(values[0]));
            key.setId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(rnu.fst.gestiondedepatement.entity.ChoixprojetproposePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getEtuid());
            sb.append(SEPARATOR);
            sb.append(value.getId());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Choixprojetpropose) {
                Choixprojetpropose o = (Choixprojetpropose) object;
                return getStringKey(o.getChoixprojetproposePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Choixprojetpropose.class.getName()});
                return null;
            }
        }

    }

}
