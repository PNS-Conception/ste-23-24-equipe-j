#language:fr
Fonctionnalité: Modification de la capacité d'un restaurant quand l'utilisateur sélectionne un menu.

  Contexte:
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté
    Et que "Antoine" "Dupont" crée une commande
    Et que L'utilisateur peut accéder aux restaurants suivant :
      | Chinois |
      | RoastBeef |
      | AfriqueDuSud |
      | SophiaRestaurantTech |
    Et que le restaurant "AfriqueDuSud" propose les menus suivant :
      | Tricheur | 5,00 |
      | Voleur | 3,00 |
    Et que le restaurant "SophiaRestaurantTech" propose les menus suivant :
      | Londres | 15,00 |
      | Liverpool | 13,00 |
    Et que le restaurant "AfriqueDuSud" a une capacité de 8 le "2019-12-12" à "12:00"
    Et que le restaurant "SophiaRestaurantTech" a une capacité de 10 le "2019-12-12" à "12:00"


  Scénario:  Normal
    Quand l'utilisateur choisit le restaurant "AfriqueDuSud"
    Quand l'utilisateur choisit le menu "Tricheur" à 5,00 €
    Alors la capacité du restaurant "AfriqueDuSud" est de 9 le "2019-12-12" à "12:00"

  Scénario:  erreur
    Quand l'utilisateur choisit le restaurant "SophiaRestaurantTech"
    Quand l'utilisateur choisit le menu "Londres" à 15,00 €
    Alors une "CapacitéDépasséException" doit être déclenché









