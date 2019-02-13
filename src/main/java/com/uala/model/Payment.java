package com.uala.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Payment {
  private String id;
  private BigDecimal ammount;
  private BigDecimal ammountPaid;
  private List<Discount> discounts;
  private ZonedDateTime paymentDate;
  private PaymentType paymentType;
  
  
  public Payment(BigDecimal ammount, PaymentType paymentType, List<Discount> discounts) {
    id = UUID.randomUUID().toString();
    this.ammount = ammount;
    paymentDate = ZonedDateTime.now();
    this.paymentType = paymentType;
    discounts = new ArrayList<>();
  }


  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public BigDecimal getAmmount() {
    return ammount;
  }

  public List<Discount> getDiscounts() {
    return discounts;
  }


  public void setDiscounts(List<Discount> discounts) {
    this.discounts = discounts;
  }


  public ZonedDateTime getPaymentDate() {
    return paymentDate;
  }


  public void setPaymentDate(ZonedDateTime paymentDate) {
    this.paymentDate = paymentDate;
  }
  
  
  

}
