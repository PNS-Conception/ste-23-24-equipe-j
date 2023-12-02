package fr.unice.polytech.utils.adress;

import java.util.Objects;

/**
 * La position d'une commande ou d'un restaurant
 * @author Equipe J
 */
public class Position implements Comparable<Position>{
    private final int latitude;
    private final int longitude;
    private final String nomPosition;
    private static final String DEFAULT_NAME_POSITION = "DEFAULT";

    /**
     * Constructeur avec le nom de la position du restaurant ou de la commande
     * @param nomPosition nom de la Position
     */
    public Position (String nomPosition){
        this(0, 0, nomPosition);
    }

    /**
     * Constructeur avec la latitude et la longitude
     * @param latitude latitude de la position
     * @param longitude longitude de la position
     */
    public Position (double latitude, double longitude) {
        this(latitude, longitude, DEFAULT_NAME_POSITION);
    }

    /**
     * Constructeur par défaut
     * @param latitude latitude de la position
     * @param longitude longitude de la position
     * @param nomPosition nom de la position (peut être une position par défaut)
     */
    public Position (double latitude, double longitude, String nomPosition) {
        this.latitude = (int) Math.round(latitude * 10000);
        this.longitude = (int) Math.round(longitude * 10000);
        this.nomPosition = nomPosition;
    }

    // Accesseurs

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public String getNomPosition() {
        return nomPosition;
    }

    // Méthodes
    /**
     * Calcul de la distance de manhattan entre deux positions
     * @param position position à laquelle on veut calculer la distance
     * @return la distance entre les deux positions
     */
    public int getDistance(Position position) {
        return Math.abs(this.latitude - position.latitude) + Math.abs(this.longitude - position.longitude);
    }


    // Equals et HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || o.getClass() != Position.class)
            return false;

        Position position = (Position) o;
        if (position.nomPosition.equals(DEFAULT_NAME_POSITION) || this.nomPosition.equals(DEFAULT_NAME_POSITION))
            return position.latitude == this.latitude && position.longitude == this.longitude;
        else
            return position.nomPosition.equals(this.nomPosition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude, nomPosition);
    }

    @Override
    public int compareTo(Position position) {
        return getDistance(position);
    }
}
