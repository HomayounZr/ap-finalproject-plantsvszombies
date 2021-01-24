package models.servermodels;

import models.servermodels.RestMessageBody;

import java.io.Serializable;

public class RestMessage implements Serializable {

    private String route;
    private RestMessageBody body;

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
