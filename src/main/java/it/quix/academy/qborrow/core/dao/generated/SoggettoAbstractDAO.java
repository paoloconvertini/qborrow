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
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.core.search.SoggettoSearch;
import it.quix.framework.core.dao.AbstractJDBCDAO;
import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.core.exception.DAODeleteException;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.core.exception.ModelJdbcException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

/**
 * The Abstract DAO for Soggetto entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 15:19:27
 */
public abstract class SoggettoAbstractDAO extends AbstractJDBCDAO {

    private static final Log log = LogFactory.getLog(SoggettoAbstractDAO.class);

    @Resource(name = "qborrowManager")
    protected QborrowManager qborrowManager;

    @Resource(name = "configuration")
    protected Configuration configuration;

    public SoggettoAbstractDAO(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Create a new record of type Soggetto on table soggetti
     * 
     * @param soggetto The Soggetto to create on database
     * @throws DAOCreateException if no record is created on database
     */
    public void create(Soggetto soggetto) throws DAOCreateException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the insert query
            StringBuilder query = new StringBuilder(EOL);
            query.append("INSERT INTO soggetti ").append(EOL);
            query.append("   (USERNAME, EMAIL, RAGIONE_SOCIALE, NOME, COGNOME, IMMAGINE, DATA_ULTIMA_MODIFICA) ").append(EOL);
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
            soggetto.prePersist(configuration);
            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, soggetto.getUsername());
            super.setParameterString(statement, p++, soggetto.getEmail());
            super.setParameterString(statement, p++, soggetto.getRagioneSociale());
            super.setParameterString(statement, p++, soggetto.getNome());
            super.setParameterString(statement, p++, soggetto.getCognome());
            super.setParameterString(statement, p++, soggetto.getImmagine());
            super.setParameterDateTime(statement, p++, soggetto.getDataUltimaModifica());

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
                    FrameworkStringUtils.concat("An error occurred creating Soggetto on database. No record created. Number of inserted rows: ",
                        numberOfCreatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOCreateException(msg);
            }
        } catch (SQLException ex) {
            String msg = "Unexpeted error on create Soggetto on database.";
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
     * Updates a record of type Soggetto on table soggetti
     * 
     * @param soggetto The Soggetto to update on database
     * @throws DAOStoreException if no record is updated on database
     */
    public void update(Soggetto soggetto) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE soggetti SET ").append(EOL);
            query.append(" email = ? , ragione_sociale = ? , nome = ? , cognome = ? , immagine = ? , data_ultima_modifica = ?  ").append(EOL);
            query.append("  WHERE username = ? ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // set preUpdate
            soggetto.preUpdate(configuration);

            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, soggetto.getEmail());
            super.setParameterString(statement, p++, soggetto.getRagioneSociale());
            super.setParameterString(statement, p++, soggetto.getNome());
            super.setParameterString(statement, p++, soggetto.getCognome());
            super.setParameterString(statement, p++, soggetto.getImmagine());
            super.setParameterDateTime(statement, p++, soggetto.getDataUltimaModifica());

            // Set the primary key
            super.setParameterString(statement, p++, soggetto.getUsername());

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
                    FrameworkStringUtils.concat("Error while updating the record of type Soggetto ", soggetto, " on database. Number of updated rows: ",
                        numberOfUpdatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOStoreException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error during update of record of type Soggetto ", soggetto, " on database.");
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
     * Return true if oldSoggetto and newSoggetto are different
     * 
     * @param oldSoggetto first model to compare
     * @param newSoggetto second model to compare
     * @return if the first model is different to the second model
     */
    protected boolean isDirty(Soggetto oldSoggetto, Soggetto newSoggetto) {
        if (oldSoggetto.getUsername() != null && !oldSoggetto.getUsername().equals(newSoggetto.getUsername()))
            return true;
        if (oldSoggetto.getUsername() == null && newSoggetto.getUsername() != null)
            return true;
        if (oldSoggetto.getEmail() != null && !oldSoggetto.getEmail().equals(newSoggetto.getEmail()))
            return true;
        if (oldSoggetto.getEmail() == null && newSoggetto.getEmail() != null)
            return true;
        if (oldSoggetto.getRagioneSociale() != null && !oldSoggetto.getRagioneSociale().equals(newSoggetto.getRagioneSociale()))
            return true;
        if (oldSoggetto.getRagioneSociale() == null && newSoggetto.getRagioneSociale() != null)
            return true;
        if (oldSoggetto.getNome() != null && !oldSoggetto.getNome().equals(newSoggetto.getNome()))
            return true;
        if (oldSoggetto.getNome() == null && newSoggetto.getNome() != null)
            return true;
        if (oldSoggetto.getCognome() != null && !oldSoggetto.getCognome().equals(newSoggetto.getCognome()))
            return true;
        if (oldSoggetto.getCognome() == null && newSoggetto.getCognome() != null)
            return true;
        if (oldSoggetto.getImmagine() != null && !oldSoggetto.getImmagine().equals(newSoggetto.getImmagine()))
            return true;
        if (oldSoggetto.getImmagine() == null && newSoggetto.getImmagine() != null)
            return true;
        if (oldSoggetto.getDataUltimaModifica() != null && !oldSoggetto.getDataUltimaModifica().equals(newSoggetto.getDataUltimaModifica()))
            return true;
        if (oldSoggetto.getDataUltimaModifica() == null && newSoggetto.getDataUltimaModifica() != null)
            return true;

        return false;
    }

    /**
     * Update on database the newSoggetto only if is different to the oldSoggetto
     * 
     * @param oldSoggetto the old model, used to find difference to the newSoggetto
     * @param newSoggetto the model to store to database if it is different to oldSoggetto
     * @throws DAOStoreException if no record is updated on database
     */
    public void updateIfDirty(Soggetto oldSoggetto, Soggetto newSoggetto) throws DAOStoreException {
        if (isDirty(oldSoggetto, newSoggetto)) {
            update(newSoggetto);
        }
    }

    /**
     * Clone the model pass as parameter into a new model. This method don't copy the toMany fields (List, Set, ...)
     * 
     * This method is static to be also called outside of the Manager
     * 
     * @param soggetto the model to be cloned
     * @return the clone of the model pass as parameter
     */
    public static Soggetto cloneWithoutChildren(Soggetto soggetto) {
        if (!soggetto.isJdbc()) {
            throw new ModelJdbcException(
                "The method SoggettoAbstractDAO.cloneWithoutChildren(Soggetto soggetto) can be executed only on jdbc model. The Soggetto pass as parameter isn't jdbc model.");
        }

        Soggetto newSoggetto = new Soggetto();
        newSoggetto.setJdbc(true);

        newSoggetto.setQborrowManager(soggetto.getQborrowManager());
        newSoggetto.setUsername(soggetto.getUsername());
        newSoggetto.setEmail(soggetto.getEmail());
        newSoggetto.setRagioneSociale(soggetto.getRagioneSociale());
        newSoggetto.setNome(soggetto.getNome());
        newSoggetto.setCognome(soggetto.getCognome());
        newSoggetto.setImmagine(soggetto.getImmagine());
        newSoggetto.setDataUltimaModifica(soggetto.getDataUltimaModifica());

        return newSoggetto;
    }

    /**
     * Return a record of Soggetto on table soggetti
     * 
     * @param username
     * @return the object Soggetto
     * @throws DAOFinderException if no record found with passed params
     */
    public Soggetto get(String username) throws DAOFinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM soggetti ").append(EOL);
            query.append("WHERE USERNAME = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, username);

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
                Soggetto soggettoModel = buildModelFromResultSet(rs);
                return soggettoModel;
            }
            throw new DAOFinderException(FrameworkStringUtils.concat("Cannot find Soggetto on database with [username = ", username, "]"));

        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error on method get(String username) for Soggetto on database with [username = ", username, "]");
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
     * Fill all object (Soggetto) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @return the Soggetto object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    protected Soggetto buildModelFromResultSet(ResultSet rs) throws SQLException {
        return buildModelFromResultSet(rs, qborrowManager);
    }

    /**
     * Fill all object (Soggetto) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @param qborrowManager the manager to set
     * @return the Soggetto object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    public Soggetto buildModelFromResultSet(ResultSet rs, QborrowManager qborrowManager) throws SQLException {
        Soggetto soggetto = new Soggetto();

        soggetto.setJdbc(true);
        soggetto.setQborrowManager(qborrowManager);

        soggetto.setUsername(getParameterString(rs, "username"));
        soggetto.setEmail(getParameterString(rs, "email"));
        soggetto.setRagioneSociale(getParameterString(rs, "ragione_sociale"));
        soggetto.setNome(getParameterString(rs, "nome"));
        soggetto.setCognome(getParameterString(rs, "cognome"));
        soggetto.setImmagine(getParameterString(rs, "immagine"));
        soggetto.setDataUltimaModifica(getParameterDate(rs, "data_ultima_modifica"));

        return soggetto;
    }

    /**
     * Delete a record of Soggetto on table soggetti
     * 
     * @param username
     *
     * @throws DAODeleteException if no record deleted
     */
    public void delete(String username) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM soggetti ").append(EOL);
            query.append("WHERE USERNAME = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, username);

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
                String msg = FrameworkStringUtils.concat("No record of Soggetto deleted with params [username = ", username, "]");
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Error during delete records on Soggetto with params [username = ", username, "]");
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
     * Execute the count query on soggetti filtered for searchModel SoggettoSearch
     * 
     * @param SoggettoSearch search model
     * @return nRow the number of records found
     */
    public Long count(SoggettoSearch search) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT COUNT(*) AS TOT ").append(EOL);
            query.append(" FROM soggetti ").append(EOL);
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
     * Retur a list of Soggetto filtered for searchModel SoggettoSearch
     * 
     * @param SoggettoSearch search model
     * @return a list of Soggetto
     */
    public List<Soggetto> getList(SoggettoSearch search) {
        List<Soggetto> list = new ArrayList<Soggetto>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT * ").append(EOL);
            query.append(" FROM soggetti ").append(EOL);
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
                Soggetto soggetto = buildModelFromResultSet(rs);
                list.add(soggetto);
                count++;
                if (stopRows(count, search)) {
                    break;
                }
            }
            return list;
        } catch (SQLException ex) {
            String msg = "Unexpeted error on find Soggetto  on database.";
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
     * @param SoggettoSearch the search model
     * @return the StringBuilder that compose where clause on query
     */
    protected StringBuilder getWhereQuery(Map<Integer, Object> parameters, SoggettoSearch search) {
        StringBuilder whereClause = new StringBuilder("");
        int p = 1;
        if (StringUtils.isNotEmpty(search.getUsername())) {
            whereClause.append(" AND username ");

            whereClause.append(" = ? ");
            parameters.put(new Integer(p), search.getUsername());

            p++;
        }

        if (StringUtils.isNotEmpty(search.getEmail())) {
            whereClause.append("AND email  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getEmail() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getRagioneSociale())) {
            whereClause.append("AND ragione_sociale  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getRagioneSociale() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getNome())) {
            whereClause.append("AND nome  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getNome() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getCognome())) {
            whereClause.append("AND cognome  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getCognome() + "%");
            p++;
        }
        if (StringUtils.isNotEmpty(search.getImmagine())) {
            whereClause.append("AND immagine  LIKE ? ");
            parameters.put(new Integer(p), "%" + search.getImmagine() + "%");
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
        return whereClause;
    }

    /**
     * Return the StringBuilder that compose orderBy clause on query
     * 
     * @param SoggettoSearch the search model
     * @param the query to order
     */
    protected void addOrderClause(SoggettoSearch search, StringBuilder query) {
        switch (search.getOrder()) {
            case 1:
                query.append(" ORDER BY nome ASC ");
                break;
            case 2:
                query.append(" ORDER BY nome DESC ");
                break;
            case 3:
                query.append(" ORDER BY cognome ASC ");
                break;
            case 4:
                query.append(" ORDER BY cognome DESC ");
                break;

            default:
                query.append(" ORDER BY nome ASC, cognome ASC");
                break;
        }
    }

    protected void skipFirstRows(ResultSet rs, SoggettoSearch search) throws SQLException {
        int offset = (search.getPage() - 1) * search.getRowPerPage();
        if (offset > 0) {
            rs.absolute(offset);
        }
    }

    protected boolean stopRows(int count, SoggettoSearch search) {
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