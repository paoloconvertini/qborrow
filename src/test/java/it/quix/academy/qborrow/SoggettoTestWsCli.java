package it.quix.academy.qborrow;

import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.web.ws.SoggettoWs;
import it.quix.academy.qborrow.web.ws.TestWs;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class SoggettoTestWsCli {

    public static void main(String[] args) {

        try {
            URL url = new URL("http://localhost:9098/ws/soggetti?wsdl");

            QName qname = new QName("http://ws.web.qborrow.academy.quix.it/", "SoggettoWsImplService");
 
            Service service = Service.create(url, qname);

            SoggettoWs cli = service.getPort(SoggettoWs.class);
            
    		System.out.println(cli.getSoggetto().toString());
    		
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
