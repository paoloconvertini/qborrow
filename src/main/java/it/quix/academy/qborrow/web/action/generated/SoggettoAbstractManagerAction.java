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
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.core.model.*;
import it.quix.academy.qborrow.core.search.SoggettoSearch;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.manager.QborrowException;
// import it.quix.academy.qborrow.core.handler.SoggettoHandler;
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
 * Action class for the Soggetto model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class SoggettoAbstractManagerAction extends QborrowManagerAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Log
     */
    private static Log log = LogFactory.getLog(SoggettoAbstractManagerAction.class);

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
    private SoggettoSearch soggettoSearch = new SoggettoSearch();

    /**
     * Edit model
     */
    private Soggetto soggetto = null;

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
                log.info("Reset the soggettoSearch model");
            }
            soggettoSearch = new SoggettoSearch();
            soggettoSearch.setRowPerPage(10);
        }
        // if first call the search model must be initialized
        if (soggettoSearch == null) {
            soggettoSearch = new SoggettoSearch();
            soggettoSearch.setRowPerPage(10);
            if (log.isInfoEnabled()) {
                log.info("soggettoSearch initialized");
            }
        }
    }

    /**
     * List task.
     * This method find soggetto that satisfy search filters.
     * 
     * @throws QborrowException if an error occurs
     */
    public String list() throws QborrowException {
        try {
            // Validate the search model
            getQborrowManager().validateSoggettoSearch(soggettoSearch);
            // Perform count of record that satisfy search filters
            long total = getQborrowManager().countSoggetto(soggettoSearch);
            // If there are results ...
            List<Soggetto> soggettoList = null;
            if (total > 0) {
                // Search the results to display
                do {
                    soggettoList = getQborrowManager().getSoggettoList(soggettoSearch);
                    if (soggettoList.isEmpty() && soggettoSearch.getPage() > 0) {
                        if (log.isInfoEnabled()) {
                            log.info("The request page " + soggettoSearch.getPage() + " was empty."
                                + ((soggettoSearch.getPage() > 1) ? " Try with page " + (soggettoSearch.getPage() - 1) + "." : ""));
                        }
                        soggettoSearch.setPage(soggettoSearch.getPage() - 1);
                    }
                } while (0 < soggettoSearch.getPage() && soggettoList.isEmpty());
            }

            // Compose the response
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", soggettoList);
            map.put("count", total);
            return manageSerialize(map, new JSONSerializer().include("list"));
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "list");
        } catch (Exception e) {
            return manageException("Error on list Soggetto", e);
        }
    }

    /**
     * XLS Export task.
     * This method export all soggetto in xls format.
     * 
     * @throws Exception if an error occurs
     */
    public String exportXls() throws Exception {
        int page = soggettoSearch.getPage();
        soggettoSearch.setPage(-1);
        // retrieve the list from DB
        List<Soggetto> soggettoList = getQborrowManager().getSoggettoList(soggettoSearch);
        long countSoggetto = getQborrowManager().countSoggetto(soggettoSearch);

        // create the file
        File tmpXlsFile = File.createTempFile("SoggettoList", ".xls");
        // populate the file
        ExcelComposer c = new ExcelComposer() {

            @Override
            protected Object translate(Field field, Object value) {
                return super.translate(field, value);
            }
        };

        c.create(getUserContext(), soggettoList, Soggetto.class, "excel.soggetto", "excel.soggetto.name", 1, 2, countSoggetto, soggettoSearch, tmpXlsFile);
        soggettoSearch.setPage(page);
        return sendFileToClient(tmpXlsFile, "application/vnd.ms-excel");
    }

    /**
     * Edit task.
     * This method edit one soggetto instance.
     */
    public String edit() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            soggetto = getQborrowManager().getSoggetto(soggetto.getUsername());
            return manageSerialize(soggetto);
        } catch (Exception e) {
            return manageException("Error on edit Soggetto", e);
        }
    }

    /**
     * Save task.
     * This method save one soggetto instance.
     */
    public String save() {
        if (soggetto == null) {
            // New Soggetto and all fields are empty. Create a new empty Soggetto to avoid NPE on validators.
            soggetto = new Soggetto();
        }
        try {
            getQborrowManager().saveSoggetto(soggetto);
            return manageOkMessage();
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "save");
        } catch (Exception e) {
            return manageException("Error on save Soggetto", e);
        }
    }

    /**
     * Delete task.
     * This method delete one soggetto instance.
     */
    public String delete() {
        try {
            getQborrowManager().deleteSoggetto(soggetto.getUsername());
            return manageOkMessage();
        } catch (Exception e) {
            return manageException("Error on delete Soggetto", e);
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
     * @see #soggettoSearch
     * @return the soggettoSearch
     */
    public SoggettoSearch getSoggettoSearch() {
        return soggettoSearch;
    }

    /**
     * @see #soggettoSearch
     * @param soggettoSearch the soggettoSearch to set
     */
    public void setSoggettoSearch(SoggettoSearch soggettoSearch) {
        this.soggettoSearch = soggettoSearch;
    }

    /**
     * @see #soggetto
     * @return the soggetto
     */
    public Soggetto getSoggetto() {
        return soggetto;
    }

    /**
     * @see #soggetto
     * @param soggetto the soggetto to set
     */
    public void setSoggetto(Soggetto soggetto) {
        this.soggetto = soggetto;
    }
}
