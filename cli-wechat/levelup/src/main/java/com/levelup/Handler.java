package com.levelup;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.Security;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.levelup.model.*;
import com.sun.net.httpserver.HttpServer;
import com.google.gson.JsonObject;


public class Handler extends Thread {
    private Scanner scanner;
    private Logger logger;
    private final String baseURI = "http://wechat-beans-app.eu-west-1.elasticbeanstalk.com";
    // private final String baseURI = "http://localhost:8080";
    private String globalUser = "";
    private String username = "";
    private HttpClient client;
    private int clientID;
    private String accessToken = "";

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
                        String code = login();
                        this.accessToken = getAccessToken(code);
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
                        String accessCode = login();
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

    private Optional<String> code = Optional.empty();

    private void checkAccessToken(){
        if (this.accessToken == "") {
            System.out.println("Please login first");
        }
    }

    private String login() throws URISyntaxException, IOException, InterruptedException {
        String stringCode = "";
        String clientId = "baacd8518020cf9e7322";
        String clientLoginURL = "https://github.com/login/oauth/authorize?client_id=" + clientId
                + "&scope=user";
        System.out.println(clientLoginURL);
        String resp = "Authentication Successful, you can close window";
        try {

            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/login", exchange -> {
                String query = exchange.getRequestURI().getQuery();
                if (query != null && query.startsWith("code=")) {
                    code = Optional.of(query.substring(5));
                    exchange.sendResponseHeaders(200, resp.getBytes().length);
                    exchange.getResponseBody().write(resp.getBytes());
                } else {
                    exchange.sendResponseHeaders(401, 0);
                }
                exchange.close();
                server.stop(0);
            });
            server.start();
            while (code.isEmpty()) {
                Thread.sleep(50);
            }
            stringCode = code.get();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return stringCode;
    }

    private String getAccessToken(String code)
            throws URISyntaxException, IOException, InterruptedException {

                HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://github.com/login/oauth/access_token"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(
                        String.format("client_id=%s&client_secret=%s&code=%s", "baacd8518020cf9e7322", "d95a1c43b6651c50ed47a58c109f648c45d3f3b2",
                                URLEncoder.encode(code, "UTF-8"))))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.statusCode()==200? response.body().split("&")[0].split("=")[1]: "";
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
                .header("Authorization", "Bearer "+this.accessToken)
                .POST(publisher)
                .build();
        return client.send(request, BodyHandlers.ofString());
    }

    private HttpResponse<String> get(String extension, String header, String value)
            throws URISyntaxException, IOException, InterruptedException {
        System.out.println(baseURI + extension);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseURI + extension))
                .header("Authorization", "Bearer "+this.accessToken)
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