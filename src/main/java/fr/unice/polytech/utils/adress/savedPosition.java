package fr.unice.polytech.utils.adress;

import java.util.ArrayList;
import java.util.HashMap;

public class savedPosition {


    ArrayList<Position> savedPosition ;


    public savedPosition() {
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
