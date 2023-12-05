package fr.unice.polytech.tracabilite;

import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.util.*;

public class Historique {

    /**
     * Class used to store the history of the commands for a selected restaurant
     */

    //ATTRIBUTS
    /**
     * HashMap witch contains the history of the commands for a selected restaurant
     */
    private Map<HoraireDate, List<ICommande>> historiqueCommmande;


    //CONSTRUCTEUR
    public Historique() {
        this.historiqueCommmande = new HashMap<>();
    }


    //GETTER AND SETTER
    public Map<HoraireDate, List<ICommande>> getCommandeUser() {
        return historiqueCommmande;
    }

    public List<ICommande> getCommandeHoraire(HoraireDate horaireDate) {
        return historiqueCommmande.get(horaireDate);
    }

    public List<ICommande> getArrayListCommande() {
        Collection<List<ICommande>> collection = historiqueCommmande.values();

        ArrayList<ICommande> toutesLesCommandes = new ArrayList<>();

        // Parcourir chaque ArrayList dans la Collection et ajouter ses éléments à la nouvelle liste
        for (List<ICommande> listeCommandes : collection) {
            toutesLesCommandes.addAll(listeCommandes);
        }
        return toutesLesCommandes;
    }


    //METHODS
    public void addCommande(ICommande commande) {
        HoraireDate horaireDate = commande.getInformationLivraison().getHoraireDate();
        if (historiqueCommmande.containsKey(horaireDate)) {
            List<ICommande> commandes = historiqueCommmande.get(horaireDate);
            commandes.add(commande);
            historiqueCommmande.put(commande.getInformationLivraison().getHoraireDate(), commandes);
        } else {
            ArrayList<ICommande> commandes = new ArrayList<>();
            commandes.add(commande);
            historiqueCommmande.put(horaireDate, commandes);
        }
    }
}
