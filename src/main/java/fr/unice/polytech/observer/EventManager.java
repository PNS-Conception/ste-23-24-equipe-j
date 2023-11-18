package fr.unice.polytech.observer;

import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

/**
 * Class observer qui permet de notifier des utilisateurs
 * @author Equipe J
 */
public class EventManager {
    /**
     * Constructeur par défaut
     */
    public EventManager() {

    }

    /**
     * Notifie un utilisateur
     * @param commande la commande que l'utilisateur a passé
     * @param message le message à lui envoyer
     */
    public void notify(Commande commande, String message) {
        commande.getCompteUtilisateur().notify(message);
    }
}
