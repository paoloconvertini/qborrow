package it.quix.academy.qborrow.core.manager;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.core.exception.DAODeleteException;
import it.quix.framework.core.exception.DAOFinderException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.core.validation.exception.ValidationException;
import it.quix.framework.core.manager.UserContextHolder;
import it.quix.academy.qborrow.core.model.QborrowUserContext;

import it.quix.academy.qborrow.core.validation.ValidatorFactory;
import it.quix.academy.qborrow.core.dao.DAOFactory;
import it.quix.academy.qborrow.core.model.Oggetto;
import it.quix.academy.qborrow.core.search.OggettoSearch;
import it.quix.academy.qborrow.core.model.Prestito;
import it.quix.academy.qborrow.core.search.PrestitoSearch;
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.core.search.SoggettoSearch;

import it.quix.framework.core.handler.SysAttributeHandler;

/**
 * Interface of the application manager.<br>
 * This class exposes all the application object management APIs.
 * 
 * @author Quix CodeGenerator version 03.03.00-SNAPSHOT
 */
public class QborrowManager {

    private static Log log = LogFactory.getLog(QborrowManager.class);

    @Resource(name = "daoFactory")
    private DAOFactory daoFactory;

    @Resource(name = "validatorFactory")
    private ValidatorFactory validatorFactory;

    @Resource(name = "sysAttributeHandler")
    private SysAttributeHandler sysAttributeHandler;

    /**
     * Returns the list of Oggetto that satisfy conditions passed in
     * oggettoSearch parameter
     * 
     * @param oggettoSearch search model that contains the filters to use
     * @return returns the list of oggetto that satisfy conditions
     * @see OggettoSearch
     * @see Oggetto
     */
    @Transactional(readOnly = true)
    public List<Oggetto> getOggettoList(OggettoSearch oggettoSearch) {
        List<Oggetto> list = daoFactory.getOggettoDAO().getList(oggettoSearch);
        return list;
    }

    /**
     * Funzione per ottenere la lista dei miei oggetti.
     * 
     * @param oggettoSearch
     * @return
     */
    @Transactional(readOnly = true)
    public List<Oggetto> getMieiOggettiList(OggettoSearch oggettoSearch) {
        List<Oggetto> list = daoFactory.getOggettoDAO().getMieOggettiList(oggettoSearch);
        return list;
    }

    @Transactional(readOnly = true)
    public List<Oggetto> getOggettoListBySoggetto(String proprietario_username) {
        List<Oggetto> list = daoFactory.getOggettoDAO().getOggettoListByProprietario(proprietario_username);
        return list;
    }

    /**
     * Returns the number of Oggetto that satisfy conditions passed in
     * oggettoSearch parameter
     * 
     * @param oggettoSearch search model that contains the filters to use
     * @return the number of Oggetto found
     * @see OggettoSearch
     * @see Oggetto
     */
    @Transactional(readOnly = true)
    public Long countOggetto(OggettoSearch oggettoSearch) {
        return daoFactory.getOggettoDAO().count(oggettoSearch);
    }

    /**
     * Returns the number of Oggetto that satisfy conditions passed in
     * oggettoSearch parameter
     * 
     * @param oggettoSearch search model that contains the filters to use
     * @return the number of Oggetto found
     * @see OggettoSearch
     * @see Oggetto
     */
    @Transactional(readOnly = true)
    public Long countMieiOggetti(OggettoSearch oggettoSearch) {
        return daoFactory.getOggettoDAO().countMieiOggetti(oggettoSearch);
    }

    /**
     * retrieve from persistence system the required Oggetto record
     * 
     * @param oggettoId the key to retrieve the oggetto
     * @return the requested Oggetto record
     * @throws QborrowException if an unexpected exception occurs or no record
     *             is found
     * @see Oggetto
     */
    @Transactional(readOnly = true, rollbackFor = { QborrowException.class })
    public Oggetto getOggetto(Integer id) throws DAOFinderException {
        Oggetto oggetto = null;
        oggetto = daoFactory.getOggettoDAO().get(id);
        return oggetto;
    }

    /**
     * persist the passed Oggetto object to database, previous validation
     * 
     * @param oggetto the object to save
     * @return the persisted object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Oggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Oggetto saveOggetto(Oggetto oggetto) throws QborrowException, ValidationException {
        return saveOggetto(oggetto, true);
    }

    /**
     * persist the passed Oggetto object to database
     * 
     * @param oggetto the object to save
     * @param validate false skip model validation
     * @return the persisted object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Oggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Oggetto saveOggetto(Oggetto oggetto, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validateOggetto(oggetto);
        }

        if (oggetto.getId() == null) {
            createOggetto(oggetto, validate);
            Prestito prestito = oggetto.getPrestito();

            if (prestito != null) {
                prestito.setOggettoPrestato_id(oggetto.getId());
                createPrestito(prestito);
            }
        } else {
            updateOggetto(oggetto, validate);
            Prestito prestito = oggetto.getPrestito();
            if (prestito != null) {
                List<Prestito> prestitoListByOggettoPrestato = getDaoFactory().getPrestitoDAO().getPrestitoListByOggettoPrestato(oggetto.getId());
                if (prestitoListByOggettoPrestato.size() > 0) {
                    updatePrestitoWithoutSoggettoBeneficiario(prestito, validate);
                } else {
                    createPrestito(prestito, validate);
                }
            }
        }

        /*
         * if (oggetto.getId() == null) {
         * createOggetto(oggetto, validate);
         * } else {
         * updateOggetto(oggetto, validate);
         * }
         */
        return oggetto;
    }

    /**
     * create the passed Oggetto object to database, previous validation
     * 
     * @param oggetto the object to create
     * @return the created object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Oggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Oggetto createOggetto(Oggetto oggetto) throws QborrowException, ValidationException {
        return createOggetto(oggetto, true);
    }

    /**
     * create the passed Oggetto object to database
     * 
     * @param oggetto the object to create
     * @param validate false skip model validation
     * @return the created object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Oggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Oggetto createOggetto(Oggetto oggetto, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validateOggetto(oggetto);
        }
        try {
            daoFactory.getOggettoDAO().create(oggetto);
            return oggetto;
        } catch (DAOCreateException ex) {
            throw new QborrowException(ex, oggetto);
        }
    }

    /**
     * update the passed Oggetto object to database, previous validation
     * 
     * @param oggetto the object to update
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Oggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Oggetto updateOggetto(Oggetto oggetto) throws QborrowException, ValidationException {
        return updateOggetto(oggetto, true);
    }

    /**
     * update the passed Oggetto object to database
     * 
     * @param oggetto the object to update
     * @param validate false skip model validation
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Oggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Oggetto updateOggetto(Oggetto oggetto, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validateOggetto(oggetto);
        }
        try {

            daoFactory.getOggettoDAO().update(oggetto);

            return oggetto;
        } catch (DAOStoreException ex) {
            throw new QborrowException(ex, oggetto);
        }
    }

    /**
     * delete the Oggetto object from the database
     * 
     * @param oggetto the object to delete
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @see Oggetto
     */
    @Transactional(rollbackFor = { QborrowException.class })
    public void deleteOggetto(Integer id) throws QborrowException {
        try {
            daoFactory.getOggettoDAO().delete(id);
        } catch (DAODeleteException e) {
            throw new QborrowException("Error on delete Oggetto", e);
        }
    }

    /**
     * performs the validation of the selected Oggetto
     *
     * @param oggetto the object to be validated
     * @param groups group name(s) targeted for validation (default to <code>[blank]</code> means all the validation will be done)
     * @throws ValidationException if validation error occurs
     * @see Oggetto
     * @see it.quix.academy.qborrow.core.validation.OggettoValidator
     */
    @Transactional(readOnly = true, rollbackFor = { ValidationException.class })
    public void validateOggetto(Oggetto oggetto, String... groups) throws ValidationException {
        QborrowUserContext userContext = (QborrowUserContext) UserContextHolder.getUserContext();
        validatorFactory.getOggettoValidator().validate(userContext, oggetto, groups);
    }

    /**
     * performs the validation of the selected search model OggettoSearch
     *
     * @param oggettoSearch the search model to be validated
     * @param groups group name(s) targeted for validation (default to <code>[blank]</code> means all the validation will be done)
     * @throws ValidationException if validation error occurs
     * @see OggettoSearch
     * @see it.quix.academy.qborrow.core.validation.OggettoValidator
     */
    @Transactional(readOnly = true, rollbackFor = { ValidationException.class })
    public void validateOggettoSearch(OggettoSearch oggettoSearch, String... groups) throws ValidationException {
        QborrowUserContext userContext = (QborrowUserContext) UserContextHolder.getUserContext();
        validatorFactory.getOggettoSearchValidator().validate(userContext, oggettoSearch, groups);
    }

    /**
     * Returns the list of Prestito that satisfy conditions passed in
     * prestitoSearch parameter
     * 
     * @param prestitoSearch search model that contains the filters to use
     * @return returns the list of prestito that satisfy conditions
     * @see PrestitoSearch
     * @see Prestito
     */
    @Transactional(readOnly = true)
    public List<Prestito> getPrestitoList(PrestitoSearch prestitoSearch) {
        List<Prestito> list = daoFactory.getPrestitoDAO().getList(prestitoSearch);
        return list;
    }

    @Transactional(readOnly = true)
    public List<Prestito> getPrestitoListBySoggetto(String soggettoBeneficiario_username) {
        List<Prestito> list = daoFactory.getPrestitoDAO().getPrestitoListBySoggettoBeneficiario(soggettoBeneficiario_username);
        return list;
    }

    @Transactional(readOnly = true)
    public List<Prestito> getPrestitoListByOggetto(Integer oggettoPrestato_id) {
        List<Prestito> list = daoFactory.getPrestitoDAO().getPrestitoListByOggettoPrestato(oggettoPrestato_id);
        return list;
    }

    /**
     * Returns the number of Prestito that satisfy conditions passed in
     * prestitoSearch parameter
     * 
     * @param prestitoSearch search model that contains the filters to use
     * @return the number of Prestito found
     * @see PrestitoSearch
     * @see Prestito
     */
    @Transactional(readOnly = true)
    public Long countPrestito(PrestitoSearch prestitoSearch) {
        return daoFactory.getPrestitoDAO().count(prestitoSearch);
    }

    /**
     * retrieve from persistence system the required Prestito record
     * 
     * @param prestitoId the key to retrieve the prestito
     * @return the requested Prestito record
     * @throws QborrowException if an unexpected exception occurs or no record
     *             is found
     * @see Prestito
     */
    @Transactional(readOnly = true, rollbackFor = { QborrowException.class })
    public Prestito getPrestito(String soggettoBeneficiario_username, Integer oggettoPrestato_id) throws DAOFinderException {
        Prestito prestito = null;
        prestito = daoFactory.getPrestitoDAO().get(soggettoBeneficiario_username, oggettoPrestato_id);
        return prestito;
    }

    /**
     * persist the passed Prestito object to database, previous validation
     * 
     * @param prestito the object to save
     * @return the persisted object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Prestito
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Prestito savePrestito(Prestito prestito) throws QborrowException, ValidationException {
        return savePrestito(prestito, true);
    }

    /**
     * persist the passed Prestito object to database
     * 
     * @param prestito the object to save
     * @param validate false skip model validation
     * @return the persisted object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Prestito
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Prestito savePrestito(Prestito prestito, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validatePrestito(prestito);
        }
        if (prestito.getSoggettoBeneficiario() == null && prestito.getOggettoPrestato() == null) {
            createPrestito(prestito, validate);
        } else {
        	updatePrestitoWithoutSoggettoBeneficiario(prestito, validate);
        }
        return prestito;
    }

    /**
     * create the passed Prestito object to database, previous validation
     * 
     * @param prestito the object to create
     * @return the created object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Prestito
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Prestito createPrestito(Prestito prestito) throws QborrowException, ValidationException {
        return createPrestito(prestito, true);
    }

    /**
     * create the passed Prestito object to database
     * 
     * @param prestito the object to create
     * @param validate false skip model validation
     * @return the created object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Prestito
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Prestito createPrestito(Prestito prestito, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validatePrestito(prestito);
        }
        try {
            daoFactory.getPrestitoDAO().create(prestito);
            return prestito;
        } catch (DAOCreateException ex) {
            throw new QborrowException(ex, prestito);
        }
    }

    /**
     * update the passed Prestito object to database, previous validation
     * 
     * @param prestito the object to update
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Prestito
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Prestito updatePrestito(Prestito prestito) throws QborrowException, ValidationException {
        return updatePrestito(prestito, true);
    }

    /**
     * update the passed Prestito object to database
     * 
     * @param prestito the object to update
     * @param validate false skip model validation
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Prestito
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Prestito updatePrestito(Prestito prestito, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validatePrestito(prestito);
        }
        try {

            daoFactory.getPrestitoDAO().update(prestito);

            return prestito;
        } catch (DAOStoreException ex) {
            throw new QborrowException(ex, prestito);
        }
    }

    /**
     * update the passed Prestito object to database
     * 
     * @param prestito the object to update
     * @param validate false skip model validation
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Prestito
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Prestito updatePrestitoWithoutSoggettoBeneficiario(Prestito prestito, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validatePrestito(prestito);
        }
        try {

            daoFactory.getPrestitoDAO().updateWithoutSoggettoBeneficiario(prestito);

            return prestito;
        } catch (DAOStoreException ex) {
            throw new QborrowException(ex, prestito);
        }
    }

    /**
     * delete the Prestito object from the database
     * 
     * @param prestito the object to delete
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @see Prestito
     */
    @Transactional(rollbackFor = { QborrowException.class })
    public void deletePrestito(String soggettoBeneficiario_username, Integer oggettoPrestato_id) throws QborrowException {
        try {
            daoFactory.getPrestitoDAO().delete(soggettoBeneficiario_username, oggettoPrestato_id);
        } catch (DAODeleteException e) {
            throw new QborrowException("Error on delete Prestito", e);
        }
    }

    /**
     * performs the validation of the selected Prestito
     *
     * @param prestito the object to be validated
     * @param groups group name(s) targeted for validation (default to <code>[blank]</code> means all the validation will be done)
     * @throws ValidationException if validation error occurs
     * @see Prestito
     * @see it.quix.academy.qborrow.core.validation.PrestitoValidator
     */
    @Transactional(readOnly = true, rollbackFor = { ValidationException.class })
    public void validatePrestito(Prestito prestito, String... groups) throws ValidationException {
        QborrowUserContext userContext = (QborrowUserContext) UserContextHolder.getUserContext();
        validatorFactory.getPrestitoValidator().validate(userContext, prestito, groups);
    }

    /**
     * performs the validation of the selected search model PrestitoSearch
     *
     * @param prestitoSearch the search model to be validated
     * @param groups group name(s) targeted for validation (default to <code>[blank]</code> means all the validation will be done)
     * @throws ValidationException if validation error occurs
     * @see PrestitoSearch
     * @see it.quix.academy.qborrow.core.validation.PrestitoValidator
     */
    @Transactional(readOnly = true, rollbackFor = { ValidationException.class })
    public void validatePrestitoSearch(PrestitoSearch prestitoSearch, String... groups) throws ValidationException {
        QborrowUserContext userContext = (QborrowUserContext) UserContextHolder.getUserContext();
        validatorFactory.getPrestitoSearchValidator().validate(userContext, prestitoSearch, groups);
    }

    /**
     * Returns the list of Soggetto that satisfy conditions passed in
     * soggettoSearch parameter
     * 
     * @param soggettoSearch search model that contains the filters to use
     * @return returns the list of soggetto that satisfy conditions
     * @see SoggettoSearch
     * @see Soggetto
     */
    @Transactional(readOnly = true)
    public List<Soggetto> getSoggettoList(SoggettoSearch soggettoSearch) {
        List<Soggetto> list = daoFactory.getSoggettoDAO().getList(soggettoSearch);
        return list;
    }

    /**
     * Returns the number of Soggetto that satisfy conditions passed in
     * soggettoSearch parameter
     * 
     * @param soggettoSearch search model that contains the filters to use
     * @return the number of Soggetto found
     * @see SoggettoSearch
     * @see Soggetto
     */
    @Transactional(readOnly = true)
    public Long countSoggetto(SoggettoSearch soggettoSearch) {
        return daoFactory.getSoggettoDAO().count(soggettoSearch);
    }

    /**
     * retrieve from persistence system the required Soggetto record
     * 
     * @param soggettoId the key to retrieve the soggetto
     * @return the requested Soggetto record
     * @throws QborrowException if an unexpected exception occurs or no record
     *             is found
     * @see Soggetto
     */
    @Transactional(readOnly = true, rollbackFor = { QborrowException.class })
    public Soggetto getSoggetto(String username) throws DAOFinderException {
        Soggetto soggetto = null;
        soggetto = daoFactory.getSoggettoDAO().get(username);
        return soggetto;
    }

    /**
     * retrieve from persistence system the required Soggetto record
     * 
     * @param soggettoId the key to retrieve the soggetto
     * @return the requested Soggetto record
     * @throws QborrowException if an unexpected exception occurs or no record
     *             is found
     * @see Soggetto
     */
    @Transactional(readOnly = true, rollbackFor = { QborrowException.class })
    public Soggetto getSoggettoWithCompleanno(String username) throws DAOFinderException {
        Soggetto soggetto = null;
        soggetto = daoFactory.getSoggettoDAO().getWithCompleanno(username);
        return soggetto;
    }

    /**
     * persist the passed Soggetto object to database, previous validation
     * 
     * @param soggetto the object to save
     * @return the persisted object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto saveSoggetto(Soggetto soggetto) throws QborrowException, ValidationException {
        return saveSoggetto(soggetto, true);
    }

    /**
     * persist the passed Soggetto object to database, previous validation
     * 
     * @param soggetto the object to save
     * @return the persisted object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto saveSoggettoWithCompleanno(Soggetto soggetto) throws QborrowException, ValidationException {
        return saveSoggettoWithCompleanno(soggetto, true);
    }

    /**
     * persist the passed Soggetto object to database
     * 
     * @param soggetto the object to save
     * @param validate false skip model validation
     * @return the persisted object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto saveSoggetto(Soggetto soggetto, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validateSoggetto(soggetto);
        }
        if (soggetto.getUsername() == null) {
            createSoggetto(soggetto, validate);
        } else {
            updateSoggetto(soggetto, validate);
        }
        return soggetto;
    }

    /**
     * persist the passed Soggetto object to database
     * 
     * @param soggetto the object to save
     * @param validate false skip model validation
     * @return the persisted object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto saveSoggettoWithCompleanno(Soggetto soggetto, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validateSoggetto(soggetto);
        }
        if (soggetto.getUsername() == null) {
            createSoggetto(soggetto, validate);
        } else {
            updateSoggettoWithCompleanno(soggetto, validate);
        }
        return soggetto;
    }

    /**
     * create the passed Soggetto object to database, previous validation
     * 
     * @param soggetto the object to create
     * @return the created object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto createSoggetto(Soggetto soggetto) throws QborrowException, ValidationException {
        return createSoggetto(soggetto, true);
    }

    /**
     * create the passed Soggetto object to database
     * 
     * @param soggetto the object to create
     * @param validate false skip model validation
     * @return the created object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto createSoggetto(Soggetto soggetto, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validateSoggetto(soggetto);
        }
        try {
            daoFactory.getSoggettoDAO().create(soggetto);
            return soggetto;
        } catch (DAOCreateException ex) {
            throw new QborrowException(ex, soggetto);
        }
    }

    /**
     * update the passed Soggetto object to database, previous validation
     * 
     * @param soggetto the object to update
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto updateSoggetto(Soggetto soggetto) throws QborrowException, ValidationException {
        return updateSoggetto(soggetto, true);
    }

    /**
     * update the passed Soggetto object to database
     * 
     * @param soggetto the object to update
     * @param validate false skip model validation
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto updateSoggetto(Soggetto soggetto, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validateSoggetto(soggetto);
        }
        try {

            daoFactory.getSoggettoDAO().update(soggetto);

            return soggetto;
        } catch (DAOStoreException ex) {
            throw new QborrowException(ex, soggetto);
        }
    }

    /**
     * update the passed Soggetto object to database, previous validation
     * 
     * @param soggetto the object to update
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto updateSoggettoWithCompleanno(Soggetto soggetto) throws QborrowException, ValidationException {
        return updateSoggettoWithCompleanno(soggetto, true);
    }

    /**
     * update the passed Soggetto object to database
     * 
     * @param soggetto the object to update
     * @param validate false skip model validation
     * @return the updated object
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @throws ValidationException if input data doesn't satisfy validation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class, ValidationException.class })
    public Soggetto updateSoggettoWithCompleanno(Soggetto soggetto, boolean validate) throws QborrowException, ValidationException {
        if (validate) {
            validateSoggetto(soggetto);
        }
        try {

            daoFactory.getSoggettoDAO().updateWithCompleanno(soggetto);

            return soggetto;
        } catch (DAOStoreException ex) {
            throw new QborrowException(ex, soggetto);
        }
    }

    /**
     * delete the Soggetto object from the database
     * 
     * @param soggetto the object to delete
     * @throws QborrowException if an unexpected exception occurs during
     *             operation
     * @see Soggetto
     */
    @Transactional(rollbackFor = { QborrowException.class })
    public void deleteSoggetto(String username) throws QborrowException {
        try {
            daoFactory.getSoggettoDAO().delete(username);
        } catch (DAODeleteException e) {
            throw new QborrowException("Error on delete Soggetto", e);
        }
    }

    /**
     * performs the validation of the selected Soggetto
     *
     * @param soggetto the object to be validated
     * @param groups group name(s) targeted for validation (default to <code>[blank]</code> means all the validation will be done)
     * @throws ValidationException if validation error occurs
     * @see Soggetto
     * @see it.quix.academy.qborrow.core.validation.SoggettoValidator
     */
    @Transactional(readOnly = true, rollbackFor = { ValidationException.class })
    public void validateSoggetto(Soggetto soggetto, String... groups) throws ValidationException {
        QborrowUserContext userContext = (QborrowUserContext) UserContextHolder.getUserContext();
        validatorFactory.getSoggettoValidator().validate(userContext, soggetto, groups);
    }

    /**
     * performs the validation of the selected search model SoggettoSearch
     *
     * @param soggettoSearch the search model to be validated
     * @param groups group name(s) targeted for validation (default to <code>[blank]</code> means all the validation will be done)
     * @throws ValidationException if validation error occurs
     * @see SoggettoSearch
     * @see it.quix.academy.qborrow.core.validation.SoggettoValidator
     */
    @Transactional(readOnly = true, rollbackFor = { ValidationException.class })
    public void validateSoggettoSearch(SoggettoSearch soggettoSearch, String... groups) throws ValidationException {
        QborrowUserContext userContext = (QborrowUserContext) UserContextHolder.getUserContext();
        validatorFactory.getSoggettoSearchValidator().validate(userContext, soggettoSearch, groups);
    }

    public DAOFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public ValidatorFactory getValidatorFactory() {
        return validatorFactory;
    }

    public void setValidatorFactory(ValidatorFactory validatorFactory) {
        this.validatorFactory = validatorFactory;
    }

    public void setSysAttributeHandler(SysAttributeHandler sysAttributeHandler) {
        this.sysAttributeHandler = sysAttributeHandler;
    }

    public SysAttributeHandler getSysAttributeHandler() {
        return sysAttributeHandler;
    }

}