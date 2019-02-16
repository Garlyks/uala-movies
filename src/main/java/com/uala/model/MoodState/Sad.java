package com.uala.model.MoodState;

import com.uala.model.resource.Movie;
import com.uala.model.resource.Resource;

public class Sad implements MoodState {

  @Override
  public boolean isInterestedIn(Resource resource) {
    if (resource instanceof Movie && ((Movie) resource).getDurationMinutes() > 120) {
      return true;
    }
    return false;
  }
}
