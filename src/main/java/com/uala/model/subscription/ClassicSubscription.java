package com.uala.model.subscription;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class ClassicSubscription extends Subscription{
  
  public ClassicSubscription() {
    ZonedDateTime date = ZonedDateTime.now();
    this.setStartDate(date);
    this.setEndDate(date);
    this.setPayments(new ArrayList<>());
    this.setCost(BigDecimal.valueOf(100));
    this.setActualConnections(0);
    this.setMaxConnections(1);
  }
}
