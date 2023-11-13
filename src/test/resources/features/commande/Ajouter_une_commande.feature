#language:fr
  Fonctionnalité: Passer une commande

    Contexte:
      Etant donné que l'utilisateur "Antoine" "Dupont" est loggé
      Et que "Antoine" "Dupont" crée une commande
      Et que L'utilisateur peut accéder aux restaurants suivant :
        | Chinois |
        | RoastBeef |
        | RestaurantTech |
        | SophiaRestaurantTech |
      Et que le restaurant "RestaurantTech" propose les menus suivant :
        | Chine | 5,00 |
        | Japon | 3,00 |
      Et que les adresses suivantes sont pré-enregistré :
        | 1 rue de la paix |
        | 2 rue de la paix |
      Et que le restaurant "SophiaRestaurantTech" propose les menus suivant :
      | Londres | 15,00 |
      | Liverpool | 13,00 |
      Et que l'utilisateur choisit le restaurant "RestaurantTech"
      Et que l'utilisateur choisit le menu "Chine" à 5 €
      Et que l'utilisateur choisit la livraison le "2019-12-12" à "12:00" à l'adresse pré-enregistré "1 rue de la paix"

    Scénario:  Normal
      Quand l'utilisateur paye sa commande à 5€
      Alors la commande est ajouté à la liste des commandes "EN_PREPARATION" et elle est envoyé au restaurant "RestaurantTech"
