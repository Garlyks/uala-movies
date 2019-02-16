package com.uala.model.payment;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
  private BigDecimal totalAmmount;
  private BigDecimal discountedAmmount;
  private ZonedDateTime paymentDate;
  private Double discountRate;
  private List<Discount> discounts = new ArrayList<>();
  private PayMethod payMethod;
  private PaymentStatus status;
  
  public Payment(PayMethod payMethod) {
    totalAmmount = BigDecimal.ZERO;
    discountedAmmount = BigDecimal.ZERO;
    discounts = new ArrayList<>();
    this.payMethod=payMethod;
    status = PaymentStatus.CREATED;
  }
  
  public BigDecimal getNetAmmount() {
    return getTotalAmmount().subtract(getDiscountedAmmount());
  }
  
}
