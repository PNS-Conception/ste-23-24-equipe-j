package fr.unice.polytech.restaurant;

public class Coordinate implements Comparable<Coordinate> {

    private final int latitude;
    private final int longitude;
    private int hashCode;



    public Coordinate(double latitude, double longitude) {
        this.latitude = (int) Math.round(latitude * 10000);
        this.longitude = (int) Math.round(longitude * 10000);
        this.hashCode = generateHashCode();
    }

    private int generateHashCode() {
        int index;
        if (this.latitude< 0 && this.longitude < 0)
            index = 1;
        else if (this.latitude< 0 && this.longitude > 0)
            index = 2;
        else if (this.latitude> 0 && this.longitude < 0)
            index = 3;
        else {
            index = 4;
        }
        return index * 10*(8+7) + Math.abs(this.latitude) *10*8 + Math.abs(this.longitude);
    }


    public int getRange(int latitude, int longitude) {
        return Math.abs(this.latitude - latitude) + Math.abs(this.longitude - longitude);
    }

    public int getRangeWithCoordinate(Coordinate coordinate) {
        return this.getRange(coordinate.getLatitude(), coordinate.getLongitude());
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || o.getClass() != Restaurant.class)
            return false;
        Coordinate coordinate = (Coordinate) o;
        return coordinate.latitude == this.latitude && coordinate.longitude == this.longitude;
    }

    @Override
    public int hashCode(){
        return this.hashCode;
    }


    @Override
    public int compareTo(Coordinate coordinate) {
        int compare = getRangeWithCoordinate(coordinate);
        return compare;
    }
}
