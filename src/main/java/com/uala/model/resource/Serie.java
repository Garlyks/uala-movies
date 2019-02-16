package com.uala.model.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Serie implements Resource {
  private String title;
  private Integer seasonNumber;

  @Override
  public boolean IsInteresting() {
    if (seasonNumber == 4 || seasonNumber == 5) {
      return true;
    }
    return false;
  }

}
