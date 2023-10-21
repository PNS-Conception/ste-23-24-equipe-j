#language:fr
Fonctionnalité: Ajouter une commande

  Contexte:
    Etant donné que l'utilisateur "Antoine" "Dupont" est loggé
    Et que "Antoine" "Dupont" crée une commande "LesSudAfricainsSontDesVoleursQuiNeMeritentPasDeGagner"
    Et que l'utilisateur peut accéder aux restaurants suivant :
      | Chinois |
      | RoastBeef |
    Et que le restaurant "RestaurantTech" propose les menus suivant :
      | NomDuChamp | Valeur |
      | Chine | 5,00 |
      | Japon | 3,00 |
    Et que les adresses suivantes sont pré-enregistré :
      | 1 rue de la paix |
      | 2 rue de la paix |
    Et que le restaurant "SophiaRestaurantTech" propose les menus suivant :
      | NomDuChamp | Valeur |
      | Londres | 15,00 |
      | Liverpool | 13,00 |
    Et que il choisit le restaurant "Chinois"
    Et que il choisit le menu "Chine" à 5 €
    Et que il choisit la livraison le "2019-12-12" à "12:00" à l'adresse pré-enregistré "1 rue de la paix"

  Scénario:  Normal
    Quand l'utilisateur confirme sa commande et qu'il paye les 5 €
    Alors la commande est ajouté à la liste des commandes en préparation et elle est envoyé au restaurant "Chinois"