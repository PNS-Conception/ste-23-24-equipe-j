package fr.unice.polytech.recommendation;

import fr.unice.polytech.globalSystem.GlobalSystem;
import fr.unice.polytech.livraison.CompteLivreur;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.*;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NotationUtilisateur {
    CompteUtilisateur compteUtilisateur;
    CompteLivreur compteLivreur;
    GlobalSystem globalSystem = new GlobalSystem();
    List<Integer> notesRetardBefore;
    int nbNotesRetardBefore;
    double noteRetardTotalBefore;

    List<Integer> notesAmabiliteBefore;
    int nbNotesAmabiliteBefore;
    double noteAmabiliteTotalBefore;

    @Etantdonnéque("le livreur {string} {string} a livré l'utilisateur {string} {string}")
    public void livreurALivréUtilisateur(String prenomLivreur, String nomLivreur, String prenomUtilisateur, String nomUtilisateur){
        compteLivreur = new CompteLivreur(prenomLivreur,nomLivreur);
        compteUtilisateur = globalSystem.createAccount(nomUtilisateur, prenomUtilisateur);
    }

    @Etque("l'utilisateur a la liste de notes suivante pour le retard:")
    public void utilisateurAListeDeNotesRetard(List<Integer> notesRetard){
        for (Integer note: notesRetard) {
            compteUtilisateur.addNoteRetard(note);
        }
    }

    @Etque("l'utilisateur a la liste de notes suivante pour l'amabilité:")
    public void utilisateurAListeDeNotesAmabilité(List<Integer> notesAmabilite){
        for (Integer note: notesAmabilite) {
            compteUtilisateur.addNoteAmabilite(note);
        }
    }

    @Quand("le livreur attribue les notes de {int} et {int} à l'utilisateur")
    public void livreurAttribueNotesAUtilisateur(int noteRetard, int noteAmabilite){
        // Retard
        notesRetardBefore = compteUtilisateur.getNotesRetard();
        nbNotesRetardBefore = compteUtilisateur.getNotesRetard().size();
        noteRetardTotalBefore = compteUtilisateur.getNoteRetard();

        // Amabilité
        notesAmabiliteBefore = compteUtilisateur.getNotesAmabilite();
        nbNotesAmabiliteBefore = compteUtilisateur.getNotesAmabilite().size();
        noteAmabiliteTotalBefore = compteUtilisateur.getNoteAmabilite();

        compteLivreur.noteUtilisateur(compteUtilisateur, noteRetard, noteAmabilite);
    }

    @Alors("les notes ont été ajoutées aux listes de notes de l'utilisateur")
    public void lesNotesOntEteAjouteesAuxNotesUtilisateur(){
        assertEquals(nbNotesRetardBefore+1,compteUtilisateur.getNotesRetard().size());
        assertEquals(nbNotesAmabiliteBefore+1, compteUtilisateur.getNotesAmabilite().size());
    }

    @Et("les notes de l'utilisateur ont été modifiées par l'ajout de ces nouvelles notes : {int} et {int}")
    public void lesNotesOntEteModifiees(int noteRetard, int noteAmabilite){
        //note retard
        double expectedRetard = 0;
        for (Integer note: notesRetardBefore){
            expectedRetard += note;
        }
        expectedRetard /= compteUtilisateur.getNotesRetard().size();

        assertEquals(expectedRetard, compteUtilisateur.getNoteRetard(), 0.0001);

        //note amabilité
        double expectedAmabilite = 0;
        for (Integer note: notesAmabiliteBefore){
            expectedAmabilite += note;
        }
        expectedAmabilite /= compteUtilisateur.getNotesAmabilite().size();

        assertEquals(expectedAmabilite, compteUtilisateur.getNoteAmabilite(), 0.0001);
    }

}
