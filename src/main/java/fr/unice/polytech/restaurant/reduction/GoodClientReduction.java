package fr.unice.polytech.restaurant.Reduction;

import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import fr.unice.polytech.utils.temps.HoraireDate;

import java.time.LocalDateTime;
import java.util.HashMap;

public class GoodClientReduction {

    /**
     * Class used to manage the reduction for good client
     * The reduction is applied if :
     * - the user has made more than nbCommandeToGetReduction command in the last nbDateReductionLast days
     * - the date of the last command is less than nbDateReductionLast days
     */

    // ATTRIBUTES
    private int nbCommandeToGetReduction;

    /**
     * In order to avoid computer imprecision of double type, the reduction rate is stored as an int as follows :
     * reductionRate = Math.around(100 * reductionRateDouble)
     *
     * So the last 2 digits of the number correspond to the decimal part of the reduction rate
     */
    private int reductionRate;

    private int nbDateReductionLast;

    /**
     * HashMap used to store the number of command made by each user
     */
    private HashMap<CompteUtilisateur, Integer> nbCommandeByUser;

    /**
     * HashMap used to store the last date of discount applied for good client (client who benefit a reduction)
     */
    private HashMap<CompteUtilisateur, HoraireDate> goodClientRange;


    // CONSTRUCTOR
    /**
     * Constructor
     * @param nbCommande : number of command to get the reduction
     * @param reduction : reduction rate (in int as explained above)
     * @param nbDay : number of day to get the reduction
     */
    public GoodClientReduction(int nbCommande, int reduction, int nbDay) {
        this.nbCommandeToGetReduction = nbCommande;
        this.reductionRate = reduction;
        this.nbDateReductionLast = nbDay;
        this.nbCommandeByUser = new HashMap<>();
        this.goodClientRange = new HashMap<>();
    }

    /**
     * Constructor
     * @param nbCommande : number of command to get the reduction
     * @param reduction : reduction rate (in double)
     * @param nbDay : number of day to get the reduction
     */
    public GoodClientReduction(int nbCommande, double reduction, int nbDay) {
        int rateFormatted = (int) Math.round(reduction * 100);
        this.nbCommandeToGetReduction = nbCommande;
        this.reductionRate = rateFormatted;
        this.nbDateReductionLast = nbDay;
        this.nbCommandeByUser = new HashMap<>();
        this.goodClientRange = new HashMap<>();
    }

    /**
     * Constructor by default with 0 advantage
     */
    public GoodClientReduction() {
        this(0,0,0);
    }



    // SETTER
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


    // GETTER AND COMMON USED METHODS
    public int getReductionRate(CompteUtilisateur user, HoraireDate horaireDate) {
        if (this.checkIfUserIsGoodClient(user,horaireDate)) {
            return this.reductionRate;
        }
        return 0;
    }

    public int getNbCommandeToGetReduction() {
        return nbCommandeToGetReduction;
    }

    public int getNbDateReductionLast() {
        return nbDateReductionLast;
    }

    public int getReductionRateEntered() {
        return reductionRate;
    }

    public int getNbCommandeByUser(CompteUtilisateur user) {
        if (nbCommandeByUser.containsKey(user)) {
            return nbCommandeByUser.get(user);
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


    // PRIVATE METHODS
    /**
     * Method used to check after paiement in order to know if it is needed to update Hashmap of good client with this last one
     * @param user : user to check
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
     * Method used to check before paiement in order to apply reduction if needed
     * @param user : user to check
     * @return
     */
    private boolean checkIfUserIsGoodClient(CompteUtilisateur user, HoraireDate horaireDate) {
        if (this.goodClientRange.containsKey(user)) {
            HoraireDate goodClientLast = this.goodClientRange.get(user);
            return horaireDate.compareTo(goodClientLast) < 0;
        }
        return false;
    }

    /**
     * Method used to check if the number of command made by the user is greater than the number of command to get the reduction
     * @param user : user to check
     * @return
     */
    private boolean checkNbCommandeCondition(CompteUtilisateur user) {
        if (nbCommandeByUser.containsKey(user)) {
            int nbCommande = nbCommandeByUser.get(user);
            return nbCommande >= nbCommandeToGetReduction;
        }
        return false;
    }

    /**
     * Method used to get the current date and hour
     * @return
     */
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

    /**
     * Method used to determinate the date and hour of the end of the reduction
     * @return
     */
    private HoraireDate determinateGoodClientLast() {

        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth() + this.nbDateReductionLast;

        boolean modif = true;
        while (modif==true) {
            modif = false;
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (day > 31) {
                    day = day - 31;
                    month = month + 1;
                    if (month > 12) {
                        month = month - 12;
                        year = year + 1;
                    }
                    modif = true;
                }
            } else if (month == 2) {
                if (day > 28) {
                    day = day - 28;
                    month = month + 1;
                    if (month > 12) {
                        month = month - 12;
                        year = year + 1;
                    }
                    modif = true;
                }
            } else {
                day = day - 30;
                month = month + 1;
                if (month > 12) {
                    month = month - 12;
                    year = year + 1;
                }
                modif = true;
            }
        }



        int hour = now.getHour();
        int minute = now.getMinute();
        Date date = new Date(day, month, year);
        Horaire horaire = new Horaire(hour, minute);
        HoraireDate horaireDate = new HoraireDate(date, horaire);
        return horaireDate;
    }
}
