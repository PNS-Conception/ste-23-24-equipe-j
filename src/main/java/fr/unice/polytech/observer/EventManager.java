package fr.unice.polytech.observer;

import fr.unice.polytech.commande.Commande;
import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class observer qui permet de notifier des utilisateurs
 * @author Equipe J
 */
public class EventManager {
    private static Map<String, List<EventListenerSystem>> listenerSystemes;

    /**
     * Constructeur par défaut
     */
    private EventManager() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Permet à des élements de système d'être notifié
     * @param listener les élements du systèmes à notifier
     * @param eventType les types de status de commande auquel le système doit être notifié
     */
    public static void subscribe(EventListenerSystem listener, String... eventType) {
        if (listenerSystemes == null)
            listenerSystemes = new HashMap<>();

        for (String event : eventType) {
            if (listenerSystemes.containsKey(event)) {
                listenerSystemes.get(event).add(listener);
            }
            else {
                List<EventListenerSystem> nouvelleListe = new ArrayList<>();
                nouvelleListe.add(listener);
                listenerSystemes.put(event, nouvelleListe);
            }
        }
    }

    /**
     * Notifie un utilisateur
     * @param commande la commande que l'utilisateur a passé
     * @param message le message à lui envoyer
     */
    public static void notify(Commande commande, String message) {
        CompteUtilisateur compteUtilisateur = commande.getCompteUtilisateur();

        if (listenerSystemes != null && (listenerSystemes.containsKey(message))) {
                for (EventListenerSystem eventListenerSystem : listenerSystemes.get(message))
                    eventListenerSystem.update(commande);
        }

        if (compteUtilisateur != null) {
            compteUtilisateur.notify(message);
        }
    }
}
