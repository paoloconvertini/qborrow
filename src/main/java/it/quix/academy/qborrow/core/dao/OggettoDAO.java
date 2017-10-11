package it.quix.academy.qborrow.core.dao;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrow.core.dao.generated.OggettoAbstractDAO;

/**
 * The DAO for Oggetto entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 15:19:27
 */
public class OggettoDAO extends OggettoAbstractDAO {

    private static Log log = LogFactory.getLog(OggettoDAO.class);

    public OggettoDAO(DataSource dataSource) {
        super(dataSource);
        if (log.isDebugEnabled()) {
            log.debug("OggettoDAO initialized!");
        }
    }

}