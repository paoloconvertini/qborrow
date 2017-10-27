package it.quix.academy.qborrow.web.action;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Color;
import java.awt.Font;

import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import javax.annotation.Resource;

import flexjson.JSONSerializer;
import it.quix.framework.core.validation.InvalidConstraintImpl;
import it.quix.framework.core.validation.api.InvalidConstraint;
import it.quix.framework.core.validation.exception.ValidationException;
import it.quix.academy.qborrow.core.model.Oggetto;
import it.quix.academy.qborrow.core.search.OggettoSearch;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.manager.QborrowException;
// import it.quix.academy.qborrow.core.handler.OggettoHandler;
import it.quix.academy.qborrow.web.action.generated.OggettoAbstractManagerAction;
import it.quix.framework.web.annotation.QwScope;
import it.quix.framework.web.result.IncludeResultAware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * Action class for the Oggetto model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class OggettoManagerAction extends OggettoAbstractManagerAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Log
     */
    private static Log log = LogFactory.getLog(OggettoManagerAction.class);

    /**
     * Search filters
     */
    private OggettoSearch oggettoSearch = new OggettoSearch();

    private List<Oggetto> mieiOggettiStruts = new ArrayList<Oggetto>();

    private String search;

    /**
     * Pagina lista miei oggetti
     * 
     * @return
     */
    public String mieiOggetti() {
        return "mieiOggetti";
    }

    /**
     * Metodo di lista che torna solo i miei oggetti.
     * This method find oggetto that satisfy search filters.
     * 
     * @throws QborrowException if an error occurs
     */
    public String listMieiOggetti() throws QborrowException {
        try {
            log.debug("il mio username è " + getUserContext().getRealUserDn());
            oggettoSearch.setProprietario_username(getUserContext().getRealUserDn());
            // Validate the search model
            getQborrowManager().validateOggettoSearch(oggettoSearch);
            // Perform count of record that satisfy search filters
            long total = getQborrowManager().countMieiOggetti(oggettoSearch);
            // If there are results ...
            List<Oggetto> oggettoList = null;
            if (total > 0) {
                // Search the results to display
                do {
                    oggettoList = getQborrowManager().getMieiOggettiList(oggettoSearch);
                    if (oggettoList.isEmpty() && oggettoSearch.getPage() > 0) {
                        if (log.isInfoEnabled()) {
                            log.info("The request page " + oggettoSearch.getPage() + " was empty."
                                + ((oggettoSearch.getPage() > 1) ? " Try with page " + (oggettoSearch.getPage() - 1) + "." : ""));
                        }
                        oggettoSearch.setPage(oggettoSearch.getPage() - 1);
                    }
                } while (0 < oggettoSearch.getPage() && oggettoList.isEmpty());
            }

            // Compose the response
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", oggettoList);
            map.put("count", total);
            return manageSerialize(map, new JSONSerializer().include("list"));
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "list");
        } catch (Exception e) {
            return manageException("Error on list Oggetto", e);
        }
    }

    /**
     * Metodo di lista che torna solo i miei oggetti.
     * This method find oggetto that satisfy search filters.
     * 
     * @throws QborrowException if an error occurs
     */
    public String listMieiOggettiStruts() throws QborrowException {
        oggettoSearch = new OggettoSearch();
        oggettoSearch.setPage(0);
        oggettoSearch.setRowPerPage(10);
        mieiOggettiStruts = getQborrowManager().getMieiOggettiList(oggettoSearch);
        return "listMieiOggettiStruts";
    }

    /**
     * @return the oggettoSearch
     */
    public OggettoSearch getOggettoSearch() {
        return oggettoSearch;
    }

    /**
     * @param oggettoSearch the oggettoSearch to set
     */
    public void setOggettoSearch(OggettoSearch oggettoSearch) {
        this.oggettoSearch = oggettoSearch;
    }

    /**
     * @return the mieiOggettiStruts
     */
    public List<Oggetto> getMieiOggettiStruts() {
        return mieiOggettiStruts;
    }

    /**
     * @param mieiOggettiStruts the mieiOggettiStruts to set
     */
    public void setMieiOggettiStruts(List<Oggetto> mieiOggettiStruts) {
        this.mieiOggettiStruts = mieiOggettiStruts;
    }

    /**
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }

}
