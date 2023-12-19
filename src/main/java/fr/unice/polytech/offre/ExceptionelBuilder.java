package fr.unice.polytech.offre;

import fr.unice.polytech.utils.OffreUtils;

public class ExceptionelBuilder extends CreneauBuilder implements ICreneauBuilder {


    @Override
    public void reset() {
        result = new CreneuExeptionel();

    }

    @Override
    public void buildJOurDuCreneau(String jour) {
        ((CreneuExeptionel) result).setDateCreneau(OffreUtils.convertStringToLocaleDate(jour));
    }



    @Override
    public CreneuExeptionel getResult() {
        return (CreneuExeptionel) result;
    }
}
