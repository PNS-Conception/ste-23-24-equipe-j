package fr.unice.polytech.restaurant;

import java.util.HashMap;
import java.util.Map;

public class SousCommande {
    private double prix;
    private Map<String, Integer> plats;

    public SousCommande(){
        prix = 0;
        plats = new HashMap<>();
    }

    public double getPrix() {
        return prix;
    }

    public Map<String, Integer> getPlats() {
        return plats;
    }

    public void ajout_plat(Plat plat){
        if (plats.containsKey(plat.getName())){
            int quantite = plats.get(plat.getName()) + 1;
            plats.put(plat.getName(), quantite);
        }
        else {
            plats.put(plat.getName(), 1);
        }
        prix += plat.getPrix();
    }

    public void suppression_plat(Plat plat){
        if (plats.containsKey(plat.getName())){
            int quantite = plats.get(plat.getName()) - 1;
            if (quantite == 0){
                plats.remove(plat.getName());
            }
            else {
                plats.put(plat.getName(), quantite);
            }
        }
    }
}
