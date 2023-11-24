package fr.unice.polytech.restaurant.Reduction;

import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.time.LocalDateTime;
import java.util.HashMap;

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
        this.goodClientRange = new HashMap<>();
    }

    public GoodClientReduction(int nbCommande, double reduction, int nbDay) {
        int rateFormatted = (int) Math.round(reduction * 100);
        this.nbCommandeToGetReduction = nbCommande;
        this.reductionRate = rateFormatted;
        this.nbDateReductionLast = nbDay;
        this.nbCommandeByUser = new HashMap<>();
        this.goodClientRange = new HashMap<>();
    }

    public GoodClientReduction() {
        this(0,0,0);
    }



    public void setNbCommandeToGetReduction(int nbCommandeToGetReduction) {
        this.nbCommandeToGetReduction = nbCommandeToGetReduction;
    }

    public void setReductionRate(int reductionRate) {
        this.reductionRate = reductionRate;
    }

    public void setReductionRate(double reductionRate) {
        int rateFormatted = (int) Math.round(reductionRate * 100);
        this.reductionRate = rateFormatted;
    }

    public void setNbDateReductionLast(int nbDateReductionLast) {
        this.nbDateReductionLast = nbDateReductionLast;
    }



    public int getReductionRate(CompteUtilisateur user) {
        if (this.checkIfUserIsGoodClient(user)) {
            return this.reductionRate;
        }
        return 0;
    }



    /**
     * To do after paiement but before check in order to update the number of command made by the user
     * @param user
     */
    public void addCommande(CompteUtilisateur user) {
        if (nbCommandeByUser.containsKey(user)) {
            int nbCommandeByDate = nbCommandeByUser.get(user);
            nbCommandeByDate = nbCommandeByDate + 1;
            nbCommandeByUser.put(user, nbCommandeByDate);
        } else {
            nbCommandeByUser.put(user, 1);
        }
        this.checkIfUserIsGoodClientAfterPaiement(user);
    }



    /**
     * To check after paiement in order to know if need to update Hashmap of good client with this last one
     * @param user
     */
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



    /**
     * To check before paiement in order to apply reduction if needed
     * @param user
     * @return
     */
    private boolean checkIfUserIsGoodClient(CompteUtilisateur user) {
        if (this.goodClientRange.containsKey(user)) {
            HoraireDate goodClientLast = this.goodClientRange.get(user);
            HoraireDate now = this.getNowHoraireDate();
            return goodClientLast.compareTo(now) > 0;
        }
        return false;
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









}
