package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static fr.unice.polytech.offre.DayEnum.getDayOfTheWeek;

public class CreneauQuotidien extends Creneau {
    public DayOfWeek getJourDeLaSemaine() {
        return jourDeLaSemaine;
    }

    public void setJourDeLaSemaine(DayOfWeek jourDeLaSemaine) {
        this.jourDeLaSemaine = jourDeLaSemaine;
    }

    private  DayOfWeek jourDeLaSemaine;
    public CreneauQuotidien(Horaire debut, Horaire fin, int capacite_par_slot,String jourDeLaSemaine) {

        super(debut, fin, capacite_par_slot);
        this.jourDeLaSemaine= getDayOfTheWeek(jourDeLaSemaine);
    }
    public CreneauQuotidien(){
        super();

    }

    @Override
    public boolean estDisponible(Date date,Horaire debut,Horaire fin) {

        return estDisponible(date)&&(getDebut().compareTo(debut)<=0&&getFin().compareTo(fin)>=0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CreneauQuotidien that = (CreneauQuotidien) o;

        return jourDeLaSemaine == that.jourDeLaSemaine;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + jourDeLaSemaine.hashCode();
        return result;
    }



    @Override
    public boolean estDisponible(Date date) {
        LocalDate localDate=LocalDate.of(date.getAnnee(), date.getMois(),date.getJour());
        DayOfWeek jourDeLaDate=localDate.getDayOfWeek();
        return super.estDisponible(date)&&jourDeLaSemaine.equals(jourDeLaDate);
    }
}
