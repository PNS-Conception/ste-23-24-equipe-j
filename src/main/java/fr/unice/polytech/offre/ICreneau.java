package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;

import java.time.LocalDate;

public interface ICreneau {
    public Horaire getDebut() ;
    public Horaire getFin() ;
    public int getDuree();
    public int getCapaciteParSlot();
    public boolean estIdentique(ICreneau iCreneau);
    public boolean estDisponible(Date date,Horaire debut,Horaire fin );
    public boolean estDisponible(Date date );
    public static int getCapaciteCreneau(ICreneau iCreneau){
      return   iCreneau.getDuree() / 10 * iCreneau.getCapaciteParSlot();

    }

}
