package com.uala.model.payment;

import java.math.BigDecimal;

public class PayPalPayMethod extends PayMethod {
  
  @Override
  public void getMoney(BigDecimal ammount) throws Exception{    
    //here goes some logic about get money
    //that should throw if fails
    System.out.println("Payed using paypal");
  }

}
