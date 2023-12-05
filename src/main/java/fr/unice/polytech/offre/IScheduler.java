package fr.unice.polytech.offre;

import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IScheduler {
    public boolean restaurantContientCreneau(Restaurant restaurant,String date, Horaire debut, Horaire fin);


    public List<ICreneau> getCreneauxDisponibles(Restaurant restaurant);
public Map<Restaurant,List<ICreneau>> getCreneauxDisponbles(LocalDate date);
    public List<ICreneau> getCreneauxDisponblesFor(Date date, Restaurant restaurant);
    public void diminuerLaCapacitePourCreneau(Restaurant restaurant,Date date,ICreneau creneau,int capacite);
}
