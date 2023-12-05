package fr.unice.polytech.utils.temps;

import java.util.Objects;

public class HoraireDate implements Comparable<HoraireDate>{

    private Date date;
    private Horaire horaire;

    public HoraireDate(String dateInput, String hourInput) {
        date = new Date(dateInput);
        horaire = new Horaire(hourInput);
    }

    public HoraireDate() {
        this(new Date(), new Horaire());
    }

    public HoraireDate(Date date, Horaire horaire) {
        this.date = date;
        this.horaire = horaire;
    }

    public Date getDate() {
        return date;
    }

    public Horaire getHoraire() {
        return horaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || o.getClass() != HoraireDate.class)
            return false;

        HoraireDate horaireDate = (HoraireDate) o;
        return horaireDate.date.equals(this.date) && horaireDate.horaire.equals(this.horaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, horaire);
    }

    @Override
    public String toString() {
        return "HoraireDate{" +
                "date=" + date +
                ", horaire=" + horaire +
                '}';
    }

    @Override
    public int compareTo(HoraireDate horaireDate) {

        if (this.equals(horaireDate)) {
            return 0;
        }
        if (this.date.compareTo(horaireDate.date) == 0) {
            return this.horaire.compareTo(horaireDate.horaire);
        } else {
            return this.date.compareTo(horaireDate.date);
        }

    }

}
