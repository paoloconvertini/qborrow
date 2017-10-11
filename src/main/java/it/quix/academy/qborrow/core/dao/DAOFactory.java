package it.quix.academy.qborrow.core.dao;

import javax.annotation.Resource;

import it.quix.framework.core.dao.AbstractDaoFactory;

/**
 * DAO Factory class for every model DAO.<br>
 * Uses injection to retrieve all DAO implementation.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class DAOFactory extends AbstractDaoFactory {

    /**
     * Dao for Oggetto model.
     * 
     * @see OggettoDAO
     */
    @Resource(name = "oggettoDAO")
    private OggettoDAO oggettoDAO;

    /**
     * Dao for Prestito model.
     * 
     * @see PrestitoDAO
     */
    @Resource(name = "prestitoDAO")
    private PrestitoDAO prestitoDAO;

    /**
     * Dao for Soggetto model.
     * 
     * @see SoggettoDAO
     */
    @Resource(name = "soggettoDAO")
    private SoggettoDAO soggettoDAO;

    // ///////////////////////////////////////////////////////////////////////////

    /**
     * oggettoDAO getter.
     * 
     * @return the Oggetto DAO implementation
     * @see OggettoDAO
     */
    public OggettoDAO getOggettoDAO() {
        return oggettoDAO;
    }

    /**
     * oggettoDAO setter.
     * 
     * @param oggettoDAO the Oggetto DAO implementation
     * @see OggettoDAO
     */
    public void setOggettoDAO(OggettoDAO oggettoDAO) {
        this.oggettoDAO = oggettoDAO;
    }

    /**
     * prestitoDAO getter.
     * 
     * @return the Prestito DAO implementation
     * @see PrestitoDAO
     */
    public PrestitoDAO getPrestitoDAO() {
        return prestitoDAO;
    }

    /**
     * prestitoDAO setter.
     * 
     * @param prestitoDAO the Prestito DAO implementation
     * @see PrestitoDAO
     */
    public void setPrestitoDAO(PrestitoDAO prestitoDAO) {
        this.prestitoDAO = prestitoDAO;
    }

    /**
     * soggettoDAO getter.
     * 
     * @return the Soggetto DAO implementation
     * @see SoggettoDAO
     */
    public SoggettoDAO getSoggettoDAO() {
        return soggettoDAO;
    }

    /**
     * soggettoDAO setter.
     * 
     * @param soggettoDAO the Soggetto DAO implementation
     * @see SoggettoDAO
     */
    public void setSoggettoDAO(SoggettoDAO soggettoDAO) {
        this.soggettoDAO = soggettoDAO;
    }

}
