package fr.unice.polytech.livraison;


import fr.unice.polytech.utils.Position;

import java.util.HashMap;
import java.util.Map;

/**
 * Système de livraison des commandes
 * @author Equipe J
 */
public class SystemeLivraison {
    private final Map<Position, CompteLivreur> livreursDisponibles;

    // Constructeur
    /**
     * Constructeur par défaut
     */
    public SystemeLivraison() {
        this.livreursDisponibles = new HashMap<>();
    }

    /**
     * Ajoute un livreur au système de livraison
     * @param compteLivreur compte du livreur
     */
    public void addLivreur(CompteLivreur compteLivreur) {
        livreursDisponibles.put(compteLivreur.getPosition(), compteLivreur);
    }

    /**
     * Récupère le livreur le plus proche d'une position
     * @param position la position avec laquelle on cherche le plus proche livreur
     * @return le livreur le plus proche
     */
    public CompteLivreur getPlusProcheLivreur(Position position) {
        int minDistance = -1;
        Position meilleurPosition = null;

        if (livreursDisponibles.isEmpty())
            return null;
        if (livreursDisponibles.containsKey(position)) {
            return livreursDisponibles.get(position);
        }

        for (Position pos : livreursDisponibles.keySet()) {
            int distance = position.compareTo(pos);

            if (minDistance == -1 || distance < minDistance) {
                minDistance = distance;
                meilleurPosition = pos;
            }

            if (minDistance == 1) {
                return livreursDisponibles.get(meilleurPosition);
            }
        }

        return livreursDisponibles.get(meilleurPosition);
    }
}
