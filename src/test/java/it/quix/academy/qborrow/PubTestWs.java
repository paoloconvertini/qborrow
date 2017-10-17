package it.quix.academy.qborrow;

import it.quix.academy.qborrow.web.ws.TestWsImpl;

import javax.xml.ws.Endpoint;

public class PubTestWs {

    public static void main(String[] args) {

        Endpoint.publish("http://localhost:9999/ws/test", new TestWsImpl());
    }

}
