package fr.unice.polytech.nourriture;

/**
 * Interface qui regroupe les menus et les plats pour les ajouter tous les deux dans une commande
 * @Author Equipe J
 */
public interface MenuPlat {
    /**
     * Récupérer le nom du plat ou du menu
     * @return Le nom du plat ou du menu
     */
    String getNom();

    /**
     * Récupère le prix du plat ou du menu
     * @return le prix du plat ou du menu
     */
    double getPrix();
}
