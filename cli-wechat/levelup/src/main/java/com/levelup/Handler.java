package com.levelup;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.levelup.model.*;

public class Handler extends Thread {
    private Scanner scanner;
    private Logger logger;
    private final String baseURI = "http://wechat-beans-app.eu-west-1.elasticbeanstalk.com";
    // private final String baseURI = "http://localhost:8080";
    private String globalUser = "";
    private String username = "";
    private HttpClient client;
    private int clientID;

    public Handler(Scanner scanner, Logger logger) {
        this.scanner = scanner;
        this.logger = logger;
        client = HttpClient.newHttpClient();
    }

    public Handler(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        try {
            begin();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void begin() {
        input();
    }

    protected static final String commands[] = { "--exit", "--msg", "--signIn", "--help" };

    private void input() {
        boolean msgTrigger = false;
        System.out.println("Please enter your command");
        System.out.println("Type --help <enter> to see all commands available");
        boolean breakLoop = false;
        while (!breakLoop) {
            try {
                System.out.println("Start");
                String command = scanner.next().trim();
                switch (command.toLowerCase()) {
                    case "--exit":
                        breakLoop = true;
                        scanner.close();
                        break;
                    case "--convo":
                        displayConvo();
                        break;
                    case "--msg":
                        startMessage();
                        msgTrigger = true;
                        break;
                    case "--creategroup":
                        String groupName = scanner.next();
                        String nextLine = scanner.nextLine();
                        if (nextLine == "") {
                            System.out.println("Enter group members username (separated by spaces)");
                            scanUsers(scanner.nextLine().trim());
                        } else {
                            scanUsers(nextLine);
                        }
                        System.out.println("Created group " + groupName);
                        break;
                    case "--help":
                        command = scanner.nextLine().trim();
                        if (command == null || command == "") {
                            System.out.println("Commands: " + Arrays.toString(commands));
                            System.out.println("For more info: --help <command>");
                        } else {
                            processHelp(command);
                        }
                        break;
                    case "--adduser":
                        String user = scanner.next().trim();
                        System.out.println(findUser(user) ? "Added Friend" : "Can't find user");
                        break;
                    case "--signin":
                        String authorizationUrl = "https://github.com/login/oauth/authorize?client_id=Iv1.e7597fd0dd9b7d63&redirect_uri=http://localhost:8080/";
                        System.out.println("Please visit the following URL to authenticate:");
                        System.out.println(authorizationUrl);
                        System.out.println("Enter Username:");
                        username = scanner.next().trim();
                        System.out.println(username + "+++++++++++++++++++++++++++++");
                        getClientID();
                        break;
                    case "--addgroupmember":
                        String group = scanner.next().trim();
                        System.out.println(findGroup(group) ? "Found Group" : "Wrong Group Entered");
                        scanUsers(scanner.nextLine().trim());
                        break;
                    case "--viewgroup":
                        group = scanner.next().trim();
                        System.out.println(group + ": " + viewMembers(group));
                        break;
                    case "--login":
                        System.out.println("Duncan begin");
                        login();
                        break;
                    default:
                        if (command.startsWith("--")) {
                            System.err.println("Incorrect command entered.");
                        } else if (msgTrigger) {
                            System.out.println(processMsg(command));
                        } else {
                            System.err.println(
                                    "Unknown command entered.\nType --help <enter> to see all commands available");
                        }
                        break;
                }
            } catch (Exception e) {
                System.err.println("Mishap: ");
                e.printStackTrace();
            }
        }

    }

    private void login() throws URISyntaxException, IOException, InterruptedException {
        String clientId_secret = "Iv1.e7597fd0dd9b7d63";
        String redirect_uri = "http://localhost:8080/";
        String clientLoginURL = "https://github.com/login/oauth/authorize?client_id=" + clientId_secret
                + "&redirect_uri=" + redirect_uri;
        System.out.println(clientLoginURL);

        // HttpRequest request = HttpRequest.newBuilder()
        // .uri(new URI(clientLoginURL))
        // .headers("Content-Type", "text/plain;charset=UTF-8")
        // .POST(HttpRequest.BodyPublishers.noBody())
        // .build();
        // HttpResponse resp = client.send(request, BodyHandlers.ofString());
        // System.err.println(resp.body());
    }

    private String processMsg(String command) throws URISyntaxException, IOException, InterruptedException {
        String msg = command.trim() + " " + scanner.nextLine().trim();
        sendMessage(msg);
        return "Me: " + msg;
    }

    private String viewMembers(String group) {
        return "Place Holder Group Members";
    }

    private boolean findGroup(String group) {
        return true;
    }

    private void processHelp(String command) {
        command = command.replace("--", "");
        switch (command.toLowerCase()) {
            case "exit":
                System.out.println("Exits the program");
                break;
            case "msg":
                System.out.println("Send a message to a specified user");
                System.out.println("--msg <user> <message contents>");
                break;
            case "creategroup":
                System.out.println("Creates a group for specified users with a unique group name");
                System.out.println("--createGroup <GroupName> <User>[, <User>]");
                break;
            case "adduser":
                System.out.println("Adds a user to your friend list");
                System.out.println("--addUser <User>");
                break;
            case "signin":
                System.out.println("Signs in");
                break;
            case "addgroupmember":
                System.out.println("Adds users to a specified group");
                System.out.println("--addGroupMember <Group Name> <User>[ <User>]");
                break;
            case "viewgroup":
                System.out.println("Prints the group members within a specified group");
                System.out.println("--viewGroup <Group Name>");
                break;
            default:
                System.err.println("Command not applicable");
                break;
        }
    }

    private void scanUsers(String users) {
        // String users = scanner.nextLine().trim();
        String[] usersArr = users.split(" ");
        logger.log(Level.WARNING, Arrays.toString(usersArr));
        for (String s : usersArr) {
            System.out.println(findUser(s) ? "Added Users" : "Cannot find " + s);
        }
    }

    private boolean findUser(String s) {
        return true;
    }

    private HttpResponse<String> post(String extension, HttpRequest.BodyPublisher publisher)
            throws URISyntaxException, IOException, InterruptedException {
        System.out.println(baseURI + extension);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseURI + extension))
                .headers("Content-Type", "text/plain;charset=UTF-8")
                .POST(publisher)
                .build();
        return client.send(request, BodyHandlers.ofString());
    }

    private HttpResponse<String> get(String extension, String header, String value)
            throws URISyntaxException, IOException, InterruptedException {
        System.out.println(baseURI + extension);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseURI + extension))
                .header(header, value)
                .GET()
                .build();
        return client.send(request, BodyHandlers.ofString());
    }

    private void displayConvo() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("DEBUG: Display Convo");
        String resp = get("/chats", "Get", "value").body();
        JsonArray convertedObject = new Gson().fromJson(resp, JsonArray.class);
        convertedObject.asList().stream()
                .forEach(o -> System.out.println(o.getAsJsonObject().get("chatID").toString()));
        System.out.println(convertedObject.asList());
        System.out.println();
        System.out.println(get("/messages", "get", "value").body());
    }

    private void startMessage() throws URISyntaxException, IOException, InterruptedException {
        globalUser = scanner.next().trim();
        String msg = scanner.nextLine().trim();
        System.out.println("Started coversation with " + globalUser);
        System.out.println("Sending to " + globalUser + (msg == "" ? ":" : (":\nMe:" + msg)));
        String json = jsonifyString(new Chat(-1, username, globalUser));
        HttpResponse<String> response = post("/chats",
                HttpRequest.BodyPublishers.ofString(json));
        if (msg != "")
            sendMessage(msg);

    }

    private void getClientID() throws URISyntaxException, IOException, InterruptedException {
        try {
            String ans = get("/users", "username", username).body();
            clientID = Integer.parseInt(ans);
            System.out.println("DEF ANS: " + ans);
        } catch (Exception e) {
            System.out.println("New Username: ");
            username = scanner.next();
            System.out.println("Enter email: ");
            String email = scanner.next();
            System.out.println("Enter Phone number (Integer): ");
            String number = scanner.next();
            String contents = username + "," + email + "," + number;
            post("/users", HttpRequest.BodyPublishers.ofString(contents));
        }
        System.out.println(clientID);
        System.out.println("GET: Client ID");
    }

    private void sendMessage(String msg) throws URISyntaxException, IOException, InterruptedException {
        String json = jsonifyString(new Message(username, globalUser, msg));
        System.out.println("DEBUG json: " + json);
        HttpResponse<String> response = post("/messages", HttpRequest.BodyPublishers.ofString(json));
        System.out.println(response.body());
    }

    private String jsonifyString(Object o) {
        Gson gson = new Gson();
        return gson.toJson(o);
    }
    /*
     * Registerd app
     * gotten secrets
     * istall libraries (maven dependencies)
     * 
     */
}