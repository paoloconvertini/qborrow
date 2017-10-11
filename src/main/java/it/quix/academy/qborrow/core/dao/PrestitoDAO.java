package it.quix.academy.qborrow.core.dao;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrow.core.dao.generated.PrestitoAbstractDAO;

/**
 * The DAO for Prestito entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 15:19:27
 */
public class PrestitoDAO extends PrestitoAbstractDAO {

    private static Log log = LogFactory.getLog(PrestitoDAO.class);

    public PrestitoDAO(DataSource dataSource) {
        super(dataSource);
        if (log.isDebugEnabled()) {
            log.debug("PrestitoDAO initialized!");
        }
    }

}