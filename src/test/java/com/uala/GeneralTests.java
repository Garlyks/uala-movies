package com.uala;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;

import com.uala.model.User;
import com.uala.model.MoodState.Happy;
import com.uala.model.MoodState.Melancholic;
import com.uala.model.MoodState.Sad;
import com.uala.model.payment.CreditCardPayMethod;
import com.uala.model.payment.PayPalPayMethod;
import com.uala.model.resource.Documentary;
import com.uala.model.resource.Movie;
import com.uala.model.resource.Resource;
import com.uala.model.resource.Serie;
import com.uala.model.subscription.ClassicSubscription;
import com.uala.model.subscription.GoldSubscription;
import com.uala.model.subscription.PlatiniumSubscription;

public class GeneralTests {

  @Test
  public void resourceIsInterestingTest() {
    Resource testResource1 = new Movie("Test pelicula",1988,121,Arrays.asList("Oscar"));
    assertTrue("Pelicula que gano el oscar es interesante",testResource1.IsInteresting());
    
    Resource testResource2 = new Serie("Test serie", 4);
    assertTrue("Serie con 4 temporadas es interesante",testResource2.IsInteresting());
    Resource testResource3 = new Serie("Test serie", 5);
    assertTrue("Serie con 5 temporadas es interesante",testResource3.IsInteresting());
    
    Resource testResource4 = new Documentary("Test Documentary “unofficial”");
    assertTrue("Documental con titulo que contiene unofficial es interesante",
        testResource4.IsInteresting());
  }

  @Test
  public void userIsWatchingSomethingInterestingTest() {
    User user = new User();
    user.setMoodState(new Happy());
    user.setActualResourcesWatching(Arrays.asList(new Movie("Test pelicula",ZonedDateTime.now().getYear(),60,new ArrayList<>())));
    assertTrue("Usuario feliz interesado en ver una pelicula",user.isWatchingSomethingInteresting());
    
    user.setMoodState(new Sad());
    user.setActualResourcesWatching(Arrays.asList(new Movie("Test pelicula",ZonedDateTime.now().getYear(),121,new ArrayList<>())));
    assertTrue("Usuario triste interesado en pelicula larga",user.isWatchingSomethingInteresting());
    
    user.setMoodState(new Melancholic());
    user.setActualResourcesWatching(Arrays.asList(new Movie("Test pelicula",1980,60,new ArrayList<>())));
    assertTrue("Usuario melancolico interesado en ver una pelicula vieja",user.isWatchingSomethingInteresting());
  }

  @Test
  public void userPayOkWithPaypaltest() {    
    User user = new User();
    user.setSuscription(new ClassicSubscription());
    assertFalse("No puede conectar, suscripcion impaga",user.getSuscription().connectDevice());
    user.getSuscription().paySubscription(new PayPalPayMethod());
    assertTrue("Puede conectar, suscripcion paga",user.getSuscription().connectDevice());    
    assertFalse("Pago reflejado en la suscripcion",user.getSuscription().getPayments().isEmpty());
    assertTrue("Paypal aplica descuento de 2%",
        user.getSuscription().getCost()
        .subtract(user.getSuscription().getCost()
        .multiply(BigDecimal.valueOf(2).divide(BigDecimal.valueOf(100))))
        .compareTo(user.getSuscription().getPayments().get(0).getNetAmmount()) == 0);    
  }
  
  @Test
  public void userPayOkWithCreditCardtest() {    
    User user = new User();
    user.setSuscription(new ClassicSubscription());
    assertFalse("No puede conectar, suscripcion impaga",user.getSuscription().connectDevice());
    user.getSuscription().paySubscription(new CreditCardPayMethod());
    assertTrue("Puede conectar, suscripcion paga",user.getSuscription().connectDevice());    
    assertFalse("Pago reflejado en la suscripcion",user.getSuscription().getPayments().isEmpty());
    assertTrue("Sin descuento en tarjeta de credito",
        user.getSuscription().getCost()        
        .compareTo(user.getSuscription().getPayments().get(0).getNetAmmount()) == 0);    
  }
  
  @Test
  public void userPayOkWithDescuentoDeAntiguedad() {
    
    Integer yearsOld = 2;
    
    User user = new User();
    user.setSuscription(new ClassicSubscription());
    user.getSuscription().setStartDate(ZonedDateTime.now().minusYears(2));
    assertFalse("No puede conectar, suscripcion impaga",user.getSuscription().connectDevice());
    user.getSuscription().paySubscription(new CreditCardPayMethod());
    assertTrue("Puede conectar, suscripcion paga",user.getSuscription().connectDevice());    
    assertFalse("Pago reflejado en la suscripcion",user.getSuscription().getPayments().isEmpty());
    assertTrue(yearsOld+" de antiguedad descuento "+(yearsOld*3)+"%",
        user.getSuscription().getCost()
        .subtract(user.getSuscription().getCost()
        .multiply(BigDecimal.valueOf(3*yearsOld).divide(BigDecimal.valueOf(100))))
        .compareTo(user.getSuscription().getPayments().get(0).getNetAmmount()) == 0);
  }
  
  @Test
  public void loginTest() {    
    User user = new User();
    user.setSuscription(new ClassicSubscription());
    user.getSuscription().paySubscription(new CreditCardPayMethod());
    assertTrue("Puede conectar 1 session",user.getSuscription().connectDevice());   
    assertFalse("Falla conectar 2 sessiones",user.getSuscription().connectDevice());

    user.setSuscription(new GoldSubscription());
    user.getSuscription().paySubscription(new CreditCardPayMethod());
    assertTrue("Puede conectar 1 session",user.getSuscription().connectDevice()); 
    assertTrue("Puede conectar 2 session",user.getSuscription().connectDevice()); 
    assertTrue("Puede conectar 3 session",user.getSuscription().connectDevice()); 
    assertFalse("Falla conectar 4 sessiones",user.getSuscription().connectDevice());
    
    user.setSuscription(new PlatiniumSubscription());
    user.getSuscription().paySubscription(new CreditCardPayMethod());
    IntStream.range(1, 6)
    .forEach(index -> {
      assertTrue("Puede conectar "+index+" sessiones",user.getSuscription().connectDevice()); 
    });  
    assertFalse("Falla conectar 6 sessiones",user.getSuscription().connectDevice()); 
  }
  
  
  

}
