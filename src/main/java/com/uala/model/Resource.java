package com.uala.model;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Resource {
  
  private String title;
  private List<Award> awards;
  private Integer releaseYear;
  private Integer seasons;
  private ResourceType resourceType;
  private Integer durationMinutes;
  
  
  
  public String getTitle() {
    return title;
  }



  public void setTitle(String title) {
    this.title = title;
  }



  public List<Award> getAwards() {
    return awards;
  }



  public void setAwards(List<Award> awards) {
    this.awards = awards;
  }



  public Integer getReleaseYear() {
    return releaseYear;
  }



  public void setReleaseYear(Integer releaseYear) {
    this.releaseYear = releaseYear;
  }



  public Integer getSeasons() {
    return seasons;
  }



  public void setSeasons(Integer seasons) {
    this.seasons = seasons;
  }



  public ResourceType getResourceType() {
    return resourceType;
  }



  public void setResourceType(ResourceType resourceType) {
    this.resourceType = resourceType;
  }



  public Integer getDurationMinutes() {
    return durationMinutes;
  }



  public void setDurationMinutes(Integer durationMinutes) {
    this.durationMinutes = durationMinutes;
  }



  public Boolean isInteresting() {
    
    if(seasons>=4 && seasons<=5 &&  resourceType == ResourceType.SERIE) {
      return true;
    }else if(awards.stream().filter(award->award.getName().contains("Oscar"))
        .collect(Collectors.toList()).size()>0 && resourceType == ResourceType.MOVIE ) {
      return true;
    }else if(title.contains("unofficial") && resourceType == ResourceType.DOCUMENTARY) {
      return true;
    }
    return false;    
  }
}
