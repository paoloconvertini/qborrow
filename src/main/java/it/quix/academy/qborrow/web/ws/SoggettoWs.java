package it.quix.academy.qborrow.web.ws;

import java.util.List;

import it.quix.academy.qborrow.core.model.Oggetto;
import it.quix.academy.qborrow.core.model.Soggetto;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public interface SoggettoWs {

    @WebMethod
    Soggetto getSoggetto();

    // @WebMethod
    // List<Soggetto> getListSoggetti (Soggetto soggetto);

}
