package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.OffreUtils;

import java.util.List;

import static fr.unice.polytech.offre.DayEnum.getDayOfTheWeek;

public class QuotidienBuilder extends CreneauBuilder implements ICreneauBuilder{


    @Override
    public void reset() {
        result = new CreneauQuotidien();

    }

    @Override
    public void buildJOurDuCreneau(String jour) {
        ((CreneauQuotidien) result).setJourDeLaSemaine(getDayOfTheWeek(jour));
    }


    @Override
    public CreneauQuotidien getResult() {
        return (CreneauQuotidien) result;
    }
}
