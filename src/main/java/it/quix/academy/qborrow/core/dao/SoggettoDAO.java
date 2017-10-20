package it.quix.academy.qborrow.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrow.core.dao.generated.SoggettoAbstractDAO;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

/**
 * The DAO for Soggetto entity.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT, generated 11/10/2017 15:19:27
 */
public class SoggettoDAO extends SoggettoAbstractDAO {

    private static Log log = LogFactory.getLog(SoggettoDAO.class);

    public SoggettoDAO(DataSource dataSource) {
        super(dataSource);
        if (log.isDebugEnabled()) {
            log.debug("SoggettoDAO initialized!");
        }
    }

    public void init() {
        log.info("soggetto.dao da loggare");
    }

    /**
     * Return a record of Soggetto on table soggetti
     * 
     * @param username
     * @return the object Soggetto
     * @throws DAOFinderException if no record found with passed params
     */
    public Soggetto getWithCompleanno(String username) throws DAOFinderException {
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
                Soggetto soggettoModel = buildModelFromResultSetWithCompleanno(rs);
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
    protected Soggetto buildModelFromResultSetWithCompleanno(ResultSet rs) throws SQLException {
        return buildModelFromResultSetWithCompleanno(rs, qborrowManager);
    }

    /**
     * Fill all object (Soggetto) fields from the ResultSet
     * 
     * @param rs the ResultSet to copy to the object
     * @param qborrowManager the manager to set
     * @return the Soggetto object
     * @throws SQLException if any error on retrieve fields from the ResultSet
     */
    public Soggetto buildModelFromResultSetWithCompleanno(ResultSet rs, QborrowManager qborrowManager) throws SQLException {
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
        soggetto.setDataCompleanno(getParameterDate(rs, "data_compleanno"));

        return soggetto;
    }

    public void updateWithCompleanno(Soggetto soggetto) throws DAOStoreException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Compose the update query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" UPDATE soggetti SET ").append(EOL);
            query.append(" email = ? , ragione_sociale = ? , nome = ? , cognome = ? , immagine = ? , data_ultima_modifica = ?,  data_compleanno = ? ").append(
                EOL);
            query.append("  WHERE username = ? ").append(EOL);
            query.append("  AND (date_format(data_compleanno, '%y') % 2 ) = 0 ").append(EOL);

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
            super.setParameterDate(statement, p++, soggetto.getDataCompleanno());

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
}