package fr.unice.polytech.restaurant;


import java.util.HashMap;

public class SystemeLivraison {

    private HashMap<Coordinate,Livreur> livreursDisponibles;

    public SystemeLivraison() {
        this.livreursDisponibles = new HashMap<>();
    }

    public void addLivreur(Livreur livreur) {
        this.livreursDisponibles.put(livreur.getCoordonnees(), livreur);
    }


    /**
     * Complexité : O(n)
     * @param lon
     * @param lat
     * @return
     */
    public Livreur getClosestLivreur(int lon, int lat) {
        return this.getClosestLivreur(new Coordinate(lon, lat));
    }

    public Livreur getClosestLivreur(Coordinate coordinate) {
        int minDistance = -1;
        Coordinate bestCoordinate = null;
        boolean isCoordinateInside = livreursDisponibles.containsKey(coordinate);
        if (isCoordinateInside) {
            return livreursDisponibles.get(coordinate);
        }
        for (Coordinate coodinates : livreursDisponibles.keySet()) {
            int distance = coordinate.compareTo(coodinates);
            if (minDistance == -1 || distance < minDistance) {
                minDistance = distance;
                bestCoordinate = coodinates;
            }
            if (minDistance == 1) {
                return this.livreursDisponibles.get(bestCoordinate);
            }
        }
        return this.livreursDisponibles.get(bestCoordinate);
    }
}
