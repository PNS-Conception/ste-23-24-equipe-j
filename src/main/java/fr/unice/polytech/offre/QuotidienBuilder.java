package fr.unice.polytech.offre;

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
