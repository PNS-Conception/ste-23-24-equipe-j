package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Horaire;

public class CreneauBuilder  {
    protected Creneau result;


    public void reset() {
        result = new Creneau();

    }


    public void buildCapacite(String stringCapacite) {
        try {
            int capacite = stringCapacite.equals("null") ? 0 : Integer.parseInt(stringCapacite);
            result.setCapaciteParSlot(capacite);
        } catch (NumberFormatException e) {
            setResultToNull();
        }

    }


    public void buildDebut(String debut) {
        result.setDebut(new Horaire(debut));


    }


    public void buildFin(String fin) {
        result.setFin(new Horaire(fin));

    }


    public void buildDuree() {
        result.setDuree(Horaire.calculerDuree(result.debut, result.fin));
    }


    public void capaciteParSlot(String capacite) {
        try {
            result.setCapaciteParSlot(Integer.parseInt(capacite));
        } catch (NumberFormatException e) {
            setResultToNull();
        }

    }




    public void setResultToNull() {
        result = null;
    }

    public Creneau getResult() {
        return result;
    }

}
