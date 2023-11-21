package fr.unice.polytech.restaurant;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.CommandeSimpleAvecID;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.HashMap;

public class Statistique {

    private final String UserPassword = "0000";
    HashMap<CompteUtilisateur,Integer> nbCommandeUtilisateur;
    HashMap<Restaurant,Integer> nbCommandeRestaurant;


    public Statistique() {
        nbCommandeUtilisateur = new HashMap<>();
        nbCommandeRestaurant = new HashMap<>();
    }

    public boolean updateUserStat(CommandeAvecID commande, String password) {
        if (password.equals(UserPassword)) {
            Restaurant restaurant = ((CommandeSimpleAvecID) commande).getRestaurant().orElse(null);
            CompteUtilisateur compteUtilisateur = commande.getCreateur();
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
            return true;
        }
        return false;
    }

    public HashMap<CompteUtilisateur,Integer> getUserStat(String password) throws PasswordException {
        if (password.equals(UserPassword)) {
            return nbCommandeUtilisateur;
        }
        throw new PasswordException();
    }

    public HashMap<Restaurant,Integer> getRestaurantStat(String password) throws PasswordException {
        if (password.equals(UserPassword)) {
            return nbCommandeRestaurant;
        }
        throw new PasswordException();
    }


}
