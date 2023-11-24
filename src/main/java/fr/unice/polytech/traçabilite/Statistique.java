package fr.unice.polytech.traçabilite;

import fr.unice.polytech.commande.CommandeAvecID;
import fr.unice.polytech.commande.CommandeSimpleAvecID;
import fr.unice.polytech.exceptions.PasswordException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Position;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.util.HashMap;

public class Statistique {

    private final String UserPassword = "0000";
    private final String RestaurantPassword = "1111";
    HashMap<CompteUtilisateur,Integer> nbCommandeUtilisateur;
    HashMap<Restaurant,Integer> nbCommandeRestaurant;
    HashMap<HoraireDate,Integer> nbCommandeHoraire;
    HashMap<Position,Integer> nbCommandePosition;


    public Statistique() {
        nbCommandeUtilisateur = new HashMap<>();
        nbCommandeRestaurant = new HashMap<>();
    }

    public boolean updateUserStat(CommandeAvecID commande, String password) {
        if (password.equals(UserPassword)) {

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

    public HashMap<HoraireDate,Integer> getHoraireStat() {
        return nbCommandeHoraire;
    }

    public HashMap<Position,Integer> getPositionStat() {
        return nbCommandePosition;

    }


}
