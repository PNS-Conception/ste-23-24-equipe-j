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
User story #8 : "Obtenir la liste des menus"   
    En tant que utilisateur du campus, Je veux obtenir la liste de menu disponible dans un restaurant, afin de choisir mon menu dans un restaurant précis.

- "Accéder à l'historique de ses anciennes commandes" :  
User story #9 : "Accéder à un historique quand on est un utilisateur enregistré"  
En tant que utilisateur enregistré, Je veux accéder à mon historique afin de voir mes commandes précédentes.

- "Organiser sa commande" :  
User Story #12 "Seul les utilisateurs enregistrés peuvent effectuer une commande"  
En tant que utilisateur enregistré, Je veux créer une commande afin de me faire livrer.

- "Choisir son menu" :  
User Story #10 : "Ajouter un plat à ma commande"  
En tant que utilisateur du campus, Je veux ajouter un plat à ma commande afin de le commander.

- "Donner le lieu de livraison" / "Donner la date de livraison" :  
User Story "#16" : "Sélectionner un lieu de livraison pré-enregistré et une date de livraison possible"  
En tant que utilisateur enregistré, Je veux sélectionner un lieu de livraison ainsi qu'une date afin d' être livré au bon endroit et au bon moment.

- "Ajout/Suppression Restaurants" :  
User Story #13 : "Ajouter un restaurant"  
En tant que Administrateur du campus, Je veux ajouter un restaurant afin de permettre aux utilisateurs de SophiaTech Eats d'avoir plus de choix de repas pour leurs commandes.

- "Consulter la commande" / "Accepter la commande" :  
User Story #14 : "Consulter les commandes en préparation et les valider"  
En tant que restaurateur, Je veux valider une commande afin que un livreur puisse la prendre.



### Principales User stories

Vous mettez en évidence les principales user stories de votre projet.
Chaque user story doit être décrite par 
   - son identifiant en tant que issue github (#), 
   - sa forme classique (As a… I want to… In order to…) (pour faciliter la lecture)
   - Le nom du fichier feature Cucumber et le nom des scénarios qui servent de tests d’acceptation pour la story.
   Les contenus détaillés sont dans l'issue elle-même.
   

   
