package fr.unice.polytech.recommendation;

import fr.unice.polytech.globalsystem.GlobalSystem;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etque;
import io.cucumber.java.fr.Quand;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NotationRestaurant {
    Restaurant restaurant;
    GlobalSystem globalSystem = new GlobalSystem();
    CompteUtilisateur compteUtilisateur =  globalSystem.createAccount("Dupont", "Antoine");
    List<Integer> notesBefore;
    int nbNotesBefore;
    double noteTotalBefore;

    @Etque("le restaurant {string} a la liste de notes suivante :")
    public void leRestaurantALaListeDeNotes(String nomRestaurant,List<Integer> notesRestaurant){
        restaurant = new Restaurant(nomRestaurant);
        for (Integer note: notesRestaurant) {
            restaurant.addNote(note);
        }
    }

    @Quand("l'utilisateur attribue la note de {int} au restaurant")
    public void lUtilisateurAttribueNoteAuRestaurant(int note){
        notesBefore = restaurant.getNotes();
        nbNotesBefore = restaurant.getNotes().size();
        noteTotalBefore = restaurant.getNote();
        compteUtilisateur.noteRestaurant(restaurant, note);
    }

    @Alors("la note a été ajouté à la liste de notes du restaurant")
    public void laNoteAEtéAjoutéàLaListeDeNotesRestaurant(){
        assertEquals(nbNotesBefore+1,restaurant.getNotes().size());
    }

    @Et("la note du restaurant à été modifiée par l'ajout de cette nouvelle note : {int}")
    public void laNoteAEtéModifiée(int noteAjoutee){
        double expected = 0;
        for (Integer note: notesBefore){
            expected += note;
        }
        expected /= restaurant.getNotes().size();

        assertEquals(expected, restaurant.getNote(), 0.0001);
    }

}
