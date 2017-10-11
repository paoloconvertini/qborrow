package it.quix.academy.qborrow.web.converter;

import java.util.Map;
import java.lang.Exception;
import java.math.BigInteger;
import java.util.Date;

import javax.annotation.Resource;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.model.Prestito;
import it.quix.academy.qborrow.core.model.QborrowUserContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.conversion.TypeConversionException;
import com.opensymphony.xwork2.util.ValueStack;
import it.quix.framework.web.converter.AbstractTypeConverter;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * Converter class for the Prestito model.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class PrestitoConverter extends AbstractTypeConverter<Prestito> {

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
            if (!arg2.equals(Prestito.class))
                throw new TypeConversionException();
            ValueStack stack = ActionContext.getContext().getValueStack();
            // QborrowUserContext uc = (QborrowUserContext) stack.findValue("userContext");

            String[] pks = arg1[0].split("[|]");
            String soggettoBeneficiario_username = (String) convertFromString(pks[0], String.class);
            Integer oggettoPrestato_id = (Integer) convertFromString(pks[1], Integer.class);
            Prestito prestito = getQborrowManager().getPrestito(soggettoBeneficiario_username, oggettoPrestato_id);

            if (prestito == null) {
                throw new TypeConversionException("Error on PrestitoConverter. Prestito with key (id serialized) " + arg1[0] + " not found.");
            }
            return prestito;
        } catch (Exception e) {
            throw new TypeConversionException("Error on PrestitoConverter.", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public String convertToString(Map context, Object obj) {
        Prestito prestito = (Prestito) obj;
        try {
            StringBuilder pk = new StringBuilder();
            pk.append(convertToString(prestito.getSoggettoBeneficiario_username()));
            pk.append("|");
            pk.append(convertToString(prestito.getOggettoPrestato_id()));
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
