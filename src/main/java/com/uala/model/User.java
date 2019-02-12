package com.uala.model;

import java.time.ZonedDateTime;

public class User {
  
  private Integer id;
  private String firstName;
  private String lastName;
  private MoodState moodState;
  private Suscription suscription;
  private Resource actualResourceWatching;
  
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
  
  public Boolean isWatchingSomethingInteresting() {
    if(actualResourceWatching.isInteresting()) {
      return true;
    }else if(moodState == MoodState.SAD && actualResourceWatching.getDurationMinutes()>=120) {
      return true;
    }else if(moodState == MoodState.HAPPY && actualResourceWatching.getResourceType() == ResourceType.MOVIE) {
      return true;
    } else if(moodState == MoodState.MELANCHOLIC && 
        (ZonedDateTime.now().getYear()-actualResourceWatching.getReleaseYear())>=10) {//aclarar tecnisismo "hace más de 10 años"
      return true;
    }
    return false;
  }
  
  
}
