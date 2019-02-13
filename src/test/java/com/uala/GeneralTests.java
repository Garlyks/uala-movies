package com.uala;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.ZonedDateTime;
import java.util.Arrays;

import org.junit.Test;

import com.uala.model.Award;
import com.uala.model.MoodState;
import com.uala.model.Resource;
import com.uala.model.ResourceType;
import com.uala.model.Suscription;
import com.uala.model.SuscriptionType;
import com.uala.model.User;

public class GeneralTests {
  
  @Test
  public void resourceIsInterestingTest() {
    Resource resourceTest1 = new Resource();  
    resourceTest1.setSeasons(5);
    resourceTest1.setResourceType(ResourceType.SERIE);
    assertTrue(resourceTest1.isInteresting());
    
    Resource resourceTest2 = new Resource();  
    Award oscarAward = new Award();
    oscarAward.setName("Oscar al mejor actor");
    resourceTest2.setAwards(Arrays.asList(oscarAward));
    resourceTest2.setResourceType(ResourceType.MOVIE);
    assertTrue(resourceTest2.isInteresting());
    
    Resource resourceTest3 = new Resource(); 
    resourceTest3.setTitle("Las pruebas del delito (unofficial)");
    resourceTest3.setResourceType(ResourceType.DOCUMENTARY);
    assertTrue(resourceTest3.isInteresting());
  }
  
  @Test
  public void userIsWatchingSOmethingInterestingTest() {
    User user = new User();
    Resource resource = new Resource();
    resource.setResourceType(ResourceType.MOVIE);
    resource.setReleaseYear(1980);
    user.setMoodState(MoodState.MELANCHOLIC);//melancolico le gustan las peliculas de mas de 10 años
    user.setActualResourceWatching(resource);
    assertTrue(user.isWatchingSomethingInteresting());
    user.setMoodState(MoodState.HAPPY);//le ineteresan todas las peliculas
    assertTrue(user.isWatchingSomethingInteresting());
    //triste le interesan las peliculas largas (120 min)
    user.setMoodState(MoodState.SAD);
    resource.setResourceType(ResourceType.MOVIE);
    resource.setDurationMinutes(200);
    assertTrue(user.isWatchingSomethingInteresting());    
  }
  
  @Test
  public void userPayOktest() {
    User user = new User();
    Suscription suscription = new Suscription(ZonedDateTime.now(),ZonedDateTime.now().plusDays(30),SuscriptionType.GOLD);
    user.setSuscription(suscription); 
    user.paySubscription();
    
    assertEquals(1,user.getSuscription().getPayments().size());
    
  }
  

}
