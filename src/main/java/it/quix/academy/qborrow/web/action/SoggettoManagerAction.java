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

import it.quix.framework.core.validation.InvalidConstraintImpl;
import it.quix.framework.core.validation.api.InvalidConstraint;
import it.quix.framework.core.validation.exception.ValidationException;
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.core.search.SoggettoSearch;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.manager.QborrowException;
// import it.quix.academy.qborrow.core.handler.SoggettoHandler;
import it.quix.academy.qborrow.web.action.generated.SoggettoAbstractManagerAction;
import it.quix.framework.web.annotation.QwScope;
import it.quix.framework.web.result.IncludeResultAware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * Action class for the Soggetto model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class SoggettoManagerAction extends SoggettoAbstractManagerAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Log
     */
    private static Log log = LogFactory.getLog(SoggettoManagerAction.class);

    /**
     * Edit task.
     * This method edit one soggetto instance.
     */
    public String editWithCompleanno() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            Soggetto soggetto = getQborrowManager().getSoggettoWithCompleanno(getSoggetto().getUsername());
            return manageSerialize(soggetto);
        } catch (Exception e) {
            return manageException("Error on edit Soggetto", e);
        }
    }

    /**
     * Save task.
     * This method save one soggetto instance.
     */
    public String saveWithCompleanno() {
        if (getSoggetto() == null) {
            // New Soggetto and all fields are empty. Create a new empty Soggetto to avoid NPE on validators.
            setSoggetto(new Soggetto());
        }
        try {
            getQborrowManager().saveSoggettoWithCompleanno(getSoggetto());
            return manageOkMessage();
        } catch (ValidationException e) {
            return manageValidationError(e.getInvalidConstraints(), "save");
        } catch (Exception e) {
            return manageException("Error on save Soggetto", e);
        }
    }

}
