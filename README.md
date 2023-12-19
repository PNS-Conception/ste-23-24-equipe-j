# SopiaTech Eats-Team-23-24-J

## TEAM
Software Architect (SA) : Adote Adjo Emmanuelle  
Quality Assurance Engineer (QA) : Chloé Vandenbrulle  
Continuous Integration and Repository Manager (OPS) : Nicolas Zanin  
Business Analyst and Product Owner (PO) : Arrigoni Guillaume  

## doc
Contient vos rapports et les documents qui présentent votre projet.


## .github
   1. Contient sous workflows/maven.yml, une version d'un fichier d'actions qui est déclenché dès que vous poussez du code. 
Sur cette version initiale seule un test Junit5 est déclenché pour vérifier que tout fonctionne.
       - Github Actions (See in .github/workflows) to simply make a maven+test compilation
  2. Contient sous ISSUE_TEMPLATE, les modèles pour les issues user_story et bug. Vous pouvez le compléter à votre guise.

## src
 - pom.xml :  
       - Cucumber 7 et JUnit 5  
       - JDK 17   
       - Etc.  
   Ce pom.xml sera mis à jour avec la démonstration qui vous sera donnée ultérieurement.

Lorsque vous passerez en développement, les codes donnés peuvent être éliminés.   
De nouveaux exemples vous seront donnés.   
Nous les laissons cependant pour votre permettre de vérifier que vous n'avez pas de problème d'intégration continue.


Les User Case :
- "Parcourir les divers menus et restaurants" :  
   - User story #8 : "Obtenir la liste des menus"   
**En tant que** utilisateur du campus, **je veux** obtenir la liste de menu disponible dans un restaurant, **afin de** choisir mon menu dans un restaurant précis.

- "Accéder à l'historique de ses anciennes commandes" :  
   - User story #9 : "Accéder à un historique quand on est un utilisateur enregistré"  
**En tant que** utilisateur enregistré, **je veux** accéder à mon historique **afin de** voir mes commandes précédentes.

- "Organiser sa commande" :  
   - User Story #12 "Seul les utilisateurs enregistrés peuvent effectuer une commande"  
**En tant que** utilisateur enregistré, **je veux** créer une commande **afin de** me faire livrer.

- "Choisir son menu" :  
   - User Story #10 : "Ajouter un plat à ma commande"  
**En tant que** utilisateur du campus, **je veux** ajouter un plat à ma commande **afin de** le commander.  
   - User Story #76 : "Suppression d'un plat dans une commande"  
**En tant que** utilisateur, **je veux** supprimer un plat dans une commande **afin de** ne plus l'avoir dans la commande.

- "Donner le lieu de livraison" / "Donner la date de livraison" :  
   - User Story "#16" : "Sélectionner un lieu de livraison pré-enregistré et une date de livraison possible"  
**En tant que** utilisateur enregistré, **je veux** sélectionner un lieu de livraison ainsi qu'une date **afin d'** être livré au bon endroit et au bon moment.

- "Ajout/Suppression Restaurants" :  
   - User Story #13 : "Ajouter un restaurant"  
**En tant que** Administrateur du campus, **je veux** ajouter un restaurant **afin de** permettre aux utilisateurs de SophiaTech Eats d'avoir plus de choix de repas pour leurs commandes.

- "Consulter la commande" / "Accepter la commande" :  
   - User Story #14 : "Consulter les commandes en préparation et les valider"  
**En tant que** restaurateur, **je veux** valider une commande **afin que** un livreur puisse la prendre.

- "Modifier horaires du restaurant" :  
   - User Story #20 : "Mettre à jour les horaires du restaurant"  
**En tant que** restaurateur, **je veux** pouvoir mettre à jour les horaires de mon restaurant **afin de** permettre aux utilisateurs de commander pendant toutes les plages horaires possibles.

- "Créer un groupe" :  
   - User Story #88 : "Crée une commande groupe"  
**En tant que** utilisateur, **je veux** créer une commande groupe **afin de** faire des commandes avec d'autres personnes.
   - User Story #89 : "Rejoindre une commande groupe"  
**En tant que** utilisateur, **je veux** rejoindre une commande groupe **afin d'** ajouter une commande à l'intérieur.

- "Payer sa commande" :  
   - User Story #40 : "Finaliser une commande"  
**En tant qu'** utilisateur enregistré, **je veux** confirmer et payer ma commande **afin de** la valider.

- "Visionner les statistiques sur les restaurants et livraisons"
   - User Story #41 : "Statistique commande par restaurant"  
**En tant qu'** administrateur de l'application, **je veux** pouvoir consulter le nombre de commande par restaurant **afin de** faire une étude sur les possibles bénéfices de l'application.  
   - User Story #42 : "Statistique commande par utilisateur"
**En tant qu'** administrateur de l'application, **je veux** pouvoir consulter le nombre de commande par utilisateur **afin de** faire une étude sur les possibles bénéfices de l'application.

- "Modifier offres sur les menus" :  
   - User Story #43 : "Tarif spécial"  
**En tant que** restaurateur, **je veux** définir des tarifs différents selon les utilisateurs **afin d'** être plus attractif.  
   - User Story #44 : "Réduction pour les bons clients"  
**En tant que** restaurateur, **je veux** que les clients ayant effectué plus de X commandes bénéficient d'une réduction temporaire de Y% pendant Z jours **afin d'** être plus attractif.

- "Noter un utilisateur" :  
   - User Story #45 : "Evaluation d'un utilisateur"  
**En tant qu'** utilisateur (n'importe lequel), **je veux** pouvoir attribué une note à un autre utilisateur **afin de** l'évaluer.

- "Envoi une notification à l'utilisateur" :  
   - User Story #46 : "Notifier l'utilisateur du changement de status de commande"  
**En tant que** utilisateur, **je veux** recevoir les notifications **afin de** connaitre où en est la commande.

- "Envoi une notification au livreur" :  
   - User Story #56 : "NotificationLivreur"  
**En tant que** livreur, **je veux** obtenir les commandes prête **afin de** pouvoir livrer la commande

### Principales User stories

Vous mettez en évidence les principales user stories de votre projet.
Chaque user story doit être décrite par 
   - son identifiant en tant que issue github (#), 
   - sa forme classique (As a… I want to… In order to…) (pour faciliter la lecture)
   - Le nom du fichier feature Cucumber et le nom des scénarios qui servent de tests d’acceptation pour la story.
   Les contenus détaillés sont dans l'issue elle-même.
   

   
