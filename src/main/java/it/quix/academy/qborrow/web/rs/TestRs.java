package it.quix.academy.qborrow.web.rs;

import it.quix.academy.qborrow.core.dao.DAOFactory;
import it.quix.academy.qborrow.core.dao.OggettoDAO;
import it.quix.academy.qborrow.core.manager.QborrowManager;
import it.quix.academy.qborrow.core.model.Oggetto;
import it.quix.academy.qborrow.core.model.Soggetto;
import it.quix.academy.qborrow.core.search.OggettoSearch;
import it.quix.academy.qborrow.core.search.SoggettoSearch;
import it.quix.framework.core.exception.DAOFinderException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/soggetti")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestRs {

    @Resource(name = "qborrowManager")
    private QborrowManager qborrowManager;

    @GET
    public Response getSoggetti() {

        Soggetto soggetto = new Soggetto();

        try {
            soggetto = qborrowManager.getSoggetto("paolo.onvertini");
        } catch (DAOFinderException e) {
            e.printStackTrace();
        }

        return Response.ok(soggetto, MediaType.APPLICATION_JSON).build();
    }

    /**
     * @return the qborrowManager
     */
    public QborrowManager getQborrowManager() {
        return qborrowManager;
    }

    /**
     * @param qborrowManager the qborrowManager to set
     */
    public void setQborrowManager(QborrowManager qborrowManager) {
        this.qborrowManager = qborrowManager;
    }

    @POST
    public Oggetto putOggetto(Oggetto oggetto) {
        return oggetto;
    }

    @DELETE
    public String deleteOggetto() {
        return "{\"message\": \"OK\"}";
    }
}
