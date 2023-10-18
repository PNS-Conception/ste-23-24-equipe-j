package fr.unice.polytech.nourriture;

import java.util.Objects;

/**
 * Record du menu d'un restaurant
 * @param nomMenu le nom du Menu
 */
public record Menu(String nomMenu, double prix) implements MenuPlat{
    // Constructeur

    /**
     * Constructeur par défaut
     * @param nomMenu le nom du Menu
     */
    public Menu {
        if (nomMenu == null || nomMenu.isEmpty())
            throw new IllegalArgumentException("Nom vide");
    }

    // Accessor
    @Override
    public String getNom() {
        return nomMenu;
    }

    @Override
    public double getPrix() {
        return prix;
    }

    // Equals et HashCode

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (o == null || o.getClass() != Menu.class)
            return false;
        Menu menu = (Menu) o;
        return menu.nomMenu().equals(nomMenu());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomMenu);
    }
}
