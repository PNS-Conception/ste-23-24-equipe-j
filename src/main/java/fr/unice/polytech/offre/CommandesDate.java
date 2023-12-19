package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;

import java.util.*;

public class CommandesDate {
    private final Map<Date, List<CommandeCreneau>> commandesDate;

    public CommandesDate() {
        this.commandesDate = new HashMap<>();
    }

    public boolean ajouterCommandePourLaDate(Date date,ICreneau iCreneau,int nbCommandes){
     List<CommandeCreneau>commandeCreneaus=  commandesDate.get(date);
     if(commandeCreneaus!=null){
         Optional<CommandeCreneau>creneauOptional= commandeCreneaus.stream().filter(c->c.getiCreneau().equals(iCreneau)).findFirst();
         if(creneauOptional.isPresent()) return creneauOptional.get().ajouterNombreDeCommande(nbCommandes);
         else {
             CommandeCreneau commandeCreneau=new CommandeCreneau(nbCommandes, iCreneau);
             if(CommandeCreneau.isValid(commandeCreneau)) return  commandeCreneaus.add(commandeCreneau);
             else return false ;
         }
     }
     else{
         CommandeCreneau commandeCreneau=new CommandeCreneau(nbCommandes,iCreneau);
         if(CommandeCreneau.isValid(commandeCreneau)){
             commandesDate.put(date, Arrays.asList(commandeCreneau));
             return true;
         }
         else return false;


     }
    }
    public CommandeCreneau getCommandeCreneau(Date date, Horaire debut ,Horaire fin){
        if(!commandesDate.containsKey(date))return null;
        Optional<CommandeCreneau>commandeCreneauOptional=commandesDate.get(date).stream().filter(c->c.getiCreneau().getDebut().equals(debut)&&c.getiCreneau().getFin().equals(fin)).findFirst();
        return commandeCreneauOptional.orElse(null);

    }

}
