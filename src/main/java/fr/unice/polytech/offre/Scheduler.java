package fr.unice.polytech.offre;

import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.OffreUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Scheduler implements IScheduler{
    public Map<Restaurant, CommandesDate> getCommandesPlannifiees() {
        return commandesPlannifiees;
    }

    private Map<Restaurant,CommandesDate>commandesPlannifiees;
    public List<Restaurant> getCreneausRestaurants() {
        return creneausRestaurants;
    }

    public void setCreneausRestaurants(List<Restaurant> creneausRestaurants) {
        this.creneausRestaurants = creneausRestaurants;
    }

    private List<Restaurant> creneausRestaurants;

    public Scheduler() {
        this.creneausRestaurants = new ArrayList<>();
        this.commandesPlannifiees =new HashMap<>();
    }
public void ajouterRestaurant(Restaurant restaurant){
        creneausRestaurants.add(restaurant);
}

    @Override
    public boolean restaurantContientCreneau(Restaurant restaurant,String date, Horaire debut, Horaire fin) {
        Optional<Restaurant> restaurant1= creneausRestaurants.stream().filter(r->r.equals(restaurant)).findFirst();
        if(restaurant1.isPresent()) return restaurant1.get().getCreneaus().stream().anyMatch(c->c.estDisponible(OffreUtils.convertStringToDate(date), debut, fin));
        return false;
    }
    private  Restaurant getRestaurant(Restaurant restaurant){
        Optional<Restaurant> restaurant1= creneausRestaurants.stream().filter(r->r.equals(restaurant)).findFirst();
        return restaurant1.orElse(null);

    }

    @Override
    public List<ICreneau> getCreneauxDisponibles(Restaurant restaurant) {

       Restaurant restaurant1= getRestaurant(restaurant);
       if(restaurant1!=null) return restaurant1.getCreneaus();
       return null;
    }

    @Override
    public Map<Restaurant, List<ICreneau>> getCreneauxDisponbles(LocalDate date) {
        return null;

    }

    @Override
    public  List<ICreneau>getCreneauxDisponblesFor(Date date, Restaurant restaurant
    ) {
        Restaurant restaurant1=getRestaurant(restaurant);
        if(restaurant1!=null)
          return   restaurant1.getCreneaus().stream().filter(c->c.estDisponible(date)).collect(Collectors.toList());
        return new ArrayList<>();

    }

    @Override
    public void diminuerLaCapacitePourCreneau(Restaurant restaurant,Date date, ICreneau creneau, int capacite) {
       Restaurant restaurant1= getRestaurant(restaurant);
       if(restaurant1!=null){
           Optional<ICreneau> creneau1=restaurant1.getCreneaus().stream().filter(c->c.equals(creneau)).findFirst();
           if(creneau1.isPresent()){
               commandesPlannifiees.putIfAbsent(restaurant1,new CommandesDate());
               CommandesDate commandesRestaurant=commandesPlannifiees.get(restaurant1);
               commandesRestaurant.ajouterCommandePourLaDate(date,creneau1.get(),capacite);
           }



       }

    }

    public ICreneau getCreneau(Restaurant restaurant, String date, Horaire debut, Horaire fin) {
       if(restaurantContientCreneau(restaurant, date, debut, fin))
           return getRestaurant(restaurant).getCreneaus().stream().filter(c->c.estDisponible(OffreUtils.convertStringToDate(date),debut,fin)).findFirst().get();
       return null;
    }
}
