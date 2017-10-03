package com.interact.Session;

import com.interact.Session.util.JsfUtil;
import com.interact.Session.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
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

@Named("sessionsController")
@SessionScoped
public class SessionsController implements Serializable {

    @EJB
    private com.interact.Session.SessionsFacade ejbFacade;
    private List<Sessions> items = null;
    private Sessions selected;
    private String joinKey;

    final static char[] candidates = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".
            toCharArray();
    final static int ID_SIZE = 16;

    public SessionsController() {
    }

    public Sessions getSelected() {
        return selected;
    }

    public void setSelected(Sessions selected) {
        this.selected = selected;
    }

    public String initSession() {
        prepareCreate();
        return "StartSession?faces-redirect=true";
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SessionsFacade getFacade() {
        return ejbFacade;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").
                getString("SessionsCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").
                getString("SessionsUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").
                getString("SessionsDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public String getJoinKey() {
        return joinKey;
    }

    public void setJoinKey(String joinKey) {
        this.joinKey = joinKey;
    }

    public List<Sessions> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    
    public List<Sessions> getSessionByKey() {
        items = ejbFacade.sessionQuery(joinKey);
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle(
                            "/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                        null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").
                        getString("PersistenceErrorOccured"));
            }
        }
    }

    public Sessions getSessions(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Sessions> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Sessions> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Sessions.class)
    public static class SessionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext,
                UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SessionsController controller = (SessionsController) facesContext.
                    getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null,
                            "sessionsController");
            return controller.getSessions(getKey(value));
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
        public String getAsString(FacesContext facesContext,
                UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Sessions) {
                Sessions o = (Sessions) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                        "object {0} is of type {1}; expected type: {2}",
                        new Object[]{object, object.getClass().getName(),
                            Sessions.class.getName()});
                return null;
            }
        }

    }
    
    public String join() {
        return "JoinSession?faces-redirect=true";
    }

    private Sessions prepareCreate() {
        selected = new Sessions(generateId());
        initializeEmbeddableKey();
        create();
        return selected;
    }

    private String generateId() {
        Random random = new Random();
        char[] id = new char[ID_SIZE];
        for (int x = 0; x < ID_SIZE; x++) {
            id[x] = candidates[random.nextInt(candidates.length)];
        }
        
        joinKey = new String(id);
        
        return new String(id);
    }
}
