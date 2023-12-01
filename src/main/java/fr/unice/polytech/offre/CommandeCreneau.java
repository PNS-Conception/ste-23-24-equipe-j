package fr.unice.polytech.offre;

public class CommandeCreneau {
    private  int nbCommandesPasses;
    private ICreneau iCreneau;

    public int getNbCommandesPossibles() {
        return nbCommandesPossibles;
    }

    public void setNbCommandesPossibles(int nbCommandesPossibles) {
        this.nbCommandesPossibles = nbCommandesPossibles;
    }

    private int nbCommandesPossibles;

    public int getNbCommandesPasses() {
        return nbCommandesPasses;
    }

    public CommandeCreneau(int nbCommandesPasses, ICreneau iCreneau) {
        this.nbCommandesPasses = nbCommandesPasses;
        this.iCreneau = iCreneau;
        nbCommandesPossibles=ICreneau.getCapaciteCreneau(iCreneau);
    }

    public void setNbCommandesPasses(int nbCommandesPasses) {
        this.nbCommandesPasses = nbCommandesPasses;
    }
    private void ajouterNombreCommandesPasses(int nbCommandesPasses){
        this.nbCommandesPasses+=nbCommandesPasses;

    }

    public ICreneau getiCreneau() {
        return iCreneau;
    }

    public void setiCreneau(ICreneau iCreneau) {
        this.iCreneau = iCreneau;
    }
    public boolean ajouterNombreDeCommande(int nbCommandesAAjouter){
        if(nbCommandesPasses+nbCommandesAAjouter<=ICreneau.getCapaciteCreneau(iCreneau)){
            ajouterNombreCommandesPasses(nbCommandesAAjouter);
            return true;
        }

        return false;
    }
    public static boolean isValid(CommandeCreneau commandeCreneau){
         return  commandeCreneau.nbCommandesPasses<=ICreneau.getCapaciteCreneau(commandeCreneau.iCreneau);

    }
}
