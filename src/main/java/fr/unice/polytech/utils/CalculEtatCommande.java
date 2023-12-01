package fr.unice.polytech.utils;

import fr.unice.polytech.commande.EtatCommande;
import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;

import java.util.Set;

/**
 * Classe qui permet de calculer l'état d'une commande groupe en fonction des commandes qui la compose
 * @author Equipe J
 */
public class CalculEtatCommande {
    /**
     * Constructeur privé pour empêcher l'instanciation de la classe
     */
    private CalculEtatCommande() {
        throw new IllegalArgumentException("Utility class");
    }

    /**
     * Récupère l'état global d'une commande et supprime les commandes annule
     * @return l'état global de la commande
     */
    public static EtatCommande calculEtatCommande(Set<ICommandeAjoutable> commandes) {
        EtatCommande etatCommandeGlobal = EtatCommande.PRETE;

        for (ICommande commande : commandes) {
            EtatCommande etatCommandeAjoutable = commande.getEtatCommande();

            if (etatCommandeAjoutable == EtatCommande.EN_ATTENTE) {
                etatCommandeGlobal = EtatCommande.EN_ATTENTE;
                break;
            }
            else if (etatCommandeAjoutable == EtatCommande.EN_PREPARATION)
                etatCommandeGlobal = EtatCommande.EN_PREPARATION;
            else if (etatCommandeAjoutable == EtatCommande.ANNULE)
                commandes.remove(commande);
        }

        return etatCommandeGlobal;
    }
}
