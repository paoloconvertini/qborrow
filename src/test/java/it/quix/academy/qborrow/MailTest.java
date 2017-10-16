package it.quix.academy.qborrow;

import it.quix.academy.qborrow.core.model.QborrowUserContext;
import it.quix.academy.qborrow.mail.MailServiceCustom;
import it.quix.framework.core.manager.ManagerHolder;

import java.util.Arrays;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailTest {

    private static Log log = LogFactory.getLog(MailTest.class);

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
    public void SimpleMainTest() {
        log.info("Questo è un messaggio di errore da SimpleMainTest");
    }

    @Test
    public void sendEmailTest() {
        log.info("sendSimpleMail - START");
        MailServiceCustom mailServiceCustom = applicationContext.getBean(MailServiceCustom.class);
        mailServiceCustom.sendEmail("matteo.renzi@quix.it", "test send email", "Ciao questo è un test");
        log.info("sendSimpleMail - END");
    }

}
