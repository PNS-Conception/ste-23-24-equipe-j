package fr.unice.polytech.offre;

import java.util.List;

public class CreneauDirector {
    private ICreneauBuilder builder;
    public void change(ICreneauBuilder iBuilder){
        builder=iBuilder;
    }
    public void make( List<String> horaire){
        builder.reset();
        builder.buildDebut(horaire.get(0));
        builder.buildFin(horaire.get(1));
        builder.buildDuree();
        builder.buildCapacite(horaire.get(2));
        builder.buildJOurDuCreneau(horaire.get(3));



    }
public ICreneau getCreneau(){
       return null;

}
}
