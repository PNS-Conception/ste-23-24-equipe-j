package fr.unice.polytech.tracabilite;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.CommandeSimpleAvecID;
import fr.unice.polytech.exceptions.PasswordException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.util.HashMap;
import java.util.Map;

public class Statistique {

    /**
     * Classe qui permet de gérer les statistiques de l'application
     */

    //ATTRIBUTS
    private static final String USER_PASSWORD = "0000";
    Map<CompteUtilisateur,Integer> nbCommandeUtilisateur;
    Map<Restaurant,Integer> nbCommandeRestaurant;
    Map<HoraireDate,Integer> nbCommandeHoraire;
    Map<Position,Integer> nbCommandePosition;


    //CONSTRUCTEUR
    public Statistique() {
        nbCommandeUtilisateur = new HashMap<>();
        nbCommandeRestaurant = new HashMap<>();
        nbCommandeHoraire = new HashMap<>();
        nbCommandePosition = new HashMap<>();
    }


    //GETTERS ET SETTERS
    public Map<CompteUtilisateur,Integer> getUserStat(String password) throws PasswordException {
        if (password.equals(USER_PASSWORD)) {
            return nbCommandeUtilisateur;
        }
        throw new PasswordException();
    }

    public Map<Restaurant,Integer> getRestaurantStat(String password) throws PasswordException {
        if (password.equals(USER_PASSWORD)) {
            return nbCommandeRestaurant;
        }
        throw new PasswordException();
    }

    public Map<HoraireDate,Integer> getHoraireStat() {
        return nbCommandeHoraire;
    }

    public Map<Position,Integer> getPositionStat() {
        return nbCommandePosition;
    }


    //METHODES
    public boolean updateUserStat(CommandeAvecID commande, String password) {
        if (password.equals(USER_PASSWORD)) {

            Restaurant restaurant = ((CommandeSimpleAvecID) commande).getRestaurant().orElse(null);
            CompteUtilisateur compteUtilisateur = commande.getCreateur();
            Position position = commande.getInformationLivraison().getLieuxLivraison();

            if (nbCommandeRestaurant.containsKey(restaurant)) {
                nbCommandeRestaurant.put(restaurant, nbCommandeRestaurant.get(restaurant) + 1);
            } else {
                nbCommandeRestaurant.put(restaurant, 1);
            }

            if (nbCommandeUtilisateur.containsKey(compteUtilisateur)) {
                nbCommandeUtilisateur.put(compteUtilisateur, nbCommandeUtilisateur.get(compteUtilisateur) + 1);
            } else {
                nbCommandeUtilisateur.put(compteUtilisateur, 1);
            }

            if (nbCommandePosition.containsKey(position)) {
                nbCommandePosition.put(position, nbCommandePosition.get(position) + 1);
            } else {
                nbCommandePosition.put(position, 1);
            }

            if (nbCommandeHoraire.containsKey(commande.getInformationLivraison().getHoraireDate())) {
                nbCommandeHoraire.put(commande.getInformationLivraison().getHoraireDate(), nbCommandeHoraire.get(commande.getInformationLivraison().getHoraireDate()) + 1);
            } else {
                nbCommandeHoraire.put(commande.getInformationLivraison().getHoraireDate(), 1);
            }
            return true;
        }
        return false;
    }
}
