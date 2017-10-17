package it.quix.academy.qborrow.web.ws;

import it.quix.academy.qborrow.core.model.Oggetto;
import it.quix.academy.qborrow.core.model.Soggetto;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "it.quix.academy.qborrow.web.ws.SoggettoWs")
public class SoggettoWsImpl implements SoggettoWs {

    public Soggetto getSoggetto() {
        Soggetto soggetto = new Soggetto();
        soggetto.setEmail("pane@cipolla.it");
        return soggetto;
    }

}
