package com.uala.model.subscription;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import com.uala.model.payment.Discount;
import com.uala.model.payment.PayMethod;
import com.uala.model.payment.Payment;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public abstract class Subscription {
  private ZonedDateTime startDate;
  private ZonedDateTime endDate;
  private List<Payment> payments;
  private Integer actualConnections;
  private Integer maxConnections;
  private BigDecimal cost;
  
  
  public void paySubscription(PayMethod payMethod) {
    Payment payment = new Payment(payMethod);
    payment.setTotalAmmount(getCost());
    
    payment.getDiscounts().addAll(Discount.getGeneralDiscounts(this));
    payment.getDiscounts().addAll(Discount.getEspecificDiscounts(this, payment.getPayMethod()));

    payment.setDiscountedAmmount(payment.getTotalAmmount()
        .multiply(BigDecimal.valueOf(payment.getDiscounts().stream().mapToDouble(discount -> discount.getRate()).sum()))
        .divide(BigDecimal.valueOf(100)));

    payment.setPaymentDate(ZonedDateTime.now());

    try {
      payment.getPayMethod().getMoney(payment.getNetAmmount());
    } catch (Exception e) {
      throw new RuntimeException();// here should be a custom exception 
    }
    
    // if everything goes right you add a month and persist
    setEndDate(getEndDate().plusMonths(1));    
    payments.add(payment);
  };
  
  public boolean connectDevice() {
    if(ZonedDateTime.now().toEpochSecond()>=endDate.toEpochSecond()) {
      return false;
    }
    if(actualConnections<maxConnections) {
      actualConnections++;
      return true;
    }
    return false;
  };
  
  public void disconnectDevice() {
    if(actualConnections>0) {
      actualConnections--;
    }else {
      throw new RuntimeException();//should be a custom exception
    }
  }
  
}
