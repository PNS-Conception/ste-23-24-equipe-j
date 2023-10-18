package fr.unice.polytech.commande;

import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.Plat;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe d'une commande d'une seul personne
 * @Author Equipe J
 */
public class SousCommande {
    private double prixSousCommande;
    private final Map<MenuPlat, Integer> menuPlats;

    /**
     * Constructeur par défaut de SousCommande
     */
    public SousCommande(){
        prixSousCommande = 0;
        menuPlats = new HashMap<>();
    }

    // Accesseurs

    /**
     * Retourne le prix de la sous commande
     * @return le prix de la sous commande
     */
    public double getPrix() {
        return prixSousCommande;
    }

    /**
     * Retourne les plats ou menus et les quantités de la sous commande
     * @return les plats ou menus et les quantités de la sous commande
     */
    public Map<MenuPlat, Integer> getMenuPlats() {
        return menuPlats;
    }

    /**
     * Ajoute un plat ou un menu avec une quantite à la sous commande
     * @param menuPlat le plat ou menu à ajouter
     * @param quantite la quantite du plat ou menu à ajouter
     */
    public void ajout_plat(MenuPlat menuPlat, int quantite){
        if (quantite < 0) {
            throw new IllegalArgumentException("La quantité doit être positive");
        }

        if (menuPlats.containsKey(menuPlat)){
            int nouvelleQuantite = menuPlats.get(menuPlat) + quantite;
            menuPlats.put(menuPlat, nouvelleQuantite);
        }
        else {
            menuPlats.put(menuPlat, quantite);
        }
        prixSousCommande += menuPlat.getPrix();
    }

    // A revoir si on ajoute une quantite à supprimer d'un plat
    public void suppression_plat(Plat plat){
        if (menuPlats.containsKey(plat)){
            int quantite = menuPlats.get(plat) - 1;
            if (quantite == 0){
                menuPlats.remove(plat);
            }
            else {
                menuPlats.put(plat, quantite);
            }
        }
    }
}
