package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;

public interface ICreneau {
    Horaire getDebut() ;
    Horaire getFin() ;
    int getDuree();
    int getCapaciteParSlot();
    boolean estDisponible(Date date, Horaire debut, Horaire fin);
    boolean estDisponible(Date date);
    static int getCapaciteCreneau(ICreneau iCreneau){
      return   iCreneau.getDuree() / 10 * iCreneau.getCapaciteParSlot();

    }

}
