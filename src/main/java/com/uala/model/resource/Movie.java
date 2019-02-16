package com.uala.model.resource;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Resource{
  private String title;
  private Integer releaseYear;  
  private Integer durationMinutes;
  
  //awards: we could handle more details but only the name for now is enougth
  private List<String> awards;
  
  
  @Override
  public boolean IsInteresting() {
    if(awards.stream().filter(award->award.contains("Oscar"))
    .collect(Collectors.toList()).size()>0) {
      return true;
    }
    return false;
  }
}
