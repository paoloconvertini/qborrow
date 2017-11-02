package it.quix.academy.qborrow.web.action.generated;

import java.util.HashSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Color;
import java.awt.Font;

import org.zefer.pd4ml.PD4ML;
import org.zefer.pd4ml.PD4PageMark;

import javax.annotation.Resource;

import it.quix.framework.core.composer.ExcelComposer;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.handler.SysAttributeHandler;
import it.quix.framework.core.model.AttributeView;
import it.quix.framework.core.validation.InvalidConstraintImpl;
import it.quix.framework.core.validation.api.InvalidConstraint;
import it.quix.framework.core.validation.exception.ValidationException;
import it.quix.academy.qborrow.core.model.*;
import it.quix.academy.qborrow.core.search.OggettoSearch;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.manager.QborrowException;
// import it.quix.academy.qborrow.core.handler.OggettoHandler;
import it.quix.academy.qborrow.web.action.QborrowManagerAction;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.util.Date;
import java.lang.reflect.Field;
import java.math.*;

/**
 * Action class for the Oggetto model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class OggettoAbstractManagerAction extends QborrowManagerAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Log
     */
    private static Log log = LogFactory.getLog(OggettoAbstractManagerAction.class);

    /**
     * QborrowManager injected by Spring
     */
    @Resource(name = "qborrowManager")
    private QborrowManager qborrowManager;

    /**
     * SysAttributeHandler injected by Spring
     */
    @Resource(name = "sysAttributeHandler")
    private SysAttributeHandler sysAttributeHandler;

    /**
     * Search filters
     */
    private OggettoSearch oggettoSearch = new OggettoSearch();

    /**
     * Edit model
     */
    private Oggetto oggetto = null;

    /**
     * Method for prepare the list task called if the PrepareInterceptor is applied to the ActionConfig.
     * This method run after injection of value form Session and before injection of parameter from querystring.
     * This method is very useful for any situation where you need to ensure some logic runs before the actual
     * execute method runs.
     */
    public void prepareList() {
        // Read reset parameter. This method is invoked before injection of parameter,
        // this parameter must be read by ActionContext class.
        String[] reset = (String[]) ActionContext.getContext().getParameters().get("reset");
        // if reset = true then reset the search model
        if (reset != null && reset.length > 0 && reset[0].equals("true")) {
            if (log.isInfoEnabled()) {
                log.info("Reset the oggettoSearch model");
            }
            oggettoSearch = new OggettoSearch();
            oggettoSearch.setRowPerPage(10);
        }
        // if first call the search model must be initialized
        if (oggettoSearch == null) {
            oggettoSearch = new OggettoSearch();
            oggettoSearch.setRowPerPage(10);
            if (log.isInfoEnabled()) {
                log.info("oggettoSearch initialized");
            }
        }
    }

    /**
     * List task.
     * This method find oggetto that satisfy search filters.
     * 
     * @throws QborrowException if an error occurs
     */
    public String list() throws QborrowException {
        try {
            // Validate the search model
            getQborrowManager().validateOggettoSearch(oggettoSearch);
            // Perform count of record that satisfy search filters
            long total = getQborrowManager().countOggetto(oggettoSearch);
            // If there are results ...
            List<Oggetto> oggettoList = null;
            if (total > 0) {
                // Search the results to display
                do {
                    oggettoList = getQborrowManager().getOggettoList(oggettoSearch);
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
     * XLS Export task.
     * This method export all oggetto in xls format.
     * 
     * @throws Exception if an error occurs
     */
    public String exportXls() throws Exception {
        int page = oggettoSearch.getPage();
        oggettoSearch.setPage(-1);
        // retrieve the list from DB
        List<Oggetto> oggettoList = getQborrowManager().getOggettoList(oggettoSearch);
        long countOggetto = getQborrowManager().countOggetto(oggettoSearch);

        // create the file
        File tmpXlsFile = File.createTempFile("OggettoList", ".xls");
        // populate the file
        ExcelComposer c = new ExcelComposer() {

            @Override
            protected Object translate(Field field, Object value) {
                if (field.getName().equals("categoria")) {
                    String stringValue = (String) value;
                    try {
                        AttributeView av =
                            sysAttributeHandler.getAttribute("QBO001_categoria", getUserContext().getLanguageForSysAttribute(), getUserContext()
                                .getOrganizationForSysSysAttribute(), stringValue, getUserContext());
                        if (av != null) {
                            return av.getDescription();
                        }
                    } catch (DAOFinderException dfe) {
                        log.debug("Cannot find sysAttribute with type = QBO001_categoria and code = stringValue", dfe);
                    }
                    return stringValue;
                }
                if (field.getName().equals("proprietario")) {
                    String stringValue = "";
                    Soggetto proprietario = (Soggetto) value;
                    if (proprietario != null) {
                        stringValue = String.valueOf(proprietario.getUsername());
                    }
                    return stringValue;
                }
                return super.translate(field, value);
            }
        };

        c.create(getUserContext(), oggettoList, Oggetto.class, "excel.oggetto", "excel.oggetto.name", 1, 2, countOggetto, oggettoSearch, tmpXlsFile);
        oggettoSearch.setPage(page);
        return sendFileToClient(tmpXlsFile, "application/vnd.ms-excel");
    }

    /**
     * Edit task.
     * This method edit one oggetto instance.
     */
    public String edit() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {

            oggetto = getQborrowManager().getOggetto(oggetto.getId());
            List<Prestito> list;
            list = getQborrowManager().getPrestitoListByOggetto(oggetto.getId());

            if (list.size() > 0) {
                Prestito prestito = list.get(0);
                oggetto.setPrestito(prestito);
            }

            return manageSerialize(oggetto);
        } catch (Exception e) {
            return manageException("Error on edit Oggetto", e);
        }
    }

    /**
     * Save task.
     * This method save one oggetto instance.
     */
    public String save() {
        if (oggetto == null) {
            // New Oggetto and all fields are empty. Create a new empty Oggetto to avoid NPE on validators.
            oggetto = new Oggetto();

        }
        try {
            oggetto.setProprietario_username(userContext.getRealUserDn());
            // oggetto.getPrestito().setSoggettoBeneficiario_username(oggetto.getPrestito().getSoggettoBeneficiario().getUsername());;
            // oggetto.getPrestito();
            /*
             * Soggetto soggetto = new Soggetto();
             * soggetto.setUsername(userContext.getRealUserDn());
             * oggetto.setProprietario(soggetto);
             */

            getQborrowManager().saveOggetto(oggetto);
            return manageOkMessage();
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "save");
        } catch (Exception e) {
            return manageException("Error on save Oggetto", e);
        }
    }

    /**
     * Delete task.
     * This method delete one oggetto instance.
     */
    public String delete() {
        try {
            getQborrowManager().deleteOggetto(oggetto.getId());
            return manageOkMessage();
        } catch (Exception e) {
            return manageException("Error on delete Oggetto", e);
        }
    }

    /**
     * @see #qborrowManager
     * @return the qborrowManager
     */
    public QborrowManager getQborrowManager() {
        return qborrowManager;
    }

    /**
     * @see #qborrowManager
     * @param qborrowManager the qborrowManager to set
     */
    public void setQborrowManager(QborrowManager qborrowManager) {
        this.qborrowManager = qborrowManager;
    }

    /**
     * @see #SysAttributeHandler
     * @return the SysAttributeHandler
     */
    public SysAttributeHandler getSysAttributeHandler() {
        return sysAttributeHandler;
    }

    /**
     * @see #SysAttributeHandler
     * @param sysAttributeHandler the SysAttributeHandler to set
     */
    public void setSysAttributeHandler(SysAttributeHandler sysAttributeHandler) {
        this.sysAttributeHandler = sysAttributeHandler;
    }

    /**
     * @see #oggettoSearch
     * @return the oggettoSearch
     */
    public OggettoSearch getOggettoSearch() {
        return oggettoSearch;
    }

    /**
     * @see #oggettoSearch
     * @param oggettoSearch the oggettoSearch to set
     */
    public void setOggettoSearch(OggettoSearch oggettoSearch) {
        this.oggettoSearch = oggettoSearch;
    }

    /**
     * @see #oggetto
     * @return the oggetto
     */
    public Oggetto getOggetto() {
        return oggetto;
    }

    /**
     * @see #oggetto
     * @param oggetto the oggetto to set
     */
    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

}
