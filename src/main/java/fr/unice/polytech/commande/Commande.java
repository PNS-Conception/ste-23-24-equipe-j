package fr.unice.polytech.commande;

import fr.unice.polytech.livraison.InformationLivraison;

import java.util.List;
import java.util.Objects;

/**
 * Classe abstraite d'une commande de plusieurs personnes ou d'une seule personne
 * @author Equipe J
 */
public abstract class Commande {
    protected final int id;
    protected InformationLivraison informationLivraison;

    protected Commande(int id) {
        this.id = id;
        informationLivraison = new InformationLivraison();
    }

    // Accesseurs et setters

    /**
     * Renvoie l'identifiant de la commande
     * @return l'identifiant de la commande
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le prix de la commande
     * @return le prix de la commande
     */
    public abstract double getPrix();

    /**
     * Retourne l'état de la commande au niveau du client et du restaurant
     * @return l'état de la commande
     */
    public abstract EtatCommande getEtatCommande();

    /**
     * Retourne les informations de livraison de la commande
     * @return les informations de livraison de la commande
     */
    public InformationLivraison getInformationLivraison() {
        return informationLivraison;
    }

    /**
     * Ajoute les informations de livraison de la commande
     * @param informationLivraison les informations de livraison de la commande
     */
    public void setInformationLivraison(InformationLivraison informationLivraison) {
        this.informationLivraison = informationLivraison;
    }

    /**
     * Méthode pour savoir si c'est une commande individuelle ou groupée
     * @return <code>true</code> si la commande est groupées, sinon <code>false</code>
     */
    public abstract boolean estGroupee();

    /**
     * Retourne la liste des commandes individuelles d'une commande
     * @return la liste des commandes individuelles de la commande groupée
     */
    public abstract List<CommandeIndividuelle> getCommandes();


    // Equals et HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Commande commande))
            return false;
        return id == commande.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estGroupee());
    }
}
