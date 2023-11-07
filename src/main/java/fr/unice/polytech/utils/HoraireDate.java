package fr.unice.polytech.utils;

import java.util.Objects;

public class HoraireDate {

    private Date date;
    private Horaire horaire;

    public HoraireDate(String dateInput, String hourInput) {
        date = new Date(dateInput);
        horaire = new Horaire(hourInput);
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

}
