package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.commande.interfacecommande.ILivrable;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe d'une commande groupe composé de commandes payables, avec des id
 * @author Equipe J
 */
public class CommandeGroupe extends ACommandeGroupe implements ILivrable {
    Set<ICommandeAjoutable> commandes;
    InformationLivraison informationLivraison;

    // Constructeur

    /**
     * Constructeur par défaut
     * @param idCommande identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    public CommandeGroupe(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
        commandes = new HashSet<>();
        informationLivraison = new InformationLivraison(this);
    }

    @Override
    public void ajouterCommande(ICommandeAjoutable commande) {
        commandes.add(commande);
    }

    @Override
    public void supprimerCommande(ICommandeAjoutable commande) {
        commandes.remove(commande);
    }

    @Override
    public List<ICommandeAjoutable> getCommandes() {
        List<ICommandeAjoutable> commandeAjoutables = new ArrayList<>();

        for (ICommandeAjoutable commande : commandes) {
            if (!commande.estCommandeSimple())
                commandeAjoutables.addAll(((ACommandeGroupe) commande).getCommandes());
            else
                commandeAjoutables.add(commande);
        }

        return commandeAjoutables;
    }

    @Override
    public void setInformationLivraison(Date dateLivraison, Horaire heureLivraison, Position lieuxLivraison) {
        informationLivraison.setInformationLivraison(dateLivraison, heureLivraison, lieuxLivraison);
    }

    @Override
    public void setInformationLivraisonForced(Date dateLivraison, Horaire heureLivraison, Position lieuxLivraison) {
        informationLivraison.setInformationLivraisonForced(dateLivraison, heureLivraison, lieuxLivraison);
    }

    @Override
    public InformationLivraison getInformationLivraison() {
        return informationLivraison;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
