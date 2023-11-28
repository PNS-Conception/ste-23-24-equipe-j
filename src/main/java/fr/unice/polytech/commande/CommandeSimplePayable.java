package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.IPayable;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.paiement.PaiementCommande;
import fr.unice.polytech.utils.Token;
import fr.unice.polytech.utils.temps.HoraireDate;

/**
 * Classe abstraite d'une commande seule payable avec un ID
 * @author Equipe J
 */
public abstract class CommandeSimplePayable extends CommandeSimpleAvecID implements IPayable {
    protected PaiementCommande paiementCommande;

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    protected CommandeSimplePayable(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
        paiementCommande = new PaiementCommande(0);
    }

    @Override
    public double getPrix() {
        if (super.restaurant != null) {
            this.checkDiscount();
        }
        return paiementCommande.getPrix();
    }

    @Override
    public void ajoutMenuPlat(MenuPlat menuPlat, TypeMenuPlat typeMenuPlat) throws RestaurantNonValideException, CapaciteDepasseException {
        super.ajoutMenuPlat(menuPlat, typeMenuPlat);
        this.checkDiscount();
        paiementCommande.ajoutPrix(menuPlat.getPrix());
        this.restaurant.increaseReservation(this.getInformationLivraison().getHoraireDate(), 1);
    }

    @Override
    public boolean supprimerMenuPlat(MenuPlat menuPlat) {
        boolean estSupprimer = super.supprimerMenuPlat(menuPlat);

        if (estSupprimer)
            paiementCommande.retraitPrix(menuPlat.getPrix());

        return estSupprimer;
    }

    @Override
    public void checkDiscount() {
        int specialRate = super.restaurant.getSpecialRate().getSpecialRate(super.createur.getStatut());
        int goodClientReduction = super.restaurant.getGoodClientReduction().getReductionRate(super.createur, this.getInformationLivraison().getHoraireDate());
        int rate = specialRate + goodClientReduction;
        paiementCommande.setDiscount(rate);
    }

    @Override
    public void payerCommande(Token token) throws TokenException {
        if (this.createur.checkToken(token) && paiementCommande.payerCommande()) {
            setEtatCommande(EtatCommande.EN_PREPARATION);
            this.createur.ajouterCommande(this, token);
            this.restaurant.getGoodClientReduction().addCommande(this.createur);
        } else if (!this.createur.checkToken(token)) {
            throw new TokenException();
        }
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
