package fr.unice.polytech.observer;

import fr.unice.polytech.commande.Commande;

/**
 * Interface permettant que certains éléments du système soit notifié d'un changement de status de commande
 * @author Equipe J
 */
public interface EventListenerSystem {
    /**
     * Notifie un élément du système
     * @param commande la commande qui a changé de status
     */
    public void notify(Commande commande);
}
