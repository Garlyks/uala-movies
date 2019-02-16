package com.uala.model.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Documentary implements Resource {
  private String title;

  @Override
  public boolean IsInteresting() {
    if(title.contains("unofficial")) {
      return true;
    }
    return false;
  }
  
  
}
