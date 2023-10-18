package fr.unice.polytech.utils;

import java.util.Objects;

/**
 * La position d'une commande ou d'un restaurant
 * @param nomPosition nom de la Position
 * @Author Equipe J
 */
public record Position(String nomPosition) {
    /**
     * Constructeur par défaut
     * @param nomPosition nom de la Position
     */
    public Position {
        if (nomPosition == null || nomPosition.isEmpty())
            throw new IllegalArgumentException("Position vide");
    }

    // Equals et HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || o.getClass() != Position.class)
            return false;
        Position position = (Position) o;
        return position.nomPosition().equals(nomPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomPosition);
    }
}
