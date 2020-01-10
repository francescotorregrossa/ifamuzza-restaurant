package com.ifamuzzarestaurant.model;

import java.util.*;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class User implements Validable, JsonPrivateSerialization {

    private String email;
    private String password;
    private String accessToken;
    private String name;
    private String address;
    private String[] openingTime;
    private String phone;
    private Integer downPayment;
    private ReceiptMethod receiptMethod;


    public User() {

    }
    
    public User(JsonNode data) {
        JsonNode receiptMethod = data.get("receiptMethod");
        setEmail(getString(data, "email"));
        setPassword(getString(data, "password"));
        setName(getString(data, "name"));
        setAddress(getString(data, "address"));
        
        JsonNode openingTime = data.get("openingTime");
        if (openingTime != null && openingTime.isArray()) {
            ArrayList<String> times = new ArrayList<>();
            for (final JsonNode time: openingTime) {
                String t = time.asText(null);
                if (t != null) {
                    times.add(t);
                }
            }
            setOpeningTime(times.toArray(new String[times.size()]));
        }
    
        setPhone(getString(data, "phone"));
        setDownPayment(getInt(data, "downPayment"));

    }

    @Override
    public ObjectNode serialize() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode openingTime = mapper.createArrayNode();
        for (String time: getOpeningTime()) {
            openingTime.add(time);
        }
        ObjectNode node = mapper.createObjectNode();
        node.put("email", getEmail());
        node.put("password", getPassword());
        node.put("name", getName());
        node.put("address", getAddress());
        node.set("openingTime", openingTime);
        node.put("phone", getPhone());
        node.put("downPayment", getDownPayment());
        node.set("receiptMethod", getReceiptMethod().serialize());
        return node;
    }
  
  
    @Override
    public List<String> validate() {

        List<String> reasons = new ArrayList<>();
        if (email == null) {
            reasons.add("email");
        }
        else {
            email = email.trim();
            // check email correctness
            Pattern emailPattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
            if (!emailPattern.matcher(email).matches()) {
                reasons.add("email");
            }
        }

        if (password == null) {
            reasons.add("password");
        }
        else {
            password = password.trim();
            // check password strength
            Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
            if (!passwordPattern.matcher(password).matches()) {
                reasons.add("password");
            }
        }

        
        if (name == null) {
            reasons.add("name");
        }
        else {
            Pattern namePattern = Pattern.compile("^([a-zA-Z ]){2,200}$");
            if (!namePattern.matcher(name).matches()) {
                reasons.add("name");
            }
        }

        if (address == null) {
            reasons.add("address");
        }
        else {
            Pattern addressPattern = Pattern.compile("^([a-zA-Z0-9 ,]){2,200}$");
            if (!addressPattern.matcher(address).matches()) {
                reasons.add("address");
            }
        }

        if (openingTime == null || (openingTime.length == 0 || openingTime.length > 7)) {
            reasons.add("openingTime");
        }
        else {  
            Pattern openingTimePattern = Pattern.compile("^((mon)|(tue)|(wed)|(thu)|(fri)|(sat)|(sun)) (([01]?[0-9]|2[0-3]):[0-5][0-9]-([01]?[0-9]|2[0-3]):[0-5][0-9])$");
            for (String ot: openingTime) {
                if (!openingTimePattern.matcher(ot).matches()) {
                    reasons.add("openingTime");
                }
            }
        }

        if (phone == null) {
            reasons.add("phone");
        }
        else {
            Pattern phonePattern = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");
            if (!phonePattern.matcher(phone).matches()) {
                reasons.add("phone");
            }
        }


        if (downPayment == null) {
            reasons.add("downPayment");
        }
        else {
            if (downPayment < 0 || downPayment > 100) {
                reasons.add("downPayment");
            }
        }

        if (receiptMethod == null) {
            reasons.add("receiptMethod");
        }
        else {
            List<String> receiptValidation = receiptMethod.validate();
            reasons.addAll(receiptValidation);
        }      

        return reasons;
    }


    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }

    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken ; }

    public String getName() { return this.name; }
    public void setName(String name){ this.name = name; }

    public String getAddress() { return this.address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone(){ return this.phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getDownPayment() { return this.downPayment; }
    public void setDownPayment(Integer downPayment) { this.downPayment = downPayment; }

    public String[] getOpeningTime() { return this.openingTime; }
    public void setOpeningTime(String[] openingTime) { this.openingTime = openingTime; }

    public ReceiptMethod getReceiptMethod() { return this.receiptMethod; }
    public void setReceiptMethod(ReceiptMethod method) { this.receiptMethod = method; }

    private static String getString(JsonNode node, String key) {
        JsonNode data = node.get(key);
        if (data != null) {
            return data.asText(null);
        }
        return null;
    }
    
    private static Integer getInt(JsonNode node, String key) {
        JsonNode data = node.get(key);
        if (data != null) {
            return data.asInt(0);
        }
        return null;
    }

}