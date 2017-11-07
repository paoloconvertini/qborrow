package it.quix.academy.qborrow.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.academy.qborrow.core.dao.generated.OggettoAbstractDAO;
import it.quix.academy.qborrow.core.model.Oggetto;
import it.quix.academy.qborrow.core.model.Prestito;
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.core.search.OggettoSearch;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.util.FrameworkStringUtils;
import it.quix.framework.util.exceptions.SystemException;

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

    /**
     * Lista di miei oggetti con info sui prestiti
     * 
     * @param OggettoSearch search model
     * @return a list of Oggetto
     */
    public List<Oggetto> getMieOggettiList(OggettoSearch search) {
        List<Oggetto> list = new ArrayList<Oggetto>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT oggetti.*, prestiti.beneficiario, prestiti.data_prestito, prestiti.data_scadenza, soggetti.nome, soggetti.cognome  ").append(
                EOL);
            query.append(" FROM oggetti ").append(EOL);
            query.append(" LEFT JOIN prestiti ON oggetti.id = prestiti.oggetto_prestato ").append(EOL);
            query.append(" LEFT JOIN soggetti ON prestiti.beneficiario = soggetti.username ").append(EOL);
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

                if (getParameterString(rs, "beneficiario") != null) {
                    oggetto.setOggettoPrestato(true);
                    Prestito prestito = new Prestito();
                    prestito.setDataPrestito(getParameterDate(rs, "data_prestito"));
                    prestito.setDataScadenza(getParameterDate(rs, "data_scadenza"));
                    Soggetto soggetto = new Soggetto();
                    soggetto.setUsername(getParameterString(rs, "beneficiario"));
                    soggetto.setNome(getParameterString(rs, "nome"));
                    soggetto.setCognome(getParameterString(rs, "cognome"));

                    prestito.setSoggettoBeneficiario(soggetto);
                    oggetto.setPrestito(prestito);
                }

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
     * Return a record of Oggetto on table oggetti
     * 
     * @param id
     * @return the object Oggetto
     * @throws DAOFinderException if no record found with passed params
     */
    public Oggetto getWithPrestato(Integer id) throws DAOFinderException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT oggetti.*, prestiti.beneficiario, prestiti.data_prestito, prestiti.data_scadenza, soggetti.nome, soggetti.cognome  ").append(
                    EOL);
                query.append(" FROM oggetti ").append(EOL);
                query.append(" LEFT JOIN prestiti ON oggetti.id = prestiti.oggetto_prestato ").append(EOL);
                query.append(" LEFT JOIN soggetti ON prestiti.beneficiario = soggetti.username ").append(EOL);
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
                if (getParameterString(rs, "beneficiario") != null) {
                	 oggettoModel.setOggettoPrestato(true);
                }

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

        if (search.getIsInPrestito() != null) {
            if (search.getIsInPrestito()) {
                whereClause.append("AND beneficiario IS NOT NULL ");
            } else {
                whereClause.append("AND beneficiario IS NULL ");
            }
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
     * Execute the count query on oggetti filtered for searchModel OggettoSearch
     * 
     * @param OggettoSearch search model
     * @return nRow the number of records found
     */
    public Long countMieiOggetti(OggettoSearch search) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            // Compose the select query
            StringBuilder query = new StringBuilder(EOL);
            query.append(" SELECT COUNT(*) AS TOT ").append(EOL);
            query.append(" FROM oggetti ").append(EOL);
            query.append(" LEFT JOIN prestiti ON oggetti.id = prestiti.oggetto_prestato ").append(EOL);
            query.append(" LEFT JOIN soggetti ON prestiti.beneficiario = soggetti.username ").append(EOL);
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
}