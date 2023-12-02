package fr.unice.polytech.offre;

import java.util.List;

public interface  ICreneauBuilder {
    public  void reset();

    public void buildCapacite(String capacite);

    public void buildDebut(String debut);
    public void buildFin(String fin);
    public void buildDuree();
    public  void  capaciteParSlot(String capacite);
    public void buildJOurDuCreneau(String jour);


}
