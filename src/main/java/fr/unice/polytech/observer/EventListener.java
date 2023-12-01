package fr.unice.polytech.observer;

/**
 * Interface qui permet de notifier un utilisateur
 * @author Equipe J
 */
public interface EventListener {
    /**
     * Notifie un utilisateur
     * @param message le message à envoyer à l'utilisateur
     */
    void notify(String message);
}
