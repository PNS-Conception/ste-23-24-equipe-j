package fr.unice.polytech.utils.adress;

import java.util.ArrayList;
import java.util.List;

public class SavedPosition {


    List<Position> savePosition ;


    public SavedPosition() {
        savePosition = new ArrayList<>();
    }

    public void addPosition(Position position){
        savePosition.add(position);
    }

    public void removePosition(Position position){
        savePosition.remove(position);
    }

    public List<Position> getSavedPosition() {
        return savePosition;
    }

    public Position getPosition(String position){
        for (Position p : savePosition){
            if (p.getNomPosition().equals(position)){
                return p;
            }
        }
        return null;
    }





}
