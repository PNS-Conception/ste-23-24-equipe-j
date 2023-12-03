package fr.unice.polytech.recommendation;

import fr.unice.polytech.globalSystem.GlobalSystem;
import fr.unice.polytech.livraison.CompteLivreur;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NotationLivreur {
    CompteUtilisateur compteUtilisateur;
    CompteLivreur compteLivreur;
    GlobalSystem globalSystem = new GlobalSystem();
    List<Integer> notesBefore;
    int nbNotesBefore;
    double noteTotalBefore;

    @Etantdonnéque("l'utilisateur {string} {string} est connecté \\(pass)")
    public void lUtilisateurEstConnecte(String prenom, String nom){
        compteUtilisateur = globalSystem.createAccount(nom, prenom);
    }

    @Etque("le livreur {string} {string} a la liste de notes suivante :")
    public void leLivreurALaListeDeNotesSuivante(String prenom, String nom, List<Integer> notes){
        compteLivreur = new CompteLivreur(prenom, nom);
        for (Integer note: notes){
            compteLivreur.addNote(note);
        }
    }

    @Quand("l'utilisateur attribue la note de {int} au livreur")
    public void lUtilisateurAttribueNoteàLivreur(int note){
        notesBefore = compteLivreur.getNotes();
        nbNotesBefore = compteLivreur.getNotes().size();
        noteTotalBefore = compteLivreur.getNote();
        compteUtilisateur.noteLivreur(compteLivreur, note);
    }

    @Alors("la note a été ajouté à la liste de notes du livreur")
    public void laNoteAEtéAjoutéàLaListeDeNotesLivreur(){
        assertEquals(nbNotesBefore+1,compteLivreur.getNotes().size());
    }

    @Et("la note du livreur à été modifiée par l'ajout de cette nouvelle note : {int}")
    public void laNoteAEtéModifiée(int noteAjoutee){
        double expected = 0;
        for (Integer note: notesBefore){
            expected += note;
        }
        expected /= compteLivreur.getNotes().size();

        assertEquals(expected, compteLivreur.getNote(), 0.0001);
    }

}
