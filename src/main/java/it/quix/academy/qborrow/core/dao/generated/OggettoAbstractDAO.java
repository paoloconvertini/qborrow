package it.quix.academy.qborrow.core.dao.generated;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrow.Configuration;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.model.Oggetto;
import it.quix.academy.qborrow.core.search.OggettoSearch;
import it.quix.framework.core.dao.AbstractJDBCDAO;
import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.core.exception.DAODeleteException;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.core.exception.ModelJdbcException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

/**
 * The Abstract DAO for Oggetto entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 15:19:27
 */
public abstract class OggettoAbstractDAO extends AbstractJDBCDAO {

    private static final Log log = LogFactory.getLog(OggettoAbstractDAO.class);

    @Resource(name = "qborrowManager")
    protected QborrowManager qborrowManager;

    @Resource(name = "configuration")
    protected Configuration configuration;

    public OggettoAbstractDAO(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Create a new record of type Oggetto on table oggetti
     * 
     * @param oggetto The Oggetto to create on database
     * @throws DAOCreateException if no record is created on database
     */
    public void create(Oggetto oggetto) throws DAOCreateException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the insert query
            StringBuilder query = new StringBuilder(EOL);
            query.append("INSERT INTO oggetti ").append(EOL);
            query.append("   (ID, TITOLO, DESCRIZIONE, IMMAGINE, CATEGORIA, DATA_ULTIMA_MODIFICA, PROPRIETARIO) ").append(EOL);
            query.append(" VALUES ").append(EOL);
            query.append(" (?, ?, ?, ?, ?, ?, ?) ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // set prePersist
            oggetto.prePersist(configuration);
            // Set the parameters
            int p = 1;
            super.setParameterInteger(statement, p++, oggetto.getId());
            super.setParameterString(statement, p++, oggetto.getTitolo());
            super.setParameterString(statement, p++, oggetto.getDescrizione());
            super.setParameterString(statement, p++, oggetto.getImmagine());
            super.setParameterString(statement, p++, oggetto.getCategoria());
            super.setParameterDateTime(statement, p++, oggetto.getDataUltimaModifica());
            super.setParameterString(statement, p++, oggetto.getProprietario_username());

            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfCreatedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfCreatedRecord < 1) {
                String msg =
                    FrameworkStringUtils.concat("An error occurred creating Oggetto on database. No record created. Number of inserted rows: ",
                        numberOfCreatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOCreateException(msg);
            }
        } catch (SQLException ex) {
            String msg = "Unexpeted error on create Oggetto on database.";
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Updates a record of type Oggetto on table oggetti
     * 
     * @param oggetto The Oggetto to update on database
     * @throws DAOStoreException if no record is updated on database
     */
    public void update(Oggetto oggetto) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE oggetti SET ").append(EOL);
            query.append(" titolo = ? , descrizione = ? , immagine = ? , categoria = ? , data_ultima_modifica = ? , proprietario = ?  ").append(EOL);
            query.append("  WHERE id = ? ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // set preUpdate
            oggetto.preUpdate(configuration);

            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, oggetto.getTitolo());
            super.setParameterString(statement, p++, oggetto.getDescrizione());
            super.setParameterString(statement, p++, oggetto.getImmagine());
            super.setParameterString(statement, p++, oggetto.getCategoria());
            super.setParameterDateTime(statement, p++, oggetto.getDataUltimaModifica());
            super.setParameterString(statement, p++, oggetto.getProprietario_username());

            // Set the primary key
            super.setParameterInteger(statement, p++, oggetto.getId());

            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfUpdatedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfUpdatedRecord < 1) {
                String msg =
                    FrameworkStringUtils.concat("Error while updating the record of type Oggetto ", oggetto, " on database. Number of updated rows: ",
                        numberOfUpdatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOStoreException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error during update of record of type Oggetto ", oggetto, " on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Return true if oldOggetto and newOggetto are different
     * 
     * @param oldOggetto first model to compare
     * @param newOggetto second model to compare
     * @return if the first model is different to the second model
     */
    protected boolean isDirty(Oggetto oldOggetto, Oggetto newOggetto) {
        if (oldOggetto.getId() != null && !oldOggetto.getId().equals(newOggetto.getId()))
            return true;
        if (oldOggetto.getId() == null && newOggetto.getId() != null)
            return true;
        if (oldOggetto.getTitolo() != null && !oldOggetto.getTitolo().equals(newOggetto.getTitolo()))
            return true;
        if (oldOggetto.getTitolo() == null && newOggetto.getTitolo() != null)
            return true;
        if (oldOggetto.getDescrizione() != null && !oldOggetto.getDescrizione().equals(newOggetto.getDescrizione()))
            return true;
        if (oldOggetto.getDescrizione() == null && newOggetto.getDescrizione() != null)
            return true;
        if (oldOggetto.getImmagine() != null && !oldOggetto.getImmagine().equals(newOggetto.getImmagine()))
            return true;
        if (oldOggetto.getImmagine() == null && newOggetto.getImmagine() != null)
            return true;
        if (oldOggetto.getCategoria() != null && !oldOggetto.getCategoria().equals(newOggetto.getCategoria()))
            return true;
        if (oldOggetto.getCategoria() == null && newOggetto.getCategoria() != null)
            return true;
        if (oldOggetto.getDataUltimaModifica() != null && !oldOggetto.getDataUltimaModifica().equals(newOggetto.getDataUltimaModifica()))
            return true;
        if (oldOggetto.getDataUltimaModifica() == null && newOggetto.getDataUltimaModifica() != null)
            return true;
        if (oldOggetto.getProprietario() != null && !oldOggetto.getProprietario().equals(newOggetto.getProprietario()))
            return true;
        if (oldOggetto.getProprietario() == null && newOggetto.getProprietario() != null)
            return true;

        return false;
    }

    /**
     * Update on database the newOggetto only if is different to the oldOggetto
     * 
     * @param oldOggetto the old model, used to find difference to the newOggetto
     * @param newOggetto the model to store to database if it is different to oldOggetto
     * @throws DAOStoreException if no record is updated on database
     */
    public void updateIfDirty(Oggetto oldOggetto, Oggetto newOggetto) throws DAOStoreException {
        if (isDirty(oldOggetto, newOggetto)) {
            update(newOggetto);
        }
    }

    /**
     * Clone the model pass as parameter into a new model. This method don't copy the toMany fields (List, Set, ...)
     * 
     * This method is static to be also called outside of the Manager
     * 
     * @param oggetto the model to be cloned
     * @return the clone of the model pass as parameter
     */
    public static Oggetto cloneWithoutChildren(Oggetto oggetto) {
        if (!oggetto.isJdbc()) {
            throw new ModelJdbcException(
                "The method OggettoAbstractDAO.cloneWithoutChildren(Oggetto oggetto) can be executed only on jdbc model. The Oggetto pass as parameter isn't jdbc model.");
        }

        Oggetto newOggetto = new Oggetto();
        newOggetto.setJdbc(true);

        newOggetto.setQborrowManager(oggetto.getQborrowManager());
        newOggetto.setId(oggetto.getId());
        newOggetto.setTitolo(oggetto.getTitolo());
        newOggetto.setDescrizione(oggetto.getDescrizione());
        newOggetto.setImmagine(oggetto.getImmagine());
        newOggetto.setCategoria(oggetto.getCategoria());
        newOggetto.setDataUltimaModifica(oggetto.getDataUltimaModifica());
        newOggetto.setProprietario(oggetto.getProprietario());

        return newOggetto;
    }

    /**
     * Return a record of Oggetto on table oggetti
     * 
     * @param id
     * @return the object Oggetto
     * @throws DAOFinderException if no record found with passed params
     */
    public Oggetto get(Integer id) throws DAOFinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM oggetti ").append(EOL);
            query.append("WHERE ID = ?  ").append(EOL);
            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // Set the parameters
            int p = 1;
            // Set the primary key
            super.setParameterInteger(statement, p++, id);

            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (rs.next()) {
                Oggetto oggettoModel = buildModelFromResultSet(rs);
                return oggettoModel;
            }
            throw new DAOFinderException(FrameworkStringUtils.concat("Cannot find Oggetto on database with [id = ", id, "]"));

        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error on method get(Integer id) for Oggetto on database with [id = ", id, "]");
            if (log.isErrorEnabled()) {
                log.error(msg);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Fill all object (Oggetto) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @return the Oggetto object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    protected Oggetto buildModelFromResultSet(ResultSet rs) throws SQLException {
        return buildModelFromResultSet(rs, qborrowManager);
    }

    /**
     * Fill all object (Oggetto) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @param qborrowManager the manager to set
     * @return the Oggetto object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    public Oggetto buildModelFromResultSet(ResultSet rs, QborrowManager qborrowManager) throws SQLException {
        Oggetto oggetto = new Oggetto();

        oggetto.setJdbc(true);
        oggetto.setQborrowManager(qborrowManager);

        oggetto.setProprietario_username(getParameterString(rs, "proprietario"));
        oggetto.setId(getParameterInteger(rs, "id"));
        oggetto.setTitolo(getParameterString(rs, "titolo"));
        oggetto.setDescrizione(getParameterString(rs, "descrizione"));
        oggetto.setImmagine(getParameterString(rs, "immagine"));
        oggetto.setCategoria(getParameterString(rs, "categoria"));
        oggetto.setDataUltimaModifica(getParameterDate(rs, "data_ultima_modifica"));

        return oggetto;
    }

    /**
     * Delete a record of Oggetto on table oggetti
     * 
     * @param id
     *
     * @throws DAODeleteException if no record deleted
     */
    public void delete(Integer id) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM oggetti ").append(EOL);
            query.append("WHERE ID = ?  ").append(EOL);
            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // Set the parameters
            int p = 1;
            // Set the primary key
            super.setParameterInteger(statement, p++, id);

            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfDeletedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfDeletedRecord < 1) {
                String msg = FrameworkStringUtils.concat("No record of Oggetto deleted with params [id = ", id, "]");
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error during delete records on Oggetto with params [id = ", id, "]");
            if (log.isErrorEnabled()) {
                log.error(msg);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Get list of Oggetto by Proprietario
     * 
     * @param Proprietario
     * @return a Oggetto list
     */
    public List<Oggetto> getOggettoListByProprietario(String proprietario_username) {
        List<Oggetto> list = new ArrayList<Oggetto>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM oggetti ").append(EOL);
            query.append("WHERE proprietario = ?  ").append(EOL);
            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, proprietario_username);

            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            while (rs.next()) {
                Oggetto oggetto = buildModelFromResultSet(rs);
                list.add(oggetto);
            }
            return list;
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on find Oggetto with proprietario_username = ", proprietario_username, "on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Delete Oggetto list by Proprietario
     * 
     * @param proprietario_username
     * @throws DAODeleteException if no record deleted
     */
    public void deleteOggettoListByProprietario(String proprietario_username) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM oggetti ").append(EOL);
            query.append("WHERE proprietario = ?  ").append(EOL);
            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, proprietario_username);

            // Execute the query
            long startTime = System.currentTimeMillis();
            int numberOfDeletedRecord = statement.executeUpdate();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            if (numberOfDeletedRecord < 1) {
                String msg =
                    FrameworkStringUtils.concat("An error occurred delete Oggetto with proprietario_username = ", proprietario_username,
                        " on database. No record created. Number of deleted rows: ", numberOfDeletedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on delete Oggetto with proprietario_username = ", proprietario_username, " on database.");
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Execute the count query on oggetti filtered for searchModel OggettoSearch
     * 
     * @param OggettoSearch search model
     * @return nRow the number of records found
     */
    public Long count(OggettoSearch search) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT COUNT(*) AS TOT ").append(EOL);
            query.append(" FROM oggetti ").append(EOL);
            query.append(" WHERE 1 = 1 ");
            Map<Integer, Object> parameters = new HashMap<Integer, Object>();
            query.append(getWhereQuery(parameters, search));
            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // Set the parameters
            setParameters(statement, parameters);

            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            long nRow = 0;
            if (rs.next()) {
                nRow = rs.getLong("TOT");
            }
            return nRow;
        } catch (Exception ex) {
            if (log.isErrorEnabled()) {
                log.error(ex.getMessage(), ex);
            }
            throw new SystemException(ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Retur a list of Oggetto filtered for searchModel OggettoSearch
     * 
     * @param OggettoSearch search model
     * @return a list of Oggetto
     */
    public List<Oggetto> getList(OggettoSearch search) {
        List<Oggetto> list = new ArrayList<Oggetto>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT * ").append(EOL);
            query.append(" FROM oggetti ").append(EOL);
            query.append(" WHERE 1= 1 ").append(EOL);
            Map<Integer, Object> parameters = new HashMap<Integer, Object>();
            query.append(getWhereQuery(parameters, search));
            addOrderClause(search, query);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Set the parameters
            setParameters(statement, parameters);
            // Execute the query
            long startTime = System.currentTimeMillis();
            rs = statement.executeQuery();
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            String msgTime = FrameworkStringUtils.concat("Query time: ", time);
            if (queryLog.isDebugEnabled()) {
                queryLog.debug(msgTime);
            }
            int count = 0;
            skipFirstRows(rs, search);
            while (rs.next()) {
                Oggetto oggetto = buildModelFromResultSet(rs);
                list.add(oggetto);
                count++;
                if (stopRows(count, search)) {
                    break;
                }
            }
            return list;
        } catch (SQLException ex) {
            String msg = "Unexpeted error on find Oggetto  on database.";
            if (log.isErrorEnabled()) {
                log.error(msg, ex);
            }
            throw new SystemException(msg, ex);
        } finally {
            closeResultSet(rs);
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Return the StringBuilder that compose where clause on query
     * 
     * @param parameters
     * @param OggettoSearch the search model
     * @return the StringBuilder that compose where clause on query
     */
    protected StringBuilder getWhereQuery(Map<Integer, Object> parameters, OggettoSearch search) {
        StringBuilder whereClause = new StringBuilder("");
        int p = 1;
        if (search.getId() != null) {
            whereClause.append("AND id = ? ");
            parameters.put(new Integer(p), search.getId());
            p++;
        } else {

        }

        if (StringUtils.isNotEmpty(search.getTitolo())) {
            whereClause.append("AND titolo  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getTitolo() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getDescrizione())) {
            whereClause.append("AND descrizione  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getDescrizione() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getImmagine())) {
            whereClause.append("AND immagine  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getImmagine() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getCategoria())) {
            whereClause.append("AND categoria  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getCategoria() + "%");
            p++;
        }
        if (search.getDataUltimaModificaFrom() != null) {
            whereClause.append("AND data_ultima_modifica >= ? ");
            parameters.put(new Integer(p), search.getDataUltimaModificaFrom());
            p++;
        }
        if (search.getDataUltimaModificaTo() != null) {
            whereClause.append("AND data_ultima_modifica <= ? ");
            parameters.put(new Integer(p), search.getDataUltimaModificaTo());
            p++;
        }
        if (search.getProprietario() != null) {
            whereClause.append("AND proprietario = ?  ");
            parameters.put(new Integer(p), search.getProprietario().getUsername());
            p++;
        } else {
            if (search.getProprietario_username() != null) {
                whereClause.append("AND proprietario = ? ");
                parameters.put(new Integer(p), search.getProprietario_username());
                p++;
            }

        }
        return whereClause;
    }

    /**
     * Return the StringBuilder that compose orderBy clause on query
     * 
     * @param OggettoSearch the search model
     * @param the query to order
     */
    protected void addOrderClause(OggettoSearch search, StringBuilder query) {
        switch (search.getOrder()) {
            case 1:
                query.append(" ORDER BY titolo ASC ");
                break;
            case 2:
                query.append(" ORDER BY titolo DESC ");
                break;

            default:
                query.append(" ORDER BY titolo ASC");
                break;
        }
    }

    protected void skipFirstRows(ResultSet rs, OggettoSearch search) throws SQLException {
        int offset = (search.getPage() - 1) * search.getRowPerPage();
        if (offset > 0) {
            rs.absolute(offset);
        }
    }

    protected boolean stopRows(int count, OggettoSearch search) {
        return !(search.getPage() < 0 || count < search.getRowPerPage());
    }

    public void setQborrowManager(QborrowManager qborrowManager) {
        this.qborrowManager = qborrowManager;
    }

    public QborrowManager getQborrowManager() {
        return qborrowManager;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

}