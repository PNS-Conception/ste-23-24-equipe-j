package fr.unice.polytech.commande;

import fr.unice.polytech.utilisateur.CompteUtilisateur;

import java.util.Objects;

public class CommandeAfterworks extends CommandeSimpleAvecID {
    int nombrePersonne;
    public CommandeAfterworks(long id, CompteUtilisateur createur, int nombrePersonne) {
        super(id, createur);
        this.nombrePersonne = nombrePersonne;
    }

    public int getNombrePersonne() {
        return nombrePersonne;
    }

    @Override
    public boolean equals(Object o) {
        if (super.equals(o)) {
            CommandeAfterworks commandeAfterworks = (CommandeAfterworks) o;
            return commandeAfterworks.nombrePersonne == this.nombrePersonne;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCommande(), createur, nombrePersonne);
    }
}
