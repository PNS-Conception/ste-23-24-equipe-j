package fr.unice.polytech.restaurant.Reduction;

import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Position;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

public class GoodClientReduction {

    private int nbCommandeToGetReduction;
    private int reductionRate;
    private int nbDateReductionLast;

    private HashMap<CompteUtilisateur, Integer> nbCommandeByUser;
    private HashMap<CompteUtilisateur, HoraireDate> goodClientRange;



    public GoodClientReduction(int nbCommande, int reduction, int nbDay) {
        this.nbCommandeToGetReduction = nbCommande;
        this.reductionRate = reduction;
        this.nbDateReductionLast = nbDay;
        this.nbCommandeByUser = new HashMap<>();
    }

    public void addCommande(CompteUtilisateur user) {
        if (nbCommandeByUser.containsKey(user)) {
            int nbCommandeByDate = nbCommandeByUser.get(user);
            nbCommandeByDate = nbCommandeByDate + 1;
            nbCommandeByUser.put(user, nbCommandeByDate);
        } else {
            nbCommandeByUser.put(user, 1);
        }
    }

    private boolean checkNbCommandeCondition(CompteUtilisateur user) {
        if (nbCommandeByUser.containsKey(user)) {
            int nbCommande = nbCommandeByUser.get(user);
            return nbCommande >= nbCommandeToGetReduction;
        }
        return false;
    }

    private HoraireDate getNowHoraireDate() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        Date date = new Date(day, month, year);
        Horaire horaire = new Horaire(hour, minute);
        HoraireDate horaireDate = new HoraireDate(date, horaire);
        return horaireDate;
    }

    private HoraireDate determinateGoodClientLast() {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth() + this.nbDateReductionLast;
        int hour = now.getHour();
        int minute = now.getMinute();
        Date date = new Date(day, month, year);
        Horaire horaire = new Horaire(hour, minute);
        HoraireDate horaireDate = new HoraireDate(date, horaire);
        return horaireDate;
    }

    private void checkIfUserIsGoodClientAfterPaiement(CompteUtilisateur user) {
        if (this.checkNbCommandeCondition(user)) {
            this.nbCommandeByUser.put(user, 0);
            this.goodClientRange.put(user, this.determinateGoodClientLast());
        } else {
            if (this.goodClientRange.containsKey(user)) {
                HoraireDate goodClientLast = this.goodClientRange.get(user);
                HoraireDate now = this.getNowHoraireDate();
                if (goodClientLast.compareTo(now) < 0) {
                    this.goodClientRange.remove(user);
                }
            }
        }
    }

    public boolean checkIfUserIsGoodClient(CompteUtilisateur user) {
        if (this.goodClientRange.containsKey(user)) {
            HoraireDate goodClientLast = this.goodClientRange.get(user);
            HoraireDate now = this.getNowHoraireDate();
            return goodClientLast.compareTo(now) > 0;
        }
        return false;
    }

    public int getReductionRate(CompteUtilisateur user) {
        if (this.checkIfUserIsGoodClient(user)) {
            return this.reductionRate;
        }
        return 0;
    }







}
