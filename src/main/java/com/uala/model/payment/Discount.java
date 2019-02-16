package com.uala.model.payment;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.uala.model.subscription.Subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Discount {
  private String name;
  private Double rate;
  
  public static List<Discount> getEspecificDiscounts(Subscription suscription, PayMethod payMethod){
    List<Discount> listResult = new ArrayList<>();
    if(payMethod instanceof PayPalPayMethod) {
      listResult.add(new Discount("Paypal",2d));
    }    
    return listResult;
  };
  
  public static List<Discount> getGeneralDiscounts(Subscription suscription){
  //for now only one general discount    
    return Arrays.asList(new Discount(
        "Years discount",
        suscription.getStartDate().until(ZonedDateTime.now(), ChronoUnit.YEARS)*3d));
  };
}
