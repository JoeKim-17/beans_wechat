package com.levelup;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class ReceiverHandler extends Thread {
    private String otherUser;
    private String myUser;
    // private String baseURI = 
    // "http://wechat-beans-app.eu-west-1.elasticbeanstalk.com";
    private final String baseURI = "http://localhost:8080";
    private HttpClient client = HttpClient.newHttpClient();

    public ReceiverHandler(String me, String them) {
        myUser = me;
        otherUser = them;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(10000);
                receive();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("STOPPED HERE");
    }

    private void receive() throws Exception {
        String url = baseURI + "/messages/notifications/"+otherUser;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("user", otherUser)
                .GET()
                .build();
    }
}
