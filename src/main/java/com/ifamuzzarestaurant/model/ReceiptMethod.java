package com.ifamuzzarestaurant.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ReceiptMethod implements Validable, JsonPrivateSerialization {

    private Integer id;
    private String holder;
    private String address;

    @Override
    public ObjectNode serialize() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("holder", getHolder());
        node.put("address", getAddress());
        return node;
    }
    
    @Override
    public List<String> validate() {
        List<String> reasons = new ArrayList<>();

        if (holder == null) {
            reasons.add("holder");
        }
        else {
            Pattern holderPattern = Pattern.compile("^([a-zA-Z ]){2,200}$");
            if (!holderPattern.matcher(holder).matches()) {
                reasons.add("holder");
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

        return reasons;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
  
    public String getHolder() { return holder; }
    public void setHolder(String holder) { this.holder = holder.trim(); }
  
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address.trim(); }

}