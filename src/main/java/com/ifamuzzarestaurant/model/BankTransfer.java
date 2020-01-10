package com.ifamuzzarestaurant.model;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ifamuzzarestaurant.utils.IbanTest;

public class BankTransfer extends ReceiptMethod {

  private String iban;

  public BankTransfer() {
    super();
  }

  @Override
  public ObjectNode serialize() {
    ObjectNode node = super.serialize();
    node.put("iban", getIBAN());
    node.put("type", "banktransfer");
    return node;
  }

  @Override
  public List<String> validate() {
    List<String> reasons = super.validate();

    if (iban == null) {
      reasons.add("iban");
    }
    else {
      if (!IbanTest.ibanTest(iban)) {
        reasons.add("iban");
      }
    }

    return reasons;    
  }

  public String getIBAN() { return iban; }
  public void setIBAN(String iban) { this.iban = iban.trim(); }

}
