#language:fr
Fonctionnalité: Création d'une commande afterworks

  Contexte:
    Etant donné qu'un utilisateur se nommant "Hector" "Le H"

  Scénario: Création de la commande réussi
    Quand il ajoute 35 personnes lors de la création de la commande afterworks
    Alors la commande afterworks d'ID 0 est crée avec 35 personnes assignées

  Scénario: Création de la commande non réussi
    Quand il ajoute -5 personnes lors de la création de la commande afterworks
    Alors une erreur est renvoyé marqué "Nombre de personne saisi est incorrect"
    Et la commande d'ID 0 n'est pas créer