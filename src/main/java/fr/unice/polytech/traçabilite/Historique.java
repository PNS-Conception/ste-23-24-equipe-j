package fr.unice.polytech.traçabilite;

import fr.unice.polytech.commande.interfacecommande.ICommande;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Historique {


    private HashMap<HoraireDate, ArrayList<ICommande>> historique;


    public Historique() {
        this.historique = new HashMap<>();
    }

    public void addCommande(ICommande commande) {
        HoraireDate horaireDate = commande.getInformationLivraison().getHoraireDate();
        if (historique.containsKey(horaireDate)) {
            ArrayList<ICommande> commandes = historique.get(horaireDate);
            commandes.add(commande);
            historique.put(commande.getInformationLivraison().getHoraireDate(), commandes);
        } else {
            ArrayList<ICommande> commandes = new ArrayList<>();
            commandes.add(commande);
            historique.put(horaireDate, commandes);
        }
    }

    public ArrayList<ICommande> getArrayListCommande() {
        Collection<ArrayList<ICommande>> collection = historique.values();

        ArrayList<ICommande> toutesLesCommandes = new ArrayList<>();

        // Parcourir chaque ArrayList dans la Collection et ajouter ses éléments à la nouvelle liste
        for (ArrayList<ICommande> listeCommandes : collection) {
            toutesLesCommandes.addAll(listeCommandes);
        }
        return toutesLesCommandes;
    }

    public HashMap<HoraireDate,ArrayList<ICommande>> getCommandeUser() {
        return historique;
    }

    public ArrayList<ICommande> getCommandeHoraire(HoraireDate horaireDate) {
        return historique.get(horaireDate);
    }
}
