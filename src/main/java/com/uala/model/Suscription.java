package com.uala.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Suscription {
  private ZonedDateTime startDate;
  private ZonedDateTime endDate;
  private SuscriptionType suscriptionType;
  private List<Payment> payments;
  private Double cost;
  
  public Suscription(ZonedDateTime startDate, SuscriptionType suscriptionType) {
    this.startDate = startDate;
    this.endDate = startDate;
    this.suscriptionType = suscriptionType;
    this.payments = new ArrayList<>();
    if(suscriptionType==SuscriptionType.CLASSIC) {
      cost=100d;
    }else if(suscriptionType==SuscriptionType.GOLD) {
      cost=200d;
    }else if(suscriptionType==SuscriptionType.GOLD) {
      cost=300d;
    }
  }  
  
  public void addPayment(Payment payment) {
    payments.add(payment);
  }  
  public ZonedDateTime getStartDate() {
    return startDate;
  }
  public void setStartDate(ZonedDateTime startDate) {
    this.startDate = startDate;
  }
  public ZonedDateTime getEndDate() {
    return endDate;
  }
  public void setEndDate(ZonedDateTime endDate) {
    this.endDate = endDate;
  }
  public SuscriptionType getSuscriptionType() {
    return suscriptionType;
  }
  public void setSuscriptionType(SuscriptionType suscriptionType) {
    this.suscriptionType = suscriptionType;
  }
  public List<Payment> getPayments() {
    return payments;
  }
  public void setPayments(List<Payment> payments) {
    this.payments = payments;
  }
  public Double getCost() {
    return cost;
  }
  public void setCost(Double cost) {
    this.cost = cost;
  }
  
}
