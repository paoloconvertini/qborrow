package it.quix.academy.qborrow;

import it.quix.academy.qborrow.web.ws.SoggettoWsImpl;

import javax.xml.ws.Endpoint;

public class PubSoggettoWs {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:9098/ws/soggetti", new SoggettoWsImpl());
    }

}
