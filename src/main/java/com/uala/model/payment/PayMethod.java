package com.uala.model.payment;

import java.math.BigDecimal;

public abstract class PayMethod {
  public abstract void getMoney(BigDecimal ammount) throws Exception;
}
