package fr.unice.polytech.commande;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.exceptions.AucunMenuException;
import fr.unice.polytech.exceptions.CapaciteDepasseException;
import fr.unice.polytech.exceptions.RestaurantNonValideException;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.offre.*;
import fr.unice.polytech.restaurant.Restaurant;
import fr.unice.polytech.restaurant.RestaurantManager;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utils.Date;
import fr.unice.polytech.utils.Horaire;
import fr.unice.polytech.utils.OffreUtils;
import io.cucumber.java.fr.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static fr.unice.polytech.utils.OffreUtils.createCreneaus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommanderPourUnCreneau {
    RestaurantManager restaurantManager=new RestaurantManager();
    Restaurant restaurant;
    CreneauDirector creneauDirector=new CreneauDirector();
    Scheduler scheduler=new Scheduler();
    CompteUtilisateur compteUtilisateur ;
    private final SystemeCommande systemeCommande = new SystemeCommande();
    Date date;
    @Etantdonné("un restaurant  {string}")
    public void unRestaurant(String nomRestaurant) {
        restaurant=new Restaurant(nomRestaurant);
        restaurantManager.addRestaurant(restaurant);

    }

    @Etqu("il a possède uniquement des créneaux  {string}:")
    public void ilAPossèdeUniquementDesCréneaux(String typeCreneau, List<List<String>>iCreneauList) {
     //   restaurant.ajouterCreneau();
            createCreneaus(typeCreneau,iCreneauList,creneauDirector).forEach(c->restaurant.ajouterCreneau(c));
        scheduler.ajouterRestaurant(restaurant);


    }

    @Quand("je demande la liste des creneaux pour le restaurant {string}")
    public void jeDemandeLaListeDesCreneauxPourLeRestaurant(String nomRestaurant) {
        restaurant=new Restaurant(nomRestaurant);

    }

    @Alors("j'obtient les creneaux {string}:")
    public void jObtient(String typeCreneau,List<List<String>>horaires) {

       assertEquals(createCreneaus(typeCreneau,horaires,creneauDirector),scheduler.getCreneauxDisponibles(restaurant)) ;

    }


    @Quand("je commande à {string} {string} prochain  entre {string} et {string} un menu:")
    public void jeCommandeÀAujourdHuiEntreEtUnMenu(String nomRestaurant, String dateCommande, String debutCreneau,String finCreneau,List<String>menus) throws AucunMenuException,CapaciteDepasseException,RestaurantNonValideException{
        Date date=OffreUtils.convertStringToDate(dateCommande);
        Horaire debut=new Horaire(debutCreneau);
        Horaire fin =new Horaire(finCreneau);
       assertTrue( scheduler.restaurantContientCreneau(restaurant,dateCommande,debut,fin));
       ICreneau creneau=scheduler.getCreneau(restaurant,dateCommande,debut,fin );
       CommandeSimple commande=   (CommandeSimple) systemeCommande.creerCommandeSimpleMultipleGroupe(compteUtilisateur,
                TypeCommandeSimple.SIMPLE);
       List<MenuPlat>menuPlats=restaurant.getMenus();
       Optional<MenuPlat> menu=menuPlats.stream().filter(m->m.getNom().equals(menus.get(0))).findFirst();
       assertTrue(menu.isPresent());
       commande.ajoutMenuPlat(menu.get(), TypeMenuPlat.PLAT);
       scheduler.diminuerLaCapacitePourCreneau(restaurant,date,creneau,1);

    }



    @Etqu("il propose les menus suivant :")
    public void ilProposeLesMenusSuivant(Map<String,Double>menus) throws AucunMenuException{
        for (Map.Entry<String, Double> menu : menus.entrySet()) {
            restaurant.addMenu(new Menu(menu.getKey(), menu.getValue()));
        }
        Assert.assertEquals(menus.size(), restaurant.getMenus().size());

    }

    @Et("un utilisateur enregisté {string} {string}")
    public void unUtilisateur(String nom, String prenom) {
       compteUtilisateur= new CompteUtilisateur(nom, prenom);
    }

    @Quand("je demande la liste des creneaux pour le restaurant {string} pour le {string}")
    public void jeDemandeLaListeDesCreneauxPourLeRestaurantPourLe(String nomRestaurant, String dateC) {
        restaurant=new Restaurant(nomRestaurant);
        date= new Date(dateC);

    }

    @Alors("j'obtient les creneaux :")
    public void jObtientLesCreneaux(List<String>creneaux) {
        CreneauQuotidien creneau=new CreneauQuotidien(new Horaire(creneaux.get(0)),new Horaire(creneaux.get(1)),Integer.parseInt(creneaux.get(2)),creneaux.get(3));
        ICreneau tes=scheduler.getCreneauxDisponblesFor(date,  restaurant).get(0);
        assertEquals(creneau, tes);
    }


    @Alors("{string} contient pour {string} prochain:")
    public void contientPourProchain(String nomR, String dateC,List<String >creneaux) {
        Date date=OffreUtils.convertStringToDate(dateC);
        CommandeCreneau commandeCreneau=scheduler.getCommandesPlannifiees().get(restaurant).getCommandeCreneau(date,new Horaire(creneaux.get(0)),new Horaire(creneaux.get(1)));
        assertTrue(commandeCreneau!=null&&commandeCreneau.getNbCommandesPasses()==Integer.parseInt(creneaux.get(2))&&commandeCreneau.getNbCommandesPossibles()==Integer.parseInt(creneaux.get(3)));
    }


}
