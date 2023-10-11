#language: fr
Fonctionnalité: Obtenir liste de menus

  Contexte:
    Etant donné que l'utilisateur à une liste de restaurant
    Et que l'utilisateur choisi le restaurant de nom "RestoTech"

  Scénario: Liste de menus non vide
    Alors l'utilisateur dois obtenir les menus du restaurant "RestoTech"

  Scénario: Liste de menus vide
    Quand le "RestoTech" n'a aucun menu
    Alors une "AucunMenuException" doit être déclenché
