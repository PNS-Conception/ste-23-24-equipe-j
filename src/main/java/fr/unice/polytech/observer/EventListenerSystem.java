package fr.unice.polytech.observer;

import fr.unice.polytech.commande.Commande;

public interface EventListenerSystem {
    public void notify(Commande commande);
}
