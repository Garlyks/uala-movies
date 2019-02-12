package com.uala.model;

import java.time.ZonedDateTime;
import java.util.List;

public class Suscription {
  private ZonedDateTime startDate;
  private ZonedDateTime endDate;
  private SuscriptionType suscriptionType;
  private List<Payment> payments;
}
