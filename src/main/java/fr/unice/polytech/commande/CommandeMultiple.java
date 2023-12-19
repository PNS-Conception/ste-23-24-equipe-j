package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ILivrable;
import fr.unice.polytech.livraison.InformationLivraison;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;

/**
 * Classe d'une commande Multiple payable et livrable
 * @author Equipe J
 */
public class CommandeMultiple extends AbstractCommandeMultiple implements ILivrable {
    private final InformationLivraison informationLivraison;

    /**
     * Constructeur par défaut d'une commande multiple
     * @param idCommande l'identifiant d'une commande
     * @param createurCommande le créateur de la commande
     */
    public CommandeMultiple(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
        informationLivraison = new InformationLivraison(this);
    }


    @Override
    public InformationLivraison getInformationLivraison() {
        return informationLivraison;
    }

    @Override
    public void setInformationLivraisonForced(Date dateLivraison, Horaire heureLivraison, Position lieuxLivraison) {
        informationLivraison.setInformationLivraisonForced(dateLivraison, heureLivraison, lieuxLivraison);
    }

    @Override
    public void setInformationLivraison(Date dateLivraison, Horaire heureLivraison, Position lieuxLivraison) {
        informationLivraison.setInformationLivraison(dateLivraison, heureLivraison, lieuxLivraison);
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
