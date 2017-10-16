package it.quix.academy.qborrow;

import it.quix.academy.qborrow.core.dao.DAOFactory;
import it.quix.academy.qborrow.core.dao.SoggettoDAO;
import it.quix.academy.qborrow.core.model.QborrowUserContext;
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.core.search.SoggettoSearch;
import it.quix.framework.core.exception.DAOCreateException;
import it.quix.framework.core.exception.DAOStoreException;
import it.quix.framework.core.manager.ManagerHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoTest {

    private static Log log = LogFactory.getLog(DaoTest.class);

    protected static ApplicationContext applicationContext;

    @BeforeClass
    public static void setUpClass() throws Exception {

        try {
            String[] contextFileNames = { "qborrow-test-spring.xml" };
            log.debug("loading application contexts=" + Arrays.toString(contextFileNames));
            applicationContext = new ClassPathXmlApplicationContext(contextFileNames);

            QborrowUserContext uc = new QborrowUserContext();
            it.quix.framework.core.manager.UserContextHolder.setUserContext(uc);

            ManagerHolder.setManagerOnThreadLocal("qborrowManager", applicationContext.getBean("qborrowManager"));

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            Assert.assertNull(ex);
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        applicationContext = null;
    }

    @Test
    public void createNewSoggetto() {
        // SoggettoDAO soggettoDAO = applicationContext.getBean(SoggettoDAO.class);
        DAOFactory daoFactory = applicationContext.getBean(DAOFactory.class);
        try {
            Date date = new Date();
            Soggetto soggetto = new Soggetto();
            soggetto.setUsername("alfredo.rossi");
            soggetto.setEmail("alfredo.rossi@gmail.com");
            soggetto.setNome("Alfredo");
            soggetto.setCognome("Rossi");
            soggetto.setImmagine("C:/Temp/immagini/immagine.jpg");
            soggetto.setRagioneSociale("Google");
            soggetto.setDataUltimaModifica(date);
            daoFactory.getSoggettoDAO().create(soggetto);
        } catch (DAOCreateException e) {
            log.error("Errore nella creazione del nuovo soggetto", e);
        }
    }

    @Test
    public void getListSoggetti() {
        SoggettoDAO soggettoDAO = applicationContext.getBean(SoggettoDAO.class);
        List<Soggetto> list = new ArrayList<Soggetto>();
        list = soggettoDAO.getList(new SoggettoSearch());
        for (Soggetto soggetto : list) {
            log.info(soggetto.toString());
        }
    }

    @Test
    public void modifySoggetto() {
        SoggettoDAO soggettoDAO = applicationContext.getBean(SoggettoDAO.class);
        SoggettoSearch soggettoRicercato = new SoggettoSearch();
        soggettoRicercato.setNome("Alfredo");

        List<Soggetto> list = new ArrayList<Soggetto>();
        list = soggettoDAO.getList(soggettoRicercato);
        log.info("Dimensione lista " + list.size());

        Soggetto soggetto = new Soggetto();
        if (list != null && list.size() > 0) {
            soggetto = list.get(0);
            soggetto.setNome("Giovanni");
        }

        try {
            soggettoDAO.update(soggetto);
        } catch (DAOStoreException e) {
            // TODO Auto-generated catch block
            log.error("Errore di connessione al database", e);
            ;
        }
    }

}
