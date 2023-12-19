# SopiaTech Eats-Team-23-24-J

## TEAM
Software Architect (SA) : Adote Adjo Emmanuelle  
Quality Assurance Engineer (QA) : Chloé Vandenbrulle  
Continuous Integration and Repository Manager (OPS) : Nicolas Zanin  
Business Analyst and Product Owner (PO) : Arrigoni Guillaume  


## NOS USER CASES :
- "Parcourir les divers menus et restaurants" :  
   - User story #8 : "Obtenir la liste des menus"   
**En tant que** utilisateur du campus, **je veux** obtenir la liste de menu disponible dans un restaurant, **afin de** choisir mon menu dans un restaurant précis.  
*Fichier Feature: restaurant/ListMenu/Obtenir_liste_menus.feature  
Fichier Cucumber: restaurant/ListMenu/ObtenirMenuStepdefs.java*  

- "Accéder à l'historique de ses anciennes commandes" :  
   - User story #9 : "Accéder à un historique quand on est un utilisateur enregistré"  
**En tant que** utilisateur enregistré, **je veux** accéder à mon historique **afin de** voir mes commandes précédentes.  
*Fichier Feature: historique/Historique.feature  
Fichier Cucumber: historique/Historique.java*  

- "Organiser sa commande" :  
   - User Story #12 "Seul les utilisateurs enregistrés peuvent effectuer une commande"  
**En tant que** utilisateur enregistré, **je veux** créer une commande **afin de** me faire livrer.  
*Fichier Feature: commande/Ajouter_une_commande.feature  
Fichier Cucumber: commande/AjouterUneCommande.java*  

- "Choisir son menu" :  
   - User Story #10 : "Ajouter un plat à ma commande"  
**En tant que** utilisateur du campus, **je veux** ajouter un plat à ma commande **afin de** le commander.  
*Fichier Feature: commande/Ajouter_un_plat_à_la_commande.feature  
Fichier Cucumber: commande/AjouterPlatALaCommande.java*  
   - User Story #76 : "Suppression d'un plat dans une commande"  
**En tant que** utilisateur, **je veux** supprimer un plat dans une commande **afin de** ne plus l'avoir dans la commande.    
*Fichier Feature: commande/suppression_plat_commande.feature  
Fichier Cucumber: commande/SupprimerPlatCommande.java*  

- "Donner le lieu de livraison" / "Donner la date de livraison" :  
   - User Story "#16" : "Sélectionner un lieu de livraison pré-enregistré et une date de livraison possible"  
**En tant que** utilisateur enregistré, **je veux** sélectionner un lieu de livraison ainsi qu'une date **afin d'** être livré au bon endroit et au bon moment.  
*Fichier Feature: commande/CommanderPourUnHoraire.feature  
Fichier Cucumber: commande/CommanderPourUnCreneau.java*  

- "Ajout Restaurants" :  
   - User Story #13 : "Ajouter un restaurant"  
**En tant que** Administrateur du campus, **je veux** ajouter un restaurant **afin de** permettre aux utilisateurs de SophiaTech Eats d'avoir plus de choix de repas pour leurs commandes.  
*Fichier Feature: restaurant/AjouterRestaurant/Ajouter_Un_Restaurant.feature  
Fichier Cucumber: restaurant/AjouterRestaurant/AjouterUnRestaurantStepdefs.java*  

- "Consulter la commande" / "Accepter la commande" :  
   - User Story #14 : "Consulter les commandes en préparation et les valider"  
**En tant que** restaurateur, **je veux** valider une commande **afin que** un livreur puisse la prendre.  
*Fichier Feature: commande/Consulter_les_commandes_en_préparation_et_les_valider.feature  
Fichier Cucumber: commande/ConsulterEtValiderUneCommande.java*  

- "Modifier horaires du restaurant" :  
   - User Story #20 : "Mettre à jour les horaires du restaurant"  
**En tant que** restaurateur, **je veux** pouvoir mettre à jour les horaires de mon restaurant **afin de** permettre aux utilisateurs de commander pendant toutes les plages horaires possibles.  
*Fichier Feature: restaurant/Ajouter_Un_Horaire.feature  
Fichier Cucumber: restaurant/AjouterUnHoraire.java*  

- "Créer un groupe" :  
   - User Story #88 : "Crée une commande groupe"  
**En tant que** utilisateur, **je veux** créer une commande groupe **afin de** faire des commandes avec d'autres personnes.  
*Fichier Feature: commandegroupe/Creer_commande_groupe.feature  
Fichier Cucumber: commandegroupe/CreerCommandeGroupe.java  
Nom Scénarios: Création commande groupe, Suppression commande groupe*  
   - User Story #89 : "Rejoindre une commande groupe"  
**En tant que** utilisateur, **je veux** rejoindre une commande groupe **afin d'** ajouter une commande à l'intérieur.  
*Fichier Feature: commandegroupe/Rejoindre_commande_groupe.feature  
Fichier Cucumber: commandegroupe/RejoindreCommandeGroupe.java*  

- "Payer sa commande" :  
   - User Story #40 : "Finaliser une commande"  
**En tant qu'** utilisateur enregistré, **je veux** confirmer et payer ma commande **afin de** la valider.  
*Fichier Feature: paiement/verification_paiement_service.feature  
Fichier Cucumber: paiement/VerificationPaiementService.java*  
- "Visionner les statistiques sur les restaurants et livraisons"  
   - User Story #41 : "Statistique commande par restaurant"  
**En tant qu'** administrateur de l'application, **je veux** pouvoir consulter le nombre de commande par restaurant **afin de** faire une étude sur les possibles bénéfices de l'application.  
*Fichier Feature: statistique/restaurant/Statistique_restaurant.feature  
Fichier Cucumber: statistique/restaurant/StatistiqueRestaurant.java*  
   - User Story #42 : "Statistique commande par utilisateur"  
**En tant qu'** administrateur de l'application, **je veux** pouvoir consulter le nombre de commande par utilisateur **afin de** faire une étude sur les possibles bénéfices de l'application.  
*Fichier Feature: statistique/utilisateur/Statistique_utilisateur.feature  
Fichier Cucumber: statistique/utilisateur/StatistiqueUtilisateur.java*  

- "Modifier offres sur les menus" :  
   - User Story #43 : "Tarif spécial"  
**En tant que** restaurateur, **je veux** définir des tarifs différents selon les utilisateurs **afin d'** être plus attractif.  
*Fichier Feature: commande/Passer_une_commande_en_etant_Etudiant.feature  
Fichier Cucumber: commande/AjouterUneCommande.java*  
   - User Story #44 : "Réduction pour les bons clients"  
**En tant que** restaurateur, **je veux** que les clients ayant effectué plus de X commandes bénéficient d'une réduction temporaire de Y% pendant Z jours **afin d'** être plus attractif.  
*Fichier Feature: restaurant/Discount/Discount.feature  
Fichier Cucumber: restaurant/Discount/Discount.java*  

- "Noter un utilisateur" :  
   - User Story #45 : "Evaluation d'un utilisateur"  
**En tant qu'** utilisateur (n'importe lequel), **je veux** pouvoir attribué une note à un autre utilisateur **afin de** l'évaluer.  
*Fichier Feature: restaurant/Discount/Discount.feature  
Fichier Cucumber: restaurant/Discount/Discount.java*  

- "Envoi une notification à l'utilisateur" :  
   - User Story #46 : "Notifier l'utilisateur du changement de status de commande"  
**En tant que** utilisateur, **je veux** recevoir les notifications **afin de** connaitre où en est la commande.
*Fichier Feature: notification/notificationUtilisateur.feature  
Fichier Cucumber: notification/NotificationUtilisateur.java*  

- "Envoi une notification au livreur" :  
   - User Story #56 : "NotificationLivreur"  
**En tant que** livreur, **je veux** obtenir les commandes prête **afin de** pouvoir livrer la commande.  
*Fichier Feature: notification/NotificationLivreur.feature  
Fichier Cucumber: notification/NotificationLivreur.java*  

   
