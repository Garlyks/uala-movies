package com.uala.model;

import java.util.List;
import java.util.stream.Collectors;

import com.uala.model.MoodState.MoodState;
import com.uala.model.resource.Resource;
import com.uala.model.subscription.Subscription;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {

  private Integer id;
  private String firstName;
  private String lastName;
  private MoodState moodState;
  private Subscription suscription;
  private List<Resource> actualResourcesWatching;

  
  public boolean isWatchingSomethingInteresting() {
    //si el recurso es interesante o su estado de amimo lo lleva a ser interesante
    if(!actualResourcesWatching.stream()
          .filter(resource->resource.IsInteresting())
          .collect(Collectors.toList()).isEmpty() ||
       !actualResourcesWatching.stream()
          .filter(resource->moodState.isInterestedIn(resource))
          .collect(Collectors.toList()).isEmpty()) {
      return true;
    }
    return false;
  }
}
