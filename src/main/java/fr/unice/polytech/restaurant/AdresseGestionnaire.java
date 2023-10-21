package fr.unice.polytech.restaurant;

import java.util.HashMap;

public class AdresseGestionnaire {

    HashMap<String,Coordinate> dictionnaireAdresse;

    public AdresseGestionnaire(){
        dictionnaireAdresse = new HashMap<>();
    }

    public void addAdresse(String adresse, Coordinate coord){
        dictionnaireAdresse.put(adresse, coord);
    }

    public Coordinate getCoordinate(String adresse){
        return dictionnaireAdresse.get(adresse);
    }

}
