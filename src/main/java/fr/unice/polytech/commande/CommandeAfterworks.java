package fr.unice.polytech.commande;

import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.nourriture.*;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Token;

import java.util.Objects;

/**
 * CommandeAfterworks qui n'est pas payable et livrable, elle peut uniquement avoir des menu et un nombre de personne
 * @author Equipe J
 */
public class CommandeAfterworks extends CommandeSimpleAvecID {
    private final int nombrePersonne;

    // Constructeur

    /**
     * Constructeur de la commande afterworks
     * @param id l'identifiant de la commande
     * @param createur le créateur de la commande
     * @param nombrePersonne le nombre de personne dans la commande Afterworks
     */
    public CommandeAfterworks(long id, CompteUtilisateur createur, int nombrePersonne) {
        super(id, createur);
        if (nombrePersonne <= 0)
            throw new IllegalArgumentException("Nombre de personne saisi est incorrect");

        this.nombrePersonne = nombrePersonne;
    }

    // Getter

    /**
     * Renvoie le nombre de personne dans la commande
     * @return le nombre de personne
     */
    public int getNombrePersonne() {
        return nombrePersonne;
    }

    // Equals et HashCode

    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            CommandeAfterworks commandeAfterworks = (CommandeAfterworks) o;
            return commandeAfterworks.nombrePersonne == this.nombrePersonne;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCommande(), createur, nombrePersonne);
    }

    // Méthode

    /**
     * @throws IllegalArgumentException Si le menu n'est pas de type afterworks
     */
    @Override
    public void ajoutMenuPlat(MenuPlat menuPlat, TypeMenuPlat typeMenuPlat) throws RestaurantNonValideException, CapaciteDepasseException {
        if (typeMenuPlat == TypeMenuPlat.PLAT)
            throw new IllegalArgumentException("Impossible d'ajouté un plat");

        Menu menu = (Menu) menuPlat;
        if (menu.getTypeMenu() != TypeMenu.AFTERWORKS)
            throw new IllegalArgumentException("Impossible d'ajouter un menu qui n'est pas afterworks");

        super.ajoutMenuPlat(menuPlat, typeMenuPlat);
    }

    @Override
    public void payerCommande(Token token) throws TokenException {
        throw new IllegalArgumentException("Impossible de payer cette commande");
    }

    @Override
    public InformationLivraison getInformationLivraison() {
        throw new IllegalArgumentException("Impossible de livrer cette commande");
    }
}
