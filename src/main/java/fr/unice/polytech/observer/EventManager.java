package fr.unice.polytech.observer;

import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class observer qui permet de notifier des utilisateurs
 * @author Equipe J
 */
public class EventManager {
    private final Map<String, List<EventListenerSystem>> listenerSystemes;

    /**
     * Constructeur par défaut
     */
    public EventManager() {
        listenerSystemes = new HashMap<>();
    }

    /**
     * Permet à des élements de système d'être notifié
     * @param listener les élements du systèmes à notifier
     * @param eventType les types de status de commande auquel le système doit être notifié
     */
    public void subscribe(EventListenerSystem listener, String... eventType) {
        for (String event : eventType) {
            if (listenerSystemes.containsKey(event)) {
                listenerSystemes.get(event).add(listener);
            }
            else {
                listenerSystemes.put(event, List.of(listener));
            }
        }
    }

    /**
     * Notifie un utilisateur
     * @param commande la commande que l'utilisateur a passé
     * @param message le message à lui envoyer
     */
    public void notify(Commande commande, String message) {
        CompteUtilisateur compteUtilisateur = commande.getCompteUtilisateur();

        for (EventListenerSystem eventListenerSystem : listenerSystemes.get(message))
            eventListenerSystem.notify(commande);

        if (compteUtilisateur != null) {
            compteUtilisateur.notify(message);
        }
    }
}
