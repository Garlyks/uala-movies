package com.uala.model;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

  private Integer id;
  private String firstName;
  private String lastName;
  private MoodState moodState;
  private Suscription suscription;
  private Resource actualResourceWatching;
  private Integer actualConnections;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public MoodState getMoodState() {
    return moodState;
  }

  public void setMoodState(MoodState moodState) {
    this.moodState = moodState;
  }

  public Suscription getSuscription() {
    return suscription;
  }

  public void setSuscription(Suscription suscription) {
    this.suscription = suscription;
  }

  public Resource getActualResourceWatching() {
    return actualResourceWatching;
  }

  public void setActualResourceWatching(Resource actualResourceWatching) {
    this.actualResourceWatching = actualResourceWatching;
  }

  public Integer getActualConnections() {
    return actualConnections;
  }

  public void setActualConnections(Integer actualConnections) {
    this.actualConnections = actualConnections;
  }

  public Boolean isWatchingSomethingInteresting() {
    if (actualResourceWatching.isInteresting()) {
      return true;
    }
    if (actualResourceWatching.getResourceType() == ResourceType.MOVIE) {
      if (moodState == MoodState.SAD && actualResourceWatching.getDurationMinutes() >= 120) {
        return true;
      } else if (moodState == MoodState.HAPPY) {
        return true;
      } else if (moodState == MoodState.MELANCHOLIC
          && (ZonedDateTime.now().getYear() - actualResourceWatching.getReleaseYear()) >= 10) {// aclarar tecnisismo
                                                                                               // "hace más de 10
                                                                                               // años"
        return true;
      }
    }

    return false;
  }

  /**
   * this should calculate and automatic debit the ammount
   * 
   * @param payment
   *          type used
   * @return the paymentId
   */
  public String paySubscription(PaymentType paymentType) {
    suscription.setEndDate(suscription.getEndDate().plusMonths(1));
    List<Discount> discounts = new ArrayList<>();
    BigDecimal paymentValue = BigDecimal.valueOf(suscription.getCost());

    if (paymentType == PaymentType.PAYPAL) {
      discounts.add(new Discount(UUID.randomUUID().toString(), "PAYPAL", 2d));
      paymentValue.subtract(paymentValue.multiply(BigDecimal.valueOf(0.02d)));
    }

    long yearsRegistered = suscription.getStartDate().until(ZonedDateTime.now(), ChronoUnit.YEARS);
    if (yearsRegistered > 0) {
      discounts.add(new Discount(UUID.randomUUID().toString(), "PAYPAL", Double.valueOf(yearsRegistered * 3)));
      paymentValue.subtract(paymentValue.multiply(BigDecimal.valueOf(yearsRegistered * 3 / 100)));
    }
    Payment payment = new Payment(paymentValue, paymentType, discounts);
    suscription.addPayment(payment);
    return payment.getId();
  }

  /**
   * this method start a new session
   * 
   * @return if was suscesfull
   */
  public Boolean startSession() {
    
    //validate active connections
    switch (suscription.getSuscriptionType()) {
    case CLASSIC:
      if(actualConnections>0) {
        return false;
      }
      break;
    case GOLD:
      if(actualConnections>=3) {
        return false;
      }
      break;
    case PLATINIUM:
      if(actualConnections>=5) {
        return false;
      }
      break;
    }
    //validate suscription time
    if(suscription.getEndDate().isBefore(ZonedDateTime.now())) {
      return false;
    }
    actualConnections++;
    return true;
  }

}
