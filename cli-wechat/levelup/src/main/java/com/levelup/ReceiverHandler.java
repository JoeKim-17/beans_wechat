package com.levelup;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class ReceiverHandler extends Thread {
    private String otherUser;
    private String myUser;
    private String baseURI = "";

    public ReceiverHandler(String me, String them) {
        myUser = me;
        otherUser = them;
    }

    @Override
    public void run() {
        while (true) {
            try {
                receive();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        System.out.println("STOPPED HERE");
    }

    private void receive() throws Exception {
        throw new Exception("Unimplemented");
        // System.out.println(baseURI + extension);
        // HttpRequest request = HttpRequest.newBuilder()
        //         .uri(new URI(baseURI + extension))
        //         .header(header, value)
        //         .GET()
        //         .build();
        // client.send(request, BodyHandlers.ofString());
    }
}
