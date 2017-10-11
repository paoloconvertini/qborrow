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
import it.quix.academy.qborrow.core.model.Prestito;
import it.quix.academy.qborrow.core.model.*;
import it.quix.academy.qborrow.core.search.PrestitoSearch;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.manager.QborrowException;
// import it.quix.academy.qborrow.core.handler.PrestitoHandler;
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
 * Action class for the Prestito model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class PrestitoAbstractManagerAction extends QborrowManagerAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Log
     */
    private static Log log = LogFactory.getLog(PrestitoAbstractManagerAction.class);

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
    private PrestitoSearch prestitoSearch = new PrestitoSearch();

    /**
     * Edit model
     */
    private Prestito prestito = null;

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
                log.info("Reset the prestitoSearch model");
            }
            prestitoSearch = new PrestitoSearch();
            prestitoSearch.setRowPerPage(10);
        }
        // if first call the search model must be initialized
        if (prestitoSearch == null) {
            prestitoSearch = new PrestitoSearch();
            prestitoSearch.setRowPerPage(10);
            if (log.isInfoEnabled()) {
                log.info("prestitoSearch initialized");
            }
        }
    }

    /**
     * List task.
     * This method find prestito that satisfy search filters.
     * 
     * @throws QborrowException if an error occurs
     */
    public String list() throws QborrowException {
        try {
            // Validate the search model
            getQborrowManager().validatePrestitoSearch(prestitoSearch);
            // Perform count of record that satisfy search filters
            long total = getQborrowManager().countPrestito(prestitoSearch);
            // If there are results ...
            List<Prestito> prestitoList = null;
            if (total > 0) {
                // Search the results to display
                do {
                    prestitoList = getQborrowManager().getPrestitoList(prestitoSearch);
                    if (prestitoList.isEmpty() && prestitoSearch.getPage() > 0) {
                        if (log.isInfoEnabled()) {
                            log.info("The request page " + prestitoSearch.getPage() + " was empty."
                                + ((prestitoSearch.getPage() > 1) ? " Try with page " + (prestitoSearch.getPage() - 1) + "." : ""));
                        }
                        prestitoSearch.setPage(prestitoSearch.getPage() - 1);
                    }
                } while (0 < prestitoSearch.getPage() && prestitoList.isEmpty());
            }

            // Compose the response
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("list", prestitoList);
            map.put("count", total);
            return manageSerialize(map, new JSONSerializer().include("list"));
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "list");
        } catch (Exception e) {
            return manageException("Error on list Prestito", e);
        }
    }

    /**
     * XLS Export task.
     * This method export all prestito in xls format.
     * 
     * @throws Exception if an error occurs
     */
    public String exportXls() throws Exception {
        int page = prestitoSearch.getPage();
        prestitoSearch.setPage(-1);
        // retrieve the list from DB
        List<Prestito> prestitoList = getQborrowManager().getPrestitoList(prestitoSearch);
        long countPrestito = getQborrowManager().countPrestito(prestitoSearch);

        // create the file
        File tmpXlsFile = File.createTempFile("PrestitoList", ".xls");
        // populate the file
        ExcelComposer c = new ExcelComposer() {

            @Override
            protected Object translate(Field field, Object value) {
                return super.translate(field, value);
            }
        };

        c.create(getUserContext(), prestitoList, Prestito.class, "excel.prestito", "excel.prestito.name", 1, 2, countPrestito, prestitoSearch, tmpXlsFile);
        prestitoSearch.setPage(page);
        return sendFileToClient(tmpXlsFile, "application/vnd.ms-excel");
    }

    /**
     * Edit task.
     * This method edit one prestito instance.
     */
    public String edit() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            prestito = getQborrowManager().getPrestito(prestito.getSoggettoBeneficiario_username(), prestito.getOggettoPrestato_id());
            return manageSerialize(prestito);
        } catch (Exception e) {
            return manageException("Error on edit Prestito", e);
        }
    }

    /**
     * Save task.
     * This method save one prestito instance.
     */
    public String save() {
        if (prestito == null) {
            // New Prestito and all fields are empty. Create a new empty Prestito to avoid NPE on validators.
            prestito = new Prestito();
        }
        try {
            getQborrowManager().savePrestito(prestito);
            return manageOkMessage();
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "save");
        } catch (Exception e) {
            return manageException("Error on save Prestito", e);
        }
    }

    /**
     * Delete task.
     * This method delete one prestito instance.
     */
    public String delete() {
        try {
            getQborrowManager().deletePrestito(prestito.getSoggettoBeneficiario_username(), prestito.getOggettoPrestato_id());
            return manageOkMessage();
        } catch (Exception e) {
            return manageException("Error on delete Prestito", e);
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
     * @see #prestitoSearch
     * @return the prestitoSearch
     */
    public PrestitoSearch getPrestitoSearch() {
        return prestitoSearch;
    }

    /**
     * @see #prestitoSearch
     * @param prestitoSearch the prestitoSearch to set
     */
    public void setPrestitoSearch(PrestitoSearch prestitoSearch) {
        this.prestitoSearch = prestitoSearch;
    }

    /**
     * @see #prestito
     * @return the prestito
     */
    public Prestito getPrestito() {
        return prestito;
    }

    /**
     * @see #prestito
     * @param prestito the prestito to set
     */
    public void setPrestito(Prestito prestito) {
        this.prestito = prestito;
    }
}
