package it.quix.academy.qborrow.web.converter;

import java.util.Map;
import java.lang.Exception;
import java.math.BigInteger;
import java.util.Date;

import javax.annotation.Resource;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.model.Oggetto;
import it.quix.academy.qborrow.core.model.QborrowUserContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.ValueStack;
import it.quix.framework.web.converter.AbstractTypeConverter;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * Converter class for the Oggetto model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class OggettoConverter extends AbstractTypeConverter<java.lang.Integer> {

    /**
     * QborrowManager injected by Spring
     */
    @Resource(name = "qborrowManager")
    private QborrowManager qborrowManager;

    @SuppressWarnings("unchecked")
    @Override
    public Object convertFromString(Map context, String[] arg1, Class arg2) {
        try {
            if (arg1 == null || arg1.length == 0 || arg1[0] == null || arg1[0].equals(""))
                return null;
            if (!arg2.equals(Oggetto.class))
                throw new TypeConversionException();
            ValueStack stack = ActionContext.getContext().getValueStack();
            // QborrowUserContext uc = (QborrowUserContext) stack.findValue("userContext");

            String[] pks = arg1[0].split("[|]");
            Integer id = (java.lang.Integer) convertFromString(pks[0], Integer.class);
            Oggetto oggetto = getQborrowManager().getOggetto(id);

            if (oggetto == null) {
                throw new TypeConversionException("Error on OggettoConverter. Oggetto with key (id serialized) " + arg1[0] + " not found.");
            }
            return oggetto;
        } catch (Exception e) {
            throw new TypeConversionException("Error on OggettoConverter.", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String convertToString(Map context, Object obj) {
        Oggetto oggetto = (Oggetto) obj;
        try {
            StringBuilder pk = new StringBuilder();
            pk.append(convertToString(oggetto.getId()));
            return pk.toString();
        } catch (Exception e) {
            throw new TypeConversionException(e);
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

}
