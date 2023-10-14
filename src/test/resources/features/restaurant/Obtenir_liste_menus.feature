#language: fr
Fonctionnalité: Obtenir liste de menus

  Contexte:
    Etant donné que l'utilisateur à une liste de restaurant :
      | RestaurantTech |
      | SophiaRestaurantTech |
    Et le restaurant "RestaurantTech" a ces menus :
      | Pate |
      | Riz  |

  Scénario: Liste de menus non vide
    Quand l'utilisateur choisi le restaurant de nom "RestaurantTech"
    Alors l'utilisateur dois obtenir les menus :
      | Pate |
      | Riz  |

  Scénario: Liste de menus vide
    Quand l'utilisateur choisi le restaurant de nom "SophiaRestaurantTech"
    Et que le restaurant n'a aucun menu
    Alors une "AucunMenuException" doit être déclenché
