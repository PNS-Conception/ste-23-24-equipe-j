package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;

public class Creneau implements ICreneau {
    Horaire debut;
    Horaire fin;
    int duree;

    int capacite_par_slot;

    public Creneau(Horaire debut, Horaire fin, int capacite_par_slot) {
        this.debut = debut;
        this.fin = fin;
        this.capacite_par_slot = capacite_par_slot;
        this.duree=Horaire.calculerDuree(debut, fin);

    }
    public Creneau(){

    }

    public void setDebut(Horaire debut) {
        this.debut = debut;
    }

    public void setFin(Horaire fin) {
        this.fin = fin;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getCapacite_par_slot() {
        return capacite_par_slot;
    }

    public void setCapacite_par_slot(int capacite_par_slot) {
        this.capacite_par_slot = capacite_par_slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creneau creneau = (Creneau) o;
        if (capacite_par_slot != creneau.getCapaciteParSlot()) return false;
        if (!debut.equals(creneau.getDebut())) return false;
        return fin.equals(creneau.getFin());
    }

    @Override
    public int hashCode() {
        int result = debut.hashCode();
        result = 31 * result + fin.hashCode();
        result = 31 * result + duree;
        result = 31 * result + capacite_par_slot;
        return result;
    }

    @Override
    public Horaire getDebut() {
        return debut;
    }

    @Override
    public Horaire getFin() {
        return fin;
    }

    @Override
    public int getDuree() {
        return duree;
    }

    @Override
    public int getCapaciteParSlot() {
        return capacite_par_slot;
    }

    @Override
    public boolean estIdentique(ICreneau iCreneau) {
        return equals(iCreneau);
    }

    @Override
    public boolean estDisponible(Date date,Horaire debut,Horaire fin) {
        return false;
    }

    @Override
    public boolean estDisponible(Date date) {
        return date!=null;
    }

}
