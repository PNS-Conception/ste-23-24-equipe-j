package fr.unice.polytech.restaurant.Discount;

import fr.unice.polytech.builder.TypeCommandeSimple;
import fr.unice.polytech.commande.CommandeSimple;
import fr.unice.polytech.commande.SystemeCommande;
import fr.unice.polytech.exceptions.*;
import fr.unice.polytech.globalsystem.GlobalSystem;
import fr.unice.polytech.nourriture.MenuPlat;
import fr.unice.polytech.nourriture.TypeMenuPlat;
import fr.unice.polytech.restaurant.*;
import fr.unice.polytech.utilisateur.CompteUtilisateur;
import fr.unice.polytech.utilisateur.StatusUtilisateur;
import fr.unice.polytech.utils.temps.Date;
import fr.unice.polytech.utils.temps.Horaire;
import fr.unice.polytech.utils.adress.Position;
import fr.unice.polytech.nourriture.Menu;
import fr.unice.polytech.utils.temps.HoraireDate;
import io.cucumber.java.fr.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Discount {
    ArrayList<CompteUtilisateur> utilisateurs = new ArrayList<>();
    GlobalSystem globalSystem = new GlobalSystem();
    private final RestaurantManager restaurantManager = new RestaurantManager();
    SystemeCommande systemeCommande = new SystemeCommande();
    Restaurant currentRestaurant;
    CommandeSimple currentCommande;
    CompteUtilisateur c;

    @Etantdonnéque("les utilisateurs connectés sont : \\(passDiscount)")
    public void lUtilisateurEstLoggé(Map<String, String> users) {
        users.keySet().forEach(prenom -> {
            utilisateurs.add(this.globalSystem.createAccount(users.get(prenom), prenom));
        });
        assertEquals(users.size(), utilisateurs.size());
    }

    @Etque("l'utilisateur {string} {string} est un {string} \\(passDiscount)")
    public void lUtilisateurEstUnPassDiscount(String prenom, String nom, String statut) {
        StatusUtilisateur userStatut = StatusUtilisateur.getStatusUtilisateur(statut);
        for (int i = 0; i< utilisateurs.size(); i++) {
            CompteUtilisateur compteUtilisateur = utilisateurs.get(i);
            if (compteUtilisateur.getNom().equals(nom) && compteUtilisateur.getPrenom().equals(prenom)) {
                compteUtilisateur.setStatusUtilisateur(userStatut);
                assertEquals(userStatut, utilisateurs.get(i).getStatusUtilisateur());
                return;
            }
        }
        assert false : "L'utilisateur n'existe pas";
    }

    @Etque("les utilisateur peut accéder aux restaurants suivant : \\(passDiscount)")
    public void lesUtilisateurPeutAccéderAuxRestaurantsSuivantPassDiscount(List<String> restaurants) {
        for (String restaurant : restaurants) {
            restaurantManager.addRestaurant(new Restaurant(restaurant, new Position(0,0), 100));
        }
        assertEquals(restaurants.size(), restaurantManager.getRestaurants().size());
    }

    @Etque("le restaurant {string} propose les menus suivant : \\(passDiscount)")
    public void leRestaurantProposeLesMenusSuivantPassDiscount(String nomRestaurant, Map<String, Double> menus) {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        for (Map.Entry<String, Double> menu : menus.entrySet()) {
            restaurant.addMenu(new Menu(menu.getKey(), menu.getValue()));
        }

        try {
            assertEquals(menus.size(), restaurant.getMenus().size());
        } catch (AucunMenuException e) {
            assert false : "Le restaurant ne doit pas avoir aucun menu";
        }
    }

    @Etque("le restaurant {string} propose les réductions suivant : \\(passDiscount)")
    public void leRestaurantProposeLesRéductionsSuivantPassDiscount(String nomRestaurant, Map<String, Integer> discounts) {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        for (String statut : discounts.keySet()) {
            StatusUtilisateur userStatut = StatusUtilisateur.getStatusUtilisateur(statut);
            restaurant.getSpecialRate().addSpecialRate(userStatut, discounts.get(statut));
        }
        for (String statut : discounts.keySet()) {
            StatusUtilisateur userStatut = StatusUtilisateur.getStatusUtilisateur(statut);
            assertEquals((int) discounts.get(statut), (int) restaurant.getSpecialRate().getSpecialRate(userStatut));
        }
    }

    @Etque("le restaurant {string} propose une réduction de {int} \\(%) valable {int} jours pour les bon clients ayant effectué plus de {int} commandes. \\(passDiscount)")
    public void leRestaurantProposeUneRéductionDeValableJoursPourLesBonClientsAyantEffectuéPlusDeCommandesPassDiscount(String nomRestaurant, int discountRate, int validity, int nbCommande) {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        restaurant.getGoodClientReduction().setNbCommandeToGetReduction(nbCommande);
        restaurant.getGoodClientReduction().setReductionRate(discountRate);
        restaurant.getGoodClientReduction().setNbDateReductionLast(validity);
        assertEquals((int) discountRate,(int) restaurant.getGoodClientReduction().getReductionRateEntered());
        assertEquals(validity, restaurant.getGoodClientReduction().getNbDateReductionLast());
        assertEquals(nbCommande, restaurant.getGoodClientReduction().getNbCommandeToGetReduction());
    }

    @Etque("l'utilisateur {string} {string} a effectué {int} commandes au restaurant {string} \\(passDiscount)")
    public void lUtilisateurAEffectuéCommandesAuRestaurantPassDiscount(String prenom, String nom, int nbCommandeDone, String nomRestaurant) throws STEException {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        for (CompteUtilisateur compteUtilisateur : utilisateurs) {
            if (compteUtilisateur.getNom().equals(nom) && compteUtilisateur.getPrenom().equals(prenom)) {
                for (int i = 0; i < nbCommandeDone; i++) {
                    this.passXCommande(restaurant, compteUtilisateur);
                }
                assertEquals(nbCommandeDone, restaurant.getGoodClientReduction().getNbCommandeByUser(compteUtilisateur));
                return;
            }
        }
    }

    @Quand("l'utilisateur {string} {string} choisit le restaurant de nom {string} \\(passDiscount)")
    public void lUtilisateurChoisiLeRestaurantDeNomPassDiscount(String prenom, String nom, String nomRestaurant) {
        currentRestaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        for (CompteUtilisateur compteUtilisateur : utilisateurs) {
            if (compteUtilisateur.getNom().equals(nom) && compteUtilisateur.getPrenom().equals(prenom)) {
                currentCommande = (CommandeSimple) systemeCommande.creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.SIMPLE);
                this.c = compteUtilisateur;
                break;
            }
        }
        assertNotNull(currentRestaurant);
        assertEquals(nomRestaurant, currentRestaurant.getNomRestaurant());
        assertEquals(currentCommande.getCreateur().getNom(), nom);
        assertEquals(currentCommande.getCreateur().getPrenom(), prenom);
    }

    @Alors("le prix total de la commande doit être de {double} \\(passDiscount)")
    public void lePrixTotalDeLaCommandeDoitÊtreDePassDiscount(double priceExpected) {
        int specialRate = this.currentRestaurant.getSpecialRate().getSpecialRate(this.c.getStatusUtilisateur());
        int goodClientReduction = this.currentRestaurant.getGoodClientReduction().getReductionRate(this.c, new HoraireDate());
        int rate = specialRate + goodClientReduction;

        assertEquals(Math.round(priceExpected*100), Math.round(currentCommande.getPrix()*100));
    }

    @Etque("l'utilisateur {string} {string} a effectué {int} commandes au restaurant {string} le {string} à {string} \\(passDiscount)")
    public void lUtilisateurAEffectuéCommandesAuRestaurantLeÀPassDiscount(String prenom, String nom, int nbCommandeDone, String nomRestaurant, String dateInput, String horaireInput) throws STEException {
        Restaurant restaurant = restaurantManager.getRestaurantParNom(nomRestaurant);
        for (CompteUtilisateur compteUtilisateur : utilisateurs) {
            if (compteUtilisateur.getNom().equals(nom) && compteUtilisateur.getPrenom().equals(prenom)) {
                int nbPreviousCommande = restaurant.getGoodClientReduction().getNbCommandeByUser(compteUtilisateur);
                for (int i = 0; i < nbCommandeDone; i++) {
                    this.passXCommande(restaurant, compteUtilisateur, new Date(dateInput), new Horaire(horaireInput));
                }
                assertEquals((nbCommandeDone + nbPreviousCommande)%restaurant.getGoodClientReduction().getNbCommandeToGetReduction(), restaurant.getGoodClientReduction().getNbCommandeByUser(compteUtilisateur));
                return;
            }
        }
    }

    @Etque("l'utilisateur {string} {string} valide la commande pour le {string} à {string} \\(passDiscount)")
    public void lUtilisateurValideLaCommandePourLeÀPassDiscount(String prenom, String nom, String dateInput, String horaireInput) throws STEException {
        currentCommande.setInformationLivraisonForced(new Date(dateInput), new Horaire(horaireInput), new Position(""));
    }

    @Etque("l'utilisateur {string} {string} choisit les menus : \\(passDiscount)")
    public void lUtilisateurChoisitLesMenusPassDiscount(String prenom, String nom, List<String> menus) throws STEException {
        currentCommande.setInformationLivraison(new Date(), new Horaire(), new Position(""));
        for (String menu : menus) {
            List<MenuPlat> listMenus = this.currentRestaurant.getMenus();
            if (listMenus.size()!=0) {
                for (MenuPlat menuPlat : listMenus) {
                    if (menuPlat.getNom().equals(menu)) {
                        try {
                            currentCommande.ajoutMenuPlat(menuPlat, TypeMenuPlat.MENU);
                        } catch (CapaciteDepasseException e) {
                            assert false : "Impossible d'ajouter une commande";
                        } catch (RestaurantNonValideException e) {
                            assert false : "Le restaurant associé à la commande n'est pas celui du menu";
                        }
                    }
                }
            } else {
                assert false : "Le restaurant n'a pas de menu";
            }
        }

        Map<MenuPlat,Integer> menuOfTheCommande = currentCommande.getMenuPlats();
        int sum = 0;
        for (MenuPlat menuPlat : menuOfTheCommande.keySet()) {
            sum = sum + menuOfTheCommande.get(menuPlat);
        }
        assertEquals(menus.size(), sum);
    }

    @Etque("le prix de la commande est de {double} \\(passDiscount)")
    public void lePrixDeLaCommandeEstDePassDiscount(double priceExpected) {
        assertEquals(Math.round(priceExpected*100), Math.round(currentCommande.getPrix()*100));
    }


    @Etque("l'utilisateur {string} {string} paye la commande \\(passDiscount)")
    public void lUtilisateurPayeLaCommandePassDiscount(String prenom, String nom) throws STEException {
        CompteUtilisateur compteUtilisateurSelected = null;
        for (CompteUtilisateur compteUtilisateur : utilisateurs) {
            if (compteUtilisateur.getNom().equals(nom) && compteUtilisateur.getPrenom().equals(prenom)) {
                compteUtilisateurSelected = compteUtilisateur;
            }
        }
        if (compteUtilisateurSelected == null) {
            assert false : "L'utilisateur n'existe pas";
            return;
        }
        currentCommande.payerCommande(compteUtilisateurSelected.createToken(CompteUtilisateur.DEFAULT_PASSWORD));
        currentCommande = null;
        currentRestaurant = null;
    }

    private void passXCommande(Restaurant restaurant, CompteUtilisateur compteUtilisateur) throws STEException {
        this.passXCommande(restaurant, compteUtilisateur, new Date(), new Horaire());
    }

    private void passXCommande(Restaurant restaurant, CompteUtilisateur compteUtilisateur, Date date, Horaire horaire) throws STEException {
        CommandeSimple commande = (CommandeSimple) systemeCommande.creerCommandeSimpleMultipleGroupe(compteUtilisateur, TypeCommandeSimple.SIMPLE);
        commande.setInformationLivraison(date, horaire, new Position(""));
        List<MenuPlat> listMenus = restaurant.getMenus();
        if (listMenus.size()!=0) {
            try {
                commande.ajoutMenuPlat(listMenus.get(0), TypeMenuPlat.MENU);
                assertEquals(1, commande.getMenuPlats().size());
            } catch (CapaciteDepasseException e) {
                assert false : "Impossible d'ajouter une commande";
            } catch (RestaurantNonValideException e) {
                assert false : "Le restaurant associé à la commande n'est pas celui du menu";
            }
        } else {
            assert false : "Le restaurant n'a pas de menu";
        }
        commande.payerCommande(compteUtilisateur.createToken(CompteUtilisateur.DEFAULT_PASSWORD));
    }
}
