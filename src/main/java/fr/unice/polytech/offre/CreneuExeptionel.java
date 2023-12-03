package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.util.Objects;

public class CreneuExeptionel extends Creneau {
    public LocalDate getDateCreneau() {
        return dateCreneau;
    }

    public void setDateCreneau(LocalDate dateCreneau) {
        this.dateCreneau = dateCreneau;
    }

    private LocalDate dateCreneau;
    public CreneuExeptionel(Horaire debut, Horaire fin, int capacite_par_slot,LocalDate dateCreneau) {
        super(debut, fin, capacite_par_slot);
        this.dateCreneau=dateCreneau;
    }

    @Override
    public boolean estDisponible(Date date,Horaire debut,Horaire fin) {

        return estDisponible(date)&&(getDebut().compareTo(debut)<=0&&getFin().compareTo(fin)>=0);
    }
    public CreneuExeptionel(){
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CreneuExeptionel that = (CreneuExeptionel) o;

        return Objects.equals(dateCreneau, that.dateCreneau);
    }



    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dateCreneau != null ? dateCreneau.hashCode() : 0);
        return result;
    }

    @Override
    public boolean estDisponible(Date date) {
        LocalDate localDate= LocalDate.of(date.getAnnee(), date.getMois(), date.getJour());
        return super.estDisponible(date)&&localDate.equals(dateCreneau);
    }
}
