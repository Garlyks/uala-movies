package com.uala.model.MoodState;

import com.uala.model.resource.Movie;
import com.uala.model.resource.Resource;

public class Happy implements MoodState {

  @Override
  public boolean isInterestedIn(Resource resource) {
    if (resource instanceof Movie && resource instanceof Movie) {
      return true;
    }
    return false;
  }
}
