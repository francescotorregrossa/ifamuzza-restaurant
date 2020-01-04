package com.ifamuzzarestaurant.model;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.prefs.Preferences;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Auth {

    private User user;
    private static Auth instance;

    private final CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();

    private final Preferences prefs = Preferences.userNodeForPackage(Auth.class);
    private final String ACCESS_TOKEN_NAME = "@ifamuzza:accesstoken";

    private Auth() {
        httpClient.start();

        String accessToken = prefs.get(ACCESS_TOKEN_NAME, null);
        if (accessToken != null) {
            System.out.println("AccessToken found.");

            HttpGet get = new HttpGet("http://127.0.0.1:8080/api/userForAccessToken");
            get.addHeader("accessToken", accessToken);

            httpClient.execute(get, new FutureCallback<HttpResponse>() {

                @Override public void failed(final Exception ex) { System.out.println("Request failed. " + ex.getMessage()); }
                @Override public void cancelled() { System.out.println("Request failed."); }
                @Override public void completed(final HttpResponse response) {

                    if (response.getStatusLine().getStatusCode() != 200) {
                        Header reason = response.getFirstHeader("reason");
                        failed(new Exception(reason != null ? reason.getValue() : "unknown error"));
                        return;
                    }

                    ObjectMapper mapper = new ObjectMapper();
                    User result;
                    if (response.getEntity() != null) {
                        String content;
						try {
                            content = EntityUtils.toString(response.getEntity());
                            JsonNode jsonObject = mapper.readTree(content);
                            result = new User(jsonObject);
                            result.setAccessToken(accessToken);
                            System.out.println("User data retrieved.");
						} catch (ParseException | IOException e) {
                            e.printStackTrace();
                            failed(e);
                            return;
						}
                    }
                    else {
                        failed(new Exception("unknown error"));
                        return;
                    }
                }
            });


        }
        else {
            System.out.println("AccessToken not found.");
        }

    }

    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return this.user != null;
    }

    public User getUser() {
        return this.user;
    }


    public void login(String email, String password, FutureCallback<User> resultCallback) {
     
        prefs.remove(ACCESS_TOKEN_NAME);

        HttpPost post = new HttpPost("http://127.0.0.1:8080/api/restaurantLogin");
        post.addHeader("Content-Type", "application/json");

		try {

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.createObjectNode();
            node.put("email", email);
            node.put("password", password);

            StringEntity json = new StringEntity(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(node));
            post.setEntity(json);

            httpClient.execute(post, new FutureCallback<HttpResponse>() {

                @Override public void failed(final Exception ex) { resultCallback.failed(ex); }
                @Override public void cancelled() { resultCallback.cancelled(); }
                @Override public void completed(final HttpResponse response) {

                    if (response.getStatusLine().getStatusCode() != 200) {
                        Header reason = response.getFirstHeader("reason");
                        failed(new Exception(reason != null ? reason.getValue() : "unknown error"));
                        return;
                    }
                    
                    User result;
                    if (response.getEntity() != null) {
                        String content;
						try {
                            content = EntityUtils.toString(response.getEntity());
                            JsonNode jsonObject = mapper.readTree(content);
                            result = new User(jsonObject);

						} catch (ParseException | IOException e) {
                            e.printStackTrace();
                            failed(e);
                            return;
						}
                    }
                    else {
                        failed(new Exception("unknown error"));
                        return;
                    }

                    if (response.getFirstHeader("set-cookie") != null) {
                        String cookie = response.getFirstHeader("set-cookie").getValue();
                        int start = cookie.indexOf("accessToken=");
                        if (start == -1) {
                            failed(new Exception("accessToken"));
                            return;
                        }

                        String accessToken = cookie.substring(start + "accessToken=".length());
                        accessToken = accessToken.substring(0, 64);
                        result.setAccessToken(accessToken);
                        prefs.put(ACCESS_TOKEN_NAME, accessToken);
                    }
                    
                    resultCallback.completed(result);
                }

            });
            
		} catch (IOException e) {
            e.printStackTrace();
            resultCallback.failed(e);
		}        
        
    }

    public void signup(User user, String password) {

    }

}