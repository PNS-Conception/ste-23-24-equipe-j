package fr.unice.polytech.utils.adress;

import java.util.ArrayList;

public class SavedPosition {


    ArrayList<Position> savedPosition ;


    public SavedPosition() {
        savedPosition = new ArrayList<>();
    }

    public void addPosition(Position position){
        savedPosition.add(position);
    }

    public void removePosition(Position position){
        savedPosition.remove(position);
    }

    public ArrayList<Position> getSavedPosition() {
        return savedPosition;
    }

    public Position getPosition(String position){
        for (Position p : savedPosition){
            if (p.getNomPosition().equals(position)){
                return p;
            }
        }
        return null;
    }





}
