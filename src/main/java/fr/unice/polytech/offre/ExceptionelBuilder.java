package fr.unice.polytech.offre;

import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.OffreUtils;

import java.util.List;

public class ExceptionelBuilder extends CreneauBuilder implements ICreneauBuilder {


    @Override
    public void reset() {
        result = new CreneuExeptionel();

    }

    @Override
    public void buildJOurDuCreneau(String jour) {
        ((CreneuExeptionel) result).setDateCreneau(OffreUtils.convertStringToLocaleDate(jour));
    }



    public CreneuExeptionel getResult() {
        return (CreneuExeptionel) result;
    }
}
