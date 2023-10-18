package fr.unice.polytech.restaurant;


public class Livreur {

    private Coordinate coordonnees ;
    private String nom;
    private String prenom;



    public Livreur(String prenom, String nom, int Latitude, int longitude) {
        this(prenom, nom, new Coordinate(Latitude, longitude));
    }

    public Livreur(String prenom, String nom, Coordinate coordonnees) {
        this.coordonnees = coordonnees;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Coordinate getCoordonnees() {
        return coordonnees;
    }

}
