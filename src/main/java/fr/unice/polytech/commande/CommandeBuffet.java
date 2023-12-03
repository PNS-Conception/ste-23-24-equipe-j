package fr.unice.polytech.commande;

import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenu;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.UserStatut;

/**
 * Classe d'une commande buffet qui hérite de tous les attributs d'une commande classique
 * @author Equipe J
 */
public class CommandeBuffet extends CommandeSimple{
    CompteUtilisateur destinataire;

    // Constructeur

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     * @param destinataireCommande le destinataire de la commande
     * @throws IllegalArgumentException si l'utilisateur n'est pas un staff universitaire ou n'existe pas
     */
    public CommandeBuffet(long idCommande, CompteUtilisateur createurCommande, CompteUtilisateur destinataireCommande) {
        super(idCommande, createurCommande);

        if (destinataireCommande == null)
            throw new IllegalArgumentException("l'usager destinataire n'existe pas");
        else if (!createurCommande.getStatut().equals(UserStatut.PROFESSEUR) &&
                !createurCommande.getStatut().equals(UserStatut.PERSONNEL))
            throw new IllegalArgumentException("le créateur de cette commande n'est pas un staff de l'université");

        destinataire = destinataireCommande;
    }

    // Getter

    /**
     * Retourne le destinataire de la commande
     * @return le destinataire de la commande
     */
    public CompteUtilisateur getDestinataire() {
        return destinataire;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    // Méthodes

    /**
     * @throws IllegalArgumentException si c'est un plat ou un menu qui n'est pas un menu buffet
     */
    @Override
    public void ajoutMenuPlat(MenuPlat menuPlat, TypeMenuPlat typeMenuPlat) throws CapaciteDepasseException, RestaurantNonValideException {
        if (!typeMenuPlat.equals(TypeMenuPlat.MENU))
            throw new IllegalArgumentException("Impossible d'ajouter un plat");

        Menu menu = (Menu) menuPlat;
        if (!menu.getTypeMenu().equals(TypeMenu.BUFFET))
            throw new IllegalArgumentException("mauvais menu ajouté");

        // A modifier dans un future proche refactor
        int nombre = menuPlats.getOrDefault(menuPlat, 0);
        menuPlats.put(menuPlat, nombre + 1);
    }
}
