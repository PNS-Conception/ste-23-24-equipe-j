package fr.unice.polytech.observer;

import fr.unice.polytech.commande.interfacecommande.ICommande;

/**
 * Interface permettant que certains éléments du système soit notifié d'un changement de status de commande
 * @author Equipe J
 */
public interface EventListenerSystem {
    /**
     * Met à jour un élément du système
     * @param commande la commande qui a changé de status
     */
    public void update(ICommande commande);
}
