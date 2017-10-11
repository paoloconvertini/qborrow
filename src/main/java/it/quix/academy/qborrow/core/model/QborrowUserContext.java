package it.quix.academy.qborrow.core.model;

import java.util.ArrayList;

import it.quix.framework.core.model.Language;
import it.quix.framework.core.model.Organization;
import it.quix.framework.core.model.Role;
import it.quix.framework.core.model.User;
import it.quix.framework.core.model.UserContext;
import it.quix.puma.core.PumaFinderException;

/**
 * Inserire all'interno di questa classe le proprieta' e i metodi
 * che si vogliono associare all'utente dell'applicazione
 */
public class QborrowUserContext extends UserContext {

    private static final long serialVersionUID = 1L;

    public QborrowUserContext() {
        super();
    }

    @Override
    public Boolean authCodeLogin(String arg0) throws PumaFinderException {
        return null;
    }

    @Override
    public String getCodCompany() {
        return null;
    }

    @Override
    public Language getLanguageForSysAttribute() {
        return null;
    }

    @Override
    public Organization getOrganizationForSysSysAttribute() {
        return null;
    }

    @Override
    public void login(String user) throws Exception {

        final String userId = user;
        User userModel = new User() {

            public String getPassword() {
                return null;
            }

            public String getName() {
                return userId;
            }

            public String getDn() {
                return userId;
            }
        };
        super.login(userModel, new ArrayList<Role>());
    }

}
