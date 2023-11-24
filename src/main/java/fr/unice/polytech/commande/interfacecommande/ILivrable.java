package fr.unice.polytech.commande.interfacecommande;

import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;

/**
 * Interface pour les informations de livraison d'une commande
 * @author Equipe J
 */
public interface ILivrable {
    /**
     * Ajoute les informations de livraison à la commande
     * @param dateLivraison la date de livraison
     * @param heureLivraison l'heure de livraison
     * @param lieuxLivraison le lieu de livraison
     */
    void setInformationLivraison(Date dateLivraison, Horaire heureLivraison, Position lieuxLivraison);

    /**
     * Retourne les informations de livraison de la commande
     * @return les informations de livraison de la commande
     */
    InformationLivraison getInformationLivraison();
}
