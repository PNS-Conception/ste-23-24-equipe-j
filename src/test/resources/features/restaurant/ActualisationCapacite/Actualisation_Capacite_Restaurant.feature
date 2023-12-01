#language:fr
Fonctionnalité: Modification de la capacité d'un restaurant quand l'utilisateur sélectionne un menu.

  Contexte:
    Etant donné que l'utilisateur "Antoine" "Dupont" est connecté (passActualisationCapaciteRestaurant)
    Et que L'utilisateur peut accéder aux restaurants suivant : (passActualisationCapaciteRestaurant)
      | Chinois |
      | RoastBeef |
      | AfriqueDuSud |
      | SophiaRestaurantTech |
    Et que le restaurant "AfriqueDuSud" propose les menus suivant : (passActualisationCapaciteRestaurant)
      | Tricheur | 5,00 |
      | Voleur | 3,00 |
    Et que le restaurant "SophiaRestaurantTech" propose les menus suivant : (passActualisationCapaciteRestaurant)
      | Londres | 15,00 |
      | Liverpool | 13,00 |
    Et que le restaurant "AfriqueDuSud" a une capacité de 8 le "2019-12-12" à "12:00" (passActualisationCapaciteRestaurant)
    Et que le restaurant "SophiaRestaurantTech" a une capacité de 10 le "2019-12-12" à "12:00" (passActualisationCapaciteRestaurant)


  Scénario:  Normal
    Quand l'utilisateur crée une commande pour le "2019-12-12" à "12:00" avec comme point de livraison "SophiaTech" (passActualisationCapaciteRestaurant)
    Quand l'utilisateur choisit le restaurant "AfriqueDuSud" (passActualisationCapaciteRestaurant)
    Quand l'utilisateur choisit le menu "Tricheur" à 5,00 € (passActualisationCapaciteRestaurant)
    Alors la capacité du restaurant "AfriqueDuSud" est de 9 le "2019-12-12" à "12:00" (passActualisationCapaciteRestaurant)

  Scénario:  erreur
    Quand l'utilisateur crée une commande pour le "2019-12-12" à "12:00" avec comme point de livraison "SophiaTech" (passActualisationCapaciteRestaurant)
    Quand l'utilisateur choisit le restaurant "SophiaRestaurantTech" (passActualisationCapaciteRestaurant)
    Quand l'utilisateur choisit le menu "Londres" à 15,00 € (passActualisationCapaciteRestaurant)
    Alors une "CapaciteDepasseException" doit être déclenché (passActualisationCapaciteRestaurant)









