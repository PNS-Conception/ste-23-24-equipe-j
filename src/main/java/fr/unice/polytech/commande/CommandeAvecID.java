package fr.unice.polytech.commande;

import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.observer.EventManager;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.HoraireDate;
import java.time.LocalDateTime;

import java.util.Objects;

/**
 * Classe abstraite d'une commande général avec un ID
 * @author Equipe J
 */
public abstract class CommandeAvecID  implements ICommande {
    private final long idCommande;
    protected final CompteUtilisateur createur;
    protected EtatCommande etatCommande;
    protected HoraireDate horaireDateLivraison;

    // Constructeur

    /**
     * Constructeur par défaut
     * @param idCommande l'identifiant de la commande
     * @param createurCommande le créateur de la commande
     */
    protected CommandeAvecID(long idCommande, CompteUtilisateur createurCommande) {
        this.idCommande = idCommande;
        createur = createurCommande;
        etatCommande = EtatCommande.EN_ATTENTE;

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        Date date = new Date(day, month, year);
        Horaire horaire = new Horaire(hour, minute);
        this.horaireDateLivraison = new HoraireDate(date, horaire);
    }

    protected CommandeAvecID(long idCommande, CompteUtilisateur createurCommande, HoraireDate horaireDateLivraison) {
        this.idCommande = idCommande;
        createur = createurCommande;
        etatCommande = EtatCommande.EN_ATTENTE;
        this.horaireDateLivraison = horaireDateLivraison;
    }

    // Getters et setters

    /**
     * Retourne l'identifiant de la commande
     * @return l'identifiant de la commande
     */
    public long getIdCommande() {
        return idCommande;
    }

    @Override
    public CompteUtilisateur getCreateur() {
        return createur;
    }

    @Override
    public void setEtatCommande(EtatCommande etatCommande) {
        EventManager.notify(this, etatCommande.toString());
        this.etatCommande = etatCommande;
    }

    @Override
    public boolean estLivrable() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        else if (!(o instanceof CommandeAvecID))
            return false;
        CommandeAvecID commande = (CommandeAvecID) o;
        return idCommande == commande.idCommande;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCommande);
    }
}
