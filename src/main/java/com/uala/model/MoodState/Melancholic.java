package com.uala.model.MoodState;

import java.time.ZonedDateTime;

import com.uala.model.resource.Movie;
import com.uala.model.resource.Resource;

public class Melancholic implements MoodState {

  @Override
  public boolean isInterestedIn(Resource resource) {
    if (resource instanceof Movie && 
        ZonedDateTime.now().getYear() -((Movie) resource).getReleaseYear() >=10) {
      return true;
    }
    return false;
  }
}
