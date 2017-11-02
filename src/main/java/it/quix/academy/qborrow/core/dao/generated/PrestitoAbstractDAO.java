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
import it.quix.academy.qborrow.core.model.Prestito;
import it.quix.academy.qborrow.core.search.PrestitoSearch;
import it.quix.framework.core.dao.AbstractJDBCDAO;
import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.core.exception.DAODeleteException;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.core.exception.ModelJdbcException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

/**
 * The Abstract DAO for Prestito entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 15:19:27
 */
public abstract class PrestitoAbstractDAO extends AbstractJDBCDAO {

    private static final Log log = LogFactory.getLog(PrestitoAbstractDAO.class);

    @Resource(name = "qborrowManager")
    protected QborrowManager qborrowManager;

    @Resource(name = "configuration")
    protected Configuration configuration;

    public PrestitoAbstractDAO(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * Create a new record of type Prestito on table prestiti
     * 
     * @param prestito The Prestito to create on database
     * @throws DAOCreateException if no record is created on database
     */
    public void create(Prestito prestito) throws DAOCreateException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the insert query
            StringBuilder query = new StringBuilder(EOL);
            query.append("INSERT INTO prestiti ").append(EOL);
            query.append("   (BENEFICIARIO, OGGETTO_PRESTATO, DATA_PRESTITO, DATA_SCADENZA) ").append(EOL);
            query.append(" VALUES ").append(EOL);
            query.append(" (?, ?, ?, ?) ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());
            // set prePersist
            prestito.prePersist(configuration);
            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, prestito.getSoggettoBeneficiario().getUsername());
            super.setParameterInteger(statement, p++, prestito.getOggettoPrestato_id());
            super.setParameterDate(statement, p++, prestito.getDataPrestito());
            super.setParameterDate(statement, p++, prestito.getDataScadenza());

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
                    FrameworkStringUtils.concat("An error occurred creating Prestito on database. No record created. Number of inserted rows: ",
                        numberOfCreatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOCreateException(msg);
            }
        } catch (SQLException ex) {
            String msg = "Unexpeted error on create Prestito on database.";
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
     * Updates a record of type Prestito on table prestiti
     * 
     * @param prestito The Prestito to update on database
     * @throws DAOStoreException if no record is updated on database
     */
    public void update(Prestito prestito) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE prestiti SET ").append(EOL);
            query.append(" data_prestito = ? , data_scadenza = ?  ").append(EOL);
            query.append("  WHERE beneficiario = ?  AND oggetto_prestato = ? ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // set preUpdate
            prestito.preUpdate(configuration);

            // Set the parameters
            int p = 1;
            super.setParameterDate(statement, p++, prestito.getDataPrestito());
            super.setParameterDate(statement, p++, prestito.getDataScadenza());

            // Set the primary key
            super.setParameterString(statement, p++, prestito.getSoggettoBeneficiario().getUsername());
            super.setParameterInteger(statement, p++, prestito.getOggettoPrestato_id());

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
                    FrameworkStringUtils.concat("Error while updating the record of type Prestito ", prestito, " on database. Number of updated rows: ",
                        numberOfUpdatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOStoreException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error during update of record of type Prestito ", prestito, " on database.");
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
     * Updates a record of type Prestito on table prestiti
     * 
     * @param prestito The Prestito to update on database
     * @throws DAOStoreException if no record is updated on database
     */
    public void updateWithoutSoggettoBeneficiario(Prestito prestito) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE prestiti SET ").append(EOL);
            query.append(" beneficiario = ? , data_prestito = ? , data_scadenza = ?  ").append(EOL);
            query.append("  WHERE oggetto_prestato = ? ").append(EOL);

            // Query logging
            if (queryLog.isInfoEnabled()) {
                queryLog.info(query);
            }
            // Get connection
            connection = getConnection();
            // Prepare the statement
            statement = connection.prepareStatement(query.toString());

            // set preUpdate
            prestito.preUpdate(configuration);

            // Set the parameters
            int p = 1;
            super.setParameterString(statement, p++, prestito.getSoggettoBeneficiario().getUsername());
            super.setParameterDate(statement, p++, prestito.getDataPrestito());
            super.setParameterDate(statement, p++, prestito.getDataScadenza());

            // Set the primary key
            // super.setParameterString(statement, p++, prestito.getSoggettoBeneficiario_username());
            super.setParameterInteger(statement, p++, prestito.getOggettoPrestato_id());

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
                    FrameworkStringUtils.concat("Error while updating the record of type Prestito ", prestito, " on database. Number of updated rows: ",
                        numberOfUpdatedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAOStoreException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error during update of record of type Prestito ", prestito, " on database.");
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
     * Return true if oldPrestito and newPrestito are different
     * 
     * @param oldPrestito first model to compare
     * @param newPrestito second model to compare
     * @return if the first model is different to the second model
     */
    protected boolean isDirty(Prestito oldPrestito, Prestito newPrestito) {
        if (oldPrestito.getDataPrestito() != null && !oldPrestito.getDataPrestito().equals(newPrestito.getDataPrestito()))
            return true;
        if (oldPrestito.getDataPrestito() == null && newPrestito.getDataPrestito() != null)
            return true;
        if (oldPrestito.getDataScadenza() != null && !oldPrestito.getDataScadenza().equals(newPrestito.getDataScadenza()))
            return true;
        if (oldPrestito.getDataScadenza() == null && newPrestito.getDataScadenza() != null)
            return true;
        if (oldPrestito.getSoggettoBeneficiario() != null && !oldPrestito.getSoggettoBeneficiario().equals(newPrestito.getSoggettoBeneficiario()))
            return true;
        if (oldPrestito.getSoggettoBeneficiario() == null && newPrestito.getSoggettoBeneficiario() != null)
            return true;
        if (oldPrestito.getOggettoPrestato() != null && !oldPrestito.getOggettoPrestato().equals(newPrestito.getOggettoPrestato()))
            return true;
        if (oldPrestito.getOggettoPrestato() == null && newPrestito.getOggettoPrestato() != null)
            return true;

        return false;
    }

    /**
     * Update on database the newPrestito only if is different to the oldPrestito
     * 
     * @param oldPrestito the old model, used to find difference to the newPrestito
     * @param newPrestito the model to store to database if it is different to oldPrestito
     * @throws DAOStoreException if no record is updated on database
     */
    public void updateIfDirty(Prestito oldPrestito, Prestito newPrestito) throws DAOStoreException {
        if (isDirty(oldPrestito, newPrestito)) {
            update(newPrestito);
        }
    }

    /**
     * Clone the model pass as parameter into a new model. This method don't copy the toMany fields (List, Set, ...)
     * 
     * This method is static to be also called outside of the Manager
     * 
     * @param prestito the model to be cloned
     * @return the clone of the model pass as parameter
     */
    public static Prestito cloneWithoutChildren(Prestito prestito) {
        if (!prestito.isJdbc()) {
            throw new ModelJdbcException(
                "The method PrestitoAbstractDAO.cloneWithoutChildren(Prestito prestito) can be executed only on jdbc model. The Prestito pass as parameter isn't jdbc model.");
        }

        Prestito newPrestito = new Prestito();
        newPrestito.setJdbc(true);

        newPrestito.setQborrowManager(prestito.getQborrowManager());
        newPrestito.setSoggettoBeneficiario(prestito.getSoggettoBeneficiario());
        newPrestito.setOggettoPrestato(prestito.getOggettoPrestato());
        newPrestito.setDataPrestito(prestito.getDataPrestito());
        newPrestito.setDataScadenza(prestito.getDataScadenza());

        return newPrestito;
    }

    /**
     * Return a record of Prestito on table prestiti
     * 
     * @param soggettoBeneficiario_username
     * @param oggettoPrestato_id
     * @return the object Prestito
     * @throws DAOFinderException if no record found with passed params
     */
    public Prestito get(String soggettoBeneficiario_username, Integer oggettoPrestato_id) throws DAOFinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM prestiti ").append(EOL);
            query.append("WHERE BENEFICIARIO = ? AND OGGETTO_PRESTATO = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, soggettoBeneficiario_username);
            super.setParameterInteger(statement, p++, oggettoPrestato_id);

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
                Prestito prestitoModel = buildModelFromResultSet(rs);
                return prestitoModel;
            }
            throw new DAOFinderException(FrameworkStringUtils.concat("Cannot find Prestito on database with [soggettoBeneficiario_username = ",
                soggettoBeneficiario_username, " oggettoPrestato_id = ", oggettoPrestato_id, "]"));

        } catch (SQLException ex) {
            String msg =
                FrameworkStringUtils
                    .concat(
                        "Error on method get(String soggettoBeneficiario_username, Integer oggettoPrestato_id) for Prestito on database with [soggettoBeneficiario_username = ",
                        soggettoBeneficiario_username, " oggettoPrestato_id = ", oggettoPrestato_id, "]");
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
     * Fill all object (Prestito) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @return the Prestito object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    protected Prestito buildModelFromResultSet(ResultSet rs) throws SQLException {
        return buildModelFromResultSet(rs, qborrowManager);
    }

    /**
     * Fill all object (Prestito) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @param qborrowManager the manager to set
     * @return the Prestito object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    public Prestito buildModelFromResultSet(ResultSet rs, QborrowManager qborrowManager) throws SQLException {
        Prestito prestito = new Prestito();

        prestito.setJdbc(true);
        prestito.setQborrowManager(qborrowManager);

        prestito.setSoggettoBeneficiario_username(getParameterString(rs, "beneficiario"));
        prestito.setOggettoPrestato_id(getParameterInteger(rs, "oggetto_prestato"));
        prestito.setDataPrestito(getParameterDate(rs, "data_prestito"));
        prestito.setDataScadenza(getParameterDate(rs, "data_scadenza"));

        return prestito;
    }

    /**
     * Delete a record of Prestito on table prestiti
     * 
     * @param soggettoBeneficiario_username
     * @param oggettoPrestato_id
     *
     * @throws DAODeleteException if no record deleted
     */
    public void delete(String soggettoBeneficiario_username, Integer oggettoPrestato_id) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM prestiti ").append(EOL);
            query.append("WHERE BENEFICIARIO = ? AND OGGETTO_PRESTATO = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, soggettoBeneficiario_username);
            super.setParameterInteger(statement, p++, oggettoPrestato_id);

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
                    FrameworkStringUtils.concat("No record of Prestito deleted with params [soggettoBeneficiario_username = ", soggettoBeneficiario_username,
                        " oggettoPrestato_id = ", oggettoPrestato_id, "]");
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg =
                FrameworkStringUtils.concat("Error during delete records on Prestito with params [soggettoBeneficiario_username = ",
                    soggettoBeneficiario_username, " oggettoPrestato_id = ", oggettoPrestato_id, "]");
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
     * Get list of Prestito by SoggettoBeneficiario
     * 
     * @param SoggettoBeneficiario
     * @return a Prestito list
     */
    public List<Prestito> getPrestitoListBySoggettoBeneficiario(String soggettoBeneficiario_username) {
        List<Prestito> list = new ArrayList<Prestito>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM prestiti ").append(EOL);
            query.append("WHERE beneficiario = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, soggettoBeneficiario_username);

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
                Prestito prestito = buildModelFromResultSet(rs);
                list.add(prestito);
            }
            return list;
        } catch (SQLException ex) {
            String msg =
                FrameworkStringUtils.concat("Unexpeted error on find Prestito with soggettoBeneficiario_username = ", soggettoBeneficiario_username,
                    "on database.");
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
     * Delete Prestito list by SoggettoBeneficiario
     * 
     * @param soggettoBeneficiario_username
     * @throws DAODeleteException if no record deleted
     */
    public void deletePrestitoListBySoggettoBeneficiario(String soggettoBeneficiario_username) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM prestiti ").append(EOL);
            query.append("WHERE beneficiario = ?  ").append(EOL);
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
            super.setParameterString(statement, p++, soggettoBeneficiario_username);

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
                    FrameworkStringUtils.concat("An error occurred delete Prestito with soggettoBeneficiario_username = ", soggettoBeneficiario_username,
                        " on database. No record created. Number of deleted rows: ", numberOfDeletedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg =
                FrameworkStringUtils.concat("Unexpeted error on delete Prestito with soggettoBeneficiario_username = ", soggettoBeneficiario_username,
                    " on database.");
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
     * Get list of Prestito by OggettoPrestato
     * 
     * @param OggettoPrestato
     * @return a Prestito list
     */
    public List<Prestito> getPrestitoListByOggettoPrestato(Integer oggettoPrestato_id) {
        List<Prestito> list = new ArrayList<Prestito>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("SELECT * FROM prestiti ").append(EOL);
            query.append("WHERE oggetto_prestato = ?  ").append(EOL);
            // query.append("ORDER BY data_prestito DESC  ").append(EOL);
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
            super.setParameterInteger(statement, p++, oggettoPrestato_id);

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
                Prestito prestito = buildModelFromResultSet(rs);
                list.add(prestito);
            }
            return list;
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on find Prestito with oggettoPrestato_id = ", oggettoPrestato_id, "on database.");
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
     * Delete Prestito list by OggettoPrestato
     * 
     * @param oggettoPrestato_id
     * @throws DAODeleteException if no record deleted
     */
    public void deletePrestitoListByOggettoPrestato(Integer oggettoPrestato_id) throws DAODeleteException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append("DELETE FROM prestiti ").append(EOL);
            query.append("WHERE oggetto_prestato = ?  ").append(EOL);
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
            super.setParameterInteger(statement, p++, oggettoPrestato_id);

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
                    FrameworkStringUtils.concat("An error occurred delete Prestito with oggettoPrestato_id = ", oggettoPrestato_id,
                        " on database. No record created. Number of deleted rows: ", numberOfDeletedRecord);
                if (log.isWarnEnabled()) {
                    log.warn(msg);
                }
                throw new DAODeleteException(msg);
            }
        } catch (SQLException ex) {
            String msg = FrameworkStringUtils.concat("Unexpeted error on delete Prestito with oggettoPrestato_id = ", oggettoPrestato_id, " on database.");
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
     * Execute the count query on prestiti filtered for searchModel PrestitoSearch
     * 
     * @param PrestitoSearch search model
     * @return nRow the number of records found
     */
    public Long count(PrestitoSearch search) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT COUNT(*) AS TOT ").append(EOL);
            query.append(" FROM prestiti ").append(EOL);
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
     * Retur a list of Prestito filtered for searchModel PrestitoSearch
     * 
     * @param PrestitoSearch search model
     * @return a list of Prestito
     */
    public List<Prestito> getList(PrestitoSearch search) {
        List<Prestito> list = new ArrayList<Prestito>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT * ").append(EOL);
            query.append(" FROM prestiti ").append(EOL);
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
                Prestito prestito = buildModelFromResultSet(rs);
                list.add(prestito);
                count++;
                if (stopRows(count, search)) {
                    break;
                }
            }
            return list;
        } catch (SQLException ex) {
            String msg = "Unexpeted error on find Prestito  on database.";
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
     * @param PrestitoSearch the search model
     * @return the StringBuilder that compose where clause on query
     */
    protected StringBuilder getWhereQuery(Map<Integer, Object> parameters, PrestitoSearch search) {
        StringBuilder whereClause = new StringBuilder("");
        int p = 1;

        if (search.getDataPrestitoFrom() != null) {
            whereClause.append("AND data_prestito >= ? ");
            parameters.put(new Integer(p), search.getDataPrestitoFrom());
            p++;
        }
        if (search.getDataPrestitoTo() != null) {
            whereClause.append("AND data_prestito <= ? ");
            parameters.put(new Integer(p), search.getDataPrestitoTo());
            p++;
        }
        if (search.getDataScadenzaFrom() != null) {
            whereClause.append("AND data_scadenza >= ? ");
            parameters.put(new Integer(p), search.getDataScadenzaFrom());
            p++;
        }
        if (search.getDataScadenzaTo() != null) {
            whereClause.append("AND data_scadenza <= ? ");
            parameters.put(new Integer(p), search.getDataScadenzaTo());
            p++;
        }
        if (search.getSoggettoBeneficiario() != null) {
            whereClause.append("AND beneficiario = ?  ");
            parameters.put(new Integer(p), search.getSoggettoBeneficiario().getUsername());
            p++;
        } else {
            if (search.getSoggettoBeneficiario_username() != null) {
                whereClause.append("AND beneficiario = ? ");
                parameters.put(new Integer(p), search.getSoggettoBeneficiario_username());
                p++;
            }

        }
        if (search.getOggettoPrestato() != null) {
            whereClause.append("AND oggetto_prestato = ?  ");
            parameters.put(new Integer(p), search.getOggettoPrestato().getId());
            p++;
        } else {
            if (search.getOggettoPrestato_id() != null) {
                whereClause.append("AND oggetto_prestato = ? ");
                parameters.put(new Integer(p), search.getOggettoPrestato_id());
                p++;
            }

        }
        return whereClause;
    }

    /**
     * Return the StringBuilder that compose orderBy clause on query
     * 
     * @param PrestitoSearch the search model
     * @param the query to order
     */
    protected void addOrderClause(PrestitoSearch search, StringBuilder query) {
        switch (search.getOrder()) {
            case 1:
                query.append(" ORDER BY data_prestito ASC ");
                break;
            case 2:
                query.append(" ORDER BY data_prestito DESC ");
                break;
            case 3:
                query.append(" ORDER BY data_scadenza ASC ");
                break;
            case 4:
                query.append(" ORDER BY data_scadenza DESC ");
                break;

            default:
                query.append(" ORDER BY data_prestito DESC, data_scadenza DESC");
                break;
        }
    }

    protected void skipFirstRows(ResultSet rs, PrestitoSearch search) throws SQLException {
        int offset = (search.getPage() - 1) * search.getRowPerPage();
        if (offset > 0) {
            rs.absolute(offset);
        }
    }

    protected boolean stopRows(int count, PrestitoSearch search) {
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