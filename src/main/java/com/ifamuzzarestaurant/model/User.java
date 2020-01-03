package com.ifamuzzarestaurant.model;

import java.util.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class User {
    private Integer id;
    private String email;
    private String accessToken;
    private String name;
    private String address;
    private String[] openingTime;
    private String phoneNumber;
    private String acconto;
    private ReceiptMethod receiptMethod;

    /*
    public User(JsonNode data) {
        setEmail(JsonUtils.getString(data, "email"));
        setPassword(JsonUtils.getString(data, "password"));
        JsonNode receiptMethod = data.get("receiptMethod");
        setName(JsonUtils.getString(data, "name"));
        setAddress(JsonUtils.getString(data, "address"));

        JsonNode openingTime = data.get("openingTime");
        if (openingTime != null && openingTime.isArray()) {
            ArrayList<String> times = new ArrayList<>();
            for (final JsonNode time : openingTime) {
                String t = time.asText(null);
                if (t != null) {
                    times.add(t);
                }
            }
            setOpeningTime(times.toArray(new String[times.size()]));
        }

        setPhone(JsonUtils.getString(data, "phone"));
        setDownPayment(JsonUtils.getInt(data, "downPayment"));

        if (receiptMethod != null) {
            String type = JsonUtils.getString(receiptMethod, "type");
            switch (type) {
            case "creditcard":
                setReceiptMethod(new ReceiptCreditCard(receiptMethod));
                break;
            case "paypal":
                setReceiptMethod(new ReceiptPayPal(receiptMethod));
                break;
            default:
                break;
            }
        }
    }
    */

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id ){
        this.id = id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        if(email.length()==0){
            this.email = null;
        }
        else{
            this.email = email;
        }
    }

    public String getAccessToken(){
        return this.accessToken;
    }

    public void setAccessToken(String accessToken){
        if(accessToken.length()==0){
            this.accessToken = null;
        }
        else{
            this.accessToken = accessToken;
        }
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return this.address;
    }

    public void setAddress(String address){
        if(address.length()==0){
            this.address = null;
        }
        else{
            this.address = address;
        }
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        if(phoneNumber.length()==0){
            this.phoneNumber = null;
        }
        else{
            this.phoneNumber = phoneNumber;
        }
    }
}