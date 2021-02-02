package models.servermodels;

import models.servermodels.RestMessageBody;

import java.io.Serializable;

/**
 * Define shape of a message to socket
 * so server and client can distinguish different requests and respnses
 */
public class RestMessage implements Serializable {

    private String route;
    private RestMessageBody body;

    /**
     * construcotor
     * @param route String route
     * @param body RestMessageBody body
     */
    public RestMessage(String route, RestMessageBody body){
        this.route = route;
        this.body = body;
    }

    public String getRoute() {
        return route;
    }

    public RestMessageBody getBody() {
        return body;
    }

}
