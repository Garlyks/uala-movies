package com.uala.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class Payment {
  private Integer id;
  private BigDecimal ammount;
  private List<Discount> discounts;
  private ZonedDateTime paymentDate;
  

}
