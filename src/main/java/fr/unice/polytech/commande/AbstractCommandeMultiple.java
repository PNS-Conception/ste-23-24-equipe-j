package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommandeAjoutable;
import fr.unice.polytech.commande.interfacecommande.IPayable;
import fr.unice.polytech.exceptions.TokenException;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Token;
import fr.unice.polytech.utils.paiement.PaiementCommande;

import java.util.*;


/**
 * Classe abstraite d'une commande multiple qui est payable et possède plusieurs sous-commandes simple
 * @author Equipe J
 */
public abstract class AbstractCommandeMultiple extends ACommandeGroupe implements IPayable {
    private final PaiementCommande paiementCommande;
    private Map<Restaurant, CommandeSimpleSansID> commandes;

    /**
     * Constructeur par défaut
     * @param idCommande identifiant de la commandes
     * @param createurCommande le createur de la commande
     */
    protected AbstractCommandeMultiple(long idCommande, CompteUtilisateur createurCommande) {
        super(idCommande, createurCommande);
        paiementCommande = new PaiementCommande();
        commandes = new HashMap<>();
    }

    @Override
    public List<ICommandeAjoutable> getCommandes() {
        return new ArrayList<>(commandes.values());
    }

    @Override
    public void ajouterCommande(ICommandeAjoutable commande) {
        if (!commande.estPayable() &&  commande.estCommandeSimple()) { // Normalement qu'une seule commande sera comme ça
            CommandeSimpleSansID commandeSimple = (CommandeSimpleSansID) commande;
            Optional<Restaurant> restaurant = commandeSimple.getRestaurant();

            restaurant.ifPresent(value -> commandes.put(value, commandeSimple));
            commandeSimple.setCommandeGroupe(this);
        }
    }

    @Override
    public void supprimerCommande(ICommandeAjoutable commande) {
        if (commande.estPayable() && commande.estCommandeSimple()) {
            CommandeSimpleSansID commandeSimple = (CommandeSimpleSansID)  commande;

            commandes.remove(commandeSimple.getRestaurant().orElse(null));
        }
    }

    @Override
    public double getPrix() {
        double sum = 0.0;

        for (CommandeSimpleSansID commande : commandes.values())
            sum += commande.getPrix();

        return sum;
    }

    @Override
    public void payerCommande(Token token) throws TokenException {
        if (paiementCommande.payerCommande()) {
            etatCommande = EtatCommande.EN_PREPARATION;
            for (CommandeSimpleSansID commande : commandes.values())
                commande.setEtatCommande(EtatCommande.EN_PREPARATION);
        }
        else
            throw new TokenException();
    }

    @Override
    public void checkDiscount() {

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
