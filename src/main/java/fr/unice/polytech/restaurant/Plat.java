package fr.unice.polytech.restaurant;

import java.util.List;

public class Plat {
    private String name;
    private double prix;
    private List<String> aliments;
    private List<String> alergnes;

    public Plat(String name, double prix, List<String> aliments, List<String> alergenes){
        this.name = name;
        this.prix = prix;
        this.aliments = aliments;
        this.alergnes = alergenes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<String> getAliments() {
        return aliments;
    }

    public void setAliments(List<String> aliments) {
        this.aliments = aliments;
    }

    public List<String> getAlergnes() {
        return alergnes;
    }

    public void setAlergnes(List<String> alergnes) {
        this.alergnes = alergnes;
    }
}
